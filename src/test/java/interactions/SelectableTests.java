package interactions;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectableTests extends BaseTest {

    @Test
    void shouldTextForSelectedItemsAppear() {
        driver.get(DataProvider.SELECTABLE_URL);

        List<WebElement> itemsList = driver.findElements(By.cssSelector(".ui-widget-content"));
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.LEFT_CONTROL).perform();

        itemsList.get(0).click();
        itemsList.get(2).click();
        itemsList.get(3).click();

        assertThat(driver.findElement(By.cssSelector("#feedback")).getText()).isEqualTo("You've selected: #1 #3 #4.");
    }
}
