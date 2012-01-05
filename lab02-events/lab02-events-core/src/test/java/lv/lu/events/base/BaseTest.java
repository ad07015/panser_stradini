package lv.lu.events.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Random;

import lv.lu.events.model.Event;
import lv.lu.events.model.Friendship;
import lv.lu.events.model.FriendshipStatus;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.PrivateEvent;
import lv.lu.events.model.PublicEvent;
import lv.lu.events.model.User;

/**
 * Base test containing the most common functions.
 */
public abstract class BaseTest {

	protected static User createTestUser(){
		User user = new User();
		user.setUsername(generateRandomString());
		user.setPassword(generateRandomString());
		user.setFullName(generateRandomString());
		user.setEmail(generateRandomString());
		user.setFacebookId(generateRandomString());
		return user;
	}
	
	protected static Friendship createTestFriendship(User user1, User user2, FriendshipStatus status){
	    if (user1.getId() != null && user2.getId() != null && !user1.getId().equals(user2.getId())){
	        Friendship friendship = new Friendship();
	        friendship.setUser1(user1);
	        friendship.setUser2(user2);
	        friendship.setStatus(status);
	        return friendship;
	    }
	    throw new IllegalArgumentException("Friendship requested between one the same user. Rejected.");
	}
	
	protected static Event createTestPublicEvent(User user){
		PublicEvent event = new PublicEvent();
		event.setOwner(user);
		event.setName(generateRandomString());
		event.setLocation(generateRandomString());
		event.setDescription(generateRandomString());
		event.setContactPhone(generateRandomString());
		event.setStartTime(new Date(System.currentTimeMillis() + 2*60*60000));
		event.setEndTime(new Date(System.currentTimeMillis() + 4*60*60000));
		event.setFacebookId(generateRandomString());
		return event;
	}
	
	protected static Event createTestPrivateEvent(User user){
	    PrivateEvent event = new PrivateEvent();
	    event.setOwner(user);
	    event.setName(generateRandomString());
	    event.setLocation(generateRandomString());
	    event.setDescription(generateRandomString());
	    event.setContactPhone(generateRandomString());
	    event.setStartTime(new Date(System.currentTimeMillis() + 2*60*60000));
	    event.setEndTime(new Date(System.currentTimeMillis() + 4*60*60000));
	    event.setAccessCode(generateRandomString());
	    event.setFacebookId(generateRandomString());
	    return event;
	}
	
	protected static Invite createTestInvite(Event event, User invitor, User invited, InviteStatus status){
	    Invite invite = new Invite();
	    invite.setEvent(event);
	    invite.setCreator(invitor);
	    invite.setInvited(invited);
	    invite.setCreatedDate(new Date());
	    invite.setStatus(status);
	    invite.setStatusUpdateDate(new Date());
	    return invite;
	}
	
    private static String generateRandomString(){
        Random r = new Random();
        String random = Long.toString(Math.abs(r.nextLong()), 36);
        return random.substring(0,8);
    }
    
    protected void assertUsersEquals(User expected, User actual){
        assertEquals("User 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("User 'username' is wrong", expected.getUsername(), actual.getUsername());
        assertEquals("User 'password' is wrong", expected.getPassword(), actual.getPassword());
        assertEquals("User 'full name' is wrong", expected.getFullName(), actual.getFullName());
        assertEquals("User 'email' is wrong", expected.getEmail(), actual.getEmail());
        assertEquals("User 'facebook id' is wrong", expected.getFacebookId(), actual.getFacebookId());
    }
    
    protected void assertInvitesEquals(Invite expected, Invite actual){
        assertEquals("Invite 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Invite 'created date' is wrong", expected.getCreatedDate(), actual.getCreatedDate());
        assertEquals("Invite 'creator' is wrong", expected.getCreator().getId(), actual.getCreator().getId());
        assertEquals("Invite 'invited user' is wrong", expected.getInvited().getId(), actual.getInvited().getId());
        assertEquals("Invite 'event' is wrong", expected.getEvent().getId(), actual.getEvent().getId());
        assertEquals("Invite 'status' is wrong", expected.getStatus(), actual.getStatus());
        assertEquals("Invite 'status' is wrong", expected.getStatusUpdateDate(), actual.getStatusUpdateDate());
    }
	
    protected void assertEventsEquals(Event expected, Event actual){
        assertEquals("Event 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Event 'Class' is wrong", expected.getClass(), actual.getClass());
        assertEquals("Event 'name' is wrong", expected.getName(), actual.getName());
        assertEquals("Event 'owner' is wrong", expected.getOwner().getId(), actual.getOwner().getId());
        assertEquals("Event 'location' is wrong", expected.getLocation(), actual.getLocation());
        assertEquals("Event 'description' is wrong", expected.getDescription(), actual.getDescription());
        assertEquals("Event 'phone' is wrong", expected.getContactPhone(), actual.getContactPhone());
        assertEquals("Event 'facebook id' is wrong", expected.getFacebookId(), actual.getFacebookId());
        assertEquals("Event 'start time' is wrong", expected.getStartTime(), actual.getStartTime());
        assertEquals("Event 'end time' is wrong", expected.getEndTime(), actual.getEndTime());
        assertNotNull("Expected event invites list is null", expected.getInvites());
        assertNotNull("Actual event invites list is null", actual.getInvites());
        assertEquals("Event invites are is wrong", expected.getInvites().size(), actual.getInvites().size());
    }
}
