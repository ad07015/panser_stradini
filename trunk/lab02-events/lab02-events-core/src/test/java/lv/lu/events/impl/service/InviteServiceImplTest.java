package lv.lu.events.impl.service;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


import lv.lu.events.base.PersistenceBaseTest;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.User;
import lv.lu.events.impl.service.InviteServiceImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class InviteServiceImplTest extends PersistenceBaseTest {

    private static InviteServiceImpl inviteService;
    private static User user1;
    private static User user2;
    private static User user3;
    
    private static Event privateEvent;
    private static Event publicEvent;

    private static Invite invite1;
    private static Invite invite2;
    private static Invite invite3;
    
    @BeforeClass
    public static void initService(){
    	inviteService = new InviteServiceImpl();
    	inviteService.setCommonDAO(commonDAO);
        // TODO [task] add any other required service initialization steps here
    }
    
    @Before
    public void prepareData(){
        user1 = createTestUser();
        commonDAO.save(user1);
        user2 = createTestUser();
        commonDAO.save(user2);
        user3 = createTestUser();
        commonDAO.save(user3);
        
        publicEvent = createTestPublicEvent(user2);
        commonDAO.save(publicEvent);
        
        privateEvent = createTestPrivateEvent(user1);
        commonDAO.save(privateEvent);
        
        invite1 = createTestInvite(privateEvent, user1, user2, InviteStatus.NOT_REPLIED);
        commonDAO.save(invite1);
        invite3 = createTestInvite(null, user2, user3, InviteStatus.NOT_REPLIED);
        flushAndClear();
    }
    
    @Test
    public void testAddInvitesToEventForEmptyEvent() {
    	invite2 = createTestInvite(publicEvent, user1, user3, InviteStatus.ATTENDING);
    	List <Invite> invites = new LinkedList<Invite>();
    	invites.add(invite2);

    	inviteService.addInvitesToEvent(publicEvent, invites);
    	Event event = commonDAO.getById(Event.class, publicEvent.getId());
    	assertNotNull(event);
    	assertEventsEquals(publicEvent, event);
    	List <Invite> currentInvites = publicEvent.getInvites();
    	List <Invite> eventInvites = event.getInvites();
    	for (Invite currInvite : currentInvites) {
    		  assertTrue(eventInvites.contains(currInvite));
    	}
    }
    
    @Test
    public void testAddInvitesToEventUpdateInviteStatus() {
    	List <Invite> invites = new LinkedList<Invite>();
    	invite1.setStatus(InviteStatus.UNSURE);
    	invites.add(invite1);
    	inviteService.addInvitesToEvent(privateEvent, invites);
    	Event event = commonDAO.getById(Event.class, privateEvent.getId());
    	assertNotNull(event);
    	assertEventsEquals(privateEvent, event);
    	List <Invite> currentInvites = privateEvent.getInvites();
    	List <Invite> eventInvites = event.getInvites();
    	for (Invite currInvite : currentInvites) {
    		  assertTrue(eventInvites.contains(currInvite));
    	}
    }

    @Test
    public void testAddInvitesToEventAddInviteAndUpdateStatus() {
    	invite3.setEvent(privateEvent);
    	List <Invite> invites = new LinkedList<Invite>();
    	invite1.setStatus(InviteStatus.DECLINED);
    	invites.add(invite1);
    	invites.add(invite3);
    	inviteService.addInvitesToEvent(privateEvent, invites);
    	Event event = commonDAO.getById(Event.class, privateEvent.getId());
    	assertNotNull(event);
    	assertEventsEquals(privateEvent, event);
    	List <Invite> currentInvites = privateEvent.getInvites();
    	List <Invite> eventInvites = event.getInvites();
    	for (Invite currInvite : currentInvites) {
    		  assertTrue(eventInvites.contains(currInvite));
    	}
    }
}