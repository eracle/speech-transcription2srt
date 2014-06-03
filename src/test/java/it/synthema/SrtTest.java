package it.synthema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SrtTest {

	
	
	@Test
	public void testGetLines() {
		List<SrtLine> lines = get_single_line_mock().getLines();
		assertNotNull(lines);
		assertTrue(lines.size()!=0);
		for (SrtLine srtLine : lines) {
			assertSrtLine(srtLine);
		}
	}

	private void assertSrtLine(SrtLine srtLine) {
		assertNotNull(srtLine);
		assertTrue(srtLine.getEnd_time()>srtLine.getStart_time());
		assertNotNull(srtLine.getFirstLine());
		assertTrue(srtLine.getFirstLine().length()!=0);
		assertNotNull(srtLine.getSecondLine());
		assertTrue(srtLine.getSecondLine().length()!=0);
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
