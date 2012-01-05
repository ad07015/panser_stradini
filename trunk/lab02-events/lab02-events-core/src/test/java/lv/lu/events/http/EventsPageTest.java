/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.http;

import com.meterware.httpunit.TableRow;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebTable;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class EventsPageTest extends HttpGenericTest {

    private WebConversation wc;

    @Before
    public void setUp() {
        wc = new WebConversation();
        login(wc);
    }

    @After
    public void teerDown() {
        logout(wc);
    }

    @Test
    public void testInvitationTable() {
//        wc = new WebConversation();
//        login(wc);
        WebLink link = null;
        try {
            link = wc.getCurrentPage().getLinkWith("Events");
            assertNotNull(link);
            link.click();

            link = wc.getCurrentPage().getLinkWith("Create new public event");
            assertNotNull(link);
            link.click();

            WebForm[] forms = wc.getCurrentPage().getForms();
            assertEquals("Wrong number of forms on the page", 1, forms.length);
            WebForm createEventForm = forms[0];
            createEventForm.setParameter("name", EVENT_NAME);
            createEventForm.setParameter("startTime", "10.11.2011 12:20");
            createEventForm.setParameter("endTime", "10.11.2011 19:20");
            createEventForm.submit();

            WebTable invitationsTable = wc.getCurrentPage().getTables()[0];
            assertEquals("Wrong number of columns in Invitation table", 6, invitationsTable.getColumnCount());
            assertEquals("Current user is not the creator of the event", FULL_NAME, getInvitationTableLastEventCreatorFullName(invitationsTable));
            assertEquals("Current user is not the creator of the event", EVENT_NAME, getInvitationTableLastEventName(invitationsTable));

            WebTable attendingTable = wc.getCurrentPage().getTables()[2];
            assertEquals("Wrong number of columns in Attending table", 3, attendingTable.getColumnCount());

            int rowCount = invitationsTable.getRowCount();
            TableCell acceptCell = invitationsTable.getTableCell(rowCount - 1, 4);
            WebForm acceptEventInvitationForm = acceptCell.getForms()[0];
            assertNotNull(acceptEventInvitationForm);
            acceptEventInvitationForm.submit();
            
            assertEquals("Wrong number of tables on the page", 3, wc.getCurrentPage().getTables().length);
            assertTrue("Event not found in Attending table", isEventInAttendingTable(attendingTable, EVENT_NAME));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }

    @Test
    public void testMyEventsTable() {
//        wc = new WebConversation();
//        login(wc);
        WebLink link = null;
        try {
            link = wc.getCurrentPage().getLinkWith("Events");
            assertNotNull(link);
            link.click();
            WebTable myEventsTable = wc.getCurrentPage().getTables()[1];
            // TODO: continue implementing
//            assertEquals("Last event name is incorrect", EVENT_NAME, getMyEventsTableLastEventName(myEventsTable));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }

    @Test
    public void testAttendingEventsTable() {
        // TODO: implement
    }

    public String getInvitationTableLastEventCreatorFullName(WebTable table) {
        int rowCount = table.getRowCount();
        int lastRowIndex = rowCount - 1;
        TableCell nameCell = table.getTableCell(lastRowIndex, 2);
        return nameCell.getText();
    }

    public String getInvitationTableLastEventName(WebTable table) {
        int rowCount = table.getRowCount();
        int lastRowIndex = rowCount - 1;
        TableCell nameCell = table.getTableCell(lastRowIndex, 0);
        return nameCell.getText();
    }

    public String getMyEventsTableLastEventName(WebTable table) {
        int rowCount = table.getRowCount();
        int lastRowIndex = rowCount - 1;
        TableCell nameCell = table.getTableCell(lastRowIndex, 0);
        return nameCell.getText();
    }

    public Boolean isEventInAttendingTable(WebTable attendingTable, String eventName) {
        for (int i = 0; i < attendingTable.getRowCount(); i++) {
            TableCell nameCell = attendingTable.getTableCell(i, 0);
            if (EVENT_NAME.equals(nameCell.getText())) {
                return true;
            }
        }
        return false;
    }
}
