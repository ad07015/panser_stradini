/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = "GetGameByVenueAndTeams",
    query = "SELECT g FROM Game g WHERE g.team1 = :team1 AND g.team2 = :team2 AND g.venue = :venue AND g.gameDate = :gameDate")
})
@Entity
public class Game implements PersistentEntity, Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date gameDate;
    private String venue;
    private Integer viewerCount;
    @OneToOne
    private Team team1;
    @OneToOne
    private Team team2;
    @OneToOne
    @JoinColumn(name = "MAIN_REF_FK")
    private Referee mainReferee;
    @OneToOne
    @JoinColumn(name = "LINE_REF_1_FK")
    private Referee lineReferee1;
    @OneToOne
    @JoinColumn(name = "LINE_REF_2_FK")
    private Referee lineReferee2;
    @Transient
    private Set<Player> team1InitialPlayerList = new LinkedHashSet<Player>();
    @Transient
    private Set<Player> team2InitialPlayerList = new LinkedHashSet<Player>();
    @OneToMany(targetEntity = Goal.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Goal> goalList = new TreeSet<Goal>();
    @Transient
    private Set<Violation> violationList = new TreeSet<Violation>();
    @Transient
    private Set<Substitusion> substitusionList = new LinkedHashSet<Substitusion>();

    public Game() {
    }

    public Game(Date date, String venue, Integer viewerCount) {
        this.gameDate = date;
        this.venue = venue;
        this.viewerCount = viewerCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(Integer viewerCount) {
        this.viewerCount = viewerCount;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Referee getLineReferee1() {
        return lineReferee1;
    }

    public void setLineReferee1(Referee lineReferee1) {
        this.lineReferee1 = lineReferee1;
    }

    public Referee getLineReferee2() {
        return lineReferee2;
    }

    public void setLineReferee2(Referee lineReferee2) {
        this.lineReferee2 = lineReferee2;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
    }

    public Set<Player> getTeam1InitialPlayerList() {
        return team1InitialPlayerList;
    }

    public void setTeam1InitialPlayerList(Set<Player> team1InitialPlayerList) {
        this.team1InitialPlayerList = team1InitialPlayerList;
    }

    public Set<Player> getTeam2InitialPlayerList() {
        return team2InitialPlayerList;
    }

    public void setTeam2InitialPlayerList(Set<Player> team2InitialPlayerList) {
        this.team2InitialPlayerList = team2InitialPlayerList;
    }

    public Set<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(Set<Goal> goalList) {
        this.goalList = goalList;
    }

    public Set<Substitusion> getSubstitusionList() {
        return substitusionList;
    }

    public void setSubstitusionList(Set<Substitusion> substitusionList) {
        this.substitusionList = substitusionList;
    }

    public Set<Violation> getViolationList() {
        return violationList;
    }

    public void setViolationList(Set<Violation> violationList) {
        this.violationList = violationList;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Datums: ").append(this.gameDate).append("\nVieta: ").append(this.venue).append("\nSkatitaju skaits: ").append(this.viewerCount);
        return sb.toString();
    }
}
