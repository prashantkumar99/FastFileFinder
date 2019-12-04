package dialog;

import javax.swing.JDialog;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class DDialog extends JDialog implements ActionListener{
	private JLabel label;
	
	public static final int APPROVE_BTN=0;
	public static final int CANCEL_BTN=1;
	private JButton btnOk;
	private JButton btnCancel;
	int clickedBtn=-1;
	/**
	 * @wbp.parser.constructor
	 */
	public DDialog(JFrame frm) {
		super(frm,true);
		setLocationRelativeTo(frm);
		makeDialog();
	}
	public DDialog(JDialog dialog) {
		super(dialog,true);
		setLocationRelativeTo(dialog);
		makeDialog();
	}
	void makeDialog(){
		setResizable(false);
		
		setSize(450, 150);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		label = new JLabel("New label");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(15, 15, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		getContentPane().add(label, gbc_label);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.SOUTH;
		gbc_separator.insets = new Insets(0, 5, 5, 5);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		getContentPane().add(separator, gbc_separator);
		
		btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 2;
		getContentPane().add(btnOk, gbc_btnOk);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 2;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		btnCancel.addActionListener(this);
		btnOk.addActionListener(this);
	}
	void makeInfoDialog(String info,String title){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(title==null)title="Info";
		setTitle(title);
		label.setIcon(new ImageIcon(DDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		label.setText(info);
		btnOk.setText("OK");
		btnCancel.setText("Cancel");
	}
	void makeErrorDialog(String err,String title){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(title==null)title="Error";
		setTitle(title);
		label.setIcon(new ImageIcon(DDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		label.setText(err);
		btnOk.setText("OK");
		btnCancel.setText("Cancel");
	}
	void makeQuestionDialog(String question,String title){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(title==null)title="Question";
		setTitle(title);
		clickedBtn=-1;
		label.setIcon(new ImageIcon(DDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		label.setText(question);
		btnOk.setText("Yes");
		btnCancel.setText("No");
	}	
	
	public void showInfoDialog(JFrame frm,String info,String title){
		setLocationRelativeTo(frm);
		makeInfoDialog(info, title);
		setVisible(true);
	}
	public void showErrorDialog(JFrame frm,String err,String title){
		setLocationRelativeTo(frm);
		makeErrorDialog(err, title);
		setVisible(true);
	}
	public int showQuestionDialog(JFrame frm,String question,String title){
		setLocationRelativeTo(frm);
		makeQuestionDialog(question, title);
		setVisible(true);
		return clickedBtn;
	}
	
	public void showInfoDialog(JDialog dialog,String info,String title){
		setLocationRelativeTo(dialog);
		makeInfoDialog(info, title);
		setVisible(true);
	}
	public void showErrorDialog(JDialog dialog,String err,String title){
		setLocationRelativeTo(dialog);
		makeErrorDialog(err, title);
		setVisible(true);
	}
	public int showQuestionDialog(JDialog dialog,String question,String title){
		setLocationRelativeTo(dialog);
		makeQuestionDialog(question, title);
		setVisible(true);
		return clickedBtn;
	}
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case "Yes":
			clickedBtn=APPROVE_BTN;
			dispose();
			break;
		case "No":
			clickedBtn=CANCEL_BTN;
			dispose();
			break;
		case "OK":
			dispose();
			break;
		case "Cancel":
			dispose();
		}
	}
}
