package lv.lu.events.model;

import lv.lu.events.constants.JPQConst;
import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Temporal;
import lv.lu.events.interfaces.PersistentEntity;

@NamedQueries({
        @NamedQuery(name = JPQConst.FriendshipJpq.QUERY_GET_FRIENDSHIP_BY_TWO_USERS,
    query = "SELECT f FROM Friendship f WHERE (f.user1 = :user1 AND f.user2 = :user2) OR (f.user1 = :user2 AND f.user2 = :user1)"),
        @NamedQuery(name = JPQConst.FriendshipJpq.QUERY_GET_FRIENDSHIP_REQUESTS,
    query = "SELECT f FROM Friendship f WHERE f.user2 = :user AND f.status = :status")
})
@SuppressWarnings("serial")
@Entity
public class Friendship implements PersistentEntity, Serializable {

    /**
     * Persistent fields
     */
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user1_id")
    private User user1;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user2_id")
    private User user2;
    private FriendshipStatus status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Friendship) {
            Friendship friendship = (Friendship) obj;
            if (this.user1.equals(friendship.getUser1())
                    && this.user2.equals(friendship.getUser2())) {

                return true;
            }
        }
        return false;
    }
}