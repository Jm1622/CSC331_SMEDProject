package edu.southalabama.csc331.smed;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    		Source twitterSource = new Source("Twitter");
    		twitterSource.startMessageGetting();
    		while(true) {
    			System.out.println(twitterSource.getMessage());
    		}
    		
    }
}
