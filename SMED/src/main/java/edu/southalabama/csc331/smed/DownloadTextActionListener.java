package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DownloadTextActionListener implements ActionListener{
	private GUI f_gui;
	public DownloadTextActionListener(GUI gui) {
		this.f_gui = gui;
	}
	public void actionPerformed(ActionEvent e) {
		
		
		//Stop the GUI processing thread if it exists
		// TODO figure out a better way to handle the GUi response problems, this case should probably completely stop until this is finished
		if(f_gui.getController().getMessageProcessingThread()!= null)
			f_gui.getController().getMessageProcessingThread().setKeepGoing(false);
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
		    f_gui.getController().writeFile(fileToSave);
		}
		//restarts the thread when they are done
		f_gui.getController().getMessageProcessingThread().setKeepGoing(true);
		f_gui.getController().restartThread();
	}

}
