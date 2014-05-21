package it.synthema;

import junit.framework.TestCase;

public class SplitTest extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	private TranscriptionWordList getList(){

		TranscriptionWordList wl = new TranscriptionWordList();
		wl.add(new TranscriptedWord("ciao", 10l, 20l));
		wl.add(new TranscriptedWord("come",22l,30l));
		wl.add(new TranscriptedWord("va",32l ,35l ));
		

		wl.add(new TranscriptedWord("bene",52l ,55l ));
		wl.add(new TranscriptedWord("grazie",57l ,60l ));
		wl.add(new TranscriptedWord("e",62l ,65l ));
		wl.add(new TranscriptedWord("tu",67l ,85l ));
		
		wl.add(new TranscriptedWord("bene",90l ,95l ));
		
		return wl;
	}
	
	public void testSplit() {
		
		TranscriptionWordList wl = this.getList();
		
		Split s = new Split(wl);
		
		assertNotNull(s);
		
	}

	public void testGetLeft() {

		TranscriptionWordList wl = this.getList();
		
		Split s = new Split(wl);
		
		TranscriptionWordList left = s.getLeft();
		
		
		assertTrue(left.get(0).word.equals("ciao"));
		assertTrue(left.get(1).word.equals("come"));
		assertTrue(left.get(2).word.equals("va"));
	}

	public void testGetRight() {
TranscriptionWordList wl = this.getList();
		
		Split s = new Split(wl);
		
		TranscriptionWordList left = s.getRight();
		

		assertTrue(left.get(0).word.equals("bene"));
		assertTrue(left.get(1).word.equals("grazie"));
		assertTrue(left.get(2).word.equals("e"));
		assertTrue(left.get(3).word.equals("tu"));
		
		assertTrue(left.get(4).word.equals("bene"));
		
	}

	public void testGetMaxSilentInterval() {
		
		TranscriptionWordList wl = this.getList();
		
		Split s = new Split(wl);
		
		SilentInterval si = s.getMaxSilentInterval();
		assertTrue(si.getStart_time()==35);
		assertTrue(si.getEnd_time()==52);
		assertTrue(si.getTimeInterval()==17);
	}

}
