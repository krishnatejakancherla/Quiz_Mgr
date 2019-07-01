package fr.epita.quiz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class CandidateQuiz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637280731339753772L;



	private JPanel mainPane;

	

	public int[] id= new int[50];
	public String[] ques= new String[50];
	public String[] chcA= new String[50];
	public String[] chcB= new String[50];
	public String[] chcC= new String[50];
	public String[] chcD= new String[50];
	public String[] crctAns= new String[50];
	public int i=0,c=0;
	
	public CandidateQuiz(String topic, int diff,String uname) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Online Quiz");
		lblNewLabel.setBounds(211, 11, 146, 14);
		mainPane.add(lblNewLabel);
		
		try {
			Connection connection = getConnection();
			String query = "SELECT * FROM question  WHERE TOPICS='"+topic+"' and difficulty="+diff+";";
			 Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(query);
			 while(rs.next())
				{
				 
				    id[i] = rs.getInt("ID");
					ques[i] = rs.getString("CONTENT");
				    chcA[i] = rs.getString("CHOICEA");
				    chcB[i] = rs.getString("CHOICEB");
				    chcC[i] = rs.getString("CHOICEC");
				    chcD[i] = rs.getString("CHOICED");
				    crctAns[i] = rs.getString("ANSWER");
				    i++;
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		i=0;
			
			JLabel quesLbl = new JLabel(ques[i]);
			quesLbl.setBounds(86, 61, 325, 14);
			mainPane.add(quesLbl);
			
			JRadioButton chcArdBtn = new JRadioButton(chcA[i]);
			chcArdBtn.setBounds(100, 102, 364, 23);
			mainPane.add(chcArdBtn);
			
			JRadioButton chcBrdBtn = new JRadioButton(chcB[i]);
			chcBrdBtn.setBounds(100, 155, 345, 23);
			mainPane.add(chcBrdBtn);
			
			JRadioButton chcCrdBtn = new JRadioButton(chcC[i]);
			chcCrdBtn.setBounds(100, 212, 333, 23);
			mainPane.add(chcCrdBtn);
			
			JRadioButton chcDrdBtn = new JRadioButton(chcD[i]);
			chcDrdBtn.setBounds(100, 274, 325, 23);
			mainPane.add(chcDrdBtn);
			
			ButtonGroup btnGrp = new ButtonGroup();
			btnGrp.add(chcArdBtn);
			btnGrp.add(chcBrdBtn);
			btnGrp.add(chcCrdBtn);
			btnGrp.add(chcDrdBtn);


			getContentPane().add(chcArdBtn);
			getContentPane().add(chcBrdBtn);
			getContentPane().add(chcCrdBtn);
			getContentPane().add(chcDrdBtn);
			
			JButton vldtBtn = new JButton("Validate");
			vldtBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chcArdBtn.isSelected()) {
						if(crctAns[i]==chcArdBtn.getText()) {
							c=c+1;
						}
					}
					else if(chcBrdBtn.isSelected()) {
						if(crctAns[i]==chcBrdBtn.getText()) {
							c=c+1;
						}
					}
					else if(chcCrdBtn.isSelected()) {
						if(crctAns[i]==chcCrdBtn.getText()) {
							c=c+1;
						}
					}
					else if(chcDrdBtn.isSelected()) {
						if(crctAns[i]==chcDrdBtn.getText()) {
							c=c+1;
						}
					}
					i++;
				}
			});
			vldtBtn.setBounds(180, 372, 89, 23);
			mainPane.add(vldtBtn);
			
			JButton nxtBtn = new JButton("Next");
			nxtBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ques[i]!=null)
					{
					quesLbl.setText(ques[i]);
					chcArdBtn.setText(chcA[i]);
					
					chcBrdBtn.setText(chcB[i]);
					chcCrdBtn.setText(chcC[i]);
					chcDrdBtn.setText(chcD[i]);
					ButtonGroup group = new ButtonGroup();
					group.add(chcArdBtn);
					group.add(chcBrdBtn);
					group.add(chcCrdBtn);
					group.add(chcDrdBtn);


					getContentPane().add(chcArdBtn);
					getContentPane().add(chcBrdBtn);
					getContentPane().add(chcCrdBtn);
					getContentPane().add(chcDrdBtn);
					}
					else {
						JButton endBtn = new JButton("End");
						endBtn.setEnabled(true);
						endBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null, "The Quiz is Completed Successfully \n The Mark is "+c);
							}
						});
						endBtn.setBounds(180, 443, 89, 23);
						mainPane.add(endBtn);
					}
				}
			});
			nxtBtn.setBounds(295, 370, 89, 23);
			mainPane.add(nxtBtn);
			
			JButton endBtn = new JButton("End");
			endBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "The Quiz is Completed Successfully \n The Mark is "+c);
					new Main();
					setVisible(false);
					
				}
				
			});
			endBtn.setBounds(180, 435, 89, 23);
			mainPane.add(endBtn);
			
			
		}
				
	

	
	
	private Connection getConnection() throws SQLException, FileNotFoundException, IOException {
			String url = "jdbc:h2:~/test";
			String username = "sa";
			String password = "";
			
			return DriverManager.getConnection(url, username, password);
		}
}
