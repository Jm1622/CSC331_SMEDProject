package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class EndDetectionActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		//get the gui from the button
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		//Sleep the messageProcessingThread, this is done to help performance
		// TODO find a better way to pause the processing thread instead of sleeping it, also link this to a controller instead of the gui
		if(gui.getMessageProcessingThread()!= null)
			gui.getMessageProcessingThread().sleep(100);
		//get the source from the gui
		Source source = gui.getSource();
		//If the source exists stopMessageGetting and stop the message processing thread
		if(source != null) {
			source.stopMessageGetting();
			gui.getMessageProcessingThread().setKeepGoing(false);
		}
		else {
			System.out.println("No Source Currently Exists");
		}
	}
}