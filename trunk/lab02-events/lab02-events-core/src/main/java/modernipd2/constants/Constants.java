/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.constants;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public final class Constants {

    public static String SEPARATOR = "-----------------------------";
    public static String ATTR_SKATITAJI = "Skatitaji";
    public static String ATTR_VIETA = "Vieta";
    public static String ATTR_DATUMS = "Laiks";
    public static String DATE_FORMAT = "yyyy/dd/MM";

    public final class GameTeamJpq {

        public static final String QUERY_GET_ALL_BY_TEAM = "GameTeam.getAllByTeam";
    }

    public final class GamePlayerJpq {

        public static final String QUERY_GET_ALL_GOALIES = "GamePlayer.getAllGoalies";
    }
}
