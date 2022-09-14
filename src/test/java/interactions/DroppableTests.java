package interactions;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;

public class DroppableTests extends BaseTest {

    @Test
    void dragAndDrop() {
        driver.get(DataProvider.DROPPABLE_URL);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.cssSelector("#draggable")), driver.findElement(By.cssSelector("#droppable"))).perform();
        assertThat(driver.findElement(By.cssSelector(".ui-droppable p")).getText()).isEqualTo("Dropped!");
    }
}
