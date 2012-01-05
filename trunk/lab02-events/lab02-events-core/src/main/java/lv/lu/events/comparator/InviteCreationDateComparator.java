/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.comparator;

import java.util.Comparator;
import lv.lu.events.model.Invite;

/**
 *
 * @author root
 */
public class InviteCreationDateComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Invite inv1 = (Invite) o1;
        Invite inv2 = (Invite) o2;
        
        if (inv1.getCreatedDate().before(inv2.getCreatedDate())) {
            return -1;
        } else if (inv1.getCreatedDate().after(inv2.getCreatedDate())) {
            return 1;
        } else 
            return 0;
    }
}
