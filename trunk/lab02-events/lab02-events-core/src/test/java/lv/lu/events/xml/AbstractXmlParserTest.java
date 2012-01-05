package lv.lu.events.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import lv.lu.events.base.BaseTest;
import lv.lu.events.interfaces.XmlParser;
import lv.lu.events.model.User;

import org.junit.Test;

/**
 * An abstract test case for testing XmlParser implementations.
 * The actual tested implementation is set in a subclass.
 */
public abstract class AbstractXmlParserTest extends BaseTest{

	private XmlParser parser;

	@Test
	public void testImportUsersData() throws UnsupportedEncodingException 
	{
		List<User> users = parser.parseUsers(
				new ByteArrayInputStream(XmlTestData.USERS_XML.getBytes("UTF-8")));
		
		assertNotNull("Users list is NULL", users);
		assertEquals("Wrong number of people extracted from XML",3, users.size());
		
		User user1 = users.get(0);
		assertEquals("Wrong user1 username", "jwhite", user1.getUsername());
		assertEquals("Wrong user1 password", "jwhitepass", user1.getPassword());
		assertEquals("Wrong user1 fullname", "John White", user1.getFullName());
		assertEquals("Wrong user1 email", "jwhite@eventsXXX.com", user1.getEmail());
		assertEquals("Wrong user1 Facebook id", "100002774971272", user1.getFacebookId());
		
		User user2 = users.get(1);
		assertEquals("Wrong user2 username", "dgreen", user2.getUsername());
		assertEquals("Wrong user2 password", "dgreenpass", user2.getPassword());
		assertEquals("Wrong user2 fullname", "David Green", user2.getFullName());
		assertEquals("Wrong user2 email", "dgreen@eventsXXX.com", user2.getEmail());
		assertEquals("Wrong user2 Facebook id", "100002808391341", user2.getFacebookId());
		
		User user3 = users.get(2);
		assertEquals("Wrong user3 username", "ablue", user3.getUsername());
		assertEquals("Wrong user3 password", "abluepass", user3.getPassword());
		assertEquals("Wrong user3 fullname", "Ann Blue", user3.getFullName());
		assertEquals("Wrong user3 email", "ablue@eventsXXX.com", user3.getEmail());
		assertEquals("Wrong use3 Facebook id", "100002771239557", user3.getFacebookId());
	}

	protected void setParser(XmlParser parser) {
		this.parser = parser;
	}

	protected XmlParser getParser() {
		return parser;
	}

}
