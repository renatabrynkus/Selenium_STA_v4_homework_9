package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TooltipTests extends BaseTest {

    @Test
    void printTooltipMgs() {
        driver.get(DataProvider.TOOLTIP_URL);
        Actions action = new Actions(driver);
        List<WebElement> tooltipsOnText = driver.findElements(By.cssSelector("p a"));
        tooltipsOnText.add(driver.findElement(By.cssSelector("#age")));

        for (WebElement element : tooltipsOnText ) {
            System.out.println(element.getAttribute("title"));
        }
    }
}