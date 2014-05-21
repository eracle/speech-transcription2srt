package it.synthema;

import static org.junit.Assert.*;

import org.junit.Test;

public class SplitTest {

	private TranscriptedWordList getList() {

		TranscriptedWordList wl = new TranscriptedWordList();
		wl.add(new TranscriptedWord("ciao", 10l, 20l));
		wl.add(new TranscriptedWord("come", 22l, 30l));
		wl.add(new TranscriptedWord("va", 32l, 35l));

		wl.add(new TranscriptedWord("bene", 52l, 55l));
		wl.add(new TranscriptedWord("grazie", 57l, 60l));
		wl.add(new TranscriptedWord("e", 62l, 65l));
		wl.add(new TranscriptedWord("tu", 67l, 85l));

		wl.add(new TranscriptedWord("bene", 90l, 95l));

		return wl;
	}

	@Test
	public void testSplit() {

		TranscriptedWordList wl = this.getList();

		Split s = new Split(wl);

		assertNotNull(s);
	}

	@Test
	public void testGetLeft() {

		TranscriptedWordList wl = this.getList();

		Split s = new Split(wl);

		TranscriptedWordList left = s.getLeft();

		assertTrue(left.get(0).word.equals("ciao"));
		assertTrue(left.get(1).word.equals("come"));
		assertTrue(left.get(2).word.equals("va"));
	}

	@Test
	public void testGetRight() {
		TranscriptedWordList wl = this.getList();

		Split s = new Split(wl);

		TranscriptedWordList left = s.getRight();

		assertTrue(left.get(0).word.equals("bene"));
		assertTrue(left.get(1).word.equals("grazie"));
		assertTrue(left.get(2).word.equals("e"));
		assertTrue(left.get(3).word.equals("tu"));

		assertTrue(left.get(4).word.equals("bene"));
	}

	@Test
	public void testGetMaxSilentInterval() {

		TranscriptedWordList wl = this.getList();

		Split s = new Split(wl);

		SilentInterval si = s.getMaxSilentInterval();
		assertTrue(si.getStart_time() == 35);
		assertTrue(si.getEnd_time() == 52);
		assertTrue(si.getTimeInterval() == 17);
	}

}
