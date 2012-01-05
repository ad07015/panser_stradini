/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.portal.mvc.controller;

import lv.lu.events.form.EventForm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import lv.lu.events.comparator.InviteStatusComparator;
import lv.lu.events.constants.Constants;
import lv.lu.events.interfaces.service.EventService;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.interfaces.service.InviteService;
import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.PrivateEvent;
import lv.lu.events.model.PublicEvent;
import lv.lu.events.model.User;
import lv.lu.events.portal.mvc.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author root
 */
@Controller
public class NewEventController {

    private static final String VIEW_NAME = "event_form";
    private static final String ATTR_FRIEND_LIST = "friendList";
    private static final String ATTR_EDIT_FORM = "editing";
    private static final String ATTR_READONLY_FORM = "readonly";
    private static final String ATTR_EVENT_INVITEES = "event_invitees";
    private static final String ATTR_EVENT_FORM = "eventForm";
    private static final String MODEL_ATTRIBUTE = "eventForm";
    private FriendshipService frService;
    private InviteService invService;
    private EventService evService;
    private UserService usService;
    private EventValidator eventValidator;
    private List<User> friendList;

    @Autowired
    public NewEventController(FriendshipService frService, InviteService invService, EventService evService, EventValidator eventValidator, UserService usService) {
        this.frService = frService;
        this.eventValidator = eventValidator;
        this.invService = invService;
        this.evService = evService;
        this.usService = usService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private String displayNewEventForm(ModelMap model, HttpSession session, Boolean privateForm) {
        // get current user from session
        User currentUser = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);

        friendList = frService.getFriends(currentUser.getId());

        EventForm eventForm = new EventForm();
        eventForm.setPrivateType(privateForm);
        eventForm.getInviteList().add(currentUser.getId());
        model.addAttribute(ATTR_FRIEND_LIST, friendList);
        model.addAttribute(ATTR_EVENT_FORM, eventForm);
        model.addAttribute(ATTR_EDIT_FORM, false);
        model.addAttribute(ATTR_READONLY_FORM, false);
        return VIEW_NAME;
    }
    
    @RequestMapping(value = "/event_private_form", method = RequestMethod.GET)
    public String displayNewPrivateEventForm(ModelMap model, HttpSession session) {
        return displayNewEventForm(model, session, true);
    }
     
    @RequestMapping(value = "/event_public_form", method = RequestMethod.GET)
    public String displayNewPublicEventForm(ModelMap model, HttpSession session) {
        return displayNewEventForm(model, session, false);
    }

    @RequestMapping(value = "/event_form", method = RequestMethod.POST)
    public String onNewEventFormSubmit(ModelMap model, HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) EventForm eventForm, BindingResult result) {

        if (eventForm.getId() != null) {
            return onUpdateEventFormSubmit(model, session, eventForm, result);
        }

        // get current user from session
        User currentUser = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        // validate new event form data
        eventValidator.validate(eventForm, result);
        
        if (result.hasErrors()) {
            // display form with validation errors
            model.addAttribute(ATTR_FRIEND_LIST, friendList);
            model.addAttribute(ATTR_EVENT_FORM, eventForm);
            model.addAttribute(ATTR_EDIT_FORM, false);
            model.addAttribute(ATTR_READONLY_FORM, false);
            return VIEW_NAME;
        }

        Event event;
        if (eventForm.getPrivateType()) {
            event = new PrivateEvent();
            ((PrivateEvent) event).setAccessCode(eventForm.getAccessCode());
        } else {
            event = new PublicEvent();
        }
        event.setName(eventForm.getName());
        event.setDescription(eventForm.getDescription());
        event.setLocation(eventForm.getLocation());
        event.setContactPhone(eventForm.getContactPhone());
        event.setStartTime(eventForm.getStartTime());
        event.setEndTime(eventForm.getEndTime());
        event.setOwner(currentUser);

        evService.addEvent(event);

        List<Long> invitedUsersIdList;
        if (eventForm.getInviteList() != null) {
            invitedUsersIdList = eventForm.getInviteList();
        } else {
            invitedUsersIdList = new ArrayList<Long>();
        }
        invitedUsersIdList.add(currentUser.getId());
        Invite invite;
        List<Invite> invites = new ArrayList<Invite>();
        for (Long userId : invitedUsersIdList) {
            User invitee = usService.getById(userId);
            invite = new Invite();
            invite.setCreator(currentUser);
            invite.setInvited(invitee);
            invite.setEvent(event);
            invite.setStatus(InviteStatus.NOT_REPLIED);
            invite.setCreatedDate(Calendar.getInstance().getTime());
            invite.setStatusUpdateDate(Calendar.getInstance().getTime());
            invites.add(invite);
        }
        invService.addInvitesToEvent(event, invites);

        return "redirect:/events";
    }

    public String onUpdateEventFormSubmit(ModelMap model, HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) EventForm eventForm, BindingResult result) {
        // get current user from session
        User currentUser = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        // validate new event form data
        eventValidator.validate(eventForm, result);
        Event event = evService.getById(eventForm.getId());

        if (result.hasErrors() || event == null) {
            // display form with validation errors
            model.addAttribute(ATTR_FRIEND_LIST, friendList);
            model.addAttribute(ATTR_EVENT_FORM, eventForm);
            model.addAttribute(ATTR_EDIT_FORM, true);
            model.addAttribute(ATTR_READONLY_FORM, !currentUser.equals(event.getOwner()));
            return VIEW_NAME;
        }
        Event updatedEvent = new Event();
        updatedEvent.setContactPhone(eventForm.getContactPhone());
        updatedEvent.setDescription(eventForm.getDescription());
        updatedEvent.setEndTime(eventForm.getEndTime());
        updatedEvent.setLocation(eventForm.getLocation());
        updatedEvent.setName(eventForm.getName());
        updatedEvent.setStartTime(eventForm.getStartTime());
        updatedEvent.setOwner(currentUser);
        evService.updateEvent(event, updatedEvent);

        updatedEvent = evService.getById(eventForm.getId());

        if (eventForm.getInviteList() != null) {
            List<Long> invitedUsersIdList = eventForm.getInviteList();
            Invite invite;
            List<Invite> invites = new ArrayList<Invite>();
            for (Long userId : invitedUsersIdList) {
                User invitee = usService.getById(userId);
                invite = new Invite();
                invite.setCreator(currentUser);
                invite.setInvited(invitee);
                invite.setEvent(updatedEvent);
                invite.setStatus(InviteStatus.NOT_REPLIED);
                invite.setCreatedDate(Calendar.getInstance().getTime());
                invite.setStatusUpdateDate(Calendar.getInstance().getTime());
                invites.add(invite);
            }
            invService.addInvitesToEvent(updatedEvent, invites);
        }
        return "redirect:/events";
    }

    @RequestMapping(value = "/event_view", method = RequestMethod.GET)
    public String displayEventForm(ModelMap model, HttpSession session, long eventId) {
        User currentUser = (User) session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        Event event = evService.getById(eventId);
        if (event == null) {
            return VIEW_NAME;
        }
        List<User> friendsAtHome = frService.getFriends(currentUser.getId());
        User invitedUser;
        for (Invite invite : event.getInvites()) {
            invitedUser = invite.getInvited();
            if (friendsAtHome.contains(invitedUser)) {
                friendsAtHome.remove(invitedUser);
            }
        }
        List<Invite> eventInvites = event.getInvites();
        Collections.sort(eventInvites, new InviteStatusComparator());
        
        EventForm eventForm = new EventForm(event);
        Collections.sort(eventInvites, new InviteStatusComparator());

        eventForm.setPrivateType((event instanceof PrivateEvent));
        model.addAttribute(ATTR_EVENT_FORM, eventForm);
        model.addAttribute(ATTR_EVENT_INVITEES, eventInvites);
        model.addAttribute(ATTR_FRIEND_LIST, friendsAtHome);
        model.addAttribute(ATTR_EDIT_FORM, true);
        model.addAttribute(ATTR_READONLY_FORM, !currentUser.equals(event.getOwner()));
        return VIEW_NAME;
    }
}
