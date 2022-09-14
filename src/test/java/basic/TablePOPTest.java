package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import pages.TablePage;

public class TablePOPTest extends BaseTest {

    @Test
    public void tableTestWithPOP() {
        driver.get(DataProvider.TABLE_URL);
        TablePage tablePage = new TablePage(driver);
        tablePage.getPeaksWithStateAndHeight("Switzerland", 4000);
    }
}
