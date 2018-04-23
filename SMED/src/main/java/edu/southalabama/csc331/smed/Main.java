package edu.southalabama.csc331.smed;

import javax.swing.JFrame;

public class Main 
{
	private static GUI f_eventDS;
    public static void main( String[] args ) throws InterruptedException
    {
    		//Builds gui and then sets it to be visible
    		JFrame frame = new JFrame("Social Media Event Detection");
	    	f_eventDS = new GUI();
	    	frame.setSize(1200, 1000);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(f_eventDS);
	    frame.setVisible(true);
		
    }
    public static GUI getGui() {
    		return f_eventDS;
    }
}
