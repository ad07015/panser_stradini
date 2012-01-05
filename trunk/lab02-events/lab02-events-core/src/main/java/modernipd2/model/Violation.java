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
public class Violation implements PersistentEntity, Serializable, Comparable {
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(targetEntity=Game.class)
    @JoinColumn(name="GAME_FK")
    private Game game;
    private Integer violationTime;
    @OneToOne
    @JoinColumn(name="PLAYER_FK")
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Integer violationTime) {
        this.violationTime = violationTime;
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;
        
        if (o instanceof Violation) {
            Violation other = (Violation) o;
            if (other.getViolationTime() > this.violationTime) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Violation{" + "violationTime=" + Utils.getTimeAsString(violationTime) + ", player=" + player + '}';
    }
}
