package lv.lu.events.impl.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Error handler for DOM XML parser.
 */
public class XmlErrorHandler implements ErrorHandler {

	public void error(SAXParseException e) throws SAXException {
		System.err.println("Error while parsing XML file");
		System.err.println(e);
		throw new RuntimeException(e);
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("Fatal error while parsing XML file");
		System.err.println(e);
		throw new RuntimeException(e);
	}

	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning is generated while parsing XML file");
		System.out.println(e.getMessage());
	}

}
