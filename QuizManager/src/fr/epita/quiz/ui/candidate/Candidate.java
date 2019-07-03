package fr.epita.quiz.ui.candidate;

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
import fr.epita.quiz.ui.Main;

/**
 * 
 * @author Krishna, Abhigna
 * @Candidate Page
 */
public class Candidate extends JFrame {

	//Variable Declaration
	private static final long serialVersionUID = -1197637509927212697L;
	private JPanel mainPane;
	private JTextField unameTxtFld;
	private JTextField nameTxtFld;
	private JPasswordField pwdFld;
	private JPasswordField pwdFld2;
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
		lblSignUp.setBounds(287, 20, 66, 20);
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
		
		pwdFld = new JPasswordField();
		pwdFld.setBounds(101, 110, 86, 20);
		mainPane.add(pwdFld);
		
		pwdFld2 = new JPasswordField();
		pwdFld2.setBounds(316, 153, 86, 20);
		mainPane.add(pwdFld2);
		
		unameTxtFld_r = new JTextField();
		unameTxtFld_r.setBounds(316, 110, 86, 20);
		mainPane.add(unameTxtFld_r);
		unameTxtFld_r.setColumns(10);
		
		JButton lgnBtn = new JButton("Login"); // Candidate Login
		lgnBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				boolean isAuth = false;
				try {
					isAuth = dao.candidateLogin( unameTxtFld.getText(), pwdFld.getText()); 
					System.out.println("isAuth::"+isAuth);
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
		lgnBtn.setBounds(46, 227, 89, 23);
		mainPane.add(lgnBtn);
		
		
		JButton registerBtn = new JButton("Sign Up"); // Candidate Registration
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("deprecation")
					boolean isRegister = dao.candidateRegister(nameTxtFld.getText(), unameTxtFld_r.getText(),pwdFld2.getText());
					if(isRegister) {
					JOptionPane.showMessageDialog(null, "Candidate have registered Successfully.");
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		registerBtn.setBounds(287, 227, 89, 23);
		mainPane.add(registerBtn);
		JButton backBtn = new JButton("Back"); // Back to main page
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		backBtn.setBounds(168, 227, 89, 23);
		mainPane.add(backBtn);
	}
	
}
