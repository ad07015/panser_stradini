package lv.lu.events.portal.mvc.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import lv.lu.events.constants.Constants;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.model.User;
import lv.lu.events.portal.mvc.validator.LoginValidator;

/**
 * Controller for performing login and logout operations.
 * 
 * Annotation-based controller.
 */
@Controller
public class LoginLogoutController {
    
    private static final Logger log = Logger.getLogger(LoginLogoutController.class);
    
    private static final String FORM_VIEW = "login";
    private static final String LOGIN_SUCCESS_VIEW = "redirect:home";
    
    private static final String MODEL_ATTRIBUTE = "user";
    
    private UserService userService;
    private FriendshipService frService;
    /* 
     * This bean is defined in Spring MVC configuration file: 
     * \lab02-events\lab02-events-portal\src\main\webapp\WEB-INF\events-servlet.xml 
     */
    private LoginValidator loginValidator;

    @Autowired
    public LoginLogoutController(LoginValidator loginValidator, UserService userService, FriendshipService frService) {
        this.userService = userService;
        this.loginValidator = loginValidator;
        this.frService = frService;
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String setupForm(ModelMap model)
    {
        /* Empty form setup */
        model.addAttribute(MODEL_ATTRIBUTE, new User());
        return FORM_VIEW;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String processSubmit(HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) User user, BindingResult result, SessionStatus status)
    {
        // validate form data
        loginValidator.validate(user, result);
        
        if (result.hasErrors()) {
            // display form with validation errors
            return FORM_VIEW;
        }

        /* Store user's information in HTTP session */
        user = userService.getByUsername(user.getUsername());
        session.setAttribute(Constants.SessionAttribute.SESSION_USER, user);
        
        long count = 0;
        if (user != null) {
            count = frService.getFriendshipRequestCount(user);
        }

        session.setAttribute(Constants.SessionAttribute.SESSION_REQ_COUNT, count);
        status.setComplete();   
        
        log.debug("User '" + user.getUsername() + "' logged in");
        return LOGIN_SUCCESS_VIEW;
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String processLogout(HttpSession session, ModelMap model) 
    {
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        log.debug("User '" + user.getUsername() + "' logged out");
        
        // invalidate session
        session.removeAttribute(Constants.SessionAttribute.SESSION_USER);
        session.invalidate();
        return "redirect:/";
    }
}