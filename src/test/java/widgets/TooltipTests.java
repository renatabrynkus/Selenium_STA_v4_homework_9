package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TooltipTests extends BaseTest {

    @Test
    void printTooltipMgs() {
        driver.get(DataProvider.TOOLTIP_URL);

        WebElement element = driver.findElement(By.cssSelector("#age"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();

        System.out.println(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText());
    }
}
