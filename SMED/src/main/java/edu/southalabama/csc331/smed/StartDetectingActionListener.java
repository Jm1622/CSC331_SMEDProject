package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class StartDetectingActionListener implements ActionListener{
	private GUI f_gui;
	public StartDetectingActionListener(GUI gui) {
		this.f_gui = gui;
	}
	public void actionPerformed(ActionEvent e) {
		f_gui.disableStartButton();
		//Use the parents of the Button to get back to the GUI
		try {
			//If we have a source selected
			if(f_gui.getSelectedSources() != null) {
				//Set the keyWords
				String words = f_gui.getKeyWords();
				
				if(!words.equals("")) {
					//Clear all messages since we are starting again
					f_gui.clearMessages();
					f_gui.getController().clearMessages();
					//Populate the keyword list and create a message processor and source
					ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(f_gui.getKeyWords().split(",")));
					f_gui.getController().startProcessing(keyWords, f_gui.getSelectedSources(), f_gui);
					f_gui.disableKeyWordSearch();
					f_gui.enableStopButton();
					f_gui.disableScrolling();
				}
				else {
					//If no keywords exist, alert the user that we need some keywords
					JOptionPane.showMessageDialog(null, "No keywords were entered, please enter some and start again!");
					f_gui.enableStartButton();
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"No source selected");
				f_gui.enableStartButton();
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
