/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TopTeamTableRow implements Comparable {
    
    private String teamName;
    private Integer pointCount;
    private Integer winCountMainTime;
    private Integer winCountOvertime;
    private Integer loseCountMainTime;
    private Integer loseCountOvertime;
    private Integer goalsScoredCount;
    private Integer goalsLetInCount;
    

    public TopTeamTableRow() {
        this.pointCount = 0;
        this.winCountMainTime = 0;
        this.winCountOvertime = 0;
        this.loseCountMainTime = 0;
        this.loseCountOvertime = 0;
        this.goalsScoredCount = 0;
        this.goalsLetInCount = 0;
    }
    
    public Integer getLoseCountMainTime() {
        return loseCountMainTime;
    }

    public void setLoseCountMainTime(Integer loseCountMainTime) {
        this.loseCountMainTime = loseCountMainTime;
    }

    public Integer getLoseCountOvertime() {
        return loseCountOvertime;
    }

    public void setLoseCountOvertime(Integer loseCountOvertime) {
        this.loseCountOvertime = loseCountOvertime;
    }

    public Integer getWinCountMainTime() {
        return winCountMainTime;
    }

    public void setWinCountMainTime(Integer winCountMainTime) {
        this.winCountMainTime = winCountMainTime;
    }

    public Integer getWinCountOvertime() {
        return winCountOvertime;
    }

    public void setWinCountOvertime(Integer winCountOvertime) {
        this.winCountOvertime = winCountOvertime;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGoalsScoredCount() {
        return goalsScoredCount;
    }

    public void setGoalsScoredCount(Integer goalCount) {
        this.goalsScoredCount = goalCount;
    }

    public Integer getGoalsLetInCount() {
        return goalsLetInCount;
    }

    public void setGoalsLetInCount(Integer goalsLetInCount) {
        this.goalsLetInCount = goalsLetInCount;
    }

    @Override
    public String toString() {
        return "TopTeamTableRow{" + "teamName=" + teamName + ", pointCount=" + pointCount + ", winCountMainTime=" + winCountMainTime + ", winCountOvertime=" + winCountOvertime + ", loseCountMainTime=" + loseCountMainTime + ", loseCountOvertime=" + loseCountOvertime + ", goalsScoredCount=" + goalsScoredCount + ", goalsLetInCount=" + goalsLetInCount + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopTeamTableRow) {
            TopTeamTableRow other = (TopTeamTableRow) o;
            if (this.pointCount > other.getPointCount()) {
                return BEFORE;
            } else if (this.pointCount < other.getPointCount()) {
                return AFTER;
            } else {
                if (this.goalsScoredCount > other.goalsScoredCount) {
                    return BEFORE;
                } else {
                    return AFTER;
                }
            }
        }
        return 1;
    }
}
