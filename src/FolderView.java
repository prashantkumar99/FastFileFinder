
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import dialog.DDialog;
import dialog.NewNameDialog;
import panel.FileViewPanel;
import panel.FolderViewPanel;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;
import java.util.Vector;


class FolderView implements ActionListener,ListSelectionListener,MouseListener,DropTargetListener{
	FastFileFinderFrame frm;
	FolderViewPanel panel;
	JFileChooser folder;
	Vector<File> dropFiles;
	DDialog dialog;
	FileView fileview;
	FolderView(FastFileFinderFrame frm,FolderViewPanel panel,FileViewPanel fileViewPanel) {
		this.frm=frm;
		this.panel=panel;
		DropTarget dt=new DropTarget();
		try {
			dt.addDropTargetListener(this);
		} catch (TooManyListenersException e) {
		}
		panel.getFolderJList().setDropTarget(dt);
		panel.getFolderJList().setDropMode(DropMode.ON_OR_INSERT);
		panel.getFolderJList().addMouseListener(this);
		this.panel.setActionLis(this);
		panel.getInfoLbl().setVisible(false);
		panel.setListSelectionLis(this);
		dialog=new DDialog(frm);
		folder=new JFileChooser();
		folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folder.setMultiSelectionEnabled(true);
		folder.setDialogTitle("Choose Folder");
		
		panel.setListSelected(false);
		
		fileview=new FileView(frm, fileViewPanel,this);
		
		loadFolderList();
	}
	void addFolder(File dir){
		if(dir.isDirectory()){
			if(!panel.isFolderExists(dir))panel.addFolder(dir);
		}
	}
	void addFolder(){
		if(folder.showDialog(frm, "Add")==JFileChooser.APPROVE_OPTION){
			File files[]=folder.getSelectedFiles();
			for(File value:files){
				if(!panel.isFolderExists(value)){
					String n=value.getName();
					if(value.exists()){
						if(!(value.getName().charAt(1)==':')){
							panel.addFolder(value);
							saveFolderList();
						}
						else{
							dialog.showErrorDialog(frm,"\""+n+"\" is not a folder.","Error");
						}
					}
					else dialog.showErrorDialog(frm,"Folder \""+n+"\" doesn't exists.","Error");
					
				}
				else dialog.showErrorDialog(frm,"Folder \""+value.getName()+"\" is already in the list.","Error");
			}
		}
	}
	void removeFolder(){
		int btn=dialog.showQuestionDialog(frm,"Are you sure to remove folder \""+panel.getSelectedFolder().getName()+"\".","Remove");
		if(btn==DDialog.APPROVE_BTN){
			panel.removeSelectedFolder();
			saveFolderList();
		}
	}
	void exploreSelectedFolder(){
		ProcessBuilder pr=new ProcessBuilder("explorer.exe",panel.getSelectedFolder().getAbsolutePath());
		try {
			pr.start();
		} catch (IOException e) {
			dialog.showErrorDialog(frm, "Could not able to explore folder \""+panel.getSelectedFolder().getName()+"\'.", "");
		}
	}
	void renameSelectedFolder(){
		File file=panel.getSelectedFolder();
		File newfile=file.getParentFile();
		NewNameDialog name=new NewNameDialog(frm);
		String newname=name.showNewNameDialog(frm,"Enter new name for folder \""+file.getName()+"\".");
		if(newname!=null){
			boolean renamed=file.renameTo(new File(newfile,"\\"+newname));
			if(!renamed){
				dialog.showErrorDialog(frm,"Could not rename folder \""+panel.getSelectedFolder().getName()+"\".","Error");
			}
			else {
				panel.replaceFolder(new File(newfile,"\\"+newname));
				saveFolderList();
			}
		}	
	}
	boolean deleteSelectedeFolder(File file){
		int btn=dialog.showQuestionDialog(frm,"Are you sure to delete folder \""+file.getName()+"\".","Delete");
		if(btn==DDialog.APPROVE_BTN){
			panel.getInfoLbl().setVisible(true);
			try {
				Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
				     @Override
				     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				         throws IOException
				     {
				         Files.delete(file);
				         return FileVisitResult.CONTINUE;
				     }
				     @Override
				     public FileVisitResult postVisitDirectory(Path dir, IOException e)
				         throws IOException
				     {
				         if (e == null) {
				             Files.delete(dir);
				             return FileVisitResult.CONTINUE;
				         } else {
				             throw e;
				         }
				     }
				     @Override
				    public FileVisitResult visitFileFailed(Path arg0,IOException arg1) throws IOException {
				    	 
				    	return FileVisitResult.CONTINUE;
				    }
				 });
				panel.removeSelectedFolder();
				panel.setSelectedFolder(0);
				saveFolderList();
				panel.getInfoLbl().setVisible(false);
				return true;
			} catch (IOException e) {
				dialog.showErrorDialog(frm, "Delete process is not completed.","Error");
				return false;
			}
		}
		return false;
	}
	void saveFolderList(){
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File(FastFileFinderFrame.getUserFolder(),"\\folderlist")))){
			oos.writeObject(panel.getFolderList());
		} catch (Exception e) {
			e.printStackTrace();
			dialog.showErrorDialog(frm, "Folder list is not saved.",null);
		} 
	}
	void loadFolderList(){
				try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(FastFileFinderFrame.getUserFolder(),"\\folderlist")))){
					panel.setFolderListModel((DefaultListModel<File>)ois.readObject());
				} catch (FileNotFoundException e) {
					
				} catch (Exception e1) {
					dialog.showErrorDialog(frm, "Error in loading folder list.",null);
				}
		
	}
	void viewFolder(){
		if(panel.getSelectedFolder().isDirectory()|panel.getSelectedFolder().exists()){
			frm.showFileViewPanel();
			fileview.setMainFolder(panel.getSelectedFolder());
			fileview.setFolder(panel.getSelectedFolder());
			saveFolderList();
		}
		else dialog.showErrorDialog(frm, "\""+panel.getSelectedFolder().getName()+"\" folder is not available.",null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case FolderViewPanel.ADD_BTN:
			addFolder();
			break;
		case FolderViewPanel.REMOVE_BTN:
			removeFolder();
			break;
		case FolderViewPanel.VIEW_BTN:
			viewFolder();
			break;
		case FolderViewPanel.EXPLORE_BTN:
			exploreSelectedFolder();
			break;
		case FolderViewPanel.RENAME_BTN:
			renameSelectedFolder();
			break;
		case FolderViewPanel.DELETE_BTN:
			if(deleteSelectedeFolder(panel.getSelectedFolder())){
				panel.removeSelectedFolder();
				saveFolderList();
			}
			break;
		case "About Us":
			frm.showAboutUsPanel();
			break;
		case FolderViewPanel.EXIT_BTN:
			frm.dispose();
			System.exit(0);
			break;
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(panel.getSelectedFolder()==null)panel.setListSelected(false);
		else panel.setListSelected(true);
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		if((me.getClickCount()==2)&(me.getButton()==MouseEvent.BUTTON1)){
			viewFolder();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		try {
			Object o=dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			String s=o.toString().substring(1, o.toString().length()-1);
			StringTokenizer st=new StringTokenizer(s, ", ");
			dropFiles=new Vector<>();
			for(;;){
				dropFiles.add(new File(st.nextToken()));
				if(!st.hasMoreTokens())break;
			}
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void dragExit(DropTargetEvent arg0) {}
	@Override
	public void dragOver(DropTargetDragEvent arg0) {}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		for(File f:dropFiles){
			addFolder(f);
		}
	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}
}
