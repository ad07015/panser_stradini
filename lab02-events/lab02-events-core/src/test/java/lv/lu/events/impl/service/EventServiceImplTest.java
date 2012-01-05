package lv.lu.events.impl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import lv.lu.events.base.PersistenceBaseTest;
import lv.lu.events.model.Event;
import lv.lu.events.model.User;
import lv.lu.events.impl.service.EventServiceImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventServiceImplTest extends PersistenceBaseTest {

    private static EventServiceImpl eventService;
    private static User user1;
    private static User user2;
    private static Event privateEvent;
    private static Event publicEvent1;
    private static Event publicEvent2;
    
    @BeforeClass
    public static void initService(){
        eventService = new EventServiceImpl();
        eventService.setCommonDAO(commonDAO);
        // TODO [task] add any other required service initialization steps here
    }
    
    @Before
    public void prepareData(){
        user1 = createTestUser();
        commonDAO.save(user1);
        user2 = createTestUser();
        commonDAO.save(user2);
        privateEvent = createTestPrivateEvent(user1);
        publicEvent1 = createTestPublicEvent(user2);
        publicEvent2 = createTestPublicEvent(user2);
        publicEvent2.setDescription("test");
        commonDAO.save(privateEvent);
        flushAndClear();
    }
    
    @Test
    public void testGetEventByFacebookId() {
    	Event fetchedEvent = eventService.getEventByFacebookId(privateEvent.getFacebookId());
    	assertNotNull(fetchedEvent);
    	assertEventsEquals(privateEvent, fetchedEvent);
	}
    
    @Test
    public void testUpdateEvent() {
    	eventService.updateEvent(publicEvent1, publicEvent2);
    	Event event = commonDAO.getById(Event.class, publicEvent1.getId());
    	assertEquals("Updated event description is wrong", event.getDescription(), publicEvent2.getDescription());
    }

}