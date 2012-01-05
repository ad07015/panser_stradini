package lv.lu.events.portal.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import lv.lu.events.constants.Constants;
import lv.lu.events.model.User;
import lv.lu.events.interfaces.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import org.springframework.ui.ModelMap;

/**
 * Controller for displaying application home page.
 * 
 * XML configuration based controller, configured in events-servlet.xml. 
 */
public class HomePageController extends AbstractController{
    
    private String viewName;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView(viewName);
        return model;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}