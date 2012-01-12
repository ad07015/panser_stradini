/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.statistics;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TopRefereeTableRow implements Comparable {

    private String firstName;
    private String lastName;
    private Integer cardCount;
    private Integer gameCount;
    private Double percentile;

    public TopRefereeTableRow() {
        this.cardCount = 0;
        this.gameCount = 0;
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

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }

    public Integer getGameCount() {
        return gameCount;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    @Override
    public String toString() {
        return "TopRefereeTableRow{" + "firstName=" + firstName + ", lastName=" + lastName + ", cardCount=" + cardCount + ", gameCount=" + gameCount + ", percentile=" + percentile + '}';
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o instanceof TopRefereeTableRow) {
            TopRefereeTableRow other = (TopRefereeTableRow) o;
            if (this.percentile > other.getPercentile()) {
                return BEFORE;
            } else {
                return AFTER;
            }
        }
        return 1;
    }
}
