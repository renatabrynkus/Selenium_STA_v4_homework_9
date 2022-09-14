package others;

import base.BaseTest;
import base.DataProvider;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class HighSiteTest extends BaseTest {

    @Test
    void shouldScrollUntilButtonVisible() throws IOException {
        driver.get(DataProvider.HIGH_SITE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Dimension screenSize = driver.manage().window().getSize();
        int screenHeight = screenSize.getHeight();

        for (int i = 0; i < screenHeight; i++) {
            try {
                driver.findElement(By.cssSelector("#scroll-button")).isDisplayed();
            } catch (NoSuchElementException element) {
                js.executeScript("window.scrollBy(0, 30)", "");
            }
        }
        takeScreenShot();
    }

    private void takeScreenShot() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(".//src/main/resources/screenshots/screen.png"));
    }
}
