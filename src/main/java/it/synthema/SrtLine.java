package it.synthema;

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
	
	private String firstLine;
	public long getStart_time() {
		return start_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}

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
		return "SrtLine [firstLine=" + firstLine + ", secondLine=" + secondLine
				+ ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
}
