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

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class Game implements PersistentEntity, Serializable {
    
    private Long id;
    private Date date;
    private String venue;
    private Integer viewerCount;
    private Team team1;
    private Team team2;
    private Referee mainReferee;
    private Referee lineReferee1;
    private Referee lineReferee2;
    
    private Set<Player> team1InitialPlayerList = new LinkedHashSet<Player>();
    private Set<Player> team2InitialPlayerList = new LinkedHashSet<Player>();
    
    private Set<Goal> goalList = new TreeSet<Goal>();
    
    private Set<Violation> violationList = new TreeSet<Violation>();
    
    private Set<Substitusion> substitusionList = new LinkedHashSet<Substitusion>();
    
    public Game() {
    }

    public Game(Date date, String venue, Integer viewerCount) {
        this.date = date;
        this.venue = venue;
        this.viewerCount = viewerCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        sb.append("Datums: ").append(this.date).append("\nVieta: ").append(this.venue).append("\nSkatitaju skaits: ").append(this.viewerCount);
        return sb.toString();
    }
}
