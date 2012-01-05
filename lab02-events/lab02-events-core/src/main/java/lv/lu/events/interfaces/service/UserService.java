package lv.lu.events.interfaces.service;

import java.util.List;

import lv.lu.events.model.User;

/**
 * Service for operations related to users.
 */
public interface UserService {

    public User getByUsername(String username);
    
    public User getById(Long id);
    
    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param excludeUsername - username, which should be excluded from result list
     */
    public List<User> getAllUsers(String excludeUsername);
	
	public User getUserByAccessToken(String accessToken);
	public List<User> getAllUsersFromDB();
	public User getUserByFacebookId(String facebookId);
}
