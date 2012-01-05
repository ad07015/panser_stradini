package lv.lu.events.interfaces.service;

import java.util.Collection;
import java.util.List;

import lv.lu.events.interfaces.PersistentEntity;
import lv.lu.events.model.Event;
import lv.lu.events.model.User;

public interface EventService {

    public Event getById(Long id);
    
    public void addEvent(Event event);

    public void updateEvent(Event existingEvent, Event newEvent);

    public Event getEventByFacebookId(String facebookId);

    public List<Event> getAllEventsFromDB();
    
    public List<Event> getEventsByOwner(User owner);
    
    public List<Event> getUserInvitedEvents(User owner);

    public List<Event> getUserAttendingEvents(User currentUser);
    
    public void save(PersistentEntity object);
    
    public void saveAll(Collection<? extends PersistentEntity> objectList);
}
