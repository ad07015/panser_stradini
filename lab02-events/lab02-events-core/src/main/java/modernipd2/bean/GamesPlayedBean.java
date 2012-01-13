/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.bean;

/**
 *
 * @author andrejs.dasko
 */
public class GamesPlayedBean {
    
    Integer gamesPlayedCount;
    Integer gamesPlayedInStartingLineupCount;
    Integer minutesPlayed;

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

    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
