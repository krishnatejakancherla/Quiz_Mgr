package fr.epita.quiz.ui.admn;

import javax.swing.JLabel;

/**
 * 
 * @author Krishna, Abhigna
 *
 */
public class StatusBar extends JLabel {

  
	private static final long serialVersionUID = -5824491744519693394L;

    public StatusBar() {
        super();
        setMessage("Ready");
    }

    public void setMessage(String message) {
        setText(" "+message);        
    }        
}