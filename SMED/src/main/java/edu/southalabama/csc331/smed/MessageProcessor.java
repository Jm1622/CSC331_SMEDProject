package edu.southalabama.csc331.smed;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class MessageProcessor {
	private String[] keyWords= new String[10];
	public MessageProcessor(String[] keyWords) {
		for(int i =0; i<keyWords.length; i++) {
			if(i<this.keyWords.length) {
				this.keyWords[i] = keyWords[i];
			}
			else {
				System.out.println("Too many keywords were provided, discarded "+keyWords[i] );
			}
		}
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
			if(keyWord != null && !keyWord.isEmpty() && !message.containsKey("delete") && message.getString("text").contains(keyWord)) {
				matchCount ++;
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
