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
    @NamedQuery(name = Constants.GameTeamJpq.QUERY_GET_ALL_BY_TEAM,
    query = "SELECT gt FROM GameTeam gt WHERE gt.team = :team")
})
@Entity
public class GameTeam implements Serializable, PersistentEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "TEAM_FK")
    private Team team;
    @OneToOne
    @JoinColumn(name = "GAME_FK")
    private Game game;
    @OneToOne
    @JoinColumn(name = "WINNER_FK")
    private Team winner;
    @OneToOne
    @JoinColumn(name = "LOSER_FK")
    private Team loser;    
    
    private Boolean won;
    private Integer points;
    private Integer goalsScoredCount;
    private Integer goalsLetInCount;

    public GameTeam() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Team getLoser() {
        return loser;
    }

    public void setLoser(Team loser) {
        this.loser = loser;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Integer getGoalsLetInCount() {
        return goalsLetInCount;
    }

    public void setGoalsLetInCount(Integer goalsLetInCount) {
        this.goalsLetInCount = goalsLetInCount;
    }

    public Integer getGoalsScoredCount() {
        return goalsScoredCount;
    }

    public void setGoalsScoredCount(Integer goalsScoredCount) {
        this.goalsScoredCount = goalsScoredCount;
    }
}
