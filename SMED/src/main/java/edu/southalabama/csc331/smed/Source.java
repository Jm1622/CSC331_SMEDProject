package edu.southalabama.csc331.smed;

import java.io.StringReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class Source {
	//The twitter client
	private Client f_hoseBirdClient;
	//The place we store our messages
	private BlockingQueue<String> f_msgQueue = new LinkedBlockingQueue<String>(100000);
	//What type of source this is
	private String f_type;
	public Source(String type) throws InterruptedException {
		this.f_type = type;
		if(type.equals("Twitter")) {
			//Code stolen from HBC's github, this connects to the twitter API with our applications keys
			Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
			Authentication hosebirdAuth = new OAuth1("eGUKWj7j0rxDwrJdt8NfjK3GW", "WHuBjyam9VMrHOUblwYdsPD5xQvDzNIHa4bZcqZOXRbz8yIVBX", "966732011628003330-p7rnhnuPpJFiqwYs5m3dJbZl71gzv0x", "RmCVXpBJihIp8xFfsU7jKCqBNuXFep3snOEhzHwhGZruv");
			StatusesSampleEndpoint sampleStatuses = new StatusesSampleEndpoint();
			ClientBuilder builder = new ClientBuilder()
					  .hosts(hosebirdHosts)
					  .authentication(hosebirdAuth)
					  .endpoint(sampleStatuses)
					  .processor(new StringDelimitedProcessor(f_msgQueue));
			f_hoseBirdClient = builder.build();
		}
		if(type.equals("YouTube")) {
			
		}
	}
	public void startMessageGetting() throws InterruptedException {
		if(f_type.equals("Twitter")) {
			//If we were doing twitter connect to the client which starts filling our message queue with json like strings
			f_hoseBirdClient.connect();
		}
	}
	public void stopMessageGetting() {
		if(f_type.equals("Twitter")) {
			//Stop the connection
			f_hoseBirdClient.stop();
			//Remove all excess items when connection ended
			f_msgQueue.clear();
		}
	}
	public Message getMessage() throws InterruptedException {
		//If no source matches return a null message
		JsonObject message = null;
		Message outputMessage = new Message();
		if(f_type.equals("Twitter")) {
			//Take a message from the queue and create a JsonObject out of it
			String msg = f_msgQueue.take();
			//Create a json reader out of a string reader
			JsonReader jsonReader = Json.createReader(new StringReader(msg));
			//Read into a json
			message = jsonReader.readObject();
			//If it is a deleted message
			while(message.containsKey("delete")) {
				msg = f_msgQueue.take();
				jsonReader = Json.createReader(new StringReader(msg));
				message = jsonReader.readObject();
			}
			if(message.get("user") != null) {
				JsonObject user = message.getJsonObject("user");
				if(user.get("screen_name") != null) {
					outputMessage.setUser(user.get("screen_name").toString());
				}
			}
			if(message.get("place") != null) {
				if(!message.get("place").toString().equals("null")) {
					JsonObject place = message.getJsonObject("place");
					outputMessage.setLocation(place.get("full_name").toString());
				}
			}
			if(message.get("text") != null) {
				outputMessage.setText(message.get("text").toString());
			}
			outputMessage.setMessageType("Tweet");
		}
		return outputMessage;
	}
	public String getType() {
		return f_type;
	}
}
