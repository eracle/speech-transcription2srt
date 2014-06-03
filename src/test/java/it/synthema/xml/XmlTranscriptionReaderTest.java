package it.synthema.xml;

import static org.junit.Assert.*;
import it.synthema.TranscriptedWord;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class XmlTranscriptionReaderTest {

	@Test
	public void testReadXmlFile() throws ParserConfigurationException, SAXException, IOException {
		URL url = this.getClass().getResource("/trans.xml");
		File trans = new File(url.getFile());
		
		assertTrue(trans.exists());
		
		List<TranscriptedWord> list = XmlTranscriptionReader.readXmlFile(trans);

		assertNotNull(list);
		assertTrue(list.size()!=0);
		for (TranscriptedWord transcriptedWord : list) {
			assertNotNull(transcriptedWord.word);
			assertTrue(transcriptedWord.word.length()!=0);
			assertTrue(transcriptedWord.end_time>transcriptedWord.start_time);
		}
	}

}
