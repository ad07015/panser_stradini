package lv.lu.events.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lv.lu.events.constants.Constants;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.model.Friendship;
import lv.lu.events.model.User;

/**
 * Controller for displaying list of portal users.
 * 
 * Annotation-based controller.
 */
@Controller
public class DisplayFriendshipRequestsController {

    private static final String VIEW_NAME = "display_requests";
    
    private static final String ATTR_USERS = "requestList";
          
    private UserService userService;
    private FriendshipService frService;
    
    @Autowired
    public DisplayFriendshipRequestsController(UserService userService, FriendshipService frService) {
        this.userService = userService;
        this.frService = frService;
    }
    
    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String displayFriendshipRequests(ModelMap model, HttpSession session) {
        // get current user from session
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        // load all users except current
        List<Friendship> requestList = frService.getFriendshipRequests(user);
        for (Friendship friendship : requestList) {
            if (friendship.getUser2().equals(user)) {
                User switchUser = friendship.getUser1();
                friendship.setUser1(friendship.getUser2());
                friendship.setUser2(switchUser);
            }
        }
        model.addAttribute(ATTR_USERS, requestList);
        return VIEW_NAME;
    }
    
    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public String respondToFriendshipRequest(ModelMap model, HttpSession session, String response, long userId) {
        // get current user from session
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        User otherUser = userService.getById(userId);
        if (response.equals("accepted")) {
            frService.acceptRequest(user, otherUser);
        } else if (response.equals("declined")) {
            frService.declineRequest(user, otherUser);
        }
        
        Long count = (Long) session.getAttribute(Constants.SessionAttribute.SESSION_REQ_COUNT);
        long lCount = count.longValue();
        lCount--;
        session.setAttribute(Constants.SessionAttribute.SESSION_REQ_COUNT, lCount);
        
        List<User> users = userService.getAllUsers(null);
        model.addAttribute(ATTR_USERS, users);
        return displayFriendshipRequests(model, session);
    }
}
