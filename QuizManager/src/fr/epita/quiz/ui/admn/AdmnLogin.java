package fr.epita.quiz.ui.admn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.DocumentException;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuizJDBCDAO;
import fr.epita.quiz.ui.Main;

public class AdmnLogin extends JFrame {
	
	private static final long serialVersionUID = 5540687645487609987L;

	private JPanel contentPane;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();

	
	public AdmnLogin() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 623);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to Admin Console");
		lblNewLabel.setBounds(96, 11, 307, 14);
		contentPane.add(lblNewLabel);

		JButton createBtn = new JButton("Create Quiz");
		createBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new CreateQuiz("ADD", null);
				setVisible(false);
			}
		});
		createBtn.setBounds(172, 48, 119, 23);
		contentPane.add(createBtn);

		JButton updateBtn = new JButton("Search Quiz");
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SearchQuiz updt = new SearchQuiz();
				updt.init();
				setVisible(false);
			}
		});
		updateBtn.setBounds(172, 98, 119, 23);
		contentPane.add(updateBtn);

		JButton deleteBtn = new JButton("Delete Quiz");
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DeleteQuiz delQ = new DeleteQuiz();
				delQ.init();
				setVisible(false);
			}
		});
		deleteBtn.setBounds(172, 158, 119, 23);
		contentPane.add(deleteBtn);

		JButton expBtn = new JButton("Export Quiz");
		expBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					boolean isSucc = dao.exportQuiz();
					if (isSucc) {
						JOptionPane.showMessageDialog(null, "Question has been exported to PDF successfully");
					}
				} catch (IOException | DocumentException e1) {
					e1.printStackTrace();
				}
			}
		});
		expBtn.setBounds(172, 208, 119, 23);
		contentPane.add(expBtn);

		JButton hmeBtn = new JButton("Home");
		hmeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		hmeBtn.setBounds(172, 258, 119, 23);
		contentPane.add(hmeBtn);
	}

	public static boolean exportQues(List<Question> quesList) throws IOException {
		final String FNAME = "Quiz.txt";
		boolean isSucc = false;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FNAME))) {
			for (Question ques : quesList) {
				bw.write(ques.toString() + "\n");
			}
			bw.close();
			isSucc = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSucc;
	}
}
