package lv.lu.events;

import lv.lu.events.interfaces.DataImportProcessor;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point into Facebook data importing application.
 * Your task is to implement it.
 * 
 * This program is going to be executed on behalf of authenticated and authorized Facebook user. 
 * See Facebook Graph API for details:
 * 		http://developers.facebook.com/docs/reference/api/
 * Graph API Explorer is a useful tool for inspecting API:
 * 		https://developers.facebook.com/tools/explorer/
 */
public class FacebookDataImportTool {

    private static Logger log = Logger.getLogger(FacebookDataImportTool.class.getSimpleName());
    public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
    private static final String SPRING_BEAN_ID = "facebookImportProcessor";

    public static void main(String[] args) {
        log.info("Application started");

        // initialize Spring application context from configuration file
        ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        DataImportProcessor importProcessor = (DataImportProcessor) appCtxt.getBean(SPRING_BEAN_ID);
        importProcessor.importData();

        log.info("Application execution successfully completed");
        System.exit(0);
    }
}
