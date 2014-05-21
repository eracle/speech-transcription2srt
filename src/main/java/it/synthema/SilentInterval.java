package it.synthema;

public class SilentInterval implements Comparable<SilentInterval>{

	private long start_time;
	private long end_time;
	
	/**
	 * The constructed object represents the silent interval between the two words passed as a parameter.
	 * The first word must temporal previous of the second. 
	 * @param first	Previous word.
	 * @param second	Second word.
	 */
	public SilentInterval(TranscriptedWord first,TranscriptedWord second){
		this.start_time=first.end_time;
		this.end_time=second.start_time;
	}

	public long getStart_time() {
		return start_time;
	}

	public long getEnd_time() {
		return end_time;
	}

	@Override
	public String toString() {
		return "SilentInterval [start_time=" + start_time + ", end_time="
				+ end_time + "]";
	}

	public long getTimeInterval(){
		return this.end_time - this.start_time;
				
	}
	public int compareTo(SilentInterval arg0) {
		long delta = this.getTimeInterval() - arg0.getTimeInterval();
		if(delta>0)
			return 1;
		if(delta < 0)
			return -1;
		return 0;
	}
}
