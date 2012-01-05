/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.comparator;

import java.util.Comparator;
import lv.lu.events.model.Event;

/**
 *
 * @author root
 */
public class EventStartTimeComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Event inv1 = (Event) o1;
        Event inv2 = (Event) o2;
        
        if (inv1.getStartTime().before(inv2.getStartTime())) {
            return -1;
        } else if (inv1.getStartTime().after(inv2.getStartTime())) {
            return 1;
        } else 
            return 0;
    }
}
