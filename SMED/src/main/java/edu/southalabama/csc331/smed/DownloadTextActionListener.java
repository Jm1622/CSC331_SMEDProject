package edu.southalabama.csc331.smed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.json.JsonObject;
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
		if(gui.getMessageProcessingThread()!= null)
			gui.getMessageProcessingThread().sleep(1000);
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
		    try {
		    		//create a file and write all messages from the guis event messages
		    		// TODO link the getEventMessages to a controller instead of the gui
				FileWriter writer = new FileWriter(fileToSave);
				writer.write("Messages determined to be event:\n");
				if(!gui.getEventMessages().isEmpty()) {
					for(JsonObject message:gui.getEventMessages()) {
						writer.write(message.toString()+"\n");
					}
				}
				//If there are no EventMessages write that out
				else {
					writer.write("No messages determined to be event.\n");
				}
				//create a file and write all messages from the guis nonevent messages
	    			// TODO link the getNonEventMessages to a controller instead of the gui
				if(!gui.getNonEventMessages().isEmpty()) {
					writer.write("Messages determined to not be event:\n");
					for(JsonObject message:gui.getNonEventMessages()) {
						writer.write(message.toString()+"\n");
					}
				}
				//if no nonevent messages write that to the file
				else {
					writer.write("No messages determined to be not an event.\n");
				}
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
