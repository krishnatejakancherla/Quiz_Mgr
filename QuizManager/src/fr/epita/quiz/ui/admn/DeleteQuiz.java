package fr.epita.quiz.ui.admn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.epita.quiz.services.data.QuizJDBCDAO;

/**
 * 
 * @author Krishna, Abhigna
 *
 */
public class DeleteQuiz extends JFrame {
	
	private static final long serialVersionUID = 6839934241297489798L;
	
	JFrame searchFrame, tableFrame, resultFrame; // Frames
	JLabel label;
	JButton delBtn;
	JPanel panel;
	static JTable table;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();

	String driverName = "org.h2.Driver";
	String url = "jdbc:h2:~/test";
	String userName = "sa";
	String password = "";
	int id;

	public void init() {
		searchFrame = new JFrame("Quiz List");
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.setLayout(null);
		searchFrame.setBounds(100, 100, 461, 623);
		label = new JLabel("Please Select Quiz :");
		label.setBounds(159, 100, 156, 23);
		searchFrame.add(label);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(159, 150, 156, 23);

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

		searchFrame.add(comboBox);
		
		delBtn = new JButton("Delete"); // To delete Quiz
		delBtn.setBounds(219, 200, 89, 23);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = comboBox.getSelectedItem().toString();
				String[] quiz = string.split(" - ");
				id = Integer.parseInt(quiz[0].toString());
				boolean isSucc = dao.deleteQuiz(id);
				
				if (isSucc) {
					JOptionPane.showMessageDialog(null, "Quiz  is Deleted successfully");
				}
			}
		});
		searchFrame.add(delBtn);
		JButton bckBtn = new JButton("Back"); // Back to Home
		bckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdmnLogin();
				setVisible(false);
			}
		});
		bckBtn.setBounds(319, 200, 89, 23);
		searchFrame.add(bckBtn);
		searchFrame.setVisible(true);
	}

}