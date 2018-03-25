package edu.southalabama.csc331.smed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SMEDController {
	private Source f_source;
	private MessageProcessor f_processor;
	private MessageLoopThread f_loopThread;
	private Thread f_thread;
	private GUI f_gui;
	ArrayList<Message> f_eventMessages = new ArrayList<Message>();
	ArrayList<Message> f_nonEventMessages = new ArrayList<Message>();
	public SMEDController(GUI gui) {
		this.f_gui = gui;
	}
	public void startProcessing(ArrayList<String> keyWords, String sourceType, GUI gui) throws InterruptedException {
		if(f_loopThread != null) {
			f_loopThread.setKeepGoing(false);
		}
		f_source = new Source(sourceType);
		f_source.startMessageGetting();
		f_processor = new MessageProcessor(keyWords);
		f_loopThread = new MessageLoopThread(f_source, true, f_processor, f_gui, this);
		f_thread = new Thread(f_loopThread);
		f_thread.start();
	}
	public boolean endProcessing() {
		if(f_loopThread != null) {
			f_loopThread.sleep(100);
		}
		if(f_source != null) {
			f_source.stopMessageGetting();
			f_loopThread.setKeepGoing(false);
			return true;
		}
		else {
			return false;
		}
	}
	public void writeFile(File fileToSave) {
		try {
	    		//create a file and write all messages from the event messages
			FileWriter writer = new FileWriter(fileToSave);
			if(!f_eventMessages.isEmpty()) {
				writer.write("Messages determined to be event:\n");
				for(Message message:f_eventMessages) {
					writer.write(message.toString()+"\n");
				}
			}
			//If there are no EventMessages write that out
			else {
				writer.write("No messages determined to be event.\n");
			}
			//create a file and write all messages from the non event messages
			if(!f_nonEventMessages.isEmpty()) {
				writer.write("Messages determined to not be event:\n");
				for(Message message:f_nonEventMessages) {
					writer.write(message.toString()+"\n");
				}
			}
			//if no nonevent messages write that to the file
			else {
				writer.write("No messages determined to be not an event.\n");
			}
			writer.close();
			f_loopThread.setKeepGoing(true);
			f_thread = new Thread(f_loopThread);
			f_thread.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	public MessageProcessor getProcessor() {
		return f_processor;
	}
	public void setPrcessor(MessageProcessor processor) {
		this.f_processor = processor;
	}
	public Source getSource() {
		return f_source;
	}
	public void setSource(Source source) {
		this.f_source = source;
	}
	public void setMessageprocessingThread(MessageLoopThread thread) {
		this.f_loopThread = thread;
	}
	public MessageLoopThread getMessageProcessingThread() {
		return f_loopThread;
	}
	public void setThread(Thread thread) {
		this.f_thread = thread;
	}
	public Thread getThread() {
		return f_thread;
	}
	public void addEventMessage(Message message) {
		f_eventMessages.add(message);
	}
	public void addNonEventMessage(Message message) {
		f_nonEventMessages.add(message);
	}
	public ArrayList<Message> getEventMessages() {
		return f_eventMessages;
	}
	public ArrayList<Message> getNonEventMessages() {
		return f_nonEventMessages;
	}
}
