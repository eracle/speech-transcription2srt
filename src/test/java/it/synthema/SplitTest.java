package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SplitTest {

	private List<TranscriptedWord> get_ciao_come_va_List() {

		List<TranscriptedWord> wl = new ArrayList<TranscriptedWord>();
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

	private List<TranscriptedWord> get_empty_List() {
		List<TranscriptedWord> wl = new ArrayList<TranscriptedWord>();
		return wl;
	}
	
	private List<TranscriptedWord> get_single_element_List() {
		List<TranscriptedWord> wl = new ArrayList<TranscriptedWord>();
		wl.add(new TranscriptedWord("ciao", 0l, 20l));
		return wl;
	}
	
	@Test
	public void testSplit() {

		List<TranscriptedWord> wl = this.get_ciao_come_va_List();

		Split s = new Split(wl);

		assertNotNull(s);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLeft_empty_case() {
		List<TranscriptedWord> wl = this.get_empty_List();

		Split s = new Split(wl);

		assertNotNull(s);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLeft_single_case() {
		List<TranscriptedWord> wl = this.get_single_element_List();

		Split s = new Split(wl);

		assertNotNull(s);
	}
	
	
	

	@Test
	public void testGetLeft() {

		List<TranscriptedWord> wl = this.get_ciao_come_va_List();

		Split s = new Split(wl);

		List<TranscriptedWord> left = s.getLeft();

		assertTrue(left.get(0).word.equals("ciao"));
		assertTrue(left.get(1).word.equals("come"));
		assertTrue(left.get(2).word.equals("va"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetRight_empty_case() {
		
		List<TranscriptedWord> wl = this.get_empty_List();

		Split s = new Split(wl);

		List<TranscriptedWord> left = s.getLeft();

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetRight_single_case() {
		
		List<TranscriptedWord> wl = this.get_single_element_List();

		Split s = new Split(wl);

		List<TranscriptedWord> left = s.getLeft();

		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetMaxSilentInterval_empty_case() {
		List<TranscriptedWord> wl = this.get_empty_List();

		Split s = new Split(wl);

		SilentInterval si = s.getMaxSilentInterval();
	}
	
	@Test
	public void testGetRight() {
		List<TranscriptedWord> wl = this.get_ciao_come_va_List();

		Split s = new Split(wl);

		List<TranscriptedWord> left = s.getRight();
		assertTrue("Size not 5 but: "+left.size(),left.size()==5);
		assertTrue(left.get(0).word.equals("bene"));
		assertTrue(left.get(1).word.equals("grazie"));
		assertTrue(left.get(2).word.equals("e"));
		assertTrue(left.get(3).word.equals("tu"));

		assertTrue(left.get(4).word.equals("bene"));
	}

	@Test
	public void testGetMaxSilentInterval() {

		List<TranscriptedWord> wl = this.get_ciao_come_va_List();

		Split s = new Split(wl);

		SilentInterval si = s.getMaxSilentInterval();
		assertTrue(si.getStart_time() == 35);
		assertTrue(si.getEnd_time() == 52);
		assertTrue(si.getTimeInterval() == 17);
	}

}
