package lv.lu.events.interfaces.service;

import java.util.List;

import lv.lu.events.model.Friendship;
import lv.lu.events.model.FriendshipStatus;
import lv.lu.events.model.User;

/**
 * Service for dealing with friendships.
 */
public interface FriendshipService {

    /**
     * Creates a friendship between two users with status FriendshipStatus.ACCEPTED.
     */
    public void addFriend(User user1, User user2);
    
    public void sendRequest(User user1, User user2);
    
    public void declineRequest(User user1, User user2);
    
    public void acceptRequest(User user1, User user2);

    /**
     * Retrieves a list of users who are in ACCEPTED friendship with requested user.
     */
    public List<User> getFriends(Long userId);
    
    public Friendship getFriendship(User user1, User user2);
    
    public List<Friendship> getFriendshipRequests(User user);
    
    public long getFriendshipRequestCount(User user);
}
