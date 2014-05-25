package it.synthema;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the srt file.
 * @author ercole
 *
 */
public class Srt {


	private List<SrtLine> lines;

	/**
	 * Basic constructor which generates an Srt composed only by an unique SrtLine.
	 * It assumes that the transcriptionWordList is composed by a small amount of characters.
	 * @param list
	 */
	public Srt(List<TranscriptedWord> list) {
		//basic implementation that splits
		//into two the list based on the number of words
		//for better results it must be changed.
		int half = (int)(((double)list.size())/2.0);
		StringBuilder f = new StringBuilder();
		StringBuilder s = new StringBuilder();
		int i = 0;
		for (TranscriptedWord word : list) {
			if(i<half){
				f.append(word.word);
				f.append(" ");
			}
			else{ 
				s.append(word.word);
				s.append(" ");
			}
			i++;	
		}
		this.lines = new ArrayList<SrtLine>();
		this.lines.add(new SrtLine(f.toString(),s.toString(),list.get(0).start_time,list.get(list.size()-1).end_time));
	}

	public Srt(ArrayList<SrtLine> lines) {
		super();
		this.lines=lines;
	}

	public List<SrtLine> getLines() {
		return lines;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		int i = 1;
		for (SrtLine line :  lines) {
			ret.append(i);
			ret.append("\n");
			ret.append(line.toString());
			ret.append("\n");
			i++;
		}
		return ret.toString();

	}



}
