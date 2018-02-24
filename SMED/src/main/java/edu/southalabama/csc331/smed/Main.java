package edu.southalabama.csc331.smed;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    		String keyWords[] = {"A", "The", "Please"};
    		MessageProcessor processor = new MessageProcessor(keyWords);
    		Source twitterSource = new Source("Twitter");
    		twitterSource.startMessageGetting();
    		while(true) {
    			System.out.println(processor.processMessage(twitterSource.getMessage()).toString());
    			//System.out.println(twitterSource.getMessage());
    		}
    }
}
