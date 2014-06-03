package it.synthema;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class SilentIntervalTest {

	

	@Test
	public void testGetTimeInterval() {
		TranscriptedWord tw1 = new TranscriptedWord("Ciao,", 0l,10l);
		TranscriptedWord tw2 = new TranscriptedWord("Come", 30l,40l);

		SilentInterval ciao_come = new SilentInterval(tw1, tw2,0);
		assertTrue(ciao_come.getTimeInterval()==20l);
	}

	@Test
	public void testCompareTo() {
		TranscriptedWord tw1 = new TranscriptedWord("Ciao,", 0l,10l);
		TranscriptedWord tw2 = new TranscriptedWord("Come", 30l,40l);
		TranscriptedWord tw3 = new TranscriptedWord("stai?", 42l,50l);
		TranscriptedWord tw4 = new TranscriptedWord("Bene", 100l,110l);

		TranscriptedWord tw5 = new TranscriptedWord("Bene", 100l,110l);
		TranscriptedWord tw6 = new TranscriptedWord("Bene", 100l,110l);
		
		SilentInterval ciao_come = new SilentInterval(tw1, tw2,2);
		SilentInterval come_stai = new SilentInterval(tw2, tw3,1);
		SilentInterval stai_bene = new SilentInterval(tw3, tw4,0);
		SilentInterval bene_bene1 = new SilentInterval(tw4, tw5,1);
		SilentInterval bene_bene2 = new SilentInterval(tw5, tw6,2);
		
		SilentInterval[] arr= new SilentInterval[5];
		arr[0]=ciao_come;
		arr[1]=come_stai;
		arr[2]=stai_bene;
		arr[3]=bene_bene1;
		arr[4]=bene_bene2;
		
		Arrays.sort(arr,Collections.reverseOrder());
		
		assertTrue(arr[0].equals(stai_bene));
		assertTrue(arr[1].equals(ciao_come));
		assertTrue(arr[2].equals(come_stai));
		assertTrue(arr[3].equals(bene_bene1));
		assertTrue(arr[4].equals(bene_bene2));
		
		/*
		System.out.println("0: "+arr[0]);
		System.out.println("1: "+arr[1]);
		System.out.println("2: "+arr[2]);
		System.out.println("3: "+arr[3]);
		System.out.println("4: "+arr[4]);
		*/
	}

}
