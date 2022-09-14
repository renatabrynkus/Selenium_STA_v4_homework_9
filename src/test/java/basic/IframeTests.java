package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class IframeTests extends BaseTest {

    @Test
    void checkIframes() {
        driver.get(DataProvider.IFRAME_URL);
        driver.switchTo().frame("iframe1");

        fillIframeShortForm(DataProvider.IFRAME_NAME, DataProvider.IFRAME_NAME);

        driver.switchTo().defaultContent();
        driver.switchTo().frame("iframe2");

        fillIframeExtendForm(DataProvider.IFRAME_LOGIN, DataProvider.IFRAME_PASSWORD, "europe");
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".nav-link")).click();
    }

    void selectContinent(String continent) {
        Select continents = new Select(driver.findElement(By.cssSelector("#inlineFormCustomSelectPref")));
        continents.selectByValue(continent);
    }

    void fillIframeShortForm(String firstName, String surname) {
        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#inputSurname3")).sendKeys(surname);
        driver.findElement(By.cssSelector(".btn")).click();
    }

    void fillIframeExtendForm(String login, String password, String continent) {
        driver.findElement(By.cssSelector("#inputLogin")).sendKeys(login);
        driver.findElement(By.cssSelector("#inputPassword")).sendKeys(password);

        selectContinent(continent);

        driver.findElement(By.cssSelector("#gridRadios5")).click();
    }
}
