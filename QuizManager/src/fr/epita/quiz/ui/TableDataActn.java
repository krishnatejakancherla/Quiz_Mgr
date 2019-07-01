package fr.epita.quiz.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableDataActn extends JFrame{
   
	private static final long serialVersionUID = -7414718176998592676L;
	private JPanel topPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;

	public TableDataActn(/* JTable tbl */){
        setTitle("Company Record Application");
        setSize(300,200);
        setBackground(Color.gray);


        topPanel = new JPanel();
        btnPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        getContentPane().add(btnPanel);
        //scrollPane = new JScrollPane(tbl);
        topPanel.add(scrollPane,BorderLayout.CENTER);
        JButton addButton = new JButton("ADD");
        JButton delButton = new JButton("DELETE");
        JButton saveButton = new JButton("SAVE");

        btnPanel.add(addButton);
        btnPanel.add(delButton);
        
        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

    }
}