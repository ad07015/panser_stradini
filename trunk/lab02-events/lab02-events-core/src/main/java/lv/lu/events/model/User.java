package lv.lu.events.model;

import lv.lu.events.constants.JPQConst;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lv.lu.events.interfaces.PersistentEntity;

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME,
    query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME,
    query = "SELECT u.id FROM User u WHERE u.username = :username"),
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_USER_BY_FACEBOOK_ID,
    query = "SELECT u FROM User u WHERE u.facebookId = :facebookId"),
    // get all users except one with specified username
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME,
    query = "SELECT u FROM User u WHERE u.username <> :username")
})
@Entity
public class User implements PersistentEntity, Serializable, Comparable<User> {

    /**
     * Persistent fields
     */
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "facebook_id")
    private String facebookId;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    private String fullName;
    private String email;
    @OneToMany(targetEntity = Event.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Event> createdEvents;

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (this.id.equals(user.getId())
                    && this.username.equals(user.getUsername())
                    && this.password.equals(user.getPassword())
                    && this.fullName.equals(user.getFullName())
                    && this.email.equals(user.getEmail())) {

                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.fullName;
    }
}