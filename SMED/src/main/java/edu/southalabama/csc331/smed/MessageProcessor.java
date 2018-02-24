package edu.southalabama.csc331.smed;


import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class MessageProcessor {
	private ArrayList<String> keyWords= new ArrayList<String>();
	public MessageProcessor(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}
	public JsonObject processMessage(JsonObject message) {
		//Create a builder with all the current information because JsonObject is immutable
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Entry<String, JsonValue> entry : message.entrySet()) {
	        job.add(entry.getKey(), entry.getValue());
	    }
		int matchCount = 0;
		//Count matches of keywords
		for(String keyWord : keyWords) {
			if(keyWord != null && !keyWord.isEmpty()) {
				//Some regex which finds each occurence of the keyword plus a space so it is an independent word
				Pattern p = Pattern.compile(keyWord+" ");
				Matcher m = p.matcher(message.getString("text").toString());
				while(m.find()) {
					matchCount++;
				}
			}
		}
		//If we have a matchCount add it to the job
		if(matchCount != 0) {
			job.add("MatchCount", matchCount);
		}
		//if not mark it as nonEvent
		else {
			job.add("NonEvent", "True");
		}
		//return this message
		return job.build();
	}
	
}
