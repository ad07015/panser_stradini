/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TopUnsportsmanlikeTableRow implements Comparable {

    private String firstName;
    private String lastName;
    private String teamName;
    private Integer violationCount;

    public TopUnsportsmanlikeTableRow() {
        this.violationCount = 0;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Integer violationCount) {
        this.violationCount = violationCount;
    }

    @Override
    public String toString() {
        return "TopUnsportsmanlikeTableRow{" + "firstName=" + firstName + ", lastName=" + lastName + ", teamName=" + teamName + ", violationCount=" + violationCount + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopUnsportsmanlikeTableRow) {
            TopUnsportsmanlikeTableRow other = (TopUnsportsmanlikeTableRow) o;
            if (this.violationCount > other.getViolationCount()) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
