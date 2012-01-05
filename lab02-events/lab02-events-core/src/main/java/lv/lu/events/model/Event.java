package lv.lu.events.model;

import lv.lu.events.constants.JPQConst;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import lv.lu.events.interfaces.PersistentEntity;

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = JPQConst.EventJpq.QUERY_GET_EVENT_BY_FACEBOOK_ID,
    query = "SELECT e FROM Event e WHERE e.facebookId = :facebookId"),
    @NamedQuery(name = JPQConst.EventJpq.QUERY_GET_EVENTS_BY_OWNER,
    query = "SELECT e FROM Event e WHERE e.owner = :owner")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event implements PersistentEntity, Serializable {

    /**
     * Persistent fields
     */
    @Id
    @GeneratedValue
    @Column(name = "EVENT_ID")
    private Long id;
    @Column(name = "FACEBOOK_ID")
    private String facebookId;
    private String name;
    private String description;
    private String location;
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;
    @Column(name = "START_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endTime;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "OWNER_FK")
    private User owner;
    @OneToMany(targetEntity = Invite.class, mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invite> invites = new LinkedList<Invite>();

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            if (this.name.equals(event.getName()) &&
                    this.startTime.equals(event.getStartTime()) &&
                    this.endTime.equals(event.getEndTime())) {
                return true;
            }
        }
        return false;
    }
}