package lv.lu.events.model;

import lv.lu.events.constants.JPQConst;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import javax.persistence.Temporal;
import lv.lu.events.interfaces.PersistentEntity;

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = JPQConst.InviteJpq.GET_USER_INVITES_BY_STATUS, 
		query = "SELECT i FROM Invite i WHERE i.invited = :invited AND i.status = :status"),
    @NamedQuery(name = JPQConst.InviteJpq.QUERY_GET_USER_INVITE_BY_EVENT, 
		query = "SELECT i FROM Invite i WHERE i.invited = :invited AND i.event = :event")
})
@Entity
public class Invite implements PersistentEntity, Serializable {

    /**
     * Persistent fields
     */
    @Id
    @GeneratedValue
    @Column(name = "INVITE_ID")
    private Long id;
    @Column(name = "CREATED_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "EVENT_FK")
    private Event event;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATOR_FK")
    private User creator;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVITED_FK")
    private User invited;
    private InviteStatus status;
    @Column(name = "STATUS_UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date statusUpdateDate;

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getInvited() {
        return invited;
    }

    public void setInvited(User invited) {
        this.invited = invited;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public void setStatus(InviteStatus status) {
        this.status = status;
    }

    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(Date statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Invite) {
            Invite invite = (Invite) obj;
            if (this.event.equals(invite.getEvent())
                    && this.invited.equals(invite.getInvited())
                    && this.creator.equals(invite.getCreator())) {

                return true;
            }
        }
        return false;
    }
}