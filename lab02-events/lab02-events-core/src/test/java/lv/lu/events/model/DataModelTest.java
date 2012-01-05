package lv.lu.events.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import lv.lu.events.base.PersistenceBaseTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Creates full data structure with associations 
 * and then tests that data is correctly saved to and retrieved from database.
 * 
 * Only persistence and O/R mapping is tested there.
 * 
 * TODO [task] this test should pass successfully when all JPA annotations 
 * are correctly added to model classes
 */
public class DataModelTest extends PersistenceBaseTest{ 
    
	private static List<User> users;
	private static List<Friendship> friendships;
	private static List<Event> events;
	private static List<Invite> invites;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		PersistenceBaseTest.setUpBeforeClass();
		em.getTransaction().begin();
		
		users = createUsers();
		friendships = createFriendships();
		events = createEvents();
		invites = createInvites();
		
		/* flush changes to data store and clear persistence context */		
		flushAndClear();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		em.getTransaction().rollback();
		PersistenceBaseTest.tearDownAfterClass();
	}
	
	@Override @Before
	public void setUp() { /* nothing */ }
	
	@Override @After
	public void tearDown() {/* nothing */ }

	/**
	 * Data creation
	 */
	
	private static List<User> createUsers() {
		List<User> users = new ArrayList<User>(3);
		User user1 = createTestUser();
		users.add(user1);	
		User user2 = createTestUser();
		users.add(user2);
		User user3 = createTestUser();
		users.add(user3);		
		commonDAO.saveAll(users);
		return users;
	}
	
	private static List<Friendship> createFriendships() {
	    List<Friendship> friendships = new ArrayList<Friendship>(3);
	    friendships.add(
	            createTestFriendship(users.get(0), users.get(1), FriendshipStatus.ACCEPTED));
	    friendships.add(
                createTestFriendship(users.get(0), users.get(2), FriendshipStatus.ACCEPTED));
	    friendships.add(
	            createTestFriendship(users.get(1), users.get(2), FriendshipStatus.DECLINED));
	    commonDAO.saveAll(friendships);
	    return friendships;
	}
	
	private static List<Event> createEvents() {
	    List<Event> events = new ArrayList<Event>(5);	    
	    events.add(createTestPublicEvent(users.get(0)));
	    events.add(createTestPublicEvent(users.get(1)));
	    events.add(createTestPrivateEvent(users.get(0)));
	    events.add(createTestPublicEvent(users.get(1)));
	    events.add(createTestPublicEvent(users.get(1)));	    
	    commonDAO.saveAll(events);
	    return events;
	}
	
	private static List<Invite> createInvites() {
	    List<Invite> invites = new ArrayList<Invite>(9);
	    
	    Event event1 = events.get(0);
	    List<Invite> inviteList = new ArrayList<Invite>(3);
	    inviteList.add(createTestInvite(event1, users.get(0), users.get(0), InviteStatus.ATTENDING));
	    inviteList.add(createTestInvite(event1, users.get(0), users.get(1), InviteStatus.UNSURE));
	    inviteList.add(createTestInvite(event1, users.get(0), users.get(2), InviteStatus.DECLINED));
	    invites.addAll(inviteList);
	    event1.setInvites(new ArrayList<Invite>(inviteList));
	    commonDAO.saveAll(inviteList);
	    commonDAO.save(event1);
	    
	    Event event2 = events.get(1);
	    inviteList.clear();
	    inviteList.add(createTestInvite(event2, users.get(1), users.get(1), InviteStatus.ATTENDING));
	    inviteList.add(createTestInvite(event2, users.get(1), users.get(2), InviteStatus.ATTENDING));
	    invites.addAll(inviteList);
        event2.setInvites(new ArrayList<Invite>(inviteList));        
        commonDAO.saveAll(inviteList);
        commonDAO.save(event2);
	    
        Event event3 = events.get(2);
        inviteList.clear();
        inviteList.add(createTestInvite(event3, users.get(0), users.get(0), InviteStatus.ATTENDING));
        inviteList.add(createTestInvite(event3, users.get(0), users.get(1), InviteStatus.NOT_REPLIED));
        invites.addAll(inviteList);
        event3.setInvites(new ArrayList<Invite>(inviteList));
        commonDAO.saveAll(inviteList);
        commonDAO.save(event3);
	    
	    Event event4 = events.get(3);
	    inviteList.clear();
	    inviteList.add(createTestInvite(event4, users.get(1), users.get(1), InviteStatus.ATTENDING));
	    invites.addAll(inviteList);
	    event4.setInvites(new ArrayList<Invite>(inviteList));
        commonDAO.saveAll(inviteList);
        commonDAO.save(event4);
	    
	    Event event5 = events.get(4);
	    inviteList.clear();
	    inviteList.add(createTestInvite(event5, users.get(1), users.get(1), InviteStatus.ATTENDING));
	    invites.addAll(inviteList);
	    event5.setInvites(new ArrayList<Invite>(inviteList));
        commonDAO.saveAll(inviteList);
        commonDAO.save(event5);
	    
	    return invites;
	}
	
	/**
	 * Test for users
	 */
	
	@Test
	public void testUsers(){			
		List<User> usersFromDB = commonDAO.findAll(User.class);
		assertEquals("Wrong number of users", users.size(), usersFromDB.size());

		for (int i=0; i<users.size(); i++){
		    assertUsersEquals(users.get(i), usersFromDB.get(i));
		}
	}
	
	/**
	 * Test for friendships
	 */
	
	@Test
	public void testFriendships(){            
	    List<Friendship> friendshipsFromDB = commonDAO.findAll(Friendship.class);
	    assertEquals("Wrong number of friendships", friendships.size(), friendshipsFromDB.size());

	    for (int i=0; i<friendships.size(); i++){
	        assertFriendshipsEquals(friendships.get(i), friendshipsFromDB.get(i));
	    }
	}
	
	private void assertFriendshipsEquals(Friendship expected, Friendship actual){
	    assertEquals("Friendship 'id' is wrong", expected.getId(), actual.getId());
	    assertEquals("Friendship 'user1' is wrong", expected.getUser1().getId(), actual.getUser1().getId());
	    assertEquals("Friendship 'user2' is wrong", expected.getUser2().getId(), actual.getUser2().getId());
	    assertEquals("Friendship 'status' is wrong", expected.getStatus(), actual.getStatus());
	}
	
	/**
     * Test for events
     */
    
    @Test
    public void testEvents(){            
        List<Event> eventsFromDB = commonDAO.findAll(Event.class);
        assertEquals("Wrong number of events", events.size(), eventsFromDB.size());

        for (int i=0; i<events.size(); i++){
            assertEventsEquals(events.get(i), eventsFromDB.get(i));
        }
    }
    

    
    /**
     * Test for invites
     */
    
    @Test
    public void testInvites(){            
        List<Invite> invitesFromDB = commonDAO.findAll(Invite.class);
        assertEquals("Wrong number of invites", invites.size(), invitesFromDB.size());

        for (int i=0; i<invites.size(); i++){
            assertInvitesEquals(invites.get(i), invitesFromDB.get(i));
        }
    }

}