package fr.epita.quiz.ui.admn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.epita.quiz.ui.Main;

/**
 * 
 * @author Krishna, Abhigna
 * @Admin Portal 
 */
public class Admn extends JFrame {

	// Variable declarations
	private static final long serialVersionUID = -6210137540705191878L;
	private JPanel panel;
	private JTextField unameFld;
	private JPasswordField pwdFld;
	private static final String UNAME = "ADM";
	private static final String PWD = "ADM";
	private StatusBar statusBar;

	public Admn() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JLabel lbl = new JLabel("Please enter the username and password for login");
		lbl.setBounds(23, 11, 411, 30);
		panel.add(lbl);

		JLabel unameLbl = new JLabel("Username");
		unameLbl.setBounds(44, 72, 95, 17);
		panel.add(unameLbl);

		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setBounds(44, 128, 95, 20);
		panel.add(pwdlbl);

		unameFld = new JTextField();
		unameFld.setBounds(180, 72, 141, 20);
		panel.add(unameFld);

		pwdFld = new JPasswordField();
		pwdFld.setBounds(180, 125, 141, 20);
		panel.add(pwdFld);

		JButton login = new JButton("LogIn");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				boolean isAuth = authenticate(unameFld.getText(), pwdFld.getText()); // Authentication for Admin
				
				if(isAuth)
				{
					new AdmnLogin(); // Admin Login for CRUD Operations
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Check the Username and Password");
				}
			}
		});
		login.setBounds(127, 181, 89, 23);
		panel.add(login);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		back.setBounds(275, 181, 89, 23);
		panel.add(back);
		statusBar = new StatusBar();
		this.add(statusBar, java.awt.BorderLayout.SOUTH);

	}

	/**
	 * Admin Authentication based on provided username and password and returns boolean value
	 * @param uname
	 * @param pwd
	 * @return
	 */
	protected boolean authenticate(String uname, String pwd) {
		return UNAME.equals(uname) && PWD.equals(pwd);
	}
}
