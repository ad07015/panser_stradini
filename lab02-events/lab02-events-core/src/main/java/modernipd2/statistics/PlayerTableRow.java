/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class PlayerTableRow implements Comparable {

    private String playerNumber;
    private String firstName;
    private String lastName;
    private Integer gamesPlayedCount;
    private Integer gamesPlayedInStartingLineupCount;
    private String minutesPlayed;
    private Integer goalsScoredCount;
    private Integer assistsCount;
    private Integer yellowCardCount;
    private Integer redCardCount;

    public PlayerTableRow() {
        gamesPlayedCount = 0;
        gamesPlayedInStartingLineupCount = 0;
        goalsScoredCount = 0;
        assistsCount = 0;
        yellowCardCount = 0;
        redCardCount = 0;
    }

    public Integer getAssistsCount() {
        return assistsCount;
    }

    public void setAssistsCount(Integer assistsCount) {
        this.assistsCount = assistsCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGamesPlayedCount() {
        return gamesPlayedCount;
    }

    public void setGamesPlayedCount(Integer gamesPlayedCount) {
        this.gamesPlayedCount = gamesPlayedCount;
    }

    public Integer getGamesPlayedInStartingLineupCount() {
        return gamesPlayedInStartingLineupCount;
    }

    public void setGamesPlayedInStartingLineupCount(Integer gamesPlayedInStartingLineupCount) {
        this.gamesPlayedInStartingLineupCount = gamesPlayedInStartingLineupCount;
    }

    public Integer getGoalsScoredCount() {
        return goalsScoredCount;
    }

    public void setGoalsScoredCount(Integer goalsScoredCount) {
        this.goalsScoredCount = goalsScoredCount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(String minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Integer getRedCardCount() {
        return redCardCount;
    }

    public void setRedCardCount(Integer redCardCount) {
        this.redCardCount = redCardCount;
    }

    public Integer getYellowCardCount() {
        return yellowCardCount;
    }

    public void setYellowCardCount(Integer yellowCardCount) {
        this.yellowCardCount = yellowCardCount;
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof PlayerTableRow) {
            PlayerTableRow other = (PlayerTableRow) o;
            if (this.goalsScoredCount > other.getGoalsScoredCount()) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "PlayerTableRow{" + "playerNumber=" + playerNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", gamesPlayedCount=" + gamesPlayedCount + ", gamesPlayedInStartingLineupCount=" + gamesPlayedInStartingLineupCount + ", minutesPlayed=" + minutesPlayed + ", goalsScoredCount=" + goalsScoredCount + ", assistsCount=" + assistsCount + ", yellowCardCount=" + yellowCardCount + ", redCardCount=" + redCardCount + '}';
    }
}
