package lv.lu.events.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lv.lu.events.constants.Constants;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class DisplayFriendsController {
    private static final String VIEW_NAME = "display_friends";
    private static final String ATTR_FRIENDS = "friendList";
    private FriendshipService frService;

    @Autowired
    public DisplayFriendsController(FriendshipService frService) {
        this.frService = frService;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String displayStartPage(ModelMap model, HttpSession session) {
       // get current user from session
        User user = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        List<User> friendList = frService.getFriends(user.getId());
        
        model.addAttribute(ATTR_FRIENDS, friendList);
        return VIEW_NAME;
    }
}
