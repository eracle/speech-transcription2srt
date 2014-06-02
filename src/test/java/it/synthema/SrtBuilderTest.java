package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SrtBuilderTest {

	@Test
	public void testBuild() {
		List<TranscriptedWord> st = gettestlist();

		SrtBuilder builder = new SrtBuilder(10,50,40l);
		assertNotNull(builder);
		Srt out = builder.build(st);
		assertNotNull(out);
		//System.out.println(out);
	}
	
	@Test
	public void testBuild_empty_case() {
		fail("not yet impl");
	}
	
	
	@Test
	public void testmerge_iterative_case() {

		
		ArrayList<SrtLine> lines1 = new ArrayList<SrtLine>();
		lines1.add(new SrtLine("ciao bello", "come va?", 1l, 10l));
		lines1.add(new SrtLine("benissimo e tu?", "", 23l, 40l));
		
		Srt srt1 = new Srt(lines1);
		
		
		ArrayList<SrtLine> lines2 = new ArrayList<SrtLine>();
		lines2.add(new SrtLine("Magnificamente, oggi è una ","bellissima giornata",90l,100l));
		lines2.add(new SrtLine("Infatti stiamo andando al ", "mare", 110l,220l));
		
		Srt srt2 = new Srt(lines2);
		

		SrtBuilder builder = new SrtBuilder(100,100,20);
		
		Srt result = builder.merge(srt1, srt2);
		
		assertTrue(result.getLines().size()==4);
		
		assertTrue(result.getLines().get(1).getEnd_time()>0);
		
		assertTrue(result.getLines().get(2).getStart_time()>result.getLines().get(1).getEnd_time());
		
		assertNotNull(result.getLines().get(0));
		assertNotNull(result.getLines().get(1));
		assertNotNull(result.getLines().get(2));
		assertNotNull(result.getLines().get(3));

		assertTrue(result.getLines().get(1).getEnd_time()==43);

		
		SrtBuilder builder2 = new SrtBuilder(100,100,66);
		Srt res2 = builder2.merge(srt1,srt2);
		assertTrue(res2.getLines().get(1).getEnd_time()==88);
		
	//	System.out.println(result);
	}


	@Test
	public void testadjustLastLine_next_srtline_is_far_away(){
		SrtLine last_line = new SrtLine("Hi","how are you",0,10);
		
		
		long next_line_start_time = 100;
		
		long max_srt_line_duration2=20;
		
		last_line = SrtBuilder.adjustLastLine(last_line, next_line_start_time , max_srt_line_duration2);
		assertTrue(last_line.getEnd_time()==20);
		assertFalse(last_line.getEnd_time()==10);
		assertFalse(last_line.getEnd_time()==100);
	}
			
	@Test
	public void testadjustLastLine_following_srt_line_is_close(){
		SrtLine last_line = new SrtLine("Hi","how are you",0,10);
		
		
		long next_line_start_time = 17;
		
		long max_srt_line_duration2=20;
		
		last_line = SrtBuilder.adjustLastLine(last_line, next_line_start_time , max_srt_line_duration2);
		assertTrue(last_line.getEnd_time()==17);
		assertFalse(last_line.getEnd_time()==10);
		assertFalse(last_line.getEnd_time()==100);
	}
	
	@Test
	public void testmerge_base_case() {
		fail("Not yet implemented");
	}

	@Test
	public void testmerge_empty_case() {

		fail("Not yet implemented");
	}
	
	@Test
	public void testSrtBuilder() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSrtBuilder_emptycase() {
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
		

		st.add(new TranscriptedWord("I", 110l, 120l));
		st.add(new TranscriptedWord("am", 125l, 160l));
		st.add(new TranscriptedWord("eating", 170l, 190l));
		
		
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
