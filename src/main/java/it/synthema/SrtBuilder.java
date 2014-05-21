package it.synthema;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * Builds an SubRip (.srt) file from a speech transcription passed as argoument.
 *
 * @author ercole
 *
 */
public class SrtBuilder 
{
	
	private final static Logger log = Logger.getLogger(SrtBuilder.class.getName());

	
	//private List<TranscriptedWord> speech_transcription;
	/*
	public SrtBuilder(List<TranscriptedWord> speech_transcription){
		this.speech_transcription= speech_transcription;	
	}
	*/
	

	/**
	 * Generates the srt from the array of transcriptions
	 * @param max_silent_threshold 
	 * @param max_characters_length 
	 * @return Srt object from where the srt file can be build.
	 */
	public static Srt build(TranscriptedWordList list, long max_silent_threshold, int max_characters_length){
		//log.debug("Start Building");
		
		
		
		// I split the transcription sequence if
		// I find an enough big silent interval
		
		Split max_silent_interval = new Split(list);
		
		// if max_silent_interval is null means that there is not an enough big
		// silent interval in the sequence, in that case I split if the sequence is 
		// too long in terms of number of characters including whitespaces.
		if(max_silent_interval.getMaxSilentInterval().getTimeInterval()<=max_silent_threshold){
			if(list.getCharacterNumber() <= max_characters_length){				
				return new Srt(list);
			}//else I will split, which is performed after the end of next two if branches
		}
		
		return null;
		//return merge(max_silent_interval.getLeft(),max_silent_interval.getRight());
	}
	
	
	
}
