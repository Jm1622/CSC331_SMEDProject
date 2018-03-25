package edu.southalabama.csc331.smed;

public class MessageLoopThread implements Runnable {
	//Private variables the the Message Loop Thread, a thread that just continuously gets messages, uses
	private Source f_source;
	private boolean f_keepGoing;
	private GUI f_gui;
	private MessageProcessor f_processor;
	private SMEDController f_controller;
	//Constructor that adds a value to all four of these variables
	// TODO replace gui with a controller class
	public MessageLoopThread(Source source, boolean keepGoing, MessageProcessor processor, GUI gui, SMEDController controller) throws InterruptedException {
		this.f_source = source;
		this.f_keepGoing = keepGoing;
		this.f_processor = processor;
		this.f_gui = gui;
		this.f_controller = controller;
	}
	public void run() {
		//This overrides runnables run method, this is what runs on the thread
		while(f_keepGoing) {
			//While that boolean is true
			try {
				//process a message from the source
				Message message = f_processor.processMessage(f_source.getMessage());
				//Add to the appropriate box dependent on this last attribute
				if(message.getMatchCount() > 0) {
					f_controller.addEventMessage(message);
					f_gui.addEventMessage(message.toString());
				}
				else{
					f_controller.addEventMessage(message);
					f_gui.addNonEventMessage(message.toString());
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
		this.f_keepGoing = keepGoing;
	}
	public boolean getKeepGoing() {
		return f_keepGoing;
	}
}
