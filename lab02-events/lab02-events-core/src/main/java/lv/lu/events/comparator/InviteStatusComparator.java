/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.comparator;


import java.util.Comparator;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;

/**
 *
 * @author Dmitry
 */
public class InviteStatusComparator implements Comparator {
/*
kuri apmeklēs pasākumu (InviteStatus.ATTENDING)
kuri vēl nav atbildējuši uz ielūgumu (InviteStatus.NOT_REPLIED)
kuri ir ielūgumu noraidījuši (InviteStatus.DECLINED)
*/
//    @Override
//    public int compare(Object o1, Object o2) {
//        Invite inv1 = (Invite) o1;
//        Invite inv2 = (Invite) o2;
//        // TODO
//        if (inv1.getStatus() == InviteStatus.ATTENDING) {
//            if (inv2.getStatus() == InviteStatus.ATTENDING) {
//                
//            } else {
//                return 1;
//            }
//        }
//        return -1;
//    }
    @Override
    public int compare(Object o1, Object o2) {
        Invite inv1 = (Invite) o1;
        Invite inv2 = (Invite) o2;
        return inv1.getStatus().compareTo(inv2.getStatus());
    }
}
