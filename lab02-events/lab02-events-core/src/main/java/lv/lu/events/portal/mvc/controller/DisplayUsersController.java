package lv.lu.events.portal.mvc.controller;

import java.util.ArrayList;
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
public class DisplayUsersController {

    private static final String VIEW_NAME = "display_users";
    private UserService userService;
    private FriendshipService friendshipService;

    @Autowired
    public DisplayUsersController(UserService userService, FriendshipService frService) {
        this.userService = userService;
        this.friendshipService = frService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String displayStartPage(ModelMap model, HttpSession session) {
        // get current user from session
        User user = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);

        // load all users except current
        List<User> users = userService.getAllUsers(user.getUsername());
        List<Friendship> friendshipList = new ArrayList<Friendship>();
        for (User temp : users) {
            Friendship friendship = friendshipService.getFriendship(temp, user);
            if (friendship == null) {
                friendship = new Friendship();
                friendship.setUser2(temp);
            } else if (friendship.getUser1().equals(temp)) {
                User switchUser = friendship.getUser1();
                friendship.setUser1(friendship.getUser2());
                friendship.setUser2(switchUser);
            }
            friendshipList.add(friendship);
        }
        
        model.addAttribute("friendshipList", friendshipList);
        return VIEW_NAME;
    }
    
    @RequestMapping(value = "/user_invite", method = RequestMethod.POST)
    public String userInvite(ModelMap model, HttpSession session, Long userId) {
        User user2 = userService.getById(userId);
        if (user2 != null) {
            User user = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
            friendshipService.sendRequest(user, user2);
        }
        return this.displayStartPage(model, session);
    }
}
