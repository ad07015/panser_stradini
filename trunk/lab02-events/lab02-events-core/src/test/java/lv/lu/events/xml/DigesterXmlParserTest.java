package lv.lu.events.xml;

import lv.lu.events.impl.xml.DigesterXmlParser;

import org.junit.Before;

/**
 * This test ensures DigesterXmlParser functionality.
 */
public class DigesterXmlParserTest extends AbstractXmlParserTest{

	@Before
	public void setUp() {
		DigesterXmlParser parser = new DigesterXmlParser();	
		setParser(parser);		
	}

}
