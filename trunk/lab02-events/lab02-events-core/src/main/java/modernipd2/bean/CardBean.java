/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.bean;

/**
 *
 * @author andrejs.dasko
 */
public class CardBean {
    
    Integer yellowCardCount;
    Integer redCardCount;

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
}
