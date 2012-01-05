package lv.lu.events.impl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import lv.lu.events.base.PersistenceBaseTest;
import lv.lu.events.model.Friendship;
import lv.lu.events.model.FriendshipStatus;
import lv.lu.events.model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for Friendship service functionality.
 * TODO [task] implement this service to make tests pass.
 */
public class FriendshipServiceImplTest extends PersistenceBaseTest {

    private static FriendshipServiceImpl friendshipService;
    private static User user1;
    private static User user2;
    
    @BeforeClass
    public static void initService(){
        friendshipService = new FriendshipServiceImpl();
        friendshipService.setCommonDAO(commonDAO);
        // TODO [task] add any other required service initialization steps here
    }
    
    @Before
    public void prepareData(){
        user1 = createTestUser();
        commonDAO.save(user1);
        user2 = createTestUser();
        commonDAO.save(user2);
        friendshipService.addFriend(user1, user2);
        flushAndClear();
    }
    
    @Test
    public void testAddFriend() {        
        List<Friendship> friendships = commonDAO.findAll(Friendship.class);
        assertNotNull(friendships);
        assertEquals("Wrong number of friendships", 1, friendships.size());
        Friendship friendship = friendships.get(0);
        assertUsersEquals(user1, friendship.getUser1());
        assertUsersEquals(user2, friendship.getUser2());
        assertEquals("Status is wrong in a friendship", FriendshipStatus.ACCEPTED, friendship.getStatus());
    }

    @Test
    public void testGetFriends() {
        List<User> friends1 = friendshipService.getFriends(user1.getId());
        assertNotNull(friends1);
        assertEquals(1, friends1.size());
        User friend1 = friends1.get(0);
        assertUsersEquals(user2, friend1);
        
        List<User> friends2 = friendshipService.getFriends(user2.getId());
        assertNotNull(friends2);
        assertEquals(1, friends2.size());
        User friend2 = friends2.get(0);
        assertUsersEquals(user1, friend2);
    }
}