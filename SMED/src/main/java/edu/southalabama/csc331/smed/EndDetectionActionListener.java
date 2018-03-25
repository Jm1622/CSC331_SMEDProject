package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EndDetectionActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		//get the gui from the button
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		boolean successfulEnd = gui.getController().endProcessing();
		if(!successfulEnd) {
			JOptionPane.showMessageDialog(null,"No source was found to end processing");
		}
	}
}