package it.synthema;

/**
 * Representation of a transcripted word.
 * Is composed by a triplet:<br>
 * - word spoken;
 * - starting time of the speech
 * - ending time of the speech
 * @author ercole
 *
 */
public class TranscriptedWord {

	public String word;
	public long start_time;
	public long end_time;
	
	@Override
	public String toString() {
		return this.word+"\t"+ start_time + "\t"+ end_time+"\n";
	}

	public TranscriptedWord(String word, long start_time, long end_time) {
		super();
		this.word = word;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	
}
