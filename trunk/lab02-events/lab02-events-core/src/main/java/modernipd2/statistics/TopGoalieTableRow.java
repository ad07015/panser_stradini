/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

import java.text.DecimalFormat;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TopGoalieTableRow implements Comparable {

    private String firstName;
    private String lastName;
    private String teamName;
    private Integer goalsLetInCount;
    private Integer gamesPlayedCount;
    private Double percentile;

    public TopGoalieTableRow() {
        this.gamesPlayedCount = 0;
        this.goalsLetInCount = 0;
        this.percentile = 0.0;
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

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGamesPlayedCount() {
        return gamesPlayedCount;
    }

    public void setGamesPlayedCount(Integer gamesPlayedCount) {
        this.gamesPlayedCount = gamesPlayedCount;
    }

    public Integer getGoalsLetInCount() {
        return goalsLetInCount;
    }

    public void setGoalsLetInCount(Integer goalsLetInCount) {
        this.goalsLetInCount = goalsLetInCount;
    }

    @Override
    public String toString() {
        return "TopGoalieTableRow{" + "firstName=" + firstName + ", lastName=" + lastName + ", teamName=" + teamName + ", goalsLetInCount=" + goalsLetInCount + ", gamesPlayedCount=" + gamesPlayedCount + ", percentile=" + (new DecimalFormat("0.0")).format(percentile) + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopGoalieTableRow) {
            TopGoalieTableRow other = (TopGoalieTableRow) o;
            if (this.percentile < other.getPercentile()) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
