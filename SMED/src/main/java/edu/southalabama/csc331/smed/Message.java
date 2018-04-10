package edu.southalabama.csc331.smed;

public class Message {
	private String f_text;
	private String f_user;
	private String f_location;
	private String f_messageType;
	private int f_matchCount;
	public Message() {
		f_text = "";
		f_user = "";
		f_location = "";
		f_matchCount = 0;
	}
	//Do not instantiate with a matchCount, matchCount will only ever be set
	public Message(String text, String user, String location) {
		this.f_text = text;
		this.f_user = user;
		this.f_location = location;
		this.f_matchCount = 0;
	}
	public String getMessageType() {
		return f_messageType;
	}
	public void setMessageType(String messageType) {
		this.f_messageType = messageType;
	}
	public String getText(){
		return f_text;
	}
	public String getUser(){
		return f_user;
	}
	public String getLocation(){
		return f_location;
	}
	public int getMatchCount() {
		return f_matchCount;
	}
	public void setText(String text) {
		this.f_text = text;
	}
	public void setUser(String user) {
		this.f_user = user;
	}
	public void setLocation(String location) {
		this.f_location = location;
	}
	public void setMatchCount(int matchCount) {
		this.f_matchCount = matchCount;
	}
	public String toString() {
		String output ="";
		output += f_messageType+": " + f_text;
		if(!f_user.equals("")) {
			output +=" User name: "+f_user;
		}
		if(!f_location.equals("")) {
			output +=" Location: "+f_location;
		}
		if(f_matchCount > 0) {
			output += " Match Count: "+ f_matchCount+"\n";
		}
		else{
			output += " Non Event: True"+"\n";
		}
		return output;
	}
}
