package fr.epita.quiz.ui;

import javax.swing.JLabel;

public class StatusBar extends JLabel {

  
	private static final long serialVersionUID = -5824491744519693394L;

	/** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        setMessage("Ready");
    }

    public void setMessage(String message) {
        setText(" "+message);        
    }        
}