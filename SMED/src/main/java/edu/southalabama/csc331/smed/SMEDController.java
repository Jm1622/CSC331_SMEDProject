package edu.southalabama.csc331.smed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SMEDController {
	private ArrayList<Source> f_sources = new ArrayList<Source>();
	private MessageProcessor f_processor;
	private MessageLoopThread f_loopThread;
	private Thread f_thread;
	private GUI f_gui;
	private Timer f_timer = new Timer();
	ArrayList<Message> f_eventMessages = new ArrayList<Message>();
	ArrayList<Message> f_nonEventMessages = new ArrayList<Message>();
	public SMEDController(GUI gui) {
		this.f_gui = gui;
	}
	public void startProcessing(ArrayList<String> keyWords, ArrayList<String> sourceTypes, GUI gui) throws InterruptedException {
		if(f_loopThread != null) {
			f_loopThread.setKeepGoing(false);
		}
		sourceTypes.forEach(sourceType->{
			try {
				f_sources.add(new Source(sourceType));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		f_sources.forEach(source->{
			try {
				source.startMessageGetting();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		f_processor = new MessageProcessor(keyWords);
		f_loopThread = new MessageLoopThread(f_sources, true, f_processor, f_gui, this);
		f_thread = new Thread(f_loopThread);
		f_thread.start();
	}
	public void pauseProcessing(long time) {
		StringBuilder f_lock = new StringBuilder("");
		if(f_loopThread != null) {
			synchronized(f_lock) {
				f_lock.replace(0, f_lock.length(), "Lock");
			}
			//If any existing tasks exist cancel them, we will create a new one
			f_timer.cancel();
			f_timer = new Timer();
			f_timer.schedule(new TimerTask() {
					private StringBuilder f_lock = new StringBuilder("");
					@Override
					public void run() {
					synchronized(f_lock) {
						f_lock.replace(0, f_lock.length(), "Unlocked");
						f_lock.notify();
					}
					}
				}, time);
				
		}
	}
	public void restartThread() {
		if(f_loopThread != null) {
			f_thread = new Thread(f_loopThread);
			f_thread.start();
		}
	}
	
	public boolean endProcessing() {
		if(f_loopThread != null) {
			this.pauseProcessing(10000);
		}
		if(f_sources != null) {
			f_sources.forEach(source->{
				source.stopMessageGetting();
			});
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
	public ArrayList<Source> getSources() {
		return f_sources;
	}
	public void setSource(ArrayList<Source> sources) {
		this.f_sources = sources;
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
