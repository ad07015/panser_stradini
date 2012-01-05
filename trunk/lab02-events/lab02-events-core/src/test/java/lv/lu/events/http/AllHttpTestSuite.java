/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.http;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
		EventsPageTest.class,
		LoginTest.class})
public class AllHttpTestSuite {
}
