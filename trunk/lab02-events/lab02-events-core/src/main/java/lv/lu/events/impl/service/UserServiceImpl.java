package lv.lu.events.impl.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import lv.lu.events.interfaces.CommonDAO;
import lv.lu.events.interfaces.service.JSONService;

import lv.lu.events.impl.AbstractDAOImpl;

import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.constants.JPQConst;
import lv.lu.events.model.User;

/**
 * Service for operations related to users.
 */
public class UserServiceImpl extends AbstractDAOImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class.getSimpleName());
    private CommonDAO commonDAO;
    private JSONService jsonService;

    public UserServiceImpl() {
    }
    
    @Autowired
    public UserServiceImpl(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public User getByUsername(String username) {
        if (username == null) {
            return null;
        }

        User user = null;
        try {
            Query query = getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
        } catch (NoResultException nre) {
            // no such user
            System.out.println("User with username '" + username + "' does not exist");
        } catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple users with username '" + username + "' in database");
        }
        return user;
    }

    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param excludeUsername - username, which should be excluded from result list
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(String excludeUsername) {
        List<User> users;
        if (excludeUsername == null) {
            users = commonDAO.findAll(User.class);
        } else {
            Query query = getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME);
            query.setParameter("username", excludeUsername);
            users = query.getResultList();
        }
        Collections.sort(users);
        return users;
    }

    @Override
    public User getUserByAccessToken(String accessToken) {
        User result = null;
        JSONObject jsonUser = jsonService.getUserByAccessToken(accessToken);

        if (jsonUser != null) {
            // Get current user facebook id
            String currentUserFacebookId = null;
            try {
                currentUserFacebookId = jsonUser.getString("id");
            } catch (JSONException e) {
                log.error("Failed to get facebookId from JSON object");
                log.error(e);
            }
            // Use named query to get current user object from the database
            Query queryGetUserByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_FACEBOOK_ID);
            queryGetUserByFacebookId.setParameter("facebookId", currentUserFacebookId);
            try {
                result = (User) queryGetUserByFacebookId.getSingleResult();
            } catch (NoResultException e) {
            }
        }
        return result;
    }

    @Override
    public List<User> getAllUsersFromDB() {
        return commonDAO.findAll(User.class);
    }

    @Override
    public User getUserByFacebookId(String facebookId) {
        User result = null;
        Query queryGetUserByFacebookId;
        queryGetUserByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_FACEBOOK_ID);
        queryGetUserByFacebookId.setParameter("facebookId", facebookId);
        try {
            result = (User) queryGetUserByFacebookId.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public void setJsonService(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public User getById(Long id) {
       return commonDAO.getById(User.class, id);
    }
}
