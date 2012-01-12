/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import modernipd2.constants.Constants;
import modernipd2.constants.utils.TeamUtils;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.interfaces.service.TeamService;
import modernipd2.model.Assist;
import modernipd2.model.Game;
import modernipd2.model.GamePlayer;
import modernipd2.model.GameTeam;
import modernipd2.model.Goal;
import modernipd2.model.Player;
import modernipd2.model.Referee;
import modernipd2.model.Substitusion;
import modernipd2.model.Team;
import modernipd2.model.Violation;
import modernipd2.persistance.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class FootballImportProcessor implements DataImportProcessor {

    public static final String ATTR_ACTION_TIME = "Laiks";
    public static final String ATTR_FIRST_NAME = "Vards";
    public static final String ATTR_LAST_NAME = "Uzvards";
    public static final String ATTR_PLAYER_NUMBER = "Nr";
    public static final String ATTR_PLAYER_ROLE = "Loma";
    public static final String ATTR_TEAM_NAME = "Nosaukums";
    DOMParser parser;
    List<Game> gameList = new ArrayList<Game>();
    private TeamService teamService;
    private PlayerService playerService;
    protected CommonDAO commonDAO;

    public FootballImportProcessor() {
    }

    @Autowired
    public FootballImportProcessor(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public void importData(String folderPath) {
        List<String> pathList = getFileList(folderPath);
//        parseXmlFile(pathList.get(0));
        for (String path : pathList) {
            parseXmlFile(path);
        }
    }

    private void createAndPersistGamePlayer(Set<Substitusion> team1SubList, Team team1, Game game, Set<Player> team1InitialPlayerList) {
        List<GamePlayer> gamePlayerList = new ArrayList<GamePlayer>();

        List<Player> addedList = new ArrayList<Player>();
        List<Player> removedList = new ArrayList<Player>();
        for (Substitusion sub : team1SubList) {
            addedList.add(sub.getAdded());
            removedList.add(sub.getRemoved());
        }

        GamePlayer gamePlayer;
        for (Player player : team1.getPlayerList()) {
            gamePlayer = new GamePlayer();
            gamePlayer.setGame(game);
            gamePlayer.setPlayer(player);
            gamePlayer.setPlayerRole(player.getPlayerRole());
            if (team1InitialPlayerList.contains(player)) {
                gamePlayer.setTimeStart(0);
                gamePlayer.setTimeEnd(game.getGameEndTime());// default end time, if not subbed
                if (removedList.contains(player)) {
                    for (Substitusion sub : team1SubList) {
                        if (sub.getRemoved().equals(player)) {
                            gamePlayer.setTimeEnd(sub.getSubstitusionTime());
                            break;
                        }
                    }
                    
                }
                gamePlayerList.add(gamePlayer);
            } else if (addedList.contains(player)) {
                for (Substitusion sub : team1SubList) {
                    if (sub.getAdded().equals(player)) {
                        gamePlayer.setTimeStart(sub.getSubstitusionTime());
                        gamePlayer.setTimeEnd(game.getGameEndTime());
                        break;
                    }
                }
                gamePlayerList.add(gamePlayer);
            }
        }
        commonDAO.saveAll(gamePlayerList);
    }

    private List<String> getFileList(String folderPath) {
        List<String> pathList = new ArrayList<String>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        String filename;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filename = listOfFiles[i].getName();
                if (filename.startsWith("futbols")) {
                    pathList.add(folderPath + filename);
                }
            }
        }
        return pathList;
    }

    private void createAndPersistGameTeam(Game game, Team team1, Set<Goal> team1GoalList, Set<Goal> team2GoalList, Team team2) {
        GameTeam gameTeam1 = new GameTeam();
        GameTeam gameTeam2 = new GameTeam();

        Team winner;
        Team loser;

        gameTeam1.setGame(game);
        gameTeam1.setTeam(team1);
        gameTeam2.setGame(game);
        gameTeam2.setTeam(team2);

        gameTeam1.setGoalsScoredCount(team1GoalList.size());
        gameTeam2.setGoalsScoredCount(team2GoalList.size());
        gameTeam1.setGoalsLetInCount(team2GoalList.size());
        gameTeam2.setGoalsLetInCount(team1GoalList.size());

        int winnerPoints;
        int loserPoints;
        if (game.getGameEndTime() > 90 * 60) {
            winnerPoints = 2;
            loserPoints = 1;
        } else {
            winnerPoints = 3;
            loserPoints = 0;
        }

        if (team1GoalList.size() > team2GoalList.size()) {
            winner = team1;
            loser = team2;
            gameTeam1.setWon(Boolean.TRUE);
            gameTeam2.setWon(Boolean.FALSE);
            gameTeam1.setPoints(winnerPoints);
            gameTeam2.setPoints(loserPoints);
        } else {
            winner = team2;
            loser = team1;
            gameTeam1.setWon(Boolean.FALSE);
            gameTeam2.setWon(Boolean.TRUE);
            gameTeam1.setPoints(loserPoints);
            gameTeam2.setPoints(winnerPoints);
        }
        gameTeam1.setWinner(winner);
        gameTeam1.setLoser(loser);
        gameTeam2.setWinner(winner);
        gameTeam2.setLoser(loser);

        commonDAO.save(gameTeam1);
        commonDAO.save(gameTeam2);
    }

    private void parseXmlFile(String path) {
        try {
            parser = new DOMParser();
            parser.parse(path);
            Document doc = parser.getDocument();
            NodeList gameNodeList = doc.getElementsByTagName("Spele");
            parseGames(gameNodeList);
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseGames(NodeList gameNodeList) {
//        System.out.println("There are " + gameNodeList.getLength()
//                + " elements.");

        if (gameNodeList != null && gameNodeList.getLength() > 0) {
            for (int i = 0; i < gameNodeList.getLength(); i++) {
                Node gameNode = gameNodeList.item(i);
                Game game = parseGame(gameNode);
                gameList.add(game);
            }
        }
    }

    private Game parseGame(Node gameNode) {
        Game game = null;
        Element gameElement = (Element) gameNode;
        Date date = parseDate(gameElement.getAttribute(Constants.ATTR_DATUMS));
        String venue = gameElement.getAttribute(Constants.ATTR_VIETA);
        Integer viewerCount = Integer.parseInt(gameElement.getAttribute(Constants.ATTR_SKATITAJI));

        Node mainRefereeNode = gameElement.getElementsByTagName("VT").item(0);
        Referee mainReferee = parseReferee(mainRefereeNode);

        NodeList lineRefereeNodeList = gameElement.getElementsByTagName("T");
        Referee lineReferee1 = parseReferee(lineRefereeNodeList.item(0));
        Referee lineReferee2 = parseReferee(lineRefereeNodeList.item(1));

        Set<Goal> team1GoalList = new TreeSet<Goal>();
        Set<Goal> team2GoalList = new TreeSet<Goal>();
        Set<Substitusion> team1SubList = new TreeSet<Substitusion>();
        Set<Substitusion> team2SubList = new TreeSet<Substitusion>();

        NodeList teamNodeList = gameElement.getElementsByTagName("Komanda");
        if (teamNodeList != null && teamNodeList.getLength() == 2) {
            Node team1Node;
            Node team2Node;
            Team team1;
            Team team2;

            team1Node = teamNodeList.item(0);
            team1 = parseTeam(team1Node);
            Set<Player> team1InitialPlayerList = getInitialPlayerList(team1Node, team1, "Pamatsastavs");

            team2Node = teamNodeList.item(1);
            team2 = parseTeam(team2Node);
            Set<Player> team2InitialPlayerList = getInitialPlayerList(team2Node, team2, "Pamatsastavs");

            game = playerService.getGameByVenueAndTeams(team1, team2, venue, date);

            if (game == null) {
                game = new Game();
                game.setGameDate(date);
                game.setVenue(venue);
                game.setViewerCount(viewerCount);
                game.setMainReferee(mainReferee);
                game.setLineReferee1(lineReferee1);
                game.setLineReferee2(lineReferee2);
                game.setTeam1InitialPlayerList(team1InitialPlayerList);
                game.setTeam1(team1);
                game.setTeam2InitialPlayerList(team2InitialPlayerList);
                game.setTeam2(team2);
                commonDAO.save(game);
                team1GoalList = getGoalList(team1Node, team1, game);
                team2GoalList = getGoalList(team2Node, team2, game);
                game.getGoalList().addAll(team1GoalList);
                game.getGoalList().addAll(team2GoalList);
                team1SubList = getSubstitusionList(team1Node, team1, game);
                team2SubList = getSubstitusionList(team2Node, team2, game);
                game.getSubstitusionList().addAll(team1SubList);
                game.getSubstitusionList().addAll(team2SubList);
                game.getViolationList().addAll(getViolationList(team1Node, team1, game));
                game.getViolationList().addAll(getViolationList(team2Node, team2, game));
                game.setGameEndTime(getGameEndTime(game));
                commonDAO.save(game);
                createAndPersistGameTeam(game, team1, team1GoalList, team2GoalList, team2);
                createAndPersistGamePlayer(team1SubList, team1, game, team1InitialPlayerList);
                createAndPersistGamePlayer(team2SubList, team2, game, team2InitialPlayerList);
            } else {
                System.out.println("This game is already persisted");
            }

        }

        return game;
    }

    private Date parseDate(String sDate) {
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = null;
        try {
            date = df.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    private Team parseTeam(Node teamNode) {
        Element teamElement = (Element) teamNode;
        String teamName = teamElement.getAttribute(ATTR_TEAM_NAME);

        Team team = teamService.getTeamByName(teamName);
        if (team == null) {
            team = new Team();
            team.setTeamName(teamName);
            commonDAO.save(team);
        }

        Set<Player> playerList = getPlayerList(teamNode, team, "Speletaji");
        Player tempPlayer;
        for (Player player : playerList) {
            tempPlayer = playerService.getPlayerByTeamAndNumber(team, player.getPlayerNumber());
            if (tempPlayer == null) {
                commonDAO.save(player);
            }
        }
        team = teamService.getTeamByName(teamName);
//        System.out.println("Team size: " + team.getPlayerList().size());
        return team;
    }

    private Referee parseReferee(Node refereeNode) {
        Element refereeElement = (Element) refereeNode;
        String firstName = refereeElement.getAttribute("Vards");
        String lastName = refereeElement.getAttribute("Uzvards");

        Referee referee = playerService.getRefereeByFirstNameAndLastName(firstName, lastName);
        if (referee == null) {
            referee = new Referee();
            referee.setFirstName(firstName);
            referee.setLastName(lastName);
            commonDAO.save(referee);
        }

        return referee;
    }

    private Set<Player> getPlayerList(Node team1Node, Team team, String playerListType) {
        Set<Player> playerList = new TreeSet<Player>();

        Element teamElement = (Element) team1Node;
        Node playersNode = teamElement.getElementsByTagName(playerListType).item(0);
        NodeList declaredPlayerNodeList = ((Element) playersNode).getElementsByTagName("Speletajs");
        if (declaredPlayerNodeList != null && declaredPlayerNodeList.getLength() > 0) {
            Node playerNode;
            Player player;
            for (int i = 0; i < declaredPlayerNodeList.getLength(); i++) {
                playerNode = declaredPlayerNodeList.item(i);
                player = parsePlayer(playerNode);
                player.setTeam(team);
//                commonDAO.save(player);
                playerList.add(player);
            }
        }
        return playerList;
    }
    
    private Set<Player> getInitialPlayerList(Node team1Node, Team team, String playerListType) {
        Set<Player> playerList = new TreeSet<Player>();

        Element teamElement = (Element) team1Node;
        Node playersNode = teamElement.getElementsByTagName(playerListType).item(0);
        NodeList declaredPlayerNodeList = ((Element) playersNode).getElementsByTagName("Speletajs");
        if (declaredPlayerNodeList != null && declaredPlayerNodeList.getLength() > 0) {
            Node playerNode;
            Player player;
            for (int i = 0; i < declaredPlayerNodeList.getLength(); i++) {
                playerNode = declaredPlayerNodeList.item(i);
                player = playerService.getPlayerByTeamAndNumber(team, ((Element)playerNode).getAttribute(ATTR_PLAYER_NUMBER));
                playerList.add(player);
            }
        }
        return playerList;
    }

    private Player parsePlayer(Node playerNode) {
        Element playerElement = (Element) playerNode;
        String firstName = playerElement.getAttribute(ATTR_FIRST_NAME);
        String lastName = playerElement.getAttribute(ATTR_LAST_NAME);
        String playerNumber = playerElement.getAttribute(ATTR_PLAYER_NUMBER);
        String playerRole = playerElement.getAttribute(ATTR_PLAYER_ROLE);

        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setPlayerNumber(playerNumber);
        player.setPlayerRole(playerRole);

        return player;
    }

    private Set<Goal> getGoalList(Node teamNode, Team team, Game game) {
        Set<Goal> goalList = new LinkedHashSet<Goal>();
        Element teamElement = (Element) teamNode;
        NodeList goalsNodeList = teamElement.getElementsByTagName("Varti");
        if (goalsNodeList != null && goalsNodeList.getLength() > 0) {
            Node goalsNode = teamElement.getElementsByTagName("Varti").item(0);
            NodeList goalNodeList = ((Element) goalsNode).getElementsByTagName("VG");
            if (goalNodeList != null && goalNodeList.getLength() > 0) {
                Node goalNode;
                Goal goal;
                for (int i = 0; i < goalNodeList.getLength(); i++) {
                    goalNode = goalNodeList.item(i);
                    goal = parseGoal(goalNode, team, game);
                    goalList.add(goal);
                }
            }
        }
//        commonDAO.saveAll(goalList);
        return goalList;
    }

    private Set<Violation> getViolationList(Node teamNode, Team team, Game game) {
        Set<Violation> violationList = new LinkedHashSet<Violation>();
        Element teamElement = (Element) teamNode;
        NodeList violationsNodeList = teamElement.getElementsByTagName("Sodi");
        if (violationsNodeList != null && violationsNodeList.getLength() > 0) {
            Node violationsNode = teamElement.getElementsByTagName("Sodi").item(0);
            NodeList violationNodeList = ((Element) violationsNode).getElementsByTagName("Sods");
            if (violationNodeList != null && violationNodeList.getLength() > 0) {
                Node violationNode;
                Violation violation;
                for (int i = 0; i < violationNodeList.getLength(); i++) {
                    violationNode = violationNodeList.item(i);
                    violation = parseViolation(violationNode, team, game);
                    violationList.add(violation);
                }
            }
        }
        return violationList;
    }

    private Set<Substitusion> getSubstitusionList(Node teamNode, Team team, Game game) {
        Set<Substitusion> substitusionList = new LinkedHashSet<Substitusion>();
        Element teamElement = (Element) teamNode;
        NodeList substitusionsNodeList = teamElement.getElementsByTagName("Mainas");
        if (substitusionsNodeList != null && substitusionsNodeList.getLength() > 0) {
            Node substitusionsNode = substitusionsNodeList.item(0);
            NodeList substitusionNodeList = ((Element) substitusionsNode).getElementsByTagName("Maina");
            if (substitusionNodeList != null && substitusionNodeList.getLength() > 0) {
                Node substitusionNode;
                Substitusion substitusion;
                for (int i = 0; i < substitusionNodeList.getLength(); i++) {
                    substitusionNode = substitusionNodeList.item(i);
                    substitusion = parseSubstitusion(substitusionNode, team, game);
                    substitusionList.add(substitusion);
                }
            }
        }
        return substitusionList;
    }

    private Goal parseGoal(Node goalNode, Team team, Game game) {
        Element goalElement = (Element) goalNode;
//        Player author = TeamUtils.findPlayerByPlayerNumber(team, goalElement.getAttribute(ATTR_PLAYER_NUMBER));
        Player author = playerService.getPlayerByTeamAndNumber(team, goalElement.getAttribute(ATTR_PLAYER_NUMBER));
        Set<Assist> assistList = new LinkedHashSet<Assist>();
        Integer time = getTimeInSeconds(goalElement.getAttribute(ATTR_ACTION_TIME));


        Goal goal = new Goal();
        goal.setAuthor(author);
        goal.setAssistList(assistList);
        goal.setGame(game);
        goal.setTime(time);
        commonDAO.save(goal);

        NodeList passPlayerNodeList = goalElement.getElementsByTagName("P");
        int assistPlayerNodeListCount = passPlayerNodeList.getLength();
        if (passPlayerNodeList != null && assistPlayerNodeListCount > 0) {
            Node assistPlayerNode;
            String assistPlayerNumber;
            Player assistingPlayer;
            Assist assist;
            for (int i = 0; i < assistPlayerNodeListCount; i++) {
                assistPlayerNode = passPlayerNodeList.item(i);
                assistPlayerNumber = ((Element) assistPlayerNode).getAttribute(ATTR_PLAYER_NUMBER);
                assistingPlayer = TeamUtils.findPlayerByPlayerNumber(team, assistPlayerNumber);
                assist = new Assist();
                assist.setPlayer(assistingPlayer);
                assistList.add(assist);
            }
        }
        for (Assist assist : assistList) {
            assist.setGoal(goal);
        }
        commonDAO.saveAll(assistList);

        return goal;
    }

    private Violation parseViolation(Node violationNode, Team team, Game game) {
        Element violationElement = (Element) violationNode;
        Integer time = getTimeInSeconds(violationElement.getAttribute(ATTR_ACTION_TIME));
        Player player = playerService.getPlayerByTeamAndNumber(team, violationElement.getAttribute(ATTR_PLAYER_NUMBER));

        Violation violation = new Violation();
        violation.setViolationTime(time);
        violation.setPlayer(player);
        violation.setGame(game);
        commonDAO.save(violation);
        return violation;
    }

    private Substitusion parseSubstitusion(Node substitusionNode, Team team, Game game) {
        Element substitusionElement = (Element) substitusionNode;
        Integer time = getTimeInSeconds(substitusionElement.getAttribute(ATTR_ACTION_TIME));
//        Player removed = TeamUtils.findPlayerByPlayerNumber(team, substitusionElement.getAttribute("Nr1"));
        Player removed = playerService.getPlayerByTeamAndNumber(team, substitusionElement.getAttribute("Nr1"));
//        Player added = TeamUtils.findPlayerByPlayerNumber(team, substitusionElement.getAttribute("Nr2"));
        Player added = playerService.getPlayerByTeamAndNumber(team, substitusionElement.getAttribute("Nr2"));

        Substitusion substitusion = new Substitusion();
        substitusion.setSubstitusionTime(time);
        substitusion.setRemoved(removed);
        substitusion.setAdded(added);
        substitusion.setGame(game);
        substitusion.setTeam(team);

        commonDAO.save(substitusion);
        return substitusion;
    }

    private int getTimeInSeconds(String sTime) {
        String[] timeSplit = sTime.split(":");
        return Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    private Integer getGameEndTime(Game game) {
        Integer result = null;
        TreeSet<Goal> goalList = new TreeSet();
        goalList.addAll(game.getGoalList());
        Integer lastGoalTime;
        if (goalList.size() > 0) {
            Goal goal = goalList.last();
            lastGoalTime = goal.getTime();
            result = lastGoalTime > (90 * 60) ? lastGoalTime : 90 * 60;
            return result;
        }
        System.out.println("--------------------THIS PLACE SHOULD NEVER HAVE BEEN REACHED!!!");
        return result;
    }
}
