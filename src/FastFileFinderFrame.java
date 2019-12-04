
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dialog.DDialog;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import panel.FolderViewPanel;
import panel.FileViewPanel;
import panel.AboutUsPanel;


public class FastFileFinderFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 703948731017826445L;
	/**
	 * Launch the application.
	 */
	private CardLayout card;
	public static void main(String[] args) {
		final FastFileFinderFrame frame= new FastFileFinderFrame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					DDialog d=new DDialog(frame);
					d.showErrorDialog(frame, "Error in launching Fast File Finder.",null);
				}
			}
		});
		FolderView folderview;
		FileViewPanel fileViewPanel;
		FolderViewPanel folderViewPanel = new FolderViewPanel();
		frame.getContentPane().add(folderViewPanel, "Folder View Panel");
		
		fileViewPanel = new FileViewPanel();
		frame.getContentPane().add(fileViewPanel, "File View Panel");
		
		folderview=new FolderView(frame, folderViewPanel,fileViewPanel);
		
		AboutUsPanel aboutUsPanel = new AboutUsPanel();
		frame.getContentPane().add(aboutUsPanel, "About Us");
		aboutUsPanel.btnHome.addActionListener(frame);
		
		//argument handler
		for(String val:args){
			File f=new File(val);
			if(f.isDirectory())folderview.addFolder(f);
			if(args.length==1){
				frame.showFileViewPanel();
				folderview.fileview.setFolder(new File(args[0]));
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public FastFileFinderFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\fff.png"));
		setTitle("Fast File Finder");
		setBounds(100, 100, 575, 552);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		card=new CardLayout();
		getContentPane().setLayout(card);
	}
	public void showFolderViewPanel(){
		card.show(getContentPane(), "Folder View Panel");
	}
	public void showFileViewPanel(){
		card.show(getContentPane(), "File View Panel");
	}
	public void showAboutUsPanel(){
		card.show(getContentPane(), "About Us");
	}
	static File getUserFolder(){
		File f=new File(System.getProperty("user.home")+"\\FFF");
		if(!f.exists())f.mkdir();
		return f;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		showFolderViewPanel();
	}

}
