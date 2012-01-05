/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = "GetTeamByName",
		query = "SELECT t FROM Team t WHERE t.teamName = :teamName")
})

@Entity
public class Team implements PersistentEntity, Serializable {
    
    private Long id;
    private String teamName;
    
    private Set<Player> playerList = new TreeSet();

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @OneToMany(targetEntity=Player.class, mappedBy="team", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    public Set<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Set<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public String toString() {
        return "Team name: " + this.teamName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Team) {
            Team other = (Team) obj;
            if (other.getTeamName().equals(this.teamName)) {
                return true;
            }
        }
        return false;
    }
}
