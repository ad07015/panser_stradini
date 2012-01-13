/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

import java.util.List;
import modernipd2.model.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrejs Daško ad07015
 */
public class Main {

    public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
    private static final String IMPORT_BEAN_ID = "footballImportProcessor";
    private static final String STATISTICS_BEAN_ID = "footballStatisticsProcessor";

    public static void main(String[] args) {
        String folderPath;
//        folderPath = args[0];
        folderPath = "C:/Users/andrejs.dasko/Documents/futbols/";
        
        ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        
        FootballImportProcessor dataImportProcessor = (FootballImportProcessor) appCtxt.getBean(IMPORT_BEAN_ID);
        dataImportProcessor.importData(folderPath);
        
        
        List<Game> gameList = dataImportProcessor.commonDAO.findAll(Game.class);
        StatisticsProcessor stProc = (StatisticsProcessor) appCtxt.getBean(STATISTICS_BEAN_ID);
        stProc.generateStatistics(gameList);
        
        System.out.println("Application execution successfully completed");
        System.exit(0);
    }
}
