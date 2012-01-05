package lv.lu.events.http;

import org.junit.Ignore;
import org.junit.Test;

import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebTable;

import junit.framework.Assert;

/**
 * Sample HttpUnit test.
 * Precondition: users.xml file is imported into database.
 * Application must be running on localhost:8080.
 */
@Ignore
public class LoginTest extends HttpGenericTest{
    
    @Test
    public void testLogin() {
        WebConversation wc = new WebConversation();
        try {
            login(wc);
            WebLink link;

            // [3] Go to "People" screen and check anything is present in a table
            link = wc.getCurrentPage().getLinkWith("People");
            Assert.assertNotNull("'People' link not found", link);
            link.click();

            // check that there is one table on the screen
            WebTable[] tables = wc.getCurrentPage().getTables();
            Assert.assertEquals("Wrong number of tables received after click on 'People'", 1, tables.length);

            // search for test Person index in the table
            WebTable peopleTable = tables[0];
            Assert.assertEquals("Wrong number of columns in People table", 2, peopleTable.getColumnCount());
            Assert.assertEquals("Wrong number of rows in People table", 5, peopleTable.getRowCount());
            Assert.assertFalse("Current user is present in People table", isCurrentUserNotPresent(peopleTable));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }

    private boolean isCurrentUserNotPresent(WebTable peopleTable){
        for (int i=0; i<peopleTable.getRowCount(); i++){
            TableCell nameCell = peopleTable.getTableCell(i, 1);
            if (USERNAME.equals(nameCell.getText())){
               return true;
            }
        }
        return false;
    }
}
