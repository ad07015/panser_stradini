package lv.lu.events.impl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import lv.lu.events.interfaces.CommonDAO;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.model.Friendship;
import lv.lu.events.model.FriendshipStatus;
import lv.lu.events.constants.JPQConst;
import lv.lu.events.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendshipServiceImpl implements FriendshipService {

    private Logger log = Logger.getLogger(FriendshipServiceImpl.class.getSimpleName());
    protected CommonDAO commonDAO;


    public FriendshipServiceImpl() {
    }
    
    @Autowired
    public FriendshipServiceImpl(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void addFriend(User user1, User user2) {
        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendship.setLastUpdated(Calendar.getInstance().getTime());

        Query queryGetFriendshipByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.FriendshipJpq.QUERY_GET_FRIENDSHIP_BY_TWO_USERS);

        queryGetFriendshipByFacebookId.setParameter("user1", user1);
        queryGetFriendshipByFacebookId.setParameter("user2", user2);
        Collection<Friendship> frienshipList = queryGetFriendshipByFacebookId.getResultList();
        if (frienshipList.size() == 0) {
            commonDAO.save(friendship);
            log.info("Friendship between current user [" + user1.getFullName() + "] and user [" + user2.getFullName() + "] has been saved to the database");
        } else {
            log.info("Friendship between current user [" + user1.getFullName() + "] and user [" + user2.getFullName() + "] is already persisted in the database");
        }
    }

    @Override
    public List<User> getFriends(Long userId) {
        User user = commonDAO.getById(User.class, userId);
        List<Friendship> acceptedFriendshipList = new LinkedList<Friendship>();
        List<Friendship> fullFriendshipList = commonDAO.findAll(Friendship.class);
        for (Friendship friendship : fullFriendshipList) {
            if (friendship.getStatus().equals(FriendshipStatus.ACCEPTED)) {
                acceptedFriendshipList.add(friendship);
            }
        }
        List<User> resultList = new LinkedList<User>();
        for (Friendship fr : acceptedFriendshipList) {
            if (fr.getUser1().equals(user)) {
                resultList.add(fr.getUser2());
            } else if (fr.getUser2().equals(user)) {
                resultList.add(fr.getUser1());
            }
        }
        return resultList;
    }
    
    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public Friendship getFriendship(User user1, User user2) {
        Query queryGetFriendshipByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.FriendshipJpq.QUERY_GET_FRIENDSHIP_BY_TWO_USERS);

        queryGetFriendshipByFacebookId.setParameter("user1", user1);
        queryGetFriendshipByFacebookId.setParameter("user2", user2);
        List<Friendship> friendshipList = queryGetFriendshipByFacebookId.getResultList();
        if (friendshipList != null && !friendshipList.isEmpty()) {
            return friendshipList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void sendRequest(User user1, User user2) {
        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setStatus(FriendshipStatus.REQUESTED);
        friendship.setLastUpdated(Calendar.getInstance().getTime());
        commonDAO.save(friendship);
        log.info("Friendship request between current user [" + user1.getFullName() + "] and user [" + user2.getFullName() + "] has been saved to the database");
       
    }
    
    @Override
    public void declineRequest(User user1, User user2) {
        Friendship friendship = getFriendship(user1, user2);
        friendship.setStatus(FriendshipStatus.DECLINED);
        friendship.setLastUpdated(Calendar.getInstance().getTime());
        commonDAO.save(friendship);
    }

    @Override
    public void acceptRequest(User user1, User user2) {
        Friendship friendship = getFriendship(user1, user2);
        friendship.setLastUpdated(Calendar.getInstance().getTime());
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        commonDAO.save(friendship);
    }

    @Override
    public List<Friendship> getFriendshipRequests(User user) {
        Query queryGetFriendshipByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.FriendshipJpq.QUERY_GET_FRIENDSHIP_REQUESTS);
        queryGetFriendshipByFacebookId.setParameter("user", user);
        queryGetFriendshipByFacebookId.setParameter("status", FriendshipStatus.REQUESTED);
        List<Friendship> friendshipList = queryGetFriendshipByFacebookId.getResultList();
        if (friendshipList != null && !friendshipList.isEmpty()) {
            return friendshipList;
        } else {
            return new ArrayList<Friendship>();
        }
    }

    @Override
    public long getFriendshipRequestCount(User user) {
        List<Friendship> resultList = getFriendshipRequests(user);
        if (resultList != null)
            return resultList.size();
        else
            return 0;
    }
}
