/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import modernipd2.model.Game;
import modernipd2.model.Goal;
import modernipd2.model.Team;
import modernipd2.statistics.TopTeamTableRow;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class StatisticsProcessor {

    void generateStatistics(List<Game> gameList) {
//        Set<Team> teamList = getAllTeams(gameList);
//        List<TopTeamTableRow> topTeamTableRowList = new ArrayList<TopTeamTableRow>();
//        TopTeamTableRow row;
//        List<Game> teamGameList = new ArrayList<Game>();
//        List<Goal> teamGoalList = new ArrayList<Goal>();
//        for (Team team : teamList) {
//            for (Game game : gameList) {
//                if (game.getTeam1().equals(team) || game.getTeam2().equals(team)) {
//                    teamGameList.add(game);
//                }
//            }
//            for (Game game : teamGameList) {
//                for (Goal goal : game.getGoalList()) {
//                    if (goal.getAuthor().getTeam().equals(team)) {
//                        teamGoalList.add(goal);
//                    }
//                }
//            }
//            
//        }
    }

    private Set<Team> getAllTeams(List<Game> gameList) {
        Set<Team> teamList = new TreeSet<Team>();
        for (Game game : gameList) {
            teamList.add(game.getTeam1());
            teamList.add(game.getTeam2());
        }
        return teamList;
    }
}
