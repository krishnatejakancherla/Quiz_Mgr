package fr.epita.quiz.ui.admn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.services.data.QuizJDBCDAO;

public class SearchQuiz extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6839934241297489798L;
	JFrame searchFrame, tableFrame, resultFrame;
	JTextField textbox;
	JLabel label;
	JButton searchBtn;
	JPanel panel;
	static JTable table;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();
	private List<Answer> retList;
	String[] columnNames = { "Id", "Question", "Topic", "Difficulty" };
	private HashMap<String, String> retMap;
	String driverName = "org.h2.Driver";
	String url = "jdbc:h2:~/test";
	String userName = "sa";
	String password = "";

	public void init() {
		searchFrame = new JFrame("Database Search Result");
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.setLayout(null);
		textbox = new JTextField();
		textbox.setBounds(190, 30, 150, 20);
		label = new JLabel("Enter Topic to search: ");
		label.setBounds(10, 30, 180, 20);
		searchFrame.setBounds(100, 100, 461, 623);

		searchFrame.add(textbox);
		searchFrame.add(label);
		searchBtn = new JButton("search");
		searchBtn.setBounds(120, 100, 100, 20);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retList = dao.retreiveAllQues(textbox.getText().toString());
				showTableData(retList);
				setVisible(true);
			}
		});

		searchFrame.add(searchBtn);
		JButton bckBtn = new JButton("Back");
		bckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdmnLogin();
				setVisible(false);
			}
		});
		bckBtn.setBounds(220, 100, 100, 20);
		searchFrame.add(bckBtn);
		searchFrame.setVisible(true);
		// searchFrame.setSize(500, 400);
	}

	public void showTableData(List<Answer> retList) {

		resultFrame = new JFrame("List of Questions");
		resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultFrame.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		int id = 0;
		String content = "";
		String topic = "";
		int diff = 0;
		try {

			int i = 0;
			for (i = 0; i < retList.size(); i++) {
				id = retList.get(i).getQuestion().getQid();
				content = retList.get(i).getQuestion().getContent();
				topic = retList.get(i).getQuestion().getTopics();
				diff = retList.get(i).getQuestion().getDifficulty();
				model.addRow(new Object[] { id, content, topic, diff });
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				int row = table.convertRowIndexToModel(table.getSelectedRow());
				int col = table.getColumnModel().getColumnIndex("Id");
				int id = (Integer) table.getModel().getValueAt(row, col);
				System.out.println("ID::"+id);
				retMap = dao.retreiveQues(id);
				new CreateQuiz("UPDATE", retMap);
				setVisible(false);
			}
		});
		resultFrame.add(scroll);
		resultFrame.setVisible(true);
		resultFrame.setBounds(100, 100, 461, 623);

	}

}