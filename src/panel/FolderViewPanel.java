package panel;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JList;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import folderlist.FolderList;
import javax.swing.JLabel;

public class FolderViewPanel extends JPanel {
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnView;
	private JButton btnExplore;
	private JButton btnRename;
	private JButton btnDelete;
	private JButton btnExit;
	private DefaultListModel<File> listmodel;
	
	public final static String ADD_BTN="Add";
	public final static String REMOVE_BTN="Remove";
	public final static String REMOVE_ALL_BTN="Remove All";
	public final static String VIEW_BTN="View";
	public final static String EXPLORE_BTN="Explore";
	public final static String RENAME_BTN="Rename";
	public final static String DELETE_BTN="Delete";
	public final static String EXIT_BTN="Exit";
	private JScrollPane scrollPane;
	private JList<File> list;
	private JButton btnAboutUs;
	private JLabel lblPleaseWait;
	
	public FolderViewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel.add(btnAdd, gbc_btnAdd);
		
		btnRemove = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 1;
		panel.add(btnRemove, gbc_btnRemove);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 5, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);
		
		btnView = new JButton("View");
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnView.insets = new Insets(0, 0, 5, 0);
		gbc_btnView.gridx = 0;
		gbc_btnView.gridy = 3;
		panel.add(btnView, gbc_btnView);
		
		btnExplore = new JButton("Explore");
		GridBagConstraints gbc_btnExplore = new GridBagConstraints();
		gbc_btnExplore.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExplore.insets = new Insets(0, 0, 5, 0);
		gbc_btnExplore.gridx = 0;
		gbc_btnExplore.gridy = 4;
		panel.add(btnExplore, gbc_btnExplore);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 5, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 5;
		panel.add(separator_1, gbc_separator_1);
		
		btnRename = new JButton("Rename");
		GridBagConstraints gbc_btnRename = new GridBagConstraints();
		gbc_btnRename.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRename.insets = new Insets(0, 0, 5, 0);
		gbc_btnRename.gridx = 0;
		gbc_btnRename.gridy = 6;
		panel.add(btnRename, gbc_btnRename);
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 7;
		panel.add(btnDelete, gbc_btnDelete);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.anchor = GridBagConstraints.SOUTH;
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 5, 5, 5);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 8;
		panel.add(separator_2, gbc_separator_2);
		
		btnAboutUs = new JButton("About Us");
		GridBagConstraints gbc_btnAboutUs = new GridBagConstraints();
		gbc_btnAboutUs.insets = new Insets(0, 0, 5, 0);
		gbc_btnAboutUs.gridx = 0;
		gbc_btnAboutUs.gridy = 9;
		panel.add(btnAboutUs, gbc_btnAboutUs);
		
		btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 10;
		panel.add(btnExit, gbc_btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Folder List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new TitledBorder(null, "List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		
		listmodel=new DefaultListModel<>();
		list = new JList<File>(listmodel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new FolderList<File>());
		list.setOpaque(false);
		scrollPane.setViewportView(list);
		
		lblPleaseWait = new JLabel("Please Wait...");
		panel_1.add(lblPleaseWait, BorderLayout.NORTH);
		
		
	}
	public JLabel getInfoLbl(){
		return lblPleaseWait;
	}
	public void addFolder(File folder){
		if(folder.isDirectory())listmodel.addElement(folder);
		else throw new IllegalArgumentException("This is not a directory");
	}
	public void removeSelectedFolder(){
		listmodel.removeElement(list.getSelectedValue());
	}
	public void replaceFolder(File folder){
		listmodel.set(list.getSelectedIndex(), folder);
	}
	public void setSelectedFolder(int index){
		list.setSelectedIndex(index);
	}
	public void setFolderListModel(DefaultListModel<File> model){
		listmodel=model;
		list.setModel(listmodel);
	}
	public DefaultListModel<File> getFolderList(){
		return listmodel;
	}
	public File getSelectedFolder(){
		return list.getSelectedValue();
	}
	public boolean isFolderExists(File folder){
		if(listmodel.contains(folder))return true;
		return false;
	}
	
	public JList<File> getFolderJList(){
		return list;
	}
	public void setListSelectionLis(ListSelectionListener lsl){
		list.addListSelectionListener(lsl);
	}
	
	public void setActionLis(ActionListener al){
		btnAdd.addActionListener(al);
		btnDelete.addActionListener(al);
		btnExit.addActionListener(al);
		btnExplore.addActionListener(al);
		btnRemove.addActionListener(al);
		btnRename.addActionListener(al);
		btnView.addActionListener(al);
		btnAboutUs.addActionListener(al);
	}
	public void setListSelected(boolean isSelected){
		btnDelete.setEnabled(isSelected);
		btnExplore.setEnabled(isSelected);
		btnRemove.setEnabled(isSelected);
		btnRename.setEnabled(isSelected);
		btnView.setEnabled(isSelected);
	}
}
