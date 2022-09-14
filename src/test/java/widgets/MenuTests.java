package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuTests extends BaseTest {

    @Test
    void menuTest() {
        driver.get(DataProvider.MENU_URL);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("#ui-id-9")).click();

        WebElement jazz = driver.findElement(By.cssSelector("#ui-id-13"));
        wait.until(ExpectedConditions.visibilityOf(jazz));
        jazz.click();

        WebElement modern = driver.findElement(By.cssSelector("#ui-id-16"));
        wait.until(ExpectedConditions.visibilityOf(modern));
        modern.click();
    }
}
