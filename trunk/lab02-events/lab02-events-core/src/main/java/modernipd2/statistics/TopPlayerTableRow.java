/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TopPlayerTableRow implements Comparable {
    
    private String firstName;
    private String lastName;
    private String teamName;
    private Integer goalsScoredCount;
    private Integer assistCount;

    public TopPlayerTableRow() {
        this.goalsScoredCount = 0;
        this.assistCount = 0;
    }

    public Integer getAssistCount() {
        return assistCount;
    }

    public void setAssistCount(Integer assistCount) {
        this.assistCount = assistCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "TopPlayerTableRow{" + "firstName=" + firstName + ", lastName=" + lastName + ", teamName=" + teamName + ", goalsScoredCount=" + goalsScoredCount + ", assistCount=" + assistCount + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopPlayerTableRow) {
            TopPlayerTableRow other = (TopPlayerTableRow) o;
            if (this.goalsScoredCount > other.getGoalsScoredCount()) {
                return BEFORE;
            } else if (this.goalsScoredCount < other.getGoalsScoredCount()) {
                return AFTER;
            } else {
                if (this.assistCount > other.getAssistCount()) {
                    return BEFORE;
                } else {
                    return AFTER;
                }
            }
        }
        return 1;
    }
}
