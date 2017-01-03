/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.filesystem.impl;

import eapli.framework.filesystem.FileSystem;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author nervousdev
 */
public abstract class XMLEncoder<T> extends FileSystem<T> {

	private static final String FILE_EXTENSION = ".xml";
	private final Document document;

	public XMLEncoder() throws ParserConfigurationException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.
			newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();
	}

	public void addElement(Element element) {

		document.appendChild(element);
	}

	public Element newElement(String element) {
		return document.createElement(element);
	}

	public Text newText(String text) {
		return document.createTextNode(text);
	}

	public void saveXMLFile(String fileName) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.
			setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(fileName + FILE_EXTENSION));

		transformer.transform(domSource, streamResult);
	}

	@Override
	public boolean saveFile(String fileName) throws IOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
