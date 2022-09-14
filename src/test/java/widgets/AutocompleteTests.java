package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AutocompleteTests extends BaseTest {

    @Test
    void shouldMatchText() {
        driver.get(DataProvider.AUTOCOMPLETE_URL);
        WebElement search = driver.findElement(By.cssSelector("#search"));
        search.sendKeys("a");

        List<WebElement> optionsList = driver.findElements(By.cssSelector(".ui-menu-item"));

        for (WebElement option : optionsList) {
            System.out.println(option.getText());
        }

        String expectedOption = selectRandomOption(optionsList);
        assertThat(search.getAttribute("value")).isEqualTo(expectedOption);
    }

    String selectRandomOption(List<WebElement> optionsList) {
        Random random = new Random();
        int chosenOptionIndex = random.nextInt(optionsList.size());
        String optionText = optionsList.get(chosenOptionIndex).getText();
        System.out.println("The chosen option is " + optionText);
        optionsList.get(chosenOptionIndex).click();

        return optionText;
    }
}
