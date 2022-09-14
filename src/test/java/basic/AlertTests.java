package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertTests extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.OK_BUTTON_MSG})
    void checkSimpleAlert(String expectedMessage) {
        driver.get(DataProvider.ALERTS_URL);
        driver.findElement(By.cssSelector("#simple-alert")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#simple-alert-label")).getText()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.GREETING_MGS})
    void checkPromptAlert(String expectedMessage) {
        driver.get(DataProvider.ALERTS_URL);
        driver.findElement(By.cssSelector("#prompt-alert")).click();
        driver.switchTo().alert().sendKeys(DataProvider.PROMPT_ALERT_NAME);
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#prompt-label")).getText()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.CONFIRM_ALERT_OK_MSG})
    void checkConfirmAlertOK(String expectedMessage) {
        driver.get(DataProvider.ALERTS_URL);
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.CONFIRM_ALERT_CANCEL_MSG})
    void checkConfirmAlertCancel(String expectedMessage) {
        driver.get(DataProvider.ALERTS_URL);
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.OK_BUTTON_MSG})
    void checkDelayedAlert(String expectedMessage) {
        driver.get(DataProvider.ALERTS_URL);
        driver.findElement(By.cssSelector("#delayed-alert")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#delayed-alert-label")).getText()).isEqualTo(expectedMessage);
    }
}
