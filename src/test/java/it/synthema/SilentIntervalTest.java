package it.synthema;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SilentIntervalTest {

	

	@Test
	public void testGetTimeInterval() {
		TranscriptedWord tw1 = new TranscriptedWord("Ciao,", 0l,10l);
		TranscriptedWord tw2 = new TranscriptedWord("Come", 30l,40l);

		SilentInterval ciao_come = new SilentInterval(tw1, tw2);
		assertTrue(ciao_come.getTimeInterval()==20l);
	}

	@Test
	public void testCompareTo() {
		TranscriptedWord tw1 = new TranscriptedWord("Ciao,", 0l,10l);
		TranscriptedWord tw2 = new TranscriptedWord("Come", 30l,40l);
		TranscriptedWord tw3 = new TranscriptedWord("stai?", 42l,50l);
		TranscriptedWord tw4 = new TranscriptedWord("Bene", 100l,110l);
		
		SilentInterval ciao_come = new SilentInterval(tw1, tw2);
		SilentInterval come_stai = new SilentInterval(tw2, tw3);
		SilentInterval stai_bene = new SilentInterval(tw3, tw4);
		
		SilentInterval[] arr= new SilentInterval[3];
		arr[0]=ciao_come;
		arr[1]=come_stai;
		arr[2]=stai_bene;
		
		Arrays.sort(arr);
		
		assertTrue(arr[0].equals(come_stai));
		assertTrue(arr[1].equals(ciao_come));
		assertTrue(arr[2].equals(stai_bene));
		
		/*
		System.out.println("0: "+arr[0]);
		System.out.println("1: "+arr[1]);
		System.out.println("2: "+arr[2]);
		*/
	}

}
