package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FormTests extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.FORM_MSG})
    void checkForm(String validatorMessage) {
        driver.get(DataProvider.FORM_URL);

        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys(DataProvider.FORM_FIRST_NAME);
        driver.findElement(By.cssSelector("#inputLastName3")).sendKeys(DataProvider.FORM_LAST_NAME);
        driver.findElement(By.cssSelector("#inputEmail3")).sendKeys(DataProvider.FORM_EMAIL);

        Random random = new Random();
        clickRandomSex(random);

        driver.findElement(By.cssSelector("#inputAge3")).sendKeys(Integer.toString(DataProvider.FORM_AGE));

        clickRandomYearOfExp(random);
        selectProfession();
        selectContinent(random);
        selectMultipleSeleniumCommands();
        uploadFile();

        driver.findElement(By.cssSelector(".btn-primary")).click();

        assertThat(driver.findElement(By.cssSelector("#validator-message")).getText()).isEqualTo(validatorMessage);
    }

    void clickRandomSex(Random random) {
        List<WebElement> radiosSex = driver.findElements(By.cssSelector("[name='gridRadiosSex']"));
        radiosSex.get(random.nextInt(radiosSex.size())).click();
    }

    void clickRandomYearOfExp(Random random) {
        List<WebElement> radiosYearOfExp = driver.findElements(By.cssSelector("[name='gridRadiosExperience']"));
        radiosYearOfExp.get(random.nextInt(radiosYearOfExp.size())).click();
    }

    void selectProfession() {
        driver.findElement(By.cssSelector("#gridCheckAutomationTester")).click();
    }

    void selectContinent(Random random) {
        Select continents = new Select(driver.findElement(By.cssSelector("#selectContinents")));
        List<WebElement> continentsList = continents.getOptions();
        continents.selectByIndex(random.nextInt(1, continentsList.size()));
    }

    void selectMultipleSeleniumCommands() {
        Select seleniumCommands = new Select(driver.findElement(By.cssSelector("#selectSeleniumCommands")));
        seleniumCommands.selectByValue("switch-commands");
        seleniumCommands.selectByValue("wait-commands");
    }

    void uploadFile() {
        File file = new File(DataProvider.FILE_PATH);
        driver.findElement(By.cssSelector("#chooseFile")).sendKeys(file.getAbsolutePath());
    }
}
