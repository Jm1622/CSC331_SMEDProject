package edu.southalabama.csc331.smed;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageProcessor {
	//private variables, an arrayList of all keywords
	private ArrayList<String> keyWords= new ArrayList<String>();
	//Constructor to fill keywords
	public MessageProcessor(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}
	public Message processMessage(Message message) {
		int matchCount = 0;
		//Count matches of keywords
		for(String keyWord : keyWords) {
			if(keyWord != null && !keyWord.isEmpty()) {
				//Some regex which finds each occurence of the keyword plus a space so it is an independent word
				Pattern p = Pattern.compile(" "+keyWord+" ", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(message.getText());
				while(m.find()) {
					matchCount++;
				}
			}
		}
		message.setMatchCount(matchCount);
		return message;
	}
	
}
