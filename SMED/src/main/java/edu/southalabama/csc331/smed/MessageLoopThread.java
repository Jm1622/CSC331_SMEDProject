package edu.southalabama.csc331.smed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MessageLoopThread implements Runnable {
	//Private variables the the Message Loop Thread, a thread that just continuously gets messages, uses
	private ArrayList<Source> f_sources;
	private boolean f_keepGoing;
	private GUI f_gui;
	private MessageProcessor f_processor;
	private SMEDController f_controller;
	private StringBuilder f_lock = new StringBuilder("Unlocked");
	private Random f_numberGenerator = new Random();
	File file = new File(System.getProperty("user.dir")+"/locations.txt");
	FileWriter fw;
	//Constructor that adds a value to all four of these variables
	// TODO replace gui with a controller class
	public MessageLoopThread(ArrayList<Source> sources, boolean keepGoing, MessageProcessor processor, GUI gui, SMEDController controller) throws InterruptedException, IOException {
		this.f_sources = sources;
		this.f_keepGoing = keepGoing;
		this.f_processor = processor;
		this.f_gui = gui;
		this.f_controller = controller;
		this.fw = new FileWriter(file, false);
	}
	public void run() {
		//This overrides runnables run method, this is what runs on the thread
			while(f_keepGoing) {
				//While that boolean is true
				try {
					synchronized(f_lock) {
						if(f_lock.toString().equals("Lock")) {
							f_lock.wait();
						}
					}
					//process a message from a random source in the list
					Message message = f_processor.processMessage(f_sources.get(f_numberGenerator.nextInt(f_sources.size())).getMessage());
					//Add to the appropriate box dependent on this last attribute
					if(message.getMatchCount() > 0) {
						f_controller.addEventMessage(message);
						f_gui.addEventMessage(message.toString());
						fw.append(message.getLocation());
					}
					else{
						f_controller.addNonEventMessage(message);
						f_gui.addNonEventMessage(message.toString());
					}
					
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	//Change the boolean, use this to end the thread.
	public void setKeepGoing(boolean keepGoing) {
		this.f_keepGoing = keepGoing;
	}
	public boolean getKeepGoing() {
		return f_keepGoing;
	}
	public StringBuilder getLock() {
		return f_lock;
	}
}
