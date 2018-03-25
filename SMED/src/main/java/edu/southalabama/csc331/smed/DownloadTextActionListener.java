package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DownloadTextActionListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		//Get the gui from the parents of the button
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		//Set gui's processingthread to sleep, if this exists
		// TODO figure out a better way to handle the GUi response problems, this case should probably completely stop until this is finished
		if(gui.getController().getMessageProcessingThread()!= null)
			gui.getController().getMessageProcessingThread().setKeepGoing(false);
		//Create a jfilechooser that is filtered to only allow text files
		JFrame parentFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		//If the user chose a file
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			//get the file
		    File fileToSave = fileChooser.getSelectedFile();
		    gui.getController().writeFile(fileToSave);
		}
		
	}

}
