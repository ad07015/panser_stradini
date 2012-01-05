/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.http;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import junit.framework.Assert;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class HttpGenericTest {

    protected static final String USERNAME = "jwhite";
    protected static final String PASSWORD = "jwhite";

    protected static final String FULL_NAME = "John White";
    protected static final String EVENT_NAME = "Event name";


    public void login(WebConversation wc) {
        // [1] Send first request to reach application
        WebRequest request = new GetMethodWebRequest("http://localhost:8080/lab02-events-portal/");
        try {
            WebResponse response = wc.getResponse(request);

            // [2] Go to "Login" screen
            WebLink link = response.getLinkWith("login");
            Assert.assertNotNull("'Login' link not found", link);
            link.click();
            WebForm[] forms = wc.getCurrentPage().getForms();
            WebForm addPersonForm = forms[0];
            addPersonForm.setParameter("username", USERNAME);
            addPersonForm.setParameter("password", PASSWORD);
            addPersonForm.submit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Login failed");
        }
    }
    
    public void logout(WebConversation wc) {
        try {
            WebLink link = wc.getCurrentPage().getLinkWith("Logout");
            link.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Logout failed");
        }
    }
}
