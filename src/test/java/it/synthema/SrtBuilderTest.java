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
	

	@Test(expected=IllegalArgumentException.class)
	public void testBuild_empty_case() {
		List<TranscriptedWord> st = new ArrayList<>();

		SrtBuilder builder = new SrtBuilder(10,50,40l);
		Srt out = builder.build(st);

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
		
		double max_duration_factor = 2.0;
		SrtBuilder builder = new SrtBuilder(100,100,max_duration_factor);
		
		Srt result = builder.merge(srt1, srt2);
		
		assertTrue(result.getLines().size()==4);
		
		assertTrue(result.getLines().get(1).getEnd_time()>0);
		
		assertTrue(result.getLines().get(2).getStart_time()>result.getLines().get(1).getEnd_time());
		
		assertNotNull(result.getLines().get(0));
		assertNotNull(result.getLines().get(1));
		assertNotNull(result.getLines().get(2));
		assertNotNull(result.getLines().get(3));

		assertTrue(result.getLines().get(1).getEnd_time()==57);

		
		SrtBuilder builder2 = new SrtBuilder(100,100,3.0);
		Srt res2 = builder2.merge(srt1,srt2);
		assertTrue(res2.getLines().get(1).getEnd_time()==88);
		
	//	System.out.println(result);
	}


	@Test
	public void testadjustLastLine_next_srtline_is_far_away(){
		SrtLine last_line = new SrtLine("Hi","how are you",0,10);
		
		
		long next_line_start_time = 15;
		
		long max_srt_line_factor= 2;
		
		last_line = SrtBuilder.adjustLastLine(last_line, next_line_start_time , max_srt_line_factor);
		assertTrue(last_line.getEnd_time()==15);
		assertFalse(last_line.getEnd_time()==10);
		assertFalse(last_line.getEnd_time()==100);
	}
			
	@Test
	public void testadjustLastLine_following_srt_line_is_close(){
		SrtLine last_line = new SrtLine("Hi","how are you",0,10);
		
		
		long next_line_start_time = 17;
		
		double max_srt_line_duration_factor= 1.5;
		
		last_line = SrtBuilder.adjustLastLine(last_line, next_line_start_time , max_srt_line_duration_factor);
		assertTrue(last_line.getEnd_time()==15);
		assertFalse(last_line.getEnd_time()==10);
		assertFalse(last_line.getEnd_time()==100);
	}
	
	@Test
	public void testmerge_base_case() {
		ArrayList<SrtLine> lines1 = new ArrayList<>();
		SrtLine srtline1 = new SrtLine("Ciao","bella",0,100);
		lines1.add(srtline1);
		Srt srt1 = new Srt(lines1);
		
		ArrayList<SrtLine> lines2 = new ArrayList<>();
		SrtLine srtline2 = new SrtLine("Come","va",110,200);
		lines2.add(srtline2);
		Srt srt2 = new Srt(lines2);
		
		SrtBuilder builder = new SrtBuilder(100,100,2.0);
		
		Srt result = builder.merge(srt1, srt2);	
		
		assertTrue(result.getLines().get(0).getEnd_time()==108);
		
		//System.out.println(result);
	}

	@Test
	public void testmerge_base_case_transcription_list_constructor() {
		ArrayList<TranscriptedWord> words = new ArrayList<>();
		words.add(new TranscriptedWord("ciao",0,100));
		
		Srt srt1 = new Srt(words);
		
		ArrayList<TranscriptedWord> words2 = new ArrayList<>();
		words2.add(new TranscriptedWord("bella", 110, 200));
		Srt srt2 = new Srt(words2);
		
		SrtBuilder builder = new SrtBuilder(100,100,2.0);
		
		Srt result = builder.merge(srt1, srt2);	

		assertTrue(result.getLines().size()==1);
		assertTrue(result.getLines().get(0).getStart_time()==0);
		assertTrue(result.getLines().get(0).getEnd_time()==200);
		
		System.out.println(result);
	}
	
	@Test(expected=Exception.class)
	public void testmerge_empty_case() {

		
		List<TranscriptedWord> list = new ArrayList<>();
		//list.add(new TranscriptedWord("ciao", 100, 200));
		Srt srt1 = new Srt(list);
		
		List<TranscriptedWord> list2 = new ArrayList<>();
		Srt srt2 = new Srt(list2);
		
		SrtBuilder b = new SrtBuilder(10,10,1.5);
		Srt srtRes = b.merge(srt1, srt2);
		System.out.println(srtRes);
	}
	
	@Test
	public void testSrtBuilder() {
		SrtBuilder builder = new SrtBuilder(10,50,40l);
		assertNotNull(builder);
	}
	
	@Test
	public void testSrtBuilder_zerocase() {
		SrtBuilder builder = new SrtBuilder(0,0,0);
		assertNotNull(builder);
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
	
	@Test
	public void testBuild_realCase() {
		SrtBuilder builder = new SrtBuilder(2,70,1.5);
		assertNotNull(builder);

		List<TranscriptedWord> wl = new ArrayList<TranscriptedWord>();
		wl.add(new TranscriptedWord("in",148470l,148485l));
		wl.add(new TranscriptedWord("quanto",148486l,148516l));
		wl.add(new TranscriptedWord("giovani",148517l,148573l));
		wl.add(new TranscriptedWord("un",148583l,148595l));
		wl.add(new TranscriptedWord("operaio",148596l,148644l));
		wl.add(new TranscriptedWord("aspettava",148666l,148713l));
		wl.add(new TranscriptedWord("anche",148714l,148733l));
		wl.add(new TranscriptedWord("lui",148734l,148757l));
		wl.add(new TranscriptedWord("un",148758l,148773l));
		wl.add(new TranscriptedWord("ultrà",148774l,148812l));
		wl.add(new TranscriptedWord("utilitaria",148813l,148887l));
		wl.add(new TranscriptedWord("piccola",148918l,148958l));
		wl.add(new TranscriptedWord("economica",148959l,149023l));
		wl.add(new TranscriptedWord("che",149024l,149034l));
		wl.add(new TranscriptedWord("fosse",149035l,149062l));
		wl.add(new TranscriptedWord("una",149063l,149077l));
		wl.add(new TranscriptedWord("vera",149078l,149099l));
		wl.add(new TranscriptedWord("automobile",149100l,149148l));
		wl.add(new TranscriptedWord("mia",149194l,149204l));
		wl.add(new TranscriptedWord("moglie",149205l,149230l));
		wl.add(new TranscriptedWord("diceva",149231l,149276l));
		wl.add(new TranscriptedWord("pensa",149306l,149341l));
		wl.add(new TranscriptedWord("che",149342l,149352l));
		wl.add(new TranscriptedWord("comodità",149353l,149399l));
		wl.add(new TranscriptedWord("al",149400l,149408l));
		wl.add(new TranscriptedWord("mattino",149409l,149453l));
		wl.add(new TranscriptedWord("poter",149454l,149480l));
		wl.add(new TranscriptedWord("accompagnare",149481l,149536l));
		wl.add(new TranscriptedWord("uno",149537l,149544l));
		wl.add(new TranscriptedWord("figli",149545l,149569l));
		wl.add(new TranscriptedWord("a",149570l,149573l));
		wl.add(new TranscriptedWord("scuola",149574l,149618l));
		wl.add(new TranscriptedWord("tutti",149652l,149683l));
		wl.add(new TranscriptedWord("dicevano",149684l,149735l));

		Srt res = builder.build(wl);
		
		//System.out.println(res);
		
		
	}
	
	
	
}
