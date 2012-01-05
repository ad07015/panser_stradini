package lv.lu.events.impl.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import lv.lu.events.interfaces.CommonDAO;
import lv.lu.events.interfaces.PersistentEntity;
import lv.lu.events.interfaces.service.EventService;
import lv.lu.events.model.Event;
import lv.lu.events.constants.JPQConst;
import lv.lu.events.interfaces.service.InviteService;
import lv.lu.events.model.Invite;
import lv.lu.events.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceImpl implements EventService {

    protected CommonDAO commonDAO;
    protected InviteService invService;

    public EventServiceImpl() {
    }

    @Autowired
    public EventServiceImpl(CommonDAO commonDAO, InviteService invService) {
        this.commonDAO = commonDAO;
        this.invService = invService;
    }
    
    @Override
    public Event getById(Long id) {
        return commonDAO.getById(Event.class, id);
    }
    
    @Override
    public Event getEventByFacebookId(String facebookId) {
        Event result = null;
        Query queryGetEventByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.EventJpq.QUERY_GET_EVENT_BY_FACEBOOK_ID);
        queryGetEventByFacebookId.setParameter("facebookId", facebookId);
        try {
            result = (Event) queryGetEventByFacebookId.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public void updateEvent(Event existingEvent, Event newEvent) {
        existingEvent.setName(newEvent.getName());
        existingEvent.setLocation(newEvent.getLocation());
        existingEvent.setDescription(newEvent.getDescription());
        existingEvent.setStartTime(newEvent.getStartTime());
        existingEvent.setEndTime(newEvent.getEndTime());
        existingEvent.setContactPhone(newEvent.getContactPhone());
        existingEvent.setOwner(newEvent.getOwner());
        commonDAO.save(existingEvent);
    }

    @Override
    public void addEvent(Event event) {
        commonDAO.save(event);
    }

    @Override
    public List<Event> getAllEventsFromDB() {
        return commonDAO.findAll(Event.class);
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public void setInviteService(InviteService invService) {
        this.invService = invService;
    }

    @Override
    public List<Event> getEventsByOwner(User owner) {
        List<Event> result = null;
        Query queryGetEventByFacebookId = commonDAO.getEntityManager().createNamedQuery(JPQConst.EventJpq.QUERY_GET_EVENTS_BY_OWNER);
        queryGetEventByFacebookId.setParameter("owner", owner);
        try {
            result = (List<Event>) queryGetEventByFacebookId.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public List<Event> getUserInvitedEvents(User owner) {
        List<Invite> notRepliedInvites = invService.getUserNotRepliedInvites(owner);
        List<Event> notRepliedEvents = new ArrayList<Event>();
        for (Invite invite : notRepliedInvites) {
            notRepliedEvents.add(invite.getEvent());
        }
        return notRepliedEvents;
    }

    @Override
    public List<Event> getUserAttendingEvents(User currentUser) {
        List<Invite> attendingInvites = invService.getUserAttendingInvites(currentUser);
        List<Event> attendingEvents = new ArrayList<Event>();
        for (Invite invite : attendingInvites) {
            attendingEvents.add(invite.getEvent());
        }
        return attendingEvents;
    }

    @Override
    public void save(PersistentEntity object) {
        commonDAO.save(object);
    }

    @Override
    public void saveAll(Collection<? extends PersistentEntity> objectList) {
        commonDAO.saveAll(objectList);
    }
}
