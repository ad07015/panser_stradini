/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author andrejs.dasko
 */
public class TopTeamAttendanceTableRow implements Comparable {
    
    private String teamName;
    private Integer gamesPlayedCount;
    private Integer totalViewerCount;
    private Integer percentile;

    public TopTeamAttendanceTableRow() {
    }

    public Integer getGamesPlayedCount() {
        return gamesPlayedCount;
    }

    public void setGamesPlayedCount(Integer gamesPlayedCount) {
        this.gamesPlayedCount = gamesPlayedCount;
    }

    public Integer getPercentile() {
        return percentile;
    }

    public void setPercentile(Integer percentile) {
        this.percentile = percentile;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTotalViewerCount() {
        return totalViewerCount;
    }

    public void setTotalViewerCount(Integer totalViewerCount) {
        this.totalViewerCount = totalViewerCount;
    }

    @Override
    public String toString() {
        return "TopTeamAttendanceTableRow{" + "teamName=" + teamName + ", gamesPlayedCount=" + gamesPlayedCount + ", totalViewerCount=" + totalViewerCount + ", percentile=" + percentile + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopTeamAttendanceTableRow) {
            TopTeamAttendanceTableRow other = (TopTeamAttendanceTableRow) o;
            if (this.percentile > other.getPercentile()) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
