/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import modernipd2.constants.Constants;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.interfaces.service.TeamService;
import modernipd2.model.Assist;
import modernipd2.model.Game;
import modernipd2.model.GamePlayer;
import modernipd2.model.GameTeam;
import modernipd2.model.Goal;
import modernipd2.model.Player;
import modernipd2.model.Team;
import modernipd2.model.Violation;
import modernipd2.persistance.CommonDAO;
import modernipd2.statistics.TopGoalieTableRow;
import modernipd2.statistics.TopPlayerTableRow;
import modernipd2.statistics.TopTeamTableRow;
import modernipd2.statistics.TopUnsportsmanlikeTableRow;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class StatisticsProcessor {

    private TeamService teamService;
    private PlayerService playerService;
    protected CommonDAO commonDAO;

    void generateStatistics(List<Game> gameList) {
        topTeam();
        topPlayer();
        topGoalie();
        topUnsportsmanlike();
    }

    private void topTeam() {
        Set<TopTeamTableRow> rowList = new TreeSet<TopTeamTableRow>();
        TopTeamTableRow row = new TopTeamTableRow();

        List<Team> teamList = getAllTeams();
        List<GameTeam> gameTeamList = null;
        for (Team team : teamList) {
            gameTeamList = playerService.getAllGameTeamByTeam(team);
            if (gameTeamList != null && gameTeamList.size() > 0) {
                row = new TopTeamTableRow();
                row.setTeamName(team.getTeamName());

                for (GameTeam gameTeam : gameTeamList) {
                    row.setGoalsScoredCount(row.getGoalsScoredCount() + gameTeam.getGoalsScoredCount());
                    row.setGoalsLetInCount(row.getGoalsLetInCount() + gameTeam.getGoalsLetInCount());
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

        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top team table ---");
        System.out.println(Constants.SEPARATOR);
        for (TopTeamTableRow r : rowList) {
            System.out.println(r);
        }
    }

    private void topPlayer() {
        Set<TopPlayerTableRow> rowList = new TreeSet<TopPlayerTableRow>();
        TopPlayerTableRow row;

        List<Team> teamList = getAllTeams();
        List<Goal> goalList = commonDAO.findAll(Goal.class);
        List<Assist> assistList = commonDAO.findAll(Assist.class);

        Set<Player> playerList;
        Integer goalCount;
        Integer assistCount;
        for (Team team : teamList) {
            playerList = team.getPlayerList();
            for (Player player : playerList) {
                row = new TopPlayerTableRow();
                row.setTeamName(team.getTeamName());
                row.setFirstName(player.getFirstName());
                row.setLastName(player.getLastName());
                goalCount = 0;
                assistCount = 0;
                for (Goal goal : goalList) {
                    if (goal.getAuthor().equals(player)) {
                        goalCount += 1;
                    }
                }
                for (Assist assist : assistList) {
                    if (assist.getPlayer().equals(player)) {
                        assistCount += 1;
                    }
                }
                row.setGoalsScoredCount(goalCount);
                row.setAssistCount(assistCount);

                rowList.add(row);
            }
        }

        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 10 player table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 10; i++) {
            System.out.println(rowArray[i]);
        }
    }

    private void topGoalie() {
        Set<TopGoalieTableRow> rowList = new TreeSet<TopGoalieTableRow>();
        TopGoalieTableRow row;
        
        List<GamePlayer> goalieAppearanceList = playerService.getAllGamePlayerGoalies();
        Set<Player> goalieList = new TreeSet<Player>();
        for (GamePlayer gamePlayer : goalieAppearanceList) {
            goalieList.add(gamePlayer.getPlayer());
        }
        
        List<GamePlayer> thisGoalieAppearanceList = new ArrayList<GamePlayer>();
        int totalGoalsLetInCount;
        int totalGamesPlayed;
        for (Player goalie : goalieList) { // for each goalkeeper persisted
            row = new TopGoalieTableRow();
            totalGoalsLetInCount = 0;
            totalGamesPlayed = 0;
            thisGoalieAppearanceList = new ArrayList<GamePlayer>();
            for (GamePlayer gamePlayer : goalieAppearanceList) {
                if (gamePlayer.getPlayer().equals(goalie)) {
                    thisGoalieAppearanceList.add(gamePlayer);
                }
            }
            Set<Game> gameList = new HashSet<Game>();
            for (GamePlayer gamePlayer: thisGoalieAppearanceList) {
                gameList.add(gamePlayer.getGame());
            }
            totalGamesPlayed = gameList.size();
            
            Integer goalsLetInThisGameCount;
            for (GamePlayer gamePlayer : thisGoalieAppearanceList) { // for each game this goalie played
                goalsLetInThisGameCount = 0;
                Set<Goal> goalList = gamePlayer.getGame().getGoalList();
                Set<Goal> goalsThisGoaliesTeamLetInList = new TreeSet<Goal>();
                for (Goal goal : goalList) { // for each goal in this game
                    if (!goal.getAuthor().getTeam().equals(goalie.getTeam())) {
                        goalsThisGoaliesTeamLetInList.add(goal);
                    }
                }
                for (Goal goal : goalsThisGoaliesTeamLetInList) {
                    if (goal.getTime() > gamePlayer.getTimeStart() && goal.getTime() < gamePlayer.getTimeEnd()) {
                        goalsLetInThisGameCount++;
                    }
                }
                totalGoalsLetInCount += goalsLetInThisGameCount;
            }
            row.setFirstName(goalie.getFirstName());
            row.setLastName(goalie.getLastName());
            row.setTeamName(goalie.getTeam().getTeamName());
            row.setPercentile((double)totalGoalsLetInCount / (double)totalGamesPlayed);
            row.setGamesPlayedCount(totalGamesPlayed);
            row.setGoalsLetInCount(totalGoalsLetInCount);
            rowList.add(row);
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 5 goalies table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 5; i++) {
            System.out.println(rowArray[i]);
        }
    }

    private void topUnsportsmanlike() {
        Set<TopUnsportsmanlikeTableRow> rowList = new TreeSet<TopUnsportsmanlikeTableRow>();
        TopUnsportsmanlikeTableRow row;

        List<Violation> violationList = commonDAO.findAll(Violation.class);
        List<Player> playerList = commonDAO.findAll(Player.class);
        Integer violationCount;
        for (Player player : playerList) {
            row = new TopUnsportsmanlikeTableRow();
            row.setTeamName(player.getTeam().getTeamName());
            row.setFirstName(player.getFirstName());
            row.setLastName(player.getLastName());
            violationCount = 0;
            for (Violation v : violationList) {
                if (v.getPlayer().equals(player)) {
                    violationCount += 1;
                }
            }
            row.setViolationCount(violationCount);

            rowList.add(row);
        }

        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 10 unspotsmanlike player table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 10; i++) {
            System.out.println(rowArray[i]);
        }
    }

    private List<Team> getAllTeams() {
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
