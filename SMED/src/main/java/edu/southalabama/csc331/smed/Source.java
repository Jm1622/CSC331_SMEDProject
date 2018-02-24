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
	private Client hoseBirdClient;
	//The place we store our messages
	private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
	//What type of source this is
	private String type;
	public Source(String type) throws InterruptedException {
		this.type = type;
		if(type.equals("Twitter")) {
			//Code stolen from HBC's github, this connects to the twitter API with our applications keys
			Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
			Authentication hosebirdAuth = new OAuth1("eGUKWj7j0rxDwrJdt8NfjK3GW", "WHuBjyam9VMrHOUblwYdsPD5xQvDzNIHa4bZcqZOXRbz8yIVBX", "966732011628003330-p7rnhnuPpJFiqwYs5m3dJbZl71gzv0x", "RmCVXpBJihIp8xFfsU7jKCqBNuXFep3snOEhzHwhGZruv");
			StatusesSampleEndpoint sampleStatuses = new StatusesSampleEndpoint();
			ClientBuilder builder = new ClientBuilder()
					  .hosts(hosebirdHosts)
					  .authentication(hosebirdAuth)
					  .endpoint(sampleStatuses)
					  .processor(new StringDelimitedProcessor(msgQueue));
			hoseBirdClient = builder.build();
		}
	}
	public void startMessageGetting() throws InterruptedException {
		if(type.equals("Twitter")) {
			//If we were doing twitter connect to the client which starts filling our message queue with json like strings
			hoseBirdClient.connect();
		}
	}
	public void stopMessageGetting() {
		if(type.equals("Twitter")) {
			//Stop the connection
			hoseBirdClient.stop();
			//Remove all excess items when connection ended
			msgQueue.clear();
		}
	}
	public JsonObject getMessage() throws InterruptedException {
		//Take a message from the queue and create a JsonObject out of it
		String msg = msgQueue.take();
		JsonReader jsonReader = Json.createReader(new StringReader(msg));
		JsonObject message = jsonReader.readObject();
		while(message.containsKey("delete")) {
			msg = msgQueue.take();
			jsonReader = Json.createReader(new StringReader(msg));
			message = jsonReader.readObject();
		}
		return message;
	}
}
