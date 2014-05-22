package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SrtTest {

	@Test
	public void testSrtListOfTranscriptedWord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSrtArrayListOfSrtLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLines() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		String str = get_single_line_mock().toString();
		assertNotNull(str);
		assertFalse(str.length()==0);
		System.out.println(get_single_line_mock());
	}
	
	private Srt get_single_line_mock(){
		ArrayList<SrtLine> a = new ArrayList<SrtLine>();
		
		a.add(new SrtLine("This subtitle is a","test.", 0l, 1000l));
		
		return new Srt(a);
		
	}

}
