/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class Team {
    
    private String teamName;
    private Set<Player> playerList = new TreeSet();

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

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
