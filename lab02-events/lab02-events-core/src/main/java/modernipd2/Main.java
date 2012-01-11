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
    private static final String SPRING_BEAN_ID = "footballImportProcessor";

    public static void main(String[] args) {
        ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        
        FootballImportProcessor dataImportProcessor = (FootballImportProcessor) appCtxt.getBean(SPRING_BEAN_ID);
        dataImportProcessor.importData();
        
        List<Game> gameList = dataImportProcessor.getGameList();
        StatisticsProcessor stProc = new StatisticsProcessor();
        stProc.generateStatistics(gameList);
        
        System.out.println("Application execution successfully completed");
        System.exit(0);
    }
}
