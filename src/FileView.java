import javax.swing.DefaultListModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dazzle.gui.event.ListDoubleClickEvent;
import dazzle.gui.event.ListDoubleClickListener;
import dialog.DDialog;
import dialog.NewNameDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Vector;

import panel.FileViewPanel;


class FileView implements ActionListener,ListDoubleClickListener,DocumentListener,ListSelectionListener,CaretListener,ItemListener{
	FastFileFinderFrame frm;
	FileViewPanel panel;
	File folder;
	Vector<File> indextree;
	Thread makeIndex,search;
	DDialog dialog;
	FolderView folderView;
	FileView(FastFileFinderFrame frm,FileViewPanel panel,FolderView folderView){
		this.panel=panel;
		this.frm=frm;
		this.folderView=folderView;
		panel.setActionLis(this);
		panel.setListSelectionLis(this);
		panel.setSearchDocumentLis(this);
		panel.getMatchWholeWord().addItemListener(this);
		panel.getSearchField().addCaretListener(this);
		panel.getInfoLbl().setVisible(false);
		panel.getFileDList().addListDoubleClickLis(this);
		dialog=new DDialog(frm);
		
		indextree=new Vector<>();
		
		
		search=new Thread();
		makeIndex=new Thread();
	}
	void setMainFolder(File folder){
		this.folder=folder;
	}
	void setFolder(File folder){
		panel.setListSelected(false);
		makeIndex(folder);
	}
	void exploreSelectedFile(){
		File f=panel.getSelectedFile();
		File p;
		if(f.isFile())p=f.getParentFile();
		else p=f;
		ProcessBuilder pr=new ProcessBuilder("explorer.exe",p.getAbsolutePath());
		try {
			pr.start();
		} catch (IOException e) {
			dialog.showErrorDialog(frm, "Could not able to explore folder \""+panel.getSelectedFile().getName()+"\'.", "");
		}
	}
	void runFile(){
		ProcessBuilder pr=new ProcessBuilder("explorer.exe",panel.getSelectedFile().getAbsolutePath());
		try {
			pr.start();
		} catch (IOException e) {
			dialog.showErrorDialog(frm, "Could not able to run \""+panel.getSelectedFile().getName()+"\'.", "");
		}
	}
	void renameSelectedFolder(){
		File file=panel.getSelectedFile();
		File newfile=file.getParentFile();
		NewNameDialog name=new NewNameDialog(frm);
		String newname=name.showNewNameDialog(frm,"Enter new name for \""+file.getName()+"\".");
		if(newname!=null){
			File renamedfile=null;
			if(file.isFile()){
				renamedfile=new File(newfile,"\\"+newname+file.getName().substring(file.getName().lastIndexOf(".")));
			}
			else renamedfile=new File(newfile,"\\"+newname);
			boolean renamed=file.renameTo(renamedfile);
			if(!renamed){
				dialog.showErrorDialog(frm,"Could not rename \""+panel.getSelectedFile().getName()+"\".","Error");
			}
			else {
				for(int i=0;i<indextree.size();i++){
					if(indextree.get(i)==file){
						indextree.remove(i);
						indextree.add(i, renamedfile);
					}
				}
				int index=panel.getFileDList().getSelectedIndex();
				panel.getFileList().remove(index);
				panel.getFileList().add(index, renamedfile);
			}
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(panel.getSelectedFile()==null)panel.setListSelected(false);
		else {
			panel.setListSelected(true);
			panel.setFileSelected(panel.getSelectedFile().isFile());
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case FileViewPanel.OPEN_BTN:
			exploreSelectedFile();
			break;
		case FileViewPanel.VIEW_BTN:
			setFolder(panel.getSelectedFile());
			break;
		case FileViewPanel.RUN_BTN:
			runFile();
			break;
		case FileViewPanel.EXPLORE_BTN:
			exploreSelectedFile();
			break;
		case FileViewPanel.RENAME_BTN:
			renameSelectedFolder();
			break;
		case FileViewPanel.DELETE_BTN:
			if(folderView.deleteSelectedeFolder(panel.getSelectedFile())){
				int index=panel.list.getSelectedIndex();
				panel.getFileList().remove(index);
				panel.list.setSelectedIndex(index);
			}
			break;
		case FileViewPanel.ADD_BTN:
			folderView.addFolder(panel.getSelectedFile());
			break;
		case FileViewPanel.RELOAD_BTN:
			setFolder(folder);
			break;
		case FileViewPanel.BACK_BTN:
			frm.showFolderViewPanel();
			if(search.isAlive())search.stop();
			if(makeIndex.isAlive())makeIndex.stop();
			panel.getFileList().removeAllElements();
			panel.setSearchText(null);
			frm.setTitle("Fast File Finder");
			break;
		case FileViewPanel.EXIT_BTN:
			frm.dispose();
			System.exit(0);
			break;
		}
	}
	void makeIndex(File fo){
		final File foder=fo;
		indextree=new Vector<>();
		makeIndex=new Thread(new Runnable() {
			
			@Override
			public void run() {
				panel.getSearchField().setText(null);
				panel.getInfoLbl().setVisible(true);
				panel.setFolder(foder);
				frm.setTitle("Fast File Finder - "+foder.getName()+" - "+foder.getAbsolutePath());
				try {
					final DefaultListModel<File> list=new DefaultListModel<>();
					Files.walkFileTree(foder.toPath(), new SimpleFileVisitor<Path>() {
					     @Override
					     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)throws IOException{
					    	 synchronized (indextree) {
					    		 indextree.add(new File(file.toString()));
							}
					    	 synchronized (list) {
					    		 list.addElement(new File(file.toString()));
							}
					        synchronized (panel) {
					        	 panel.setNoOfFiles(indextree.size());
							}
					         
					        
					         return FileVisitResult.CONTINUE;
					     }
					     @Override
					     public FileVisitResult postVisitDirectory(Path dir, IOException e)
					         throws IOException
					     {
					         if (e == null) {
					        	 synchronized (indextree) {
						    		 indextree.add(new File(dir.toString()));
								}
						    	 synchronized (list) {
						    		 list.addElement(new File(dir.toString()));
								}
						        synchronized (panel) {
						        	 panel.setNoOfFiles(indextree.size());
								}
					             return FileVisitResult.CONTINUE;
					         } else {
					             throw e;
					         }
					     }
					     public FileVisitResult visitFileFailed(Path arg0, IOException arg1) throws IOException {
					    	 System.out.println("Can't read file="+arg0.getFileName());
					    	 synchronized (indextree) {
					    		 indextree.add(new File(arg0.toString()));
							}
					    	 synchronized (list) {
					    		 list.addElement(new File(arg0.toString()));
							}
					        synchronized (panel) {
					        	 panel.setNoOfFiles(indextree.size());
							}
							return FileVisitResult.CONTINUE;
							}
					 });
					panel.setFileListModel(list);
					panel.setNoOfFiles(list.getSize());
				} catch (Exception e) {
					e.printStackTrace();
					dialog.showErrorDialog(frm, "Indexing process is not completed.","Error");
				}
				panel.getSearchField().requestFocus();
				panel.getInfoLbl().setVisible(false);
			}
		});
		try {
			search.stop();
		} catch (Exception e) {
		}
		makeIndex.start();
		
	}
	private void search(String text){
		final String searchText=text.toLowerCase();
		search=new Thread(new Runnable() {
			
			@Override
			public void run() {
				panel.getInfoLbl().setVisible(true);
				panel.setSearchStarted(true);
				DefaultListModel<File> list=new DefaultListModel<>();
				if(searchText!=null){
					for(File file:indextree){
						if(panel.isMatchWholeEnabled()){
							String name=file.getName().toLowerCase();
							if(name.equals(searchText)){
								list.addElement(file);
								panel.setNoOfFiles(panel.getFileList().getSize());
							}
							try {
								name=name.substring(0,name.lastIndexOf("."));
								if(name.equals(searchText)){
									list.addElement(file);
									panel.setNoOfFiles(panel.getFileList().getSize());
								}
							} catch (StringIndexOutOfBoundsException e) {
								
							}
						}
						else {
							if(file.getName().toLowerCase().contains(searchText)){
								list.addElement(file);
								panel.setNoOfFiles(panel.getFileList().getSize());
							}
						}
					}
				}
				
				panel.setFileListModel(list);
				panel.getInfoLbl().setVisible(false);
				panel.setSearchStarted(false);
			}
		});
		if(!panel.getSearchText().isEmpty())search.start();
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		search(panel.getSearchText());
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		search(panel.getSearchText());
	}
	@Override
	public void doubleClicked(ListDoubleClickEvent arg0) {
			panel.getRunBtn().doClick();
		
	}
	@Override
	public void caretUpdate(CaretEvent arg0) {
		if(panel.getSearchText().isEmpty()){
			DefaultListModel<File> list=new DefaultListModel<>();
			for(int i=0;i<indextree.size();i++)list.addElement(indextree.get(i));
			panel.setFileListModel(list);
			panel.setNoOfFiles(list.getSize());
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		search(panel.getSearchText());
		
	}
}
