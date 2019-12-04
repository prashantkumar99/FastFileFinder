package panel;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Insets;
import javax.swing.JButton;

public class AboutUsPanel extends JPanel {
	public JButton btnHome;

	/**
	 * Create the panel.
	 */
	public AboutUsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{123, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAboutUs = new JLabel("About Us");
		lblAboutUs.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblAboutUs = new GridBagConstraints();
		gbc_lblAboutUs.insets = new Insets(0, 0, 5, 0);
		gbc_lblAboutUs.gridwidth = 0;
		gbc_lblAboutUs.gridx = 0;
		gbc_lblAboutUs.gridy = 0;
		add(lblAboutUs, gbc_lblAboutUs);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 5, 5, 5);
		gbc_separator.gridwidth = 0;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		JLabel lblFounder = new JLabel("Founder");
		GridBagConstraints gbc_lblFounder = new GridBagConstraints();
		gbc_lblFounder.anchor = GridBagConstraints.WEST;
		gbc_lblFounder.insets = new Insets(0, 10, 5, 5);
		gbc_lblFounder.gridx = 0;
		gbc_lblFounder.gridy = 2;
		add(lblFounder, gbc_lblFounder);
		
		JLabel lblPrashantKumar = new JLabel(": Prashant Kumar");
		GridBagConstraints gbc_lblPrashantKumar = new GridBagConstraints();
		gbc_lblPrashantKumar.anchor = GridBagConstraints.WEST;
		gbc_lblPrashantKumar.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrashantKumar.gridx = 1;
		gbc_lblPrashantKumar.gridy = 2;
		add(lblPrashantKumar, gbc_lblPrashantKumar);
		
		JLabel lblCoFounder = new JLabel("Co - Founder");
		GridBagConstraints gbc_lblCoFounder = new GridBagConstraints();
		gbc_lblCoFounder.anchor = GridBagConstraints.WEST;
		gbc_lblCoFounder.insets = new Insets(0, 10, 5, 5);
		gbc_lblCoFounder.gridx = 0;
		gbc_lblCoFounder.gridy = 3;
		add(lblCoFounder, gbc_lblCoFounder);
		
		JLabel lblVikrantAgarwal = new JLabel(": Vikrant Agarwal");
		GridBagConstraints gbc_lblVikrantAgarwal = new GridBagConstraints();
		gbc_lblVikrantAgarwal.anchor = GridBagConstraints.WEST;
		gbc_lblVikrantAgarwal.insets = new Insets(0, 0, 5, 5);
		gbc_lblVikrantAgarwal.gridx = 1;
		gbc_lblVikrantAgarwal.gridy = 3;
		add(lblVikrantAgarwal, gbc_lblVikrantAgarwal);
		
		JLabel lblCompany = new JLabel("Company");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.anchor = GridBagConstraints.WEST;
		gbc_lblCompany.insets = new Insets(0, 10, 5, 5);
		gbc_lblCompany.gridx = 0;
		gbc_lblCompany.gridy = 4;
		add(lblCompany, gbc_lblCompany);
		
		JLabel lblDazzle = new JLabel(": Dazzle");
		GridBagConstraints gbc_lblDazzle = new GridBagConstraints();
		gbc_lblDazzle.anchor = GridBagConstraints.WEST;
		gbc_lblDazzle.insets = new Insets(0, 0, 5, 5);
		gbc_lblDazzle.gridx = 1;
		gbc_lblDazzle.gridy = 4;
		add(lblDazzle, gbc_lblDazzle);
		
		JLabel lblContact = new JLabel("Contact");
		GridBagConstraints gbc_lblContact = new GridBagConstraints();
		gbc_lblContact.anchor = GridBagConstraints.WEST;
		gbc_lblContact.insets = new Insets(0, 10, 5, 5);
		gbc_lblContact.gridx = 0;
		gbc_lblContact.gridy = 5;
		add(lblContact, gbc_lblContact);
		
		JLabel lblPrashantkumarymailcom = new JLabel(": prashantkumar000@ymail.com");
		GridBagConstraints gbc_lblPrashantkumarymailcom = new GridBagConstraints();
		gbc_lblPrashantkumarymailcom.anchor = GridBagConstraints.WEST;
		gbc_lblPrashantkumarymailcom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrashantkumarymailcom.gridx = 1;
		gbc_lblPrashantkumarymailcom.gridy = 5;
		add(lblPrashantkumarymailcom, gbc_lblPrashantkumarymailcom);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.anchor = GridBagConstraints.SOUTH;
		gbc_separator_1.insets = new Insets(0, 5, 5, 5);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 0;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 6;
		add(separator_1, gbc_separator_1);
		
		btnHome = new JButton("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.insets = new Insets(0, 0, 5, 5);
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridwidth = 0;
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 7;
		add(btnHome, gbc_btnHome);

	}

}
