package it.synthema;

import java.util.ArrayList;

public class TranscriptedWordList extends ArrayList<TranscriptedWord>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6821991764885608658L;
	
	private Integer char_num = null;
	public int getCharacterNumber() {
		if(char_num ==null){
			int sum=0;
			for (TranscriptedWord word : this) {
				sum+=word.word.length();
			}
			sum+=(this.size()-1);
			this.char_num=sum;
		}
		return char_num;
	}

}
