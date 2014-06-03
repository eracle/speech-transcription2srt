package it.synthema;

import it.synthema.xml.XmlTranscriptionReader;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String xml_file_path="/home/ercole/workspaces/workspace_indigo/speech-transcription2srt/src/main/resources/trans.xml";
		
		//TODO: that will be changed with arg4jparse
		
		File trans = new File(xml_file_path);
		
		//TODO: check if fiile exists
		
		List<TranscriptedWord> list = XmlTranscriptionReader.readXmlFile(trans);
		
		SrtBuilder builder = new SrtBuilder(1000,35,2000);
		
		Srt res_srt = builder.build(list);
		
		System.out.println(res_srt);
		
	}

}
