package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SrtBuilderTest {

	@Test
	public void testBuild() {
		List<TranscriptedWord> st = gettestlist();

		SrtBuilder builder = new SrtBuilder(0,50,40l);
		System.out.println(builder.build(st));
	}
	
	@Test
	public void testmerge() {
		
	}

	@Test
	public void testSrtBuilder() {
		fail("Not yet implemented");
	}

	private List<TranscriptedWord> gettestlist() {
		List<TranscriptedWord> st = new ArrayList<TranscriptedWord>();

		st.add(new TranscriptedWord("Hi", 0l, 10l));
		st.add(new TranscriptedWord("how", 12l, 20l));
		st.add(new TranscriptedWord("are", 22l, 31l));
		st.add(new TranscriptedWord("You?", 33l, 40l));

		st.add(new TranscriptedWord("Fine", 60l, 70l));
		st.add(new TranscriptedWord("Thanks", 72l, 80l));
		return st;
	}
	
	
	@Test
	public void testGetCharacterNumber() {

		List<TranscriptedWord> wl = new ArrayList<TranscriptedWord>();
		wl.add(new TranscriptedWord("ciao", 10l, 20l));
		wl.add(new TranscriptedWord("come",22l,30l));
		wl.add(new TranscriptedWord("va",32l ,35l ));
		assertTrue(SrtBuilder.getCharacterNumber(wl)==12);
	}
}
