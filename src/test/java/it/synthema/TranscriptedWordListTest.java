package it.synthema;

import static org.junit.Assert.*;

import org.junit.Test;

public class TranscriptedWordListTest {

	@Test
	public void testGetCharacterNumber() {

		TranscriptedWordList wl = new TranscriptedWordList();
		wl.add(new TranscriptedWord("ciao", 10l, 20l));
		wl.add(new TranscriptedWord("come",22l,30l));
		wl.add(new TranscriptedWord("va",32l ,35l ));
		assertTrue(wl.getCharacterNumber()==12);
	}

}
