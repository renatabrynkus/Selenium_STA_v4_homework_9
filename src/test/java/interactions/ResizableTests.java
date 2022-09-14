package interactions;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ResizableTests extends BaseTest {

    @Test
    void resizeWindowRight() {
        driver.get(DataProvider.RESIZABLE_URL);
        Actions actions = new Actions(driver);


        actions.clickAndHold(driver.findElement(By.cssSelector(".ui-resizable-e")))
                .moveByOffset(28, 0)
                .release()
                .build()
                .perform();
    }

    @Test
    void resizeWindowBottom() {
        driver.get(DataProvider.RESIZABLE_URL);
        Actions actions = new Actions(driver);

        actions.clickAndHold(driver.findElement(By.cssSelector(".ui-resizable-s")))
                .moveByOffset(0, 28)
                .release()
                .build()
                .perform();
    }

    @Test
    void resizeWindowRightBottom() {
        driver.get(DataProvider.RESIZABLE_URL);
        Actions actions = new Actions(driver);

        actions.clickAndHold(driver.findElement(By.cssSelector(".ui-resizable-se")))
                .moveByOffset(28, 28)
                .release()
                .build()
                .perform();
    }
}
