package folderlist;
import java.awt.Component;
import java.io.File;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JSeparator;

public class FolderList<E extends File> extends JPanel implements ListCellRenderer<E> {
	private static final long serialVersionUID = -3855534611505722157L;
	private JLabel lblPath;
	private JLabel lblFolder;
	private JSeparator separator;
	public FolderList() {
		setLayout(new BorderLayout(0, 0));
		
		lblFolder = new JLabel("Folder");
		lblFolder.setFont(new Font("Meiryo UI", Font.BOLD, 14));
		add(lblFolder, BorderLayout.NORTH);
		
		lblPath = new JLabel("Path");
		lblPath.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPath, BorderLayout.CENTER);
		
		separator = new JSeparator();
		separator.setForeground(UIManager.getColor("ScrollBar.thumbHighlight"));
		add(separator, BorderLayout.SOUTH);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list,E value, int index, boolean isSelected, boolean hasFocus) {
		if(isSelected){
			setBorder(new LineBorder(UIManager.getColor("ScrollBar.thumbHighlight"), 2, true));
			setOpaque(list.isOpaque());
			
		}
		else {
			setBackground(list.getBackground());
			setBorder(null);
			setOpaque(list.isOpaque());
		}
		separator.setVisible(!isSelected);
		if(hasFocus){
			
			if(isSelected){
				setBackground(UIManager.getColor("List.selectionBackground"));
				setOpaque(true);
			}
			else {
				setBackground(list.getBackground());
				setOpaque(list.isOpaque());
			}
		}
		else{
			setBackground(list.getBackground());
			setOpaque(list.isOpaque());
		}
		
		lblFolder.setText(value.getName());
		lblFolder.setForeground(list.getForeground());
		if(value.isDirectory())lblFolder.setIcon(new ImageIcon(FolderList.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		else lblFolder.setIcon(new ImageIcon(FolderList.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		
		lblPath.setText(value.getAbsolutePath());
		lblPath.setForeground(list.getForeground());
		
		return this;
	}

}
