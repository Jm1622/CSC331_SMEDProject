package edu.southalabama.csc331.smed;

import javax.swing.JFrame;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    		//Builds gui and then sets it to be visible
	    	GUI eventDS = new GUI();
		eventDS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eventDS.setSize(800,800);
		eventDS.setVisible(true);
		//eventDS.setResizable(false);
		
    }
}
