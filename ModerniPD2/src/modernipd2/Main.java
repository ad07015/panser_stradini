/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import modernipd2.constants.Constants;
import modernipd2.constants.utils.TeamUtils;
import modernipd2.model.Game;
import modernipd2.model.Goal;
import modernipd2.model.Player;
import modernipd2.model.Referee;
import modernipd2.model.Substitusion;
import modernipd2.model.Team;
import modernipd2.model.Violation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrejs Daško ad07015
 */
public class Main {

    public static final String ATTR_ACTION_TIME = "Laiks";
    public static final String ATTR_FIRST_NAME = "Vards";
    public static final String ATTR_LAST_NAME = "Uzvards";
    public static final String ATTR_PLAYER_NUMBER = "Nr";
    public static final String ATTR_PLAYER_ROLE = "Loma";
    public static final String ATTR_TEAM_NAME = "Nosaukums";
    DOMParser parser;
    List<Game> gameList = new ArrayList<Game>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.parseXmlFile();
    }

    private void parseXmlFile() {
        try {
            parser = new DOMParser();
            parser.parse("C:/Users/root/Documents/NetBeansProjects/ModerniPD2/futbols1.xml");
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
        System.out.println("There are " + gameNodeList.getLength()
                + " elements.");

        if (gameNodeList != null && gameNodeList.getLength() > 0) {
            for (int i = 0; i < gameNodeList.getLength(); i++) {
                Node gameNode = gameNodeList.item(i);
                Game game = parseGame(gameNode);
                gameList.add(game);
            }
        }
        for (Game game : gameList) {
            System.out.println(game.toString());
            System.out.println("Team 1 name: " + game.getTeam1());
            System.out.println("Team 2 name: " + game.getTeam2());
            System.out.println("Main referee: " + game.getMainReferee());
            System.out.println("First line referee: " + game.getLineReferee1());
            System.out.println("Second line referee: " + game.getLineReferee2());
            System.out.println("Team 1 player list");
            for (Player player : game.getTeam1().getPlayerList()) {
                System.out.println(player);
            }
            System.out.println("Team 2 player list");
            for (Player player : game.getTeam1().getPlayerList()) {
                System.out.println(player);
            }
            System.out.println("Goal list");
            for (Goal goal : game.getGoalList()) {
                System.out.println(goal);
            }
            System.out.println("Substitusion list");
            for (Substitusion substitusion : game.getSubstitusionList()) {
                System.out.println(substitusion);
            }
            System.out.println("Violation list");
            for (Violation violation : game.getViolationList()) {
                System.out.println(violation);
            }
        }
    }

    private Game parseGame(Node gameNode) {
        Element gameElement = (Element) gameNode;
        Date date = parseDate(gameElement.getAttribute(Constants.ATTR_DATUMS));
        String venue = gameElement.getAttribute(Constants.ATTR_VIETA);
        Integer viewerCount = Integer.parseInt(gameElement.getAttribute(Constants.ATTR_SKATITAJI));
        Game game = new Game(date, venue, viewerCount);

        Node mainRefereeNode = gameElement.getElementsByTagName("VT").item(0);
        game.setMainReferee(parseReferee(mainRefereeNode));

        NodeList lineRefereeNodeList = gameElement.getElementsByTagName("T");
        game.setLineReferee1(parseReferee(lineRefereeNodeList.item(0)));
        game.setLineReferee2(parseReferee(lineRefereeNodeList.item(1)));

        NodeList teamNodeList = gameElement.getElementsByTagName("Komanda");
        if (teamNodeList != null && teamNodeList.getLength() == 2) {
            Node team1Node;
            Node team2Node;
            Team team1;
            Team team2;

            team1Node = teamNodeList.item(0);
            team1 = parseTeam(team1Node);
            game.setTeam1(team1);
            game.setTeam1InitialPlayerList(getPlayerList(team1Node, team1, "Pamatsastavs"));

            team2Node = teamNodeList.item(1);
            team2 = parseTeam(team2Node);
            game.setTeam2InitialPlayerList(getPlayerList(team1Node, team1, "Pamatsastavs"));
            game.setTeam2(team2);

            game.getGoalList().addAll(getGoalList(team1Node, team1, game));
            game.getGoalList().addAll(getGoalList(team2Node, team2, game));
            game.getSubstitusionList().addAll(getSubstitusionList(team1Node, team1, game));
            game.getSubstitusionList().addAll(getSubstitusionList(team2Node, team2, game));
            game.getViolationList().addAll(getViolationList(team1Node, team1, game));
            game.getViolationList().addAll(getViolationList(team2Node, team2, game));
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

        Team team = new Team();
        team.setTeamName(teamName);
        team.setPlayerList(getPlayerList(teamNode, team, "Speletaji"));
        return team;
    }

    private Referee parseReferee(Node refereeNode) {
        Element refereeElement = (Element) refereeNode;
        String firstName = refereeElement.getAttribute("Vards");
        String lastName = refereeElement.getAttribute("Uzvards");

        Referee referee = new Referee();
        referee.setFirstName(firstName);
        referee.setLastName(lastName);
        return referee;
    }

    private Set<Player> getPlayerList(Node team1Node, Team team, String playerListType) {
        Set<Player> playerList = new LinkedHashSet<Player>();

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
        Player author = TeamUtils.findPlayerByPlayerNumber(team, goalElement.getAttribute(ATTR_PLAYER_NUMBER));
        Set<Player> assistList = new LinkedHashSet<Player>();
        Integer time = getTimeInSeconds(goalElement.getAttribute(ATTR_ACTION_TIME));

        NodeList passPlayerNodeList = goalElement.getElementsByTagName("P");
        int assistPlayerNodeListCount = passPlayerNodeList.getLength();
        if (passPlayerNodeList != null && assistPlayerNodeListCount > 0) {
            Node assistPlayerNode;
            String assistPlayerNumber;
            for (int i = 0; i < assistPlayerNodeListCount; i++) {
                assistPlayerNode = passPlayerNodeList.item(i);
                assistPlayerNumber = ((Element) assistPlayerNode).getAttribute(ATTR_PLAYER_NUMBER);
                assistList.add(TeamUtils.findPlayerByPlayerNumber(team, assistPlayerNumber));
            }
        }

        Goal goal = new Goal();
        goal.setAuthor(author);
        goal.setAssistList(assistList);
        goal.setGame(game);
        goal.setTime(time);
        return goal;
    }

    private Violation parseViolation(Node violationNode, Team team, Game game) {
        Element violationElement = (Element) violationNode;
        Integer time = getTimeInSeconds(violationElement.getAttribute(ATTR_ACTION_TIME));
        Player player = TeamUtils.findPlayerByPlayerNumber(team, violationElement.getAttribute(ATTR_PLAYER_NUMBER));

        Violation violation = new Violation();
        violation.setViolationTime(time);
        violation.setPlayer(player);
        violation.setGame(game);
        return violation;
    }

    private Substitusion parseSubstitusion(Node substitusionNode, Team team, Game game) {
        Element substitusionElement = (Element) substitusionNode;
        Integer time = getTimeInSeconds(substitusionElement.getAttribute(ATTR_ACTION_TIME));
        Player removed = TeamUtils.findPlayerByPlayerNumber(team, substitusionElement.getAttribute("Nr1"));
        Player added = TeamUtils.findPlayerByPlayerNumber(team, substitusionElement.getAttribute("Nr2"));

        Substitusion substitusion = new Substitusion();
        substitusion.setSubstitusionTime(time);
        substitusion.setRemoved(removed);
        substitusion.setAdded(added);
        substitusion.setGame(game);
        return substitusion;
    }

    private int getTimeInSeconds(String sTime) {
        String[] timeSplit = sTime.split(":");
        return Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
    }
}
