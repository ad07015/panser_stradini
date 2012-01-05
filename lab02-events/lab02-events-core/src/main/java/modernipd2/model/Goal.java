/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import modernipd2.constants.utils.Utils;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
@Entity
public class Goal implements Comparable, PersistentEntity, Serializable {

    private Long id;
    private Game game;
    private Integer time;
    private Player author;
    private Set<Player> assistList = new LinkedHashSet<Player>();

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public Player getAuthor() {
        return author;
    }

    public void setAuthor(Player author) {
        this.author = author;
    }

    @Transient
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Transient
    public Set<Player> getAssistList() {
        return assistList;
    }

    public void setAssistList(Set<Player> assistList) {
        this.assistList = assistList;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Goal{" + "time=" + Utils.getTimeAsString(time) + ", author=" + author + ", assistList=" + assistList + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof Goal) {
            Goal other = (Goal) o;
            if (other.getTime() > this.time) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
