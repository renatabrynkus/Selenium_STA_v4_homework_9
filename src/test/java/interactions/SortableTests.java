package interactions;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Collections;
import java.util.List;

public class SortableTests extends BaseTest {

    @Test
    void sortable() {
        driver.get(DataProvider.SORTABLE_URL);

        List<WebElement> itemsList = driver.findElements(By.cssSelector(".ui-sortable-handle"));
        List<WebElement> shuffledList = driver.findElements(By.cssSelector(".ui-sortable-handle"));

        Collections.shuffle(shuffledList);
        printShuffledList(shuffledList);

        for (int i = 0; i < itemsList.size(); i++) {
            WebElement elementToDrag = shuffledList.get(i);
            WebElement placeToDrop = driver.findElement(By.cssSelector("#sortable li:nth-child(" + (i + 1) + ")"));

            Actions actions = new Actions(driver);
            actions.dragAndDrop(elementToDrag, placeToDrop).perform();
        }
    }

    private void printShuffledList(List<WebElement> shuffledList) {
        for (WebElement element : shuffledList) {
            System.out.println(element.getText());
        }
    }
}
