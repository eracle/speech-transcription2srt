package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SrtTest {

	
	
	@Test
	public void testSrtListOfTranscriptedWord_ciao_come_va() {
		List<TranscriptedWord> list = new ArrayList<>();
		
		list.add(new TranscriptedWord("ciao", 10l, 20l));
		list.add(new TranscriptedWord("come", 22l, 30l));
		list.add(new TranscriptedWord("va", 32l, 35l));

		list.add(new TranscriptedWord("bene", 52l, 55l));
		list.add(new TranscriptedWord("grazie", 57l, 60l));
		list.add(new TranscriptedWord("e", 62l, 65l));
		list.add(new TranscriptedWord("tu", 67l, 85l));
		Srt srt = new Srt(list);
		List<SrtLine> lines = srt.getLines();
		assertTrue(lines.size()==1);
		assertNotNull(srt.toString());
		
		//System.out.println(srt.toString());
		
		assertNotNull(lines.get(0));
		//older version than 2.0
		//assertTrue(lines.get(0).getFirstLine().equals("ciao come va bene"));
		//assertTrue(lines.get(0).getSecondLine().equals("grazie e tu"));
		
		assertTrue(lines.get(0).getFirstLine().equals("ciao come va bene grazie e tu"));
		assertNull(lines.get(0).getSecondLine());
	
	}
		
	
	@Test
	public void testSrtListOfTranscriptedWord_ciao(){
		List<TranscriptedWord> list2 = new ArrayList<>();
		
		list2.add(new TranscriptedWord("ciao", 10l, 20l));
		Srt srt2 = new Srt(list2);
		assertNotNull(srt2);
		List<SrtLine> lines2 = srt2.getLines();
		assertTrue(lines2.size()==1);
		assertNotNull(lines2.get(0));
		assertTrue(lines2.get(0).getFirstLine().equals("ciao"));
		assertNull(lines2.get(0).getSecondLine());
		//version older than 2.0
		/*
		assertNotNull(lines2.get(0).getSecondLine());
		assertTrue(lines2.get(0).getSecondLine().equals(""));
		*/
		assertTrue(lines2.get(0).getStart_time()==10);
		assertTrue(lines2.get(0).getEnd_time()==20);
	}
	
	
	@Test
	public void testSrtArrayListOfSrtLine() {
		ArrayList<SrtLine> lines = new ArrayList<>();
		
		Srt srt = new Srt(lines);
		assertNotNull(srt);
		assertNotNull(srt.toString());
		assertTrue(srt.toString().length()==0);
		

	}


	@Test
	public void testToString() {
		String str = get_single_line_mock().toString();
		assertNotNull(str);
		assertFalse(str.length()==0);
		//System.out.println(get_single_line_mock());
	}
	
	private Srt get_single_line_mock(){
		ArrayList<SrtLine> a = new ArrayList<SrtLine>();
		
		a.add(new SrtLine("This subtitle is a","test .", 0l, 1000l));
		
		return new Srt(a);
		
	}
	
	

}
