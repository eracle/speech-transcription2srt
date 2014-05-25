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
public class SrtBuilder {

	private final static Logger log = Logger.getLogger(SrtBuilder.class
			.getName());

	private long max_silent_threshold;

	private int max_characters_length;

	private long max_srt_line_duration;

	/**
	 * Generates the srt from the array of transcriptions.
	 * 
	 * @param list
	 * @return Srt object from where the srt file can be build.
	 */
	public Srt build(List<TranscriptedWord> list) {
		// log.debug("Start Building");

		//recursion base case.
		//if the list is composed by only one word I can't split more.
		if(list.size()==1)
			return new Srt(list);
		
		// I split the transcription sequence if
		// I find an enough big silent interval

		Split max_silent_interval = new Split(list);

		// if max_silent_interval is null means that there is not an enough big
		// silent interval in the sequence, in that case I split if the sequence
		// is
		// too long in terms of number of characters including whitespaces.
		if (max_silent_interval.getMaxSilentInterval().getTimeInterval() <= max_silent_threshold) {
			if (getCharacterNumber(list) <= max_characters_length) {
				return new Srt(list);
			}// else I will split, which is performed after the end of next two
				// if branches
		}

		return merge(this.build(max_silent_interval.getLeft()),
				this.build(max_silent_interval.getRight()));
	}

	/**
	 * Merges the two srt passed into the returned one. The merging phase is
	 * performed by adding to the srt lines of srt1 with the srt2, the ordering
	 * is preserved. So the srt lines of srt1 will be before of the lines of
	 * srt2. Another feature is that the last srt line is extended in time until
	 * it reaches the maximum srt line duration or the starting time of the
	 * first srt line of srt2.
	 * 
	 * @param srt1
	 *            First srt to merge.
	 * @param srt2
	 *            Second srt to merge.
	 * @return Merged srt.
	 */
	public Srt merge(Srt srt1, Srt srt2) {
		List<SrtLine> lines1 = srt1.getLines();
		List<SrtLine> lines2 = srt2.getLines();

		ArrayList<SrtLine> ret_lines = new ArrayList<>();

		// iterate lines1 and skipping last srt line
		for (int i = 0; i < lines1.size() - 1; i++) {
			ret_lines.add(lines1.get(i));
		}


		// setting the new ending time of the last srt line of srt1
		SrtLine last_line = lines1.get(lines1.size() - 1);
		

		long first_line_start_time = lines2.get(0).getStart_time() - 2;
		
		last_line = adjustLastLine(last_line,first_line_start_time,this.max_srt_line_duration);
		


		ret_lines.add(last_line);
		ret_lines.addAll(lines2);

		return new Srt(ret_lines);

	}

	/**
	 * Modifies the ending time of the SrtLine passed as a parameter and returns it.
	 * The starting time of the next srtline is passed as parameter.
	 * The following contraints are followed:<br> 
	 * - The result strline will not overlap with the next line srtLine. <br>
	 * - The duration of the srtline will not exceed the max_srt_line_duration2 passed.
	 * @param last_line	The SrtLine to modify.
	 * @param next_line_start_time The starting time of the next line of the srt.
	 * @param max_srt_line_duration2 Maximum SrtLine duration.
	 * @return The modified last_line object.
	 */
	public static SrtLine adjustLastLine(SrtLine last_line,
			long next_line_start_time, long max_srt_line_duration2) {
//TODO: write better specifications!
		long new_possible_end = last_line.getStart_time() + max_srt_line_duration2;
		
		long new_end_line;
		
		if(new_possible_end<next_line_start_time)
			new_end_line = new_possible_end;
		else new_end_line = next_line_start_time;
				
		last_line.setEnd_time(new_end_line);
		
		return last_line;
	}

	/**
	 * SrtBuilder constructor which takes some parameters. All the time values are intended in milliseconds.
	 * @param max_silent_threshold Maximum Silent interval possible inside an srtline.
	 * @param max_characters_length	Maximum number of characters inside an srtline.
	 * @param max_srt_line_duration	Duration of an srtline if there is not an enough close following srtline, otherwise, as ending time is used 
	 * 2 millisecond before the start time of the following srtline.
	 */
	public SrtBuilder(long max_silent_threshold, int max_characters_length,
			long max_srt_line_duration) {
		super();
		this.max_silent_threshold = max_silent_threshold;
		this.max_characters_length = max_characters_length;
		this.max_srt_line_duration = max_srt_line_duration;
	}

	public static int getCharacterNumber(List<TranscriptedWord> list) {

		int sum = 0;
		for (TranscriptedWord word : list) {
			sum += word.word.length();
		}
		sum += (list.size() - 1);
		return sum;
	}

}
