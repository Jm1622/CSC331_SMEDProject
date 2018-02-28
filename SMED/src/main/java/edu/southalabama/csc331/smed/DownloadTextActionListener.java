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
		GUI gui = (GUI)((JButton)e.getSource()).getParent().getParent().getParent().getParent();
		
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    try {
				FileWriter writer = new FileWriter(fileToSave);
				writer.write("Messages determined to be event:\n");
				if(!gui.getEventMessages().isEmpty()) {
					for(JsonObject message:gui.getEventMessages()) {
						writer.write(message.toString()+"\n");
					}
				}
				else {
					writer.write("No messages determined to be event.\n");
				}
				if(!gui.getNonEventMessages().isEmpty()) {
					writer.write("Messages determined to not be event:\n");
					for(JsonObject message:gui.getNonEventMessages()) {
						writer.write(message.toString()+"\n");
					}
				}
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
