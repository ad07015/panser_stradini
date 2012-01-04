/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.constants.utils;

import modernipd2.exception.PlayerNotFoundException;
import java.util.Set;
import modernipd2.model.Player;
import modernipd2.model.Team;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TeamUtils {
    
    public static Player findPlayerByPlayerNumber(Team team, String playerNumber) {
        Set<Player> playerList = team.getPlayerList();
        for (Player player : playerList) {
            if (player.getPlayerNumber().equals(playerNumber))
                return player;
        }
        System.out.println("---------------------THIS SHOULD NEVER BE REACHED");
        return null;
    }
}
