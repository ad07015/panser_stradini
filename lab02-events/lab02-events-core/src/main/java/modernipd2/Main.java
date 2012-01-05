/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2;

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
        
        DataImportProcessor dataImportProcessor = (DataImportProcessor) appCtxt.getBean(SPRING_BEAN_ID);
        dataImportProcessor.importData();
        
        System.out.println("Application execution successfully completed");
        System.exit(0);
    }
}
