package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ModalDialogTests extends BaseTest {

    @Test
    void modalDialogueTest() {
        driver.get(DataProvider.MODAL_DIALOGUE_URL);
        driver.findElement(By.cssSelector("#create-user")).click();

        WebElement name = driver.findElement(By.cssSelector("#name"));
        fillFormField(name, DataProvider.MODAL_DIALOGUE_NAME);

        WebElement email = driver.findElement(By.cssSelector("#email"));
        fillFormField(email, DataProvider.MODAL_DIALOGUE_EMAIL);

        WebElement password = driver.findElement(By.cssSelector("#password"));
        fillFormField(password, DataProvider.MODAL_DIALOGUE_PASSWORD);

        driver.findElement(By.cssSelector(".ui-dialog-buttonset > .ui-button:first-child")).click();
        WebElement lastRow = driver.findElement(By.cssSelector("tbody tr:last-child"));

        String actualName = getFieldFromRow(lastRow, "td:first-child");
        String actualEmail = getFieldFromRow(lastRow, "td:nth-child(2)");
        String actualPassword = getFieldFromRow(lastRow, "td:nth-child(3)");

        assertThat(actualName).isEqualTo(DataProvider.MODAL_DIALOGUE_NAME);
        assertThat(actualEmail).isEqualTo(DataProvider.MODAL_DIALOGUE_EMAIL);
        assertThat(actualPassword).isEqualTo(DataProvider.MODAL_DIALOGUE_PASSWORD);
    }

    private void fillFormField(WebElement formField, String input) {
        formField.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        formField.sendKeys(input);
    }

    private String getFieldFromRow(WebElement lastRow, String selector) {
        return lastRow.findElement(By.cssSelector(selector)).getText();
    }
}
