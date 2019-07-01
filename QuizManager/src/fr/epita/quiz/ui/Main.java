package fr.epita.quiz.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = -2728957700299762075L;
	private StatusBar statusBar;

	Main() {
		JFrame frame = new JFrame("Quiz Manager");
		JButton admn, candidate;
		admn = new JButton("Admin");
		admn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admn();
				setVisible(false);
			}
		});
		candidate = new JButton("Candidate");
		candidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Candidate();
				setVisible(false);
			}
		});

		frame.add(admn);
		frame.add(candidate);
		frame.setLayout(new FlowLayout());
		frame.setBounds(100, 100, 461, 623);

		frame.setVisible(true);
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(270, 420));

	}

	public static void main(String[] args) {
		new Main();
	}

}
