package edu.southalabama.csc331.smed;

import java.util.ArrayList;
import java.util.Arrays;

import javax.json.JsonObject;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	ArrayList<String> keyWords = new ArrayList(Arrays.asList("The", "a", "Please"));
    		MessageProcessor processor = new MessageProcessor(keyWords);
    		Source twitterSource = new Source("Twitter");
    		twitterSource.startMessageGetting();
    		while(true) {
    			JsonObject message = processor.processMessage(twitterSource.getMessage());
    			System.out.println(message.get("place"));
    			//System.out.println(twitterSource.getMessage());
    		}
    }
}
