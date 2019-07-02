package fr.epita.quiz.ui.admn;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.MultChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.services.data.QuizJDBCDAO;

public class CreateQuiz extends JFrame {

	private static final long serialVersionUID = -8988486909734440112L;
	private JPanel pane;
	private JTextField quesFld;
	private JTextField topicFld;
	private JTextField diffFld;
	private JTextField fldA;
	private JTextField fldB;
	private JTextField fldC;
	private JTextField fldD;
	private JTextField fldCrctAns;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();
	private int qid;
	private int id;

	public CreateQuiz(String mode, HashMap<String, String> retMap) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);
		pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pane);
		pane.setLayout(null);

		JLabel lbl = new JLabel("Add a Question");
		lbl.setBounds(149, 11, 196, 20);
		pane.add(lbl);

		JLabel ques = new JLabel("Question");
		ques.setBounds(32, 49, 100, 14);
		pane.add(ques);

		JLabel topic = new JLabel("Topic");
		topic.setBounds(32, 86, 46, 14);
		pane.add(topic);

		JLabel dif = new JLabel("Difficulty");
		dif.setBounds(32, 144, 76, 14);
		pane.add(dif);

		JLabel chceA = new JLabel("Choice A");
		chceA.setBounds(32, 184, 106, 14);
		pane.add(chceA);

		JLabel chceB = new JLabel("Choice B");
		chceB.setBounds(32, 214, 126, 14);
		pane.add(chceB);

		JLabel chceC = new JLabel("Choice C");
		chceC.setBounds(32, 254, 146, 14);
		pane.add(chceC);

		JLabel chceD = new JLabel("Choice D");
		chceD.setBounds(32, 294, 166, 14);
		pane.add(chceD);

		JLabel crctAns = new JLabel("Correct Answer");
		crctAns.setBounds(32, 324, 186, 14);
		pane.add(crctAns);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 364, 206, 20);

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select ID, NAME from QUIZ");
			while (rs.next()) {
				comboBox.addItem(rs.getString("ID") +" - "+rs.getString("NAME"));
				
			}

			rs.close();
			stat.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		pane.add(comboBox);

		quesFld = new JTextField();
		quesFld.setBounds(142, 46, 267, 20);
		pane.add(quesFld);
		quesFld.setColumns(10);

		topicFld = new JTextField();
		topicFld.setBounds(142, 83, 267, 20);
		pane.add(topicFld);
		topicFld.setColumns(10);

		diffFld = new JTextField();
		diffFld.setBounds(142, 141, 267, 20);
		pane.add(diffFld);
		diffFld.setColumns(10);

		fldA = new JTextField();
		fldA.setToolTipText("");
		fldA.setBounds(142, 184, 267, 20);
		pane.add(fldA);
		fldA.setColumns(10);

		fldB = new JTextField();
		fldB.setBounds(142, 214, 267, 20);
		pane.add(fldB);
		fldB.setColumns(10);

		fldC = new JTextField();
		fldC.setBounds(142, 254, 267, 20);
		pane.add(fldC);
		fldC.setColumns(10);

		fldD = new JTextField();
		fldD.setBounds(142, 294, 267, 20);
		pane.add(fldD);
		fldD.setColumns(10);

		fldCrctAns = new JTextField();
		fldCrctAns.setBounds(142, 324, 267, 20);
		pane.add(fldCrctAns);
		fldCrctAns.setColumns(10);

		JButton addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Answer ans = new Answer(fldCrctAns.getText());
					String string = comboBox.getSelectedItem().toString();
					String[] quiz = string.split(" - ");
					id = Integer.parseInt(quiz[0].toString());
					ans.setQuestion(new Question(id, 0, quesFld.getText(), topicFld.getText(),
							Integer.parseInt(diffFld.getText())));
					ans.setMultChce(new MultChoice(fldA.getText(), fldB.getText(), fldC.getText(), fldD.getText()));
					System.out.println("Answers:" + ans);
					boolean isSucc = dao.createQues(ans);
					if (isSucc) {
						JOptionPane.showMessageDialog(null, "Question is added successfully");
					}
				} catch (CreateFailedException e1) {
					e1.printStackTrace();
				}
			}
		});
		addBtn.setBounds(9, 550, 89, 23);
		pane.add(addBtn);

		JButton updtBtn = new JButton("UPDATE");
		updtBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Answer ans = new Answer(fldCrctAns.getText());
					ans.setQuestion(new Question(0, qid, quesFld.getText(), topicFld.getText(),
							Integer.parseInt(diffFld.getText())));
					ans.setMultChce(new MultChoice(fldA.getText(), fldB.getText(), fldC.getText(), fldD.getText()));
					System.out.println("Answers:" + ans + " mmm " + qid);
					boolean isSucc = dao.updtQues(ans);
					if (isSucc) {
						JOptionPane.showMessageDialog(null, "Question is Updated successfully");
					}
				} catch (CreateFailedException e1) {
					e1.printStackTrace();
				}
			}
		});
		updtBtn.setBounds(99, 550, 89, 23);
		pane.add(updtBtn);

		JButton delBtn = new JButton("DELETE");
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isSucc = dao.delQues(qid);
					if (isSucc) {
						JOptionPane.showMessageDialog(null, "Question is Deleted successfully");
					}
				} catch (CreateFailedException e1) {
					e1.printStackTrace();
				}
			}
		});
		delBtn.setBounds(199, 550, 89, 23);
		pane.add(delBtn);

		JButton bckBtn = new JButton("Back");
		bckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdmnLogin();
				setVisible(false);
			}
		});
		bckBtn.setBounds(319, 550, 89, 23);
		pane.add(bckBtn);
		StatusBar statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(270, 420));
		pane.add(statusBar, java.awt.BorderLayout.SOUTH);

		if (mode == "UPDATE") {
			quesFld.setText(retMap.get("CONTENT"));
			topicFld.setText(retMap.get("TOPICS"));
			diffFld.setText(retMap.get("DIFFICULTY"));
			fldA.setText(retMap.get("CHOICEA"));
			fldB.setText(retMap.get("CHOICEB"));
			fldC.setText(retMap.get("CHOICEC"));
			fldD.setText(retMap.get("CHOICED"));
			fldCrctAns.setText(retMap.get("ANSWER"));
			qid = Integer.parseInt(retMap.get("QID"));
			addBtn.setEnabled(false);
		} else {
			updtBtn.setEnabled(false);
			delBtn.setEnabled(false);
		}
	}

}
