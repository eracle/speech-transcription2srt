package it.synthema;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the srt file.
 * @author ercole
 *
 */
public class Srt {
	
	/**
	 * Basic constructor which generates an Srt composed only by an unique SrtLine.
	 * It assumes that the transcriptionWordList is composed by a small amount of characters.
	 * @param list
	 */
	public Srt(TranscriptionWordList list) {
		//basic implementation that splits
		//into two the list based on the number of words
		//for better results it must be changed.
		int half = (int)(((double)list.size())/2.0);
		StringBuilder f = new StringBuilder();
		StringBuilder s = new StringBuilder();
		int i = 0;
		for (TranscriptedWord word : list) {
			if(i<half)
				f.append(word.word);
			else s.append(word.word);
			i++;	
		}
		this.lines = new ArrayList<SrtLine>();
		this.lines.add(new SrtLine(f.toString(),s.toString(),list.get(0).start_time,list.get(list.size()-1).end_time));
	}

	private List<SrtLine> lines;

}
