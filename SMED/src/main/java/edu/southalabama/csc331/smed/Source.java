package edu.southalabama.csc331.smed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

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
	private ClientBuilder f_builder;
	private Authentication f_hosebirdAuth;
	private Hosts f_hosebirdHosts;
	//The place we store our messages
	private BlockingQueue<String> f_msgQueue = new LinkedBlockingQueue<String>(100000);
	private String redditUrl = "https://www.reddit.com/r/all/new.json";
	//What type of source this is
	private String f_type;
	public Source(String type) throws InterruptedException {
		this.f_type = type;
		if(type.equals("Twitter")) {
			//Code stolen from HBC's github, this connects to the twitter API with our applications keys
			f_hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
			f_hosebirdAuth = new OAuth1("eGUKWj7j0rxDwrJdt8NfjK3GW", "WHuBjyam9VMrHOUblwYdsPD5xQvDzNIHa4bZcqZOXRbz8yIVBX", "966732011628003330-p7rnhnuPpJFiqwYs5m3dJbZl71gzv0x", "RmCVXpBJihIp8xFfsU7jKCqBNuXFep3snOEhzHwhGZruv");
			StatusesSampleEndpoint sampleStatuses = new StatusesSampleEndpoint();
			f_builder = new ClientBuilder()
					  .hosts(f_hosebirdHosts)
					  .authentication(f_hosebirdAuth)
					  .endpoint(sampleStatuses)
					  .processor(new StringDelimitedProcessor(f_msgQueue));
		}
		f_msgQueue = new LinkedBlockingQueue<String>(100000); 
	}
	public void startMessageGetting() throws InterruptedException {
		if(f_type.equals("Twitter")) {
			//If we were doing twitter connect to the client which starts filling our message queue with json like strings
			f_msgQueue = new LinkedBlockingQueue<String>(100000);
			f_hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
			f_hosebirdAuth = new OAuth1("eGUKWj7j0rxDwrJdt8NfjK3GW", "WHuBjyam9VMrHOUblwYdsPD5xQvDzNIHa4bZcqZOXRbz8yIVBX", "966732011628003330-p7rnhnuPpJFiqwYs5m3dJbZl71gzv0x", "RmCVXpBJihIp8xFfsU7jKCqBNuXFep3snOEhzHwhGZruv");
			StatusesSampleEndpoint sampleStatuses = new StatusesSampleEndpoint();
			f_builder = new ClientBuilder()
					  .hosts(f_hosebirdHosts)
					  .authentication(f_hosebirdAuth)
					  .endpoint(sampleStatuses)
					  .processor(new StringDelimitedProcessor(f_msgQueue));
			f_hoseBirdClient = f_builder.build();
			f_hoseBirdClient.connect();
		}
		//if we are doing reddit there is no required set up so do nothing
	}
	public void stopMessageGetting() {
		if(f_type.equals("Twitter")) {
			//Stop the connection
			f_hoseBirdClient.stop();
			f_hoseBirdClient = null;
		}
		//Remove all excess items when connection ended
		f_msgQueue.clear();
	}
	public Message getMessage() throws InterruptedException, IOException {
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
			if(message.get("coordinates") != null) {
				if(!message.get("coordinates").toString().equals("null")) {
					JsonObject place = message.getJsonObject("corrdinates");
					//get coordinates
					String coords = place.get("coordinates").toString();
					//due to googles formatting which takes latitude first we need to reverse it since twitter gives longitude first
					String[] list = coords.split("\\s*,\\s*");
					coords = list[1]+","+list[0];
					outputMessage.setLocation(coords);
				}
			}
			if(message.get("text") != null) {
				outputMessage.setText(message.get("text").toString());
			}
			outputMessage.setMessageType("Tweet");
		}
		if(f_type.equals("Reddit")) {
			//Known problem here: since it is pulling the 25 most recent posts if there are not 25 new posts when it pulls
			//It will repeat data
			//If reddit check if the queue is empty
			if(f_msgQueue.isEmpty()) {
				//if so open a new get connection and read in the response
				URL url = new URL(this.redditUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "CSC 331 Smed Project. URL: https://github.com/Jm1622/CSC331_SMEDProject");
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//Get the selftext and author from the data and put it in message queue
				String responseText = response.toString();
				JsonReader jsonReader = Json.createReader(new StringReader(responseText));
				message = jsonReader.readObject();
				JsonObject data = message.getJsonObject("data");
				JsonArray children = data.getJsonArray("children");
				for(int i=0; i< children.size(); i++) {
					JsonObject child = children.getJsonObject(i);
					JsonObject childData = child.getJsonObject("data");
					String queueString = "";
					if(childData.getString("selftext") != null && !childData.getString("selftext").equals("")) {
						queueString += childData.getString("selftext");
						queueString += "`"+childData.getString("author");
						f_msgQueue.put(queueString);
					}
				}
			}
			//Take a message, separate it and set an output message
			String msg = f_msgQueue.take();
			List<String> messageAsList = Arrays.asList(msg.split("`"));
			outputMessage.setText(messageAsList.get(0));
			outputMessage.setUser(messageAsList.get(1));
			outputMessage.setMessageType("Reddit");
		}
		return outputMessage;
	}
	public String getType() {
		return f_type;
	}
}
