package lv.lu.events.xml;

import lv.lu.events.impl.xml.DomXmlParser;

import org.junit.Before;

/**
 * This test ensures DomXmlParser functionality.
 */
public class DomXmlParserTest extends AbstractXmlParserTest{

	@Before
	public void setUp() {
		DomXmlParser parser = new DomXmlParser();
		// turn XML validation off
		parser.setValidateXml(false);	
		setParser(parser);		
	}
}
