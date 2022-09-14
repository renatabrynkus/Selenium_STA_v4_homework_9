package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableTests extends BaseTest {

    @Test
    void printMountainsOverHeight() {
        driver.get(DataProvider.TABLE_URL);
        printSpecificMountains("Switzerland", 4000);
    }

    public TableTests(WebDriver driver) {
        this.driver = driver;
    }

    void printSpecificMountains(String country, int minHeight) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

        if (rows.size() == 0) {
            System.out.println("Sorry, the table is empty");
        }
        for (WebElement row : rows) {
            int rank = Integer.parseInt(row.findElement(By.cssSelector("th")).getText());
            String peak = row.findElements(By.cssSelector("td")).get(getIndexOfColumn("Peak")).getText();
            String mountainRange = row.findElements(By.cssSelector("td")).get(getIndexOfColumn("Mountain range")).getText();
            String state = row.findElements(By.cssSelector("td")).get(getIndexOfColumn("State")).getText();
            int height = Integer.parseInt(row.findElements(By.cssSelector("td")).get(getIndexOfColumn("Height")).getText());

            if (state.contains(country) && (height > minHeight)) {
                System.out.println(rank + " " + peak + " " + mountainRange);
            }
        }
    }

    private int getIndexOfColumn(String columnName) {
        List<WebElement> headers = driver.findElements(By.cssSelector("thead th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equals(columnName)) {
                return i - 1;
            }
        }
        return -1;
    }
}
