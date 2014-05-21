package it.synthema;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class SrtBuilderTest {

	

	/*
	public void testBuild() {
		List<TranscriptedWord> st = gettestlist();
		
		
		SrtBuilder builder = new SrtBuilder(st);
		builder.build();
	}
	*/
/*
	private List<TranscriptedWord> gettestlist() {
		List<TranscriptedWord> st = new ArrayList<TranscriptedWord>();
		
		st.add(new TranscriptedWord("Hi",0l,10l));
		st.add(new TranscriptedWord("how",12l,20l));
		st.add(new TranscriptedWord("are",22l,31l));
		st.add(new TranscriptedWord("You?",33l,40l));
		

		st.add(new TranscriptedWord("Fine",60l,70l));
		st.add(new TranscriptedWord("Thanks",72l,80l));
		return st;
	}
	*/
	/*
	public void testcomputeSilentIntervals(){
		List<SilentInterval> silent = SrtBuilder.computeSilentIntervals(gettestlist());
		assertNotNull(silent);
		for (SilentInterval silentInterval : silent) {
			assertNotNull(silentInterval);
			assertTrue(silentInterval.getStart_time()>= 0);
			assertTrue(silentInterval.getEnd_time()>=0);
			assertTrue(silentInterval.getStart_time()<silentInterval.getEnd_time());
		}
		assertTrue(silent.size()==5);
	}

*/
}
