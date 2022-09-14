package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class WindowsTabsTests extends BaseTest {

    @Test
    void checkNewBrowserWindow() {
        driver.get(DataProvider.WINDOWS_URL);
        String originalWindow = driver.getWindowHandle();
        verifyNumberOfWindows();

        driver.findElement(By.cssSelector("#newBrowserWindow")).click();
        switchToWinOrTab(originalWindow);
        new TableTests(driver).printSpecificMountains("Switzerland", 4000);
        driver.close();

        driver.switchTo().window(originalWindow);

        driver.findElement(By.cssSelector("#newMessageWindow")).click();
        switchToWinOrTab(originalWindow);
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
        driver.close();

        driver.switchTo().window(originalWindow);
        driver.findElement(By.cssSelector("#newBrowserTab")).click();
        switchToWinOrTab(originalWindow);
        new TableTests(driver).printSpecificMountains("Switzerland", 4000);
        switchToWinOrTab(originalWindow);
        driver.close();
    }

    private void verifyNumberOfWindows() {
        if (driver.getWindowHandles().size() > 1) {
            System.out.println("There are too many windows open");
        }
    }

    private void switchToWinOrTab(String originalWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}






