/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.impl.service;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.model.Player;
import modernipd2.model.Team;
import modernipd2.persistance.AbstractDAOImpl;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class PlayerServiceImpl extends AbstractDAOImpl implements PlayerService {

    @Override
    public Player getPlayerByTeamAndNumber(Team team, String playerNumber) {
        if (team == null || playerNumber == null) {
            return null;
        }

        Player player = null;
        try {
            Query query = getEntityManager().createNamedQuery("GetPlayerByTeamAndPlayerNumber");
            query.setParameter("team", team);
            query.setParameter("playerNumber", playerNumber);
            player = (Player) query.getSingleResult();
        } catch (NoResultException nre) {
            // no such team
            System.out.println("Player does not exist");
        } catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple players with this number from this team");
        }
        return player;
    }
}
