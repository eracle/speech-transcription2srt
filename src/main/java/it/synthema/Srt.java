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
	 * @param list
	 */
	public Srt(List<TranscriptedWord> list) {
		/* More advanced implementation:
		 * Splits into two strings the list passed 
		 * by minimizing their size difference in
		 * terms of number of characters.
		*/
		int total_size = SrtBuilder.getCharacterNumber(list);
		int half_size = total_size/2;
		
		//number of character written into the first line:
		int num_wri_fir = 0;
		int list_counter = 0;
		StringBuilder first = new StringBuilder();
		
		// I write all the transcripted words until  
		// the half_size if reached
		while(num_wri_fir<half_size){
			TranscriptedWord word = list.get(list_counter++);
			first.append(word.word);
			num_wri_fir+=word.word.length();
			if(num_wri_fir<half_size){
				first.append(" ");
				num_wri_fir+=1;
			}
		}
		
		//I flush all the remaining transcripted word
		//not written yet into the second line
		StringBuilder second = new StringBuilder();
		while(list_counter<list.size()){
			second.append(list.get(list_counter).word);
			list_counter++;
			if(list_counter<list.size())
				second.append(" ");
		}
		
		
		this.lines = new ArrayList<SrtLine>();
		this.lines.add(new SrtLine(first.toString(),
				second.toString(),list.get(0).start_time,list.get(list.size()-1).end_time));
	
		//OLD IMPLEMENTATION:
		/*
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
	*/
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
