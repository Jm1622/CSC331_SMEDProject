package edu.southalabama.csc331.smed;

import javax.swing.JFrame;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    		//Builds gui and then sets it to be visible
    		JFrame frame = new JFrame("Social Media Event Detection");
	    	GUI eventDS = new GUI();
	    	frame.setSize(1153, 720);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(eventDS);
	    frame.setVisible(true);
		
    }
}
