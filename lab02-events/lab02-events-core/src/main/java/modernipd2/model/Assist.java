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
import javax.persistence.OneToOne;
import modernipd2.constants.Constants;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = Constants.AssistJpq.QUERY_GET_ALL_BY_PLAYER,
    query = "SELECT a FROM Assist a WHERE a.player = :player")
})
@Entity
public class Assist implements PersistentEntity, Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Player player;
    @ManyToOne(targetEntity=Goal.class)
    @JoinColumn(name="GOAL_FK")
    private Goal goal;

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
