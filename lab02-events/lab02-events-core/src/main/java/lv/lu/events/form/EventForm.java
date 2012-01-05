package lv.lu.events.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.PrivateEvent;
/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class EventForm {
    
    private Long id;
    private String name;
    private String description;
    private String location;
    private String contactPhone;
    private Date startTime;
    private Date endTime;
    private String accessCode;
    private List<Long> inviteList = new ArrayList<Long>();
    private Boolean privateType;

    public EventForm() {
        setPrivateType(false);
    }
    
    public EventForm(Event event) {
//        id = event.getId();
        setId(event.getId());
        setContactPhone(event.getContactPhone());
        setName(event.getName());
        setDescription(event.getDescription());
        setEndTime(event.getEndTime());
        setStartTime(event.getStartTime());
        
        List <Long> invitesIds = new ArrayList<Long>();
        for (Invite invite : event.getInvites()) {
            invitesIds.add(invite.getId());
        }
        setInviteList(invitesIds);
        setLocation(event.getLocation());
        try {
            PrivateEvent privateEvent = (PrivateEvent) event;
            setAccessCode(privateEvent.getAccessCode());
        } catch (Exception e) {
            
        }
    }
    
    public void setPrivateType(Boolean privateType) {
        this.privateType = privateType;
    }
    public Boolean getPrivateType() {
        return this.privateType;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Long> getInviteList() {
        return inviteList;
    }

    public void setInviteList(List<Long> inviteList) {
        this.inviteList = inviteList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
