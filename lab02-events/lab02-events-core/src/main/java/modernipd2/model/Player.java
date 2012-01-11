/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = "GetPlayerByTeamAndPlayerNumber",
		query = "SELECT p FROM Player p WHERE p.playerNumber = :playerNumber AND p.team = :team")
})
@Entity
public class Player implements PersistentEntity, Serializable, Comparable {
        
    private Long id;
    private String firstName;
    private String lastName;
    private String playerNumber;
    private String playerRole;
    private Team team;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity=Team.class)
    @JoinColumn(name="TEAM_FK")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player other = (Player) obj;
            if (this.id != null && other.getId() != null) {
                if (this.id.equals(other.getId())) {
                    return true;
                }
            } else if (other.getTeam().equals(this.team)
                    && other.getPlayerNumber().equals(this.playerNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("First name: ").append(this.firstName);
        sb.append("; ");
        sb.append("Last name: ").append(this.lastName);
        sb.append("; ");
        sb.append("Player number: ").append(this.playerNumber);
        sb.append("; ");
        sb.append("Player role: ").append(this.playerRole);
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;
        
        if (o instanceof Player) {
            Player other = (Player) o;
            return this.playerNumber.compareTo(other.getPlayerNumber());
        }
        return 1;
    }
}
