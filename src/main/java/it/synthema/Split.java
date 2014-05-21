package it.synthema;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the split made from a transcription word sequence. It contains:<br>
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

	private SilentInterval max_silent_interval;

	private TranscriptedWordList left;

	private TranscriptedWordList right;

	private List<TranscriptedWord> all_words;

	/**
	 * Constructor where the max interval is identified.
	 * 
	 * @param list
	 */
	public Split(List<TranscriptedWord> list) {

		this.all_words = list;
		this.left = new TranscriptedWordList();

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

	public TranscriptedWordList getLeft() {
		return this.left;
	}

	public TranscriptedWordList getRight() {
		if (this.right == null)
			this.right = generateRightPart();
		return this.right;
	}

	private TranscriptedWordList generateRightPart() {
		TranscriptedWordList ret = new TranscriptedWordList();
		for (int i = this.left.size(); i < all_words.size(); i++) {
			ret.add(all_words.get(i));
		}
		return ret;
	}

	public SilentInterval getMaxSilentInterval() {
		return this.max_silent_interval;
	}

}
