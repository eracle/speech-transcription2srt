package it.synthema;

import static org.junit.Assert.*;

import org.junit.Test;

public class SrtLineTest {

	@Test
	public void testToString() {
		String first = "First line of test";
		String second = "Second line of test";
		SrtLine line = new SrtLine(first, second, 1400356, 1400560);
		String str = line.toString();
		String[] split = str.split("\n");
		
		/*
		for (String string : split) {
			System.out.println(string);
		}
		 */
		
		assertTrue(split[1].equals(first));
		assertTrue(split[2].equals(second));
		
		split = split[0].split(" --> ");
		
		assertTrue(split[0].equals("00:23:20,356"));
		assertTrue(split[1].equals("00:23:20,560"));
		
		/*
		for (String string : split) {
			System.out.println(string);
		}
		*/
		//System.out.println(line);
	}

}
