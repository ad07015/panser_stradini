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
import javax.persistence.OneToOne;
import modernipd2.constants.utils.Utils;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
@Entity
public class Substitusion implements PersistentEntity, Serializable, Comparable {
    
    @Id
    @GeneratedValue
    private Long id;
    private Integer substitusionTime;
    @ManyToOne(targetEntity=Game.class)
    @JoinColumn(name="GAME_FK")
    private Game game;
    @OneToOne
    private Team team;
    @OneToOne
    private Player removed;
    @OneToOne
    private Player added;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getAdded() {
        return added;
    }

    public void setAdded(Player added) {
        this.added = added;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getRemoved() {
        return removed;
    }

    public void setRemoved(Player removed) {
        this.removed = removed;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getSubstitusionTime() {
        return substitusionTime;
    }

    public void setSubstitusionTime(Integer substitusionTime) {
        this.substitusionTime = substitusionTime;
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;
        
        if (o instanceof Substitusion) {
            Substitusion other = (Substitusion) o;
            if (other.getSubstitusionTime() > this.substitusionTime) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Substitusion{" + "substitusionTime=" + Utils.getTimeAsString(substitusionTime) + ", team=" + team + ", removed=" + removed + ", added=" + added + '}';
    }
}
