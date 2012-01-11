/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.interfaces.service.TeamService;
import modernipd2.model.Game;
import modernipd2.model.GameTeam;
import modernipd2.model.Team;
import modernipd2.persistance.CommonDAO;
import modernipd2.statistics.TopTeamTableRow;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class StatisticsProcessor {

    private TeamService teamService;
    private PlayerService playerService;
    protected CommonDAO commonDAO;

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
        List<TopTeamTableRow> rowList = new ArrayList<TopTeamTableRow>();
        TopTeamTableRow row = new TopTeamTableRow();
        
        List<Team> teamList = getAllTeams(gameList);
        List<GameTeam> gameTeamList = null;
        for (Team team : teamList) {
            gameTeamList = playerService.getAllGameTeamByTeam(team);
            if (gameTeamList != null && gameTeamList.size() > 0) {
                row = new TopTeamTableRow();
                row.setTeamName(team.getTeamName());
                
                for (GameTeam gameTeam : gameTeamList) {
                    row.setGoalCount(gameTeam.getGoalCount());
                    row.setPointCount(row.getPointCount() + gameTeam.getPoints());
                    if (gameTeam.getWon()) {
                        if (!isOvertime(gameTeam.getGame())) {
                            row.setWinCountMainTime(row.getWinCountMainTime() + 1);
                        } else {
                            row.setWinCountOvertime(row.getWinCountOvertime() + 1);
                        }
                    } else {
                        if (!isOvertime(gameTeam.getGame())) {
                            row.setLoseCountMainTime(row.getLoseCountMainTime() + 1);
                        } else {
                            row.setLoseCountOvertime(row.getLoseCountOvertime() + 1);
                        }
                    }
                }
                rowList.add(row);
            }
        }
        
        for (TopTeamTableRow r : rowList) {
            System.out.println(r);
        }
    }

    private List<Team> getAllTeams(List<Game> gameList) {
        List<Team> teamList = commonDAO.findAll(Team.class);
        return teamList;
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    private boolean isOvertime(Game game) {
        return game.getGameEndTime() > 90 * 60;
    }
}
