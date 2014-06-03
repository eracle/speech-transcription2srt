package it.synthema;

import java.util.concurrent.TimeUnit;

/**
 * Represent the atomic part of a srt file. For instance:
 * 
 * 1<br>
 * 00:00:10,500 --> 00:00:13,000<br>
 * Elephant's Dream<br>
 * 
 * @author ercole
 *
 */
public class SrtLine {


	public String getFirstLine() {
		return firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}


	public long getStart_time() {
		return start_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * Returns the duration interval in milliseconds.
	 * @return Duration interval in milliseconds.
	 */
	public long getDuration(){
		return this.end_time-this.start_time;
	}



	private String firstLine;
	private String secondLine;

	private long start_time;
	private long end_time;

	public SrtLine(String firstLine, String secondLine, long start_time,
			long end_time) {
		super();
		this.firstLine = firstLine;
		this.secondLine = secondLine;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public long getEnd_time() {
		return end_time;
	}


	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		b.append(formatTime(this.start_time));
		b.append(" --> ");
		b.append(formatTime(this.end_time));
		b.append("\n");

		b.append(this.getFirstLine());
		b.append("\n");

		if(this.getSecondLine()!=null && !this.getSecondLine().equals("")){
			b.append(this.getSecondLine());
			b.append("\n");
		}
		return b.toString();
	}

	private String formatTime(long millis_time) {

		long hours = TimeUnit.MILLISECONDS.toHours(millis_time);
		millis_time -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis_time);
		millis_time -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis_time);
		millis_time -= TimeUnit.SECONDS.toMillis(seconds);

		return String.format("%02d:%02d:%02d,%03d", 
				hours,minutes,seconds,millis_time
				);
	}

}
