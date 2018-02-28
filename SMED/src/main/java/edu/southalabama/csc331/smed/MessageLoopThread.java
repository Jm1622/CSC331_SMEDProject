package edu.southalabama.csc331.smed;

import javax.json.JsonObject;


public class MessageLoopThread implements Runnable {
	Source source;
	boolean keepGoing;
	GUI gui;
	MessageProcessor processor;
	public MessageLoopThread(Source source, boolean keepGoing, GUI gui, MessageProcessor processor) throws InterruptedException {
		this.source = source;
		this.keepGoing = keepGoing;
		this.gui = gui;
		this.processor = processor;
	}
	public void run() {
		while(keepGoing) {
			try {
				JsonObject message = processor.processMessage(source.getMessage());
				String output = "";
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
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setKeepGoing(boolean keepGoing) {
		this.keepGoing = keepGoing;
	}
}
