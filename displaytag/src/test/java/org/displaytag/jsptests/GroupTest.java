package org.displaytag.jsptests;

import org.displaytag.test.DisplaytagCase;
import org.displaytag.test.KnownValue;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;


/**
 * Tests for basic displaytag functionalities.
 * @author Fabrizio Giustina
 * @version $Revision$ ($Author$)
 */
public class GroupTest extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "group.jsp";
    }

    /**
     * Tests row grouping. bug #923446
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Test
    public void doTest() throws Exception
    {

        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));

        WebResponse response = runner.getResponse(request);

        if (log.isDebugEnabled())
        {
            log.debug("RESPONSE: " + response.getText());
        }

        WebTable[] tables = response.getTables();

        Assert.assertEquals("Wrong number of tables.", 1, tables.length);
        Assert.assertEquals("Wrong number of rows in table.", 3, tables[0].getRowCount());

        Assert.assertEquals("Column not grouped.", "", tables[0].getCellAsText(2, 0));
        Assert.assertEquals("Column not grouped.", "", tables[0].getCellAsText(2, 1));
        Assert.assertEquals("Column should not be grouped.", KnownValue.CAMEL, tables[0].getCellAsText(2, 2));
    }
}