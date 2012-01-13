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
public class TopFastestScoringPlayersTableRow implements Comparable {

    private String firstName;
    private String lastName;
    private String teamName;
    private Integer gamesPlayedCount;
    private Integer timePlayed;
    private String sTimePlayed;
    private Integer goalScoredCount;
    private Integer assistCount;
    private Integer timeToScoreOneGoal;
    private String sTimeToScoreOneGoal;
    private Integer timeToAssist;
    private String sTimeToAssist;
    private Double goalsScoredPerGame;
    private Double assistsPerGame;

    public TopFastestScoringPlayersTableRow() {
        timePlayed = 0;
        goalScoredCount = 0;
        gamesPlayedCount = 0;
        assistCount = 0;
        timeToAssist = 0;
        timeToScoreOneGoal = 0;
        goalsScoredPerGame = 0.0;
        assistsPerGame = 0.0;
    }

    public Double getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(Double assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGoalScoredCount() {
        return goalScoredCount;
    }

    public void setGoalScoredCount(Integer goalScoredCount) {
        this.goalScoredCount = goalScoredCount;
    }

    public Double getGoalsScoredPerGame() {
        return goalsScoredPerGame;
    }

    public void setGoalsScoredPerGame(Double goalsScoredPerGame) {
        this.goalsScoredPerGame = goalsScoredPerGame;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Integer timePlayed) {
        this.timePlayed = timePlayed;
    }

    public Integer getTimeToAssist() {
        return timeToAssist;
    }

    public void setTimeToAssist(Integer timeToAssist) {
        this.timeToAssist = timeToAssist;
    }

    public Integer getTimeToScoreOneGoal() {
        return timeToScoreOneGoal;
    }

    public void setTimeToScoreOneGoal(Integer timeToScoreOneGoal) {
        this.timeToScoreOneGoal = timeToScoreOneGoal;
    }

    public Integer getAssistCount() {
        return assistCount;
    }

    public void setAssistCount(Integer assistCount) {
        this.assistCount = assistCount;
    }

    public Integer getGamesPlayedCount() {
        return gamesPlayedCount;
    }

    public void setGamesPlayedCount(Integer gamesPlayedCount) {
        this.gamesPlayedCount = gamesPlayedCount;
    }

    public String getsTimePlayed() {
        return timePlayed / 60 + ":" + timePlayed % 60;
    }

    public String getsTimeToAssist() {
        return timeToAssist / 60 + ":" + timeToAssist % 60;
    }

    public String getsTimeToScoreOneGoal() {
        return timeToScoreOneGoal / 60 + ":" + timeToScoreOneGoal % 60;
    }

    @Override
    public String toString() {
        return "TopFastestScoringPlayersTableRow{" + "firstName=" + firstName + ", lastName=" + lastName + ", teamName=" + teamName + ", gamesPlayedCount=" + gamesPlayedCount + ", timePlayed=" + timePlayed / 60 + ":" + timePlayed % 60 + ", goalScoredCount=" + goalScoredCount + ", assistCount=" + assistCount + ", timeToScoreOneGoal=" + timeToScoreOneGoal / 60 + ":" + timeToScoreOneGoal % 60 + ", timeToAssist=" + timeToAssist / 60 + ":" + timeToAssist % 60 + ", goalsScoredPerGame=" + (new DecimalFormat("0.0")).format(goalsScoredPerGame) + ", assistsPerGame=" + (new DecimalFormat("0.0")).format(assistsPerGame) + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopFastestScoringPlayersTableRow) {
            TopFastestScoringPlayersTableRow other = (TopFastestScoringPlayersTableRow) o;
            if (this.timeToScoreOneGoal < other.timeToScoreOneGoal) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
