package it.synthema;

import junit.framework.TestCase;

public class TranscriptionWordListTest extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	public void testGetCharacterNumber() {
		
		TranscriptionWordList wl = new TranscriptionWordList();
		wl.add(new TranscriptedWord("ciao", 10l, 20l));
		wl.add(new TranscriptedWord("come",22l,30l));
		wl.add(new TranscriptedWord("va",32l ,35l ));
		assertTrue(wl.getCharacterNumber()==12);
	}

}
