package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StartDetectingActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		try {
			if(gui.getDropdownText() != null) {
				String words = gui.getKeyWords();
				if(!words.equals("")) {
					gui.clearMessages();
					ArrayList<String> keyWords = new ArrayList(Arrays.asList(gui.getKeyWords().split(",")));
					MessageProcessor processor = new MessageProcessor(keyWords);
					Source source = new Source(gui.getDropdownText());
					source.startMessageGetting();
					gui.setSource(source);
					gui.setKeepGetting(true);
					gui.setMessageprocessingThread(new MessageLoopThread(source, gui.isKeepgetting(), gui, processor));
					gui.setThread(new Thread(gui.getMessageProcessingThread()));
					gui.getThread().start();
				}
				else {
					JOptionPane.showMessageDialog(null, "No keywords were entered, please enter some and start again!");
				}
			}
			else {
				System.out.println("No source selected");
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
