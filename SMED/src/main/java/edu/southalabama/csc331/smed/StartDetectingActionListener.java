package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StartDetectingActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		//Use the parents of the Button to get back to the GUI
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		//if the thread does exist, put it to sleep for 100 seconds
		if(gui.getMessageProcessingThread()!= null)
			gui.getMessageProcessingThread().sleep(100);
		try {
			//If we have a source selected
			if(gui.getDropdownText() != null) {
				//Set the keyWords
				String words = gui.getKeyWords();
				if(!words.equals("")) {
					//Clear all messages since we are starting again
					gui.clearMessages();
					//Populate the keyword list and create a message processor and source
					ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(gui.getKeyWords().split(",")));
					MessageProcessor processor = new MessageProcessor(keyWords);
					Source source = new Source(gui.getDropdownText());
					//Make the source startMessageGetting, this puts it where it is connected with source
					source.startMessageGetting();
					//Change the gui's source, keepGetting to our new values
					gui.setSource(source);
					//Create the messageLoopThread, and create a thread around this class and start it
					// TODO link these things to a controller instead of the gui class
					gui.setMessageprocessingThread(new MessageLoopThread(source, true, gui, processor));
					gui.setThread(new Thread(gui.getMessageProcessingThread()));
					gui.getThread().start();
				}
				else {
					//If no keywords exist, alert the user that we need some keywords
					JOptionPane.showMessageDialog(null, "No keywords were entered, please enter some and start again!");
				}
			}
			else {
				//Currently impossible with our gui, but if there is no source selected alert user
				JOptionPane.showMessageDialog(null,"No source selected");
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
