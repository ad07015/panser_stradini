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
import modernipd2.bean.CardBean;
import modernipd2.bean.GamesPlayedBean;
import modernipd2.constants.Constants;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.model.Assist;
import modernipd2.model.Game;
import modernipd2.model.GamePlayer;
import modernipd2.model.GameTeam;
import modernipd2.model.Goal;
import modernipd2.model.Player;
import modernipd2.model.Referee;
import modernipd2.model.Team;
import modernipd2.model.Violation;
import modernipd2.persistance.CommonDAO;
import modernipd2.statistics.PlayerTableRow;
import modernipd2.statistics.TopFastestScoringPlayersTableRow;
import modernipd2.statistics.TopGoalieTableRow;
import modernipd2.statistics.TopPlayerTableRow;
import modernipd2.statistics.TopRefereeTableRow;
import modernipd2.statistics.TopTeamAttendanceTableRow;
import modernipd2.statistics.TopTeamTableRow;
import modernipd2.statistics.TopUnsportsmanlikeTableRow;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class StatisticsProcessor {
    
    private PlayerService playerService;
    protected CommonDAO commonDAO;
    
    void generateStatistics(List<Game> gameList) {
        topTeam();
        topPlayer();
        topGoalie();
        topUnsportsmanlike();
        topReferee();
        teamStatistics();
        teamAttendance();
        topFastestScoringPlayers();
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
    
    public Set<TopPlayerTableRow> topPlayer() {
        Set<TopPlayerTableRow> rowList = new TreeSet<TopPlayerTableRow>();
        TopPlayerTableRow row;
        
        List<Team> teamList = getAllTeams();
        
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
                
                goalCount = getGoalCount(player);
                assistCount = getAssistCount(player);
                
                row.setGoalsScoredCount(goalCount);
                row.setAssistCount(assistCount);
                
                rowList.add(row);
            }
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 10 player table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 10 && i < rowArray.length; i++) {
            System.out.println(rowArray[i]);
        }
        return rowList;
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
            for (GamePlayer gamePlayer : thisGoalieAppearanceList) {
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
            row.setPercentile((double) totalGoalsLetInCount / (double) totalGamesPlayed);
            row.setGamesPlayedCount(totalGamesPlayed);
            row.setGoalsLetInCount(totalGoalsLetInCount);
            rowList.add(row);
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 5 goalies table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 5 && i < rowArray.length; i++) {
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
        for (int i = 0; i < 10 && i < rowArray.length; i++) {
            System.out.println(rowArray[i]);
        }
    }
    
    private void topReferee() {
        Set<TopRefereeTableRow> rowList = new TreeSet<TopRefereeTableRow>();
        TopRefereeTableRow row;
        
        List<Violation> violationList = commonDAO.findAll(Violation.class);
        Set<Referee> refereeList = new HashSet<Referee>();
        for (Violation violation : violationList) {
            refereeList.add(violation.getGame().getMainReferee());
        }
        Set<Game> gameList = null;
        int cardCount;
        int gameCount;
        for (Referee referee : refereeList) {
            row = new TopRefereeTableRow();
            cardCount = 0;
            gameCount = 0;
            gameList = new HashSet<Game>();
            for (Violation violation : violationList) {
                if (violation.getGame().getMainReferee().equals(referee)) {
                    cardCount++;
                    gameList.add(violation.getGame());
                }
            }
            gameCount = gameList.size();
            row.setFirstName(referee.getFirstName());
            row.setLastName(referee.getLastName());
            row.setGameCount(gameCount);
            row.setCardCount(cardCount);
            row.setPercentile((double) cardCount / (double) gameCount);
            
            rowList.add(row);
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 5 strict referee table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < rowArray.length; i++) {
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
    
    private boolean isOvertime(Game game) {
        return game.getGameEndTime() > 90 * 60;
    }
    
    private void teamStatistics() {
        Set<PlayerTableRow> rowList;
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Team statistics tables ---");
        System.out.println(Constants.SEPARATOR);
        
        List<Team> teamList = getAllTeams();
        for (Team team : teamList) {
            System.out.println(Constants.SEPARATOR);
            System.out.println("--- " + team.getTeamName() + " ---");
            System.out.println(Constants.SEPARATOR);
            rowList = teamStatistic(team);
            for (PlayerTableRow ptr : rowList) {
                System.out.println(ptr);
            }
        }
    }
    
    private Set<PlayerTableRow> teamStatistic(Team team) {
        Set<PlayerTableRow> rowList = new TreeSet<PlayerTableRow>();
        PlayerTableRow row;
        
        Set<Player> playerList = team.getPlayerList();
        CardBean cardBean;
        GamesPlayedBean gamesPlayedBean;
        Integer minutesPlayed;
        for (Player player : playerList) {
            row = new PlayerTableRow();
            gamesPlayedBean = getGamesPlayedBean(player);
            minutesPlayed = gamesPlayedBean.getMinutesPlayed();
            
            if (minutesPlayed > 0) {
                cardBean = getCardBean(player);
                row.setGamesPlayedCount(gamesPlayedBean.getGamesPlayedCount());
                row.setGamesPlayedInStartingLineupCount(gamesPlayedBean.getGamesPlayedInStartingLineupCount());
                row.setMinutesPlayed(minutesPlayed / 60 + ":" + minutesPlayed % 60);
                row.setGoalsScoredCount(getGoalCount(player));
                row.setAssistsCount(getAssistCount(player));
                row.setYellowCardCount(cardBean.getYellowCardCount());
                row.setRedCardCount(cardBean.getRedCardCount());
                row.setPlayerNumber(player.getPlayerNumber());
                row.setFirstName(player.getFirstName());
                row.setLastName(player.getLastName());
                rowList.add(row);
            }
        }
        return rowList;
    }
    
    private int getGoalCount(Player player) {
        int goalCount = 0;
        List<Goal> goalList = playerService.getAllGoalByPlayer(player);
        if (goalList != null) {
            goalCount = goalList.size();
        }
        return goalCount;
    }
    
    private int getAssistCount(Player player) {
        int assistCount = 0;
        List<Assist> assistList = playerService.getAllAssistByPlayer(player);
        if (assistList != null) {
            assistCount = assistList.size();
        }
        return assistCount;
    }
    
    private int getGamesPlayedCount(Player player) {
        List<GamePlayer> gamePlayerList = playerService.getAllGamePlayerByPlayer(player);
        Set<Game> gameList = new HashSet();
        for (GamePlayer gamePlayer : gamePlayerList) {
            gameList.add(gamePlayer.getGame());
        }
        return gameList.size();
    }
    
    private int getGamesPlayedInStartingLineupCount(Player player) {
        List<GamePlayer> gamePlayerList = playerService.getAllGamePlayerByPlayer(player);
        Set<Game> gameList = new HashSet();
        for (GamePlayer gamePlayer : gamePlayerList) {
            if (gamePlayer.getTimeStart() == 0) {
                gameList.add(gamePlayer.getGame());
            }
        }
        return gameList.size();
    }
    
    private GamesPlayedBean getGamesPlayedBean(Player player) {
        Integer gamesPlayedCount = new Integer(0);
        Integer gamesPlayedInStartingLineupCount = new Integer(0);
        Integer minutesPlayed = new Integer(0);
        List<GamePlayer> gamePlayerList = playerService.getAllGamePlayerByPlayer(player);
        
        Set<Game> gameList = new HashSet<Game>();
        Set<Game> gamesInStartingLineupList = new HashSet<Game>();
        if (gamePlayerList != null) {
            for (GamePlayer gamePlayer : gamePlayerList) {
                minutesPlayed += (gamePlayer.getTimeEnd() - gamePlayer.getTimeStart());
                gameList.add(gamePlayer.getGame());
                if (gamePlayer.getTimeStart() == 0) {
                    gamesInStartingLineupList.add(gamePlayer.getGame());
                }
            }
            gamesPlayedCount = gameList.size();
            gamesPlayedInStartingLineupCount = gamesInStartingLineupList.size();
        }
        GamesPlayedBean result = new GamesPlayedBean();
        result.setGamesPlayedCount(gamesPlayedCount);
        result.setGamesPlayedInStartingLineupCount(gamesPlayedInStartingLineupCount);
        result.setMinutesPlayed(minutesPlayed);
        return result;
    }
    
    private CardBean getCardBean(Player player) {
        Integer yellowCardCount = new Integer(0);
        Integer redCardCount = new Integer(0);
        List<Violation> violationList = playerService.getAllViolationByPlayer(player);
        for (Violation violation : violationList) {
            if (violation.getType().equals("Y")) {
                yellowCardCount++;
            } else {
                redCardCount++;
            }
        }
        CardBean result = new CardBean();
        result.setYellowCardCount(yellowCardCount);
        result.setRedCardCount(redCardCount);
        return result;
    }
    
    private void teamAttendance() {
        Set<TopTeamAttendanceTableRow> rowList = new TreeSet<TopTeamAttendanceTableRow>();
        TopTeamAttendanceTableRow row;
        
        List<Team> teamList = commonDAO.findAll(Team.class);
        List<GameTeam> gameTeamList;
        int totalGameCount;
        int totalViewerCount;
        for (Team team : teamList) {
            row = new TopTeamAttendanceTableRow();
            totalGameCount = new Integer(0);
            totalViewerCount = new Integer(0);
            gameTeamList = playerService.getAllGameTeamByTeam(team);
            for (GameTeam gameTeam : gameTeamList) {
                totalGameCount++;
                totalViewerCount += gameTeam.getGame().getViewerCount();
            }
            row.setGamesPlayedCount(totalGameCount);
            row.setTotalViewerCount(totalViewerCount);
            row.setTeamName(team.getTeamName());
            row.setPercentile(totalViewerCount / totalGameCount);
            rowList.add(row);
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top team attendance table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < rowArray.length; i++) {
            System.out.println(rowArray[i]);
        }
    }
    
    private void topFastestScoringPlayers() {
        Set<TopFastestScoringPlayersTableRow> rowList = new TreeSet<TopFastestScoringPlayersTableRow>();
        TopFastestScoringPlayersTableRow row;
        
        List<Player> playerList = commonDAO.findAll(Player.class);
        GamesPlayedBean gamesPlayedBean;
        int assistCount;
        int goalCount;
        int gamesPlayedCount;
        int timePlayed;
        for (Player player : playerList) {
            timePlayed = 0;
            gamesPlayedBean = getGamesPlayedBean(player);
            timePlayed = gamesPlayedBean.getMinutesPlayed();
            assistCount = getAssistCount(player);
            goalCount = getGoalCount(player);
            if (goalCount > 0) {
                row = new TopFastestScoringPlayersTableRow();
                gamesPlayedCount = gamesPlayedBean.getGamesPlayedCount();
                
                row.setFirstName(player.getFirstName());
                row.setLastName(player.getLastName());
                row.setTeamName(player.getTeam().getTeamName());
                row.setTimePlayed(gamesPlayedBean.getMinutesPlayed());
                row.setGoalScoredCount(goalCount);
                row.setAssistCount(assistCount);
                row.setGamesPlayedCount(gamesPlayedCount);
                
                if (assistCount > 0) {
                    row.setTimeToAssist(timePlayed / assistCount);
                }
                if (goalCount > 0) {
                    row.setTimeToScoreOneGoal(timePlayed / goalCount);
                }
                row.setGoalsScoredPerGame((double) goalCount / (double) gamesPlayedCount);
                row.setAssistsPerGame((double) assistCount / (double) gamesPlayedCount);
                rowList.add(row);
            }
        }
        
        System.out.println(Constants.SEPARATOR);
        System.out.println("--- Top 10 fastest scoring players table ---");
        System.out.println(Constants.SEPARATOR);
        Object[] rowArray = rowList.toArray();
        for (int i = 0; i < 10 && i < rowArray.length; i++) {
            System.out.println(rowArray[i]);
        }
    }
}
