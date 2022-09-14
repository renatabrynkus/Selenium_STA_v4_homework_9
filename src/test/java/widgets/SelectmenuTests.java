package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SelectmenuTests extends BaseTest {
    Random random = new Random();

    @Test
    void selectMenu() {
        driver.get(DataProvider.SELECT_MENU_URL);

        driver.findElement(By.cssSelector("#speed-button")).click();
        selectRandomOption(driver.findElements(By.cssSelector("#speed-menu li")));

        driver.findElement(By.cssSelector("#files-button")).click();
        selectOptionByText(driver.findElements(By.cssSelector("#files-menu .ui-menu-item")));

        driver.findElement(By.cssSelector("#number-button")).click();
        List<WebElement> numbersList = driver.findElements(By.cssSelector("#number-menu li"));
        WebElement chosenNumber = numbersList.get(DataProvider.SELECTMENU_NUMBER);
        System.out.println(chosenNumber.getText());
        chosenNumber.click();

        driver.findElement(By.cssSelector("#salutation-button")).click();
        selectRandomOptionNoFirstElement(driver.findElements(By.cssSelector("#salutation-menu li")));
    }

    private void selectRandomOption(List<WebElement> list) {
        WebElement chosenElement = list.get(random.nextInt(list.size()));
        clickOnOption(chosenElement);
    }

    private void selectRandomOptionNoFirstElement(List<WebElement> list) {
        WebElement chosenElement = list.get(random.nextInt(list.size() - 1) + 1);
        clickOnOption(chosenElement);
    }

    private void clickOnOption(WebElement chosenElement) {
        System.out.println(chosenElement.getText());
        chosenElement.click();
    }

    private void selectOptionByText(List<WebElement> list) {
        for (WebElement file : list) {
            if (file.getText().equals(DataProvider.SELECTMENU_FILE)) {
                System.out.println(file.getText());
                file.click();
            }
        }
    }
}