package fr.epita.quiz.ui;

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

import fr.epita.quiz.services.data.QuizJDBCDAO;

public class Candidate extends JFrame {

	
	private static final long serialVersionUID = -1197637509927212697L;
	private JPanel mainPane;
	private JTextField unameTxtFld;
	private JTextField nameTxtFld;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField unameTxtFld_r;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();


	public Candidate() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(22, 11, 104, 25);
		mainPane.add(lblLogin);
		
		JLabel lblSignUp = new JLabel("Register");
		lblSignUp.setBounds(287, 16, 46, 14);
		mainPane.add(lblSignUp);
		
		JLabel unameLbl = new JLabel("Username");
		unameLbl.setBounds(22, 68, 69, 14);
		mainPane.add(unameLbl);
		
		JLabel pwdLbl = new JLabel("Password");
		pwdLbl.setBounds(22, 113, 69, 14);
		mainPane.add(pwdLbl);
		
		unameTxtFld = new JTextField();
		unameTxtFld.setBounds(101, 65, 86, 20);
		mainPane.add(unameTxtFld);
		unameTxtFld.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setBounds(222, 68, 46, 14);
		mainPane.add(nameLbl);
		
		JLabel unameLbl_r = new JLabel("Username");
		unameLbl_r.setBounds(222, 113, 69, 14);
		mainPane.add(unameLbl_r);
		
		JLabel pwdLbl_r = new JLabel("Password");
		pwdLbl_r.setBounds(222, 156, 73, 14);
		mainPane.add(pwdLbl_r);
		
		nameTxtFld = new JTextField();
		nameTxtFld.setBounds(316, 65, 86, 20);
		mainPane.add(nameTxtFld);
		nameTxtFld.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(101, 110, 86, 20);
		mainPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(316, 153, 86, 20);
		mainPane.add(passwordField_1);
		
		unameTxtFld_r = new JTextField();
		unameTxtFld_r.setBounds(316, 110, 86, 20);
		mainPane.add(unameTxtFld_r);
		unameTxtFld_r.setColumns(10);
		
		JButton lgnBtn = new JButton("Login");
		lgnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isAuth = false;
				try {
					isAuth = dao.candidateLogin( unameTxtFld.getText(), passwordField.getText()); 
					if(isAuth)
					{
						String uname=unameTxtFld.getText();
						new CandidateLogin(uname);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please check the Username and password.");
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		lgnBtn.setBounds(56, 173, 89, 23);
		mainPane.add(lgnBtn);
		
		
		JButton registerBtn = new JButton("Sign Up");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isRegister = dao.candidateRegister(nameTxtFld.getText(), unameTxtFld_r.getText(),passwordField_1.getText());
					if(isRegister) {
					JOptionPane.showMessageDialog(null, "Candidate have registered.");
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		registerBtn.setBounds(287, 211, 89, 23);
		mainPane.add(registerBtn);
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		backBtn.setBounds(143, 227, 89, 23);
		mainPane.add(backBtn);
	}
	
}
