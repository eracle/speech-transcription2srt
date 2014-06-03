package it.synthema.xml;

import it.synthema.TranscriptedWord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTranscriptionReader {


	/**
	 * Parse xml file passed.Returns a list of transcripted words.
	 * @param file
	 * @return
	 */
	public static List<TranscriptedWord> readXmlFile(File file) {


		NodeList nodes =null;
		ArrayList<TranscriptedWord> ret = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			
			nodes = doc.getElementsByTagName("Word");

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			TranscriptedWord tw = new TranscriptedWord(element.getFirstChild().getNodeValue(),Integer.parseInt(element.getAttribute("start"))
					, Integer.parseInt(element.getAttribute("end")));
			ret.add(tw);
		}


		return ret;
	}

}
