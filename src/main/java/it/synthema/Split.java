package it.synthema;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Represent the split of a sequence of words.
 * It contains:<br>
 * - max silent interval;<br>
 * - the sequence of transcripted words before the max silent interval (aka left
 * part);<br>
 * - the sequence of transcripted words after the max silent interval (aka right
 * part).
 * 
 * @author ercole
 * 
 */
public class Split {

	private final static Logger log = Logger.getLogger(Split.class.getName());

	
	private SilentInterval max_silent_interval;

	private List<TranscriptedWord> left;

	private List<TranscriptedWord> right;

	private List<TranscriptedWord> all_words;

	/**
	 * Constructor where the max interval is identified. A split can be performed only if there are almost two transcripted words.
	 * 
	 * @param list
	 */
	public Split(List<TranscriptedWord> list) {

		if(list.size()<2)
			throw new IllegalArgumentException("Cannot split a list of transcripted word smaller than 2 elements");
		//log.debug("Calling the Split constructor");
		
		this.all_words = list;
		this.left = new ArrayList<TranscriptedWord>();
		this.right = null;

		/*
		 * here are stored all the candidate to be in the left part. that are
		 * merged officially into the left part when a max interval is found
		 */
		ArrayList<TranscriptedWord> tmp_left = new ArrayList<TranscriptedWord>();

		SilentInterval max = null;

		TranscriptedWord previous = null;

		// I iterate over the whole list passed
		for (TranscriptedWord word : list) {

			// if is not the first word
			if (previous != null) {
				tmp_left.add(previous);

				SilentInterval temp = new SilentInterval(previous, word);
				if (max == null) {
					max = temp;
					this.left.addAll(tmp_left);
					tmp_left = new ArrayList<TranscriptedWord>();
				} else if (temp.compareTo(max) > 0) {
					// If I find a maximum I am sure that all the previous
					// words are in the left part, so I can put them into left
					// the left part will be generated in a lazy way.
					max = temp;
					this.left.addAll(tmp_left);
					tmp_left = new ArrayList<TranscriptedWord>();
				}

			}
			previous = word;
		}

		this.max_silent_interval = max;

	}

	public List<TranscriptedWord> getLeft() {
		return this.left;
	}

	public List<TranscriptedWord> getRight() {
		if (this.right == null)
			this.right = generateRightPart();
		return this.right;
	}

	private List<TranscriptedWord> generateRightPart() {
		List<TranscriptedWord> ret = new ArrayList<TranscriptedWord>();
		for (int i = this.left.size(); i < all_words.size(); i++) {
			ret.add(all_words.get(i));
		}
		return ret;
	}

	public SilentInterval getMaxSilentInterval() {
		return this.max_silent_interval;
	}

}
