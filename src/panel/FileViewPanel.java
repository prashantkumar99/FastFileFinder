package panel;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;

import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.UIManager;


import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

import dazzle.gui.DList;

public class FileViewPanel extends JPanel {
	private static final long serialVersionUID = 2924289691830678431L;
	private JTextField searchField;
	private JButton btnExit;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnRename;
	private JButton btnExplore;
	private JButton btnOpen;
	private JLabel lblFolder;
	
	public final static String BACK_BTN="Back";
	public final static String OPEN_BTN="Open";
	public final static String EXPLORE_BTN="Explore";
	public final static String RENAME_BTN="Rename";
	public final static String DELETE_BTN="Delete";
	public final static String EXIT_BTN="Exit";
	public final static String RUN_BTN="Run";
	public final static String ADD_BTN="Add";
	public final static String VIEW_BTN="View";
	public final static String RELOAD_BTN="Reload";
	private JScrollPane scrollPane;
	public DList<File> list;
	private JLabel lblInfo;
	private JSeparator separator_2;
	private JButton btnAdd;
	private JButton btnView;
	private JButton btnReload;
	private JLabel lblNooffiles;
	private JCheckBox chckbxMatchWholeWord;
	/**
	 * Create the panel.
	 */
	public FileViewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnOpen = new JButton("Open");
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpen.gridx = 0;
		gbc_btnOpen.gridy = 0;
		panel.add(btnOpen, gbc_btnOpen);
		
		btnView = new JButton("View");
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnView.insets = new Insets(0, 0, 5, 0);
		gbc_btnView.gridx = 0;
		gbc_btnView.gridy = 1;
		panel.add(btnView, gbc_btnView);
		
		btnExplore = new JButton("Explore");
		GridBagConstraints gbc_btnExplore = new GridBagConstraints();
		gbc_btnExplore.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExplore.insets = new Insets(0, 0, 5, 0);
		gbc_btnExplore.gridx = 0;
		gbc_btnExplore.gridy = 2;
		panel.add(btnExplore, gbc_btnExplore);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 5, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		panel.add(separator, gbc_separator);
		
		btnRename = new JButton("Rename");
		GridBagConstraints gbc_btnRename = new GridBagConstraints();
		gbc_btnRename.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRename.insets = new Insets(0, 0, 5, 0);
		gbc_btnRename.gridx = 0;
		gbc_btnRename.gridy = 4;
		panel.add(btnRename, gbc_btnRename);
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 5;
		panel.add(btnDelete, gbc_btnDelete);
		
		separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 5, 5, 5);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 6;
		panel.add(separator_2, gbc_separator_2);
		
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.setToolTipText("adds to folder list");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 7;
		panel.add(btnAdd, gbc_btnAdd);
		
		btnReload = new JButton("Reload");
		GridBagConstraints gbc_btnReload = new GridBagConstraints();
		gbc_btnReload.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReload.insets = new Insets(0, 0, 5, 0);
		gbc_btnReload.gridx = 0;
		gbc_btnReload.gridy = 8;
		panel.add(btnReload, gbc_btnReload);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.anchor = GridBagConstraints.SOUTH;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 5, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 9;
		panel.add(separator_1, gbc_separator_1);
		
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 10;
		panel.add(btnBack, gbc_btnBack);
		
		btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 11;
		panel.add(btnExit, gbc_btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "File List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 119};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblFolder = new JLabel("Folder");
		lblFolder.setBorder(new TitledBorder(null, "Folder", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lblFolder = new GridBagConstraints();
		gbc_lblFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFolder.gridx = 0;
		gbc_lblFolder.gridy = 0;
		panel_1.add(lblFolder, gbc_lblFolder);
		
		lblNooffiles = new JLabel("0");
		lblNooffiles.setBorder(new TitledBorder(null, "No of files found", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lblNooffiles = new GridBagConstraints();
		gbc_lblNooffiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNooffiles.insets = new Insets(0, 0, 5, 0);
		gbc_lblNooffiles.gridx = 1;
		gbc_lblNooffiles.gridy = 0;
		panel_1.add(lblNooffiles, gbc_lblNooffiles);
		
		searchField = new JTextField();
		searchField.setOpaque(false);
		searchField.setColumns(10);
		searchField.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchField.setBackground(UIManager.getColor("OptionPane.errorDialog.titlePane.background"));
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 1;
		panel_1.add(searchField, gbc_searchField);
		
		chckbxMatchWholeWord = new JCheckBox("Match whole word");
		GridBagConstraints gbc_chckbxMatchWholeWord = new GridBagConstraints();
		gbc_chckbxMatchWholeWord.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxMatchWholeWord.gridx = 1;
		gbc_chckbxMatchWholeWord.gridy = 1;
		panel_1.add(chckbxMatchWholeWord, gbc_chckbxMatchWholeWord);
		
		lblInfo = new JLabel("Please Wait...");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.gridwidth = 2;
		gbc_lblInfo.anchor = GridBagConstraints.WEST;
		gbc_lblInfo.insets = new Insets(0, 5, 5, 0);
		gbc_lblInfo.gridx = 0;
		gbc_lblInfo.gridy = 2;
		panel_1.add(lblInfo, gbc_lblInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		DefaultListModel<File> model=new DefaultListModel<>();
		list = new DList<File>(model);
		list.setCellRenderer(new folderlist.FolderList<File>());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		scrollPane.setViewportView(list);
		
		

	}
	public JCheckBox getMatchWholeWord(){
		return chckbxMatchWholeWord;
	}
	public void setSearchDocumentLis(DocumentListener dl){
		searchField.getDocument().addDocumentListener(dl);
	}
	public void setSearchText(String text){
		searchField.setText(text);
	}
	public JTextField getSearchField(){
		return searchField;
	}
	public String getSearchText(){
		return searchField.getText();
	}
	public boolean isMatchWholeEnabled(){
		return chckbxMatchWholeWord.isSelected();
	}
	public JLabel getInfoLbl(){
		return lblInfo;
	}
	public void addFile(File file ){
		((DefaultListModel<File>) list.getModel()).addElement(file);
	}
	public void removeSelectedFile(){
		((DefaultListModel<File>) list.getModel()).removeElementAt(list.getSelectedIndex());
	}
	public void setSearchStarted(boolean flag){
		chckbxMatchWholeWord.setEnabled(!flag);
	}
	public void replaceFolder(File folder){
		((DefaultListModel<File>) list.getModel()).set(list.getSelectedIndex(), folder);
	}
	public void setFileListModel(DefaultListModel<File> model){
		list.setModel(model);
		setNoOfFiles(model.getSize());
	}
	public DefaultListModel<File> getFileList(){
		return (DefaultListModel<File>) list.getModel();
	}
	public JButton getRunBtn(){
		return btnOpen;
	}
	public DList<File> getFileDList(){
		return list;
	}
	public File getSelectedFile(){
		return list.getSelectedValue();
	}
	public void setListSelectionLis(ListSelectionListener lsl){
		list.addListSelectionListener(lsl);
	}
	public void setFolder(File filename){
		lblFolder.setText(filename.getName());
		lblFolder.setToolTipText(filename.getAbsolutePath());
	}
	public void setSelectedFolder(int index){
		list.setSelectedIndex(index);
	}
	public void setNoOfFiles(int no){
		lblNooffiles.setText(""+no);
	}
	public void setActionLis(ActionListener al){
		btnBack.addActionListener(al);
		btnDelete.addActionListener(al);
		btnExit.addActionListener(al);
		btnExplore.addActionListener(al);
		btnOpen.addActionListener(al);
		btnRename.addActionListener(al);
		searchField.addActionListener(al);
		btnAdd.addActionListener(al);
		btnView.addActionListener(al);
		btnReload.addActionListener(al);
	}
	public void setListSelected(boolean isSelected){
		btnDelete.setEnabled(isSelected);
		btnExplore.setEnabled(isSelected);
		btnOpen.setEnabled(isSelected);
		btnRename.setEnabled(isSelected);
		if(btnOpen.getText()=="Open"){
			btnAdd.setEnabled(isSelected);
			btnView.setEnabled(isSelected);
		}
	}
	public void setFileSelected(boolean isSelected){
		if(isSelected)btnOpen.setText("Run");
		else btnOpen.setText("Open");
		btnAdd.setEnabled(!isSelected);
		btnView.setEnabled(!isSelected);
	}
}
