/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.constants.utils;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class Utils {
    
    public static String getTimeAsString(Integer time) {
        return time/60 + ":" + time%60;
    }
}
