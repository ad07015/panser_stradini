package lv.lu.events.portal.mvc.controller;

import lv.lu.events.comparator.InviteCreationDateComparator;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import lv.lu.events.comparator.EventStartTimeComparator;
import lv.lu.events.constants.Constants;
import lv.lu.events.interfaces.service.EventService;
import lv.lu.events.interfaces.service.InviteService;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DisplayEventsController {
    private static final String VIEW_NAME = "display_events";
    
    public static final String ATTR_INVITATIONS = "invitations";
    public static final String ATTR_MY_EVENTS = "myEvents";
    public static final String ATTR_ATTEND = "attendings";
    
    private InviteService invService;  
    private EventService evService;
    
    @Autowired
    public DisplayEventsController(InviteService invService, EventService evService) {
        this.invService = invService;
        this.evService = evService;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String displayStartPage(ModelMap model, HttpSession session) {
        // get current user from session
        User currentUser = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        List <Invite> invitations = invService.getUserNotRepliedInvites(currentUser);
        Collections.sort(invitations, new InviteCreationDateComparator());
        model.addAttribute(ATTR_INVITATIONS, invitations);
        
        List <Event> myEvents = evService.getEventsByOwner(currentUser);
        Collections.sort(myEvents, new EventStartTimeComparator());
        model.addAttribute(ATTR_MY_EVENTS, myEvents);
        
        List <Event> attendings = evService.getUserAttendingEvents(currentUser);
        Collections.sort(attendings, new EventStartTimeComparator());
        model.addAttribute(ATTR_ATTEND, attendings);
        
        return VIEW_NAME;
    }
    
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String respondToEventInvite(ModelMap model, HttpSession session, String response, long eventId) {
        
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        Event event = evService.getById(eventId);
        if (event != null) {
            Invite invite = invService.getUserInviteByEvent(event, user);
            if (invite != null) {
                if (response.equals("attend")) {
                    invService.updateInviteStatus(invite, InviteStatus.ATTENDING);
                } else if (response.equals("decline")) {
                    invService.updateInviteStatus(invite, InviteStatus.DECLINED);
                }
            }
        }
        return displayStartPage(model, session);
    }
}
