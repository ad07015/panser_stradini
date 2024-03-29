package modernipd2.portal.mvc.controller;

import java.util.Set;
import modernipd2.StatisticsProcessor;
import modernipd2.interfaces.service.PlayerService;
import modernipd2.persistance.CommonDAO;
import modernipd2.statistics.TopFastestScoringPlayersTableRow;
import modernipd2.statistics.TopGoalieTableRow;
import modernipd2.statistics.TopPlayerTableRow;
import modernipd2.statistics.TopRefereeTableRow;
import modernipd2.statistics.TopTeamAttendanceTableRow;
import modernipd2.statistics.TopTeamTableRow;
import modernipd2.statistics.TopUnsportsmanlikeTableRow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for displaying application home page.
 * 
 * XML configuration based controller, configured in events-servlet.xml. 
 */
@Controller
public class HomePageController {

    private String viewName;
    public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
    private static final String IMPORT_BEAN_ID = "footballImportProcessor";
    private static final String STATISTICS_BEAN_ID = "footballStatisticsProcessor";
    private CommonDAO commonDAO;
    private PlayerService playerService;

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    private void init() {
//
//        String folderPath;
////        folderPath = args[0];
//        folderPath = "C:/Users/root/Documents/NetBeansProjects/ModernProgTehPD2/";
//
        ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
//
//        FootballImportProcessor dataImportProcessor = (FootballImportProcessor) appCtxt.getBean(IMPORT_BEAN_ID);
//        dataImportProcessor.importData(folderPath);
//        commonDAO = dataImportProcessor.getCommonDAO();
        commonDAO = (CommonDAO) appCtxt.getBean("commonDAO");
        playerService = (PlayerService) appCtxt.getBean("playerService");
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView displayHomePage() {
        init();
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor();
        statisticsProcessor.setCommonDAO(commonDAO);
        statisticsProcessor.setPlayerService(playerService);

        ModelAndView mav = new ModelAndView();
        Set<TopPlayerTableRow> topPlayerTableRowList = statisticsProcessor.topPlayer();
        mav.addObject("topPlayerTableRowList", topPlayerTableRowList);
        Set<TopTeamTableRow> topTeamTableRowList = statisticsProcessor.topTeam();
        mav.addObject("topTeamTableRowList", topTeamTableRowList);
        Set<TopGoalieTableRow> topGoalieTableRowList = statisticsProcessor.topGoalie();
        mav.addObject("topGoalieTableRowList", topGoalieTableRowList);
        Set<TopUnsportsmanlikeTableRow> topUnsportsmanlikeTableRowList = statisticsProcessor.topUnsportsmanlike();
        mav.addObject("topUnsportsmanlikeTableRowList", topUnsportsmanlikeTableRowList);
        Set<TopRefereeTableRow> topRefereeTableRowList = statisticsProcessor.topReferee();
        mav.addObject("topRefereeTableRowList", topRefereeTableRowList);
        Set<TopTeamAttendanceTableRow> topTeamAttendanceTableRowList = statisticsProcessor.teamAttendance();
        mav.addObject("topTeamAttendanceTableRowList", topTeamAttendanceTableRowList);
        Set<TopFastestScoringPlayersTableRow> topFastestScoringPlayersTableRow = statisticsProcessor.topFastestScoringPlayers();
        mav.addObject("topFastestScoringPlayersTableRow", topFastestScoringPlayersTableRow);
        
        mav.setViewName("home");
        return mav;
    }
}