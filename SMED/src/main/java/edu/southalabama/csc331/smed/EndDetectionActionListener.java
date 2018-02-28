package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class EndDetectionActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		Source source = gui.getSource();
		if(source != null) {
			source.stopMessageGetting();
			gui.setKeepGetting(false);
			gui.getMessageProcessingThread().setKeepGoing(false);
		}
		else {
			System.out.println("No Source Currently Exists");
		}
	}
}