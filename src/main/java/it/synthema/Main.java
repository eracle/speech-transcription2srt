package it.synthema;

import it.synthema.xml.XmlTranscriptionReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String xml_file_path="/home/ercole/workspaces/workspace_indigo/speech-transcription2srt/src/main/resources/trans.xml";
		
		//TODO: that will be changed with arg4jparse
		
		File trans = new File(xml_file_path);
		
		//TODO: check if file exists
		
		List<TranscriptedWord> list = XmlTranscriptionReader.readXmlFile(trans);
		
		
		PrintStream out = new PrintStream(new File("/home/ercole/Desktop/speech2srt/out.srt"));
		/*
		for (TranscriptedWord transcriptedWord : list) {
			out.print(transcriptedWord);
		}
		*/
		
		SrtBuilder builder = new SrtBuilder(300,37,1.5);
		
		Srt res_srt = builder.build(list);
		
		out.println(res_srt);
		
	}

}
