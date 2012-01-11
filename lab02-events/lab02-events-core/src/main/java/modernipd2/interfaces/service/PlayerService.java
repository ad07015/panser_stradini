/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.interfaces.service;

import java.util.Date;
import java.util.List;
import modernipd2.model.Game;
import modernipd2.model.GameTeam;
import modernipd2.model.Player;
import modernipd2.model.Referee;
import modernipd2.model.Team;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public interface PlayerService {
    
    public Player getPlayerByTeamAndNumber(Team team, String playerNumber);
    
    public Referee getRefereeByFirstNameAndLastName(String firstName, String lastName);
    
    public Game getGameByVenueAndTeams(Team team1, Team team2, String venue, Date date);
    
    public List<GameTeam> getAllGameTeamByTeam(Team team);
}
