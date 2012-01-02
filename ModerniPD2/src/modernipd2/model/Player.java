/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class Player {
        
    private String firstName;
    private String lastName;
    private String playerNumber;
    private String playerRole;
    private Team team;

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
            if (other.getTeam().equals(this.team)
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
}
