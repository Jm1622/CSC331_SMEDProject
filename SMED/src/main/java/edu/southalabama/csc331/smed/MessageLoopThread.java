package edu.southalabama.csc331.smed;

import javax.json.JsonObject;


public class MessageLoopThread implements Runnable {
	//Private variables the the Message Loop Thread, a thread that just continuously gets messages, uses
	private Source source;
	private boolean keepGoing;
	private GUI gui;
	private MessageProcessor processor;
	//Constructor that adds a value to all four of these variables
	// TODO replace gui with a controller class
	public MessageLoopThread(Source source, boolean keepGoing, GUI gui, MessageProcessor processor) throws InterruptedException {
		this.source = source;
		this.keepGoing = keepGoing;
		this.gui = gui;
		this.processor = processor;
	}
	public void run() {
		//This overrides runnables run method, this is what runs on the thread
		while(keepGoing) {
			//While that boolean is true
			try {
				//process a message from the source
				JsonObject message = processor.processMessage(source.getMessage());
				String output = "";
				//Add certain attributes if they exist in the json we receive
				if(message.get("text") != null) {
					output += "Tweet: " + message.get("text").toString();
				}
				if(message.get("user") != null) {
					JsonObject user = message.getJsonObject("user");
					if(user.get("name") != null) {
						output += " Name: "+ user.get("name").toString();
					}
					if(user.get("screen_name") != null) {
						output += " Screen Name: "+ user.get("screen_name").toString();
					}
				}
				if(message.get("place") != null) {
					if(!message.get("place").toString().equals("null")) {
						JsonObject place = message.getJsonObject("place");
						output += " Place: " + place.get("full_name").toString();
					}
				}
				//Add to the appropriate box dependent on this last attribute
				if(message.get("MatchCount") != null) {
					output += " Match Count: "+ message.get("MatchCount").toString()+"\n";
					gui.addEventMessage(output);
					gui.addEventMessage(message);
				}
				else if(message.get("NonEvent") !=null) {
					output += " Non Event: "+ message.get("NonEvent").toString()+"\n";
					gui.addNonEventMessage(output);
					gui.addNonEventMessage(message);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//A method to put this to sleep if we ever need to
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Change the boolean, use this to end the thread.
	public void setKeepGoing(boolean keepGoing) {
		this.keepGoing = keepGoing;
	}
	public boolean getKeepGoing() {
		return keepGoing;
	}
}
