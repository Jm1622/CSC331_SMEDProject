package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EndDetectionActionListener implements ActionListener{
	private GUI f_gui;
	public EndDetectionActionListener(GUI gui) {
		this.f_gui = gui;
	}
	public void actionPerformed(ActionEvent e) {
		//When activated disable the stop button
		f_gui.disableStopButton();
		//get the gui from the button
		boolean successfulEnd = f_gui.getController().endProcessing();
		if(!successfulEnd) {
			JOptionPane.showMessageDialog(null,"No source was found to end processing");
		}
		//Enable the majority of GUI interaction
		f_gui.enableKeyWordSearch();
		f_gui.enableStartButton();
		f_gui.enableScrolling();
	}
}