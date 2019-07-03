package fr.epita.quiz.ui.candidate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Krishna, Abhigna
 * @version 1
 */
public class CandidateLogin extends JFrame {

	//Variable Declaration
	private static final long serialVersionUID = 5571572061570478099L;
	private JPanel mainPane;
	private JTextField topicTxtFld;
	private JTextField diffTxtFld;

	public CandidateLogin(String uname) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Welcome to Candidate Portal");
		welcomeLbl.setBounds(137, 11, 222, 14);
		mainPane.add(welcomeLbl);
		
		JLabel tpicDiffLbl = new JLabel("Select a Topic and the Difficulty(1 or 2 or 3) :");
		tpicDiffLbl.setBounds(106, 36, 253, 14);
		mainPane.add(tpicDiffLbl);
		
		JLabel topicLbl = new JLabel("Topic");
		topicLbl.setBounds(31, 80, 66, 14);
		mainPane.add(topicLbl);
		
		topicTxtFld = new JTextField();
		topicTxtFld.setBounds(169, 77, 104, 20);
		mainPane.add(topicTxtFld);
		topicTxtFld.setColumns(10);
		
		JLabel diffLbl = new JLabel("Difficulty");
		diffLbl.setBounds(31, 149, 66, 14);
		mainPane.add(diffLbl);
		
		diffTxtFld = new JTextField();
		diffTxtFld.setBounds(169, 146, 104, 20);
		mainPane.add(diffTxtFld);
		diffTxtFld.setColumns(10);
		
		
		JButton proceedBtn = new JButton("Proceed"); // Proceed to Quiz
		proceedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String topic = topicTxtFld.getText();
				int diff = Integer.parseInt(diffTxtFld.getText());
				new CandidateQuiz(topic,diff,uname);
				setVisible(false);
			}
		});
		proceedBtn.setBounds(144, 212, 89, 23);
		mainPane.add(proceedBtn);
	}

}
