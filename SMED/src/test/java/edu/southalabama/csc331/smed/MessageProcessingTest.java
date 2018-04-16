package edu.southalabama.csc331.smed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MessageProcessingTest {

	@Test
	void test() throws InterruptedException {
		ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList("A", "The", "Hello", "Trump", "Gun", "President", "Bomb", "War", "America", "Bill"));
		MessageProcessor processor = new MessageProcessor(keyWords);
		Source f_source = new Source("Twitter");
		f_source.startMessageGetting();
		Message message;
		long startTime, endTime;
		boolean passed = true;
		for(int i =0; i<50; i++) {
			startTime=System.nanoTime();
			message = f_source.getMessage();
			processor.processMessage(message);
			endTime = System.nanoTime();
			if((endTime-startTime)/1000000000 > 15) {
				passed = false;
			}
			System.out.println(i+" "+message.toString()+" Start Time: "+startTime+" End Time: "+endTime+" Difference: "+(endTime-startTime)+" Passed: "+passed);
		}
		assertTrue(passed);
	}

}
