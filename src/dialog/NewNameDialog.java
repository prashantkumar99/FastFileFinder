package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JLabel;

import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewNameDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	String name=null;
	
	DDialog dialog=new DDialog(this);
	private JLabel info;
	/**
	 * Create the dialog.
	 */
	public NewNameDialog(JFrame frm) {
		super(frm,true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("New Name");
		setResizable(false);
		setBounds(100, 100, 345, 141);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{101, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			info = new JLabel("Enter new name.");
			GridBagConstraints gbc_info = new GridBagConstraints();
			gbc_info.insets = new Insets(0, 0, 5, 0);
			gbc_info.gridwidth = 0;
			gbc_info.gridx = 0;
			gbc_info.gridy = 0;
			contentPanel.add(info, gbc_info);
		}
		{
			JLabel lblNewName = new JLabel("New Name:");
			GridBagConstraints gbc_lblNewName = new GridBagConstraints();
			gbc_lblNewName.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewName.anchor = GridBagConstraints.EAST;
			gbc_lblNewName.gridx = 0;
			gbc_lblNewName.gridy = 1;
			contentPanel.add(lblNewName, gbc_lblNewName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(5, 5, 5, 15);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.anchor = GridBagConstraints.SOUTH;
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.gridwidth = 0;
			gbc_separator.insets = new Insets(0, 5, 0, 5);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 2;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				name=null;
			}
		});
	}
	public String showNewNameDialog(JFrame frm,String info){
		setLocationRelativeTo(frm);
		name=null;
		textField.setText(null);
		this.info.setText(info);
		setVisible(true);
		return name;
	}
	public boolean setNewName(String name){
		if(name==null){
			info.setText("Name should not be empty.");
			return false;
		}
		if(name.isEmpty()){
			info.setText("Name should not be empty.");
			return false;
		}
		
		this.name=name.trim();
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case "OK":
			if(setNewName(textField.getText()))dispose();
			break;
		case "Cancel":
			name=null;
			dispose();
			break;
		}
	}

}
