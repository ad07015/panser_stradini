/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.impl.service;

import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.model.Game;
import modernipd2.model.Player;
import modernipd2.model.Referee;
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

    @Override
    public Referee getRefereeByFirstNameAndLastName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null;
        }

        Referee referee = null;
        try {
            Query query = getEntityManager().createNamedQuery("GetRefereeByFirstNameAndLastName");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            referee = (Referee) query.getSingleResult();
        } catch (NoResultException nre) {
            // no such team
            System.out.println("Referee does not exist");
        } catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple referees with this name");
        }
        return referee;
    }

    @Override
    public Game getGameByVenueAndTeams(Team team1, Team team2, String venue, Date gameDate) {
        if (team1 == null || team2 == null || venue == null || gameDate == null) {
            return null;
        }

        Game game = null;
        try {
            Query query = getEntityManager().createNamedQuery("GetGameByVenueAndTeams");
            query.setParameter("team1", team1);
            query.setParameter("team2", team2);
            query.setParameter("venue", venue);
            query.setParameter("gameDate", gameDate);
            game = (Game) query.getSingleResult();
        } catch (NoResultException nre) {
            // no such team
            System.out.println("Referee does not exist");
        } catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple referees with this name");
        }
        return game;
    }
}
