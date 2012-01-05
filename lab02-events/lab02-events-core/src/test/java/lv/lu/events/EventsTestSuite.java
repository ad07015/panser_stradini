package lv.lu.events;

import lv.lu.events.dao.CommonDAOImplTest;
import lv.lu.events.impl.service.EventServiceImplTest;
import lv.lu.events.impl.service.FriendshipServiceImplTest;
import lv.lu.events.impl.service.InviteServiceImplTest;
import lv.lu.events.impl.service.UserServiceImplTest;
import lv.lu.events.model.DataModelTest;
import lv.lu.events.xml.DigesterXmlParserTest;
import lv.lu.events.xml.DomXmlParserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Main test suite. Run as JUnit to execute all tests.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
		CommonDAOImplTest.class,
		DataModelTest.class,
		DomXmlParserTest.class,
		DigesterXmlParserTest.class,
		FriendshipServiceImplTest.class,
		EventServiceImplTest.class,
		InviteServiceImplTest.class,
		UserServiceImplTest.class})
public class EventsTestSuite {
}