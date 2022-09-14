package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(id = "inputLastName3")
    private WebElement lastName;

    @FindBy(id = "inputEmail3")
    private WebElement email;

    @FindBy(id = "inputAge3")
    private WebElement age;

    @FindBy(name = "gridRadiosSex")
    private List<WebElement> sex;

    @FindBy(name = "gridRadiosExperience")
    private List<WebElement> yearsOfExperience;

    @FindBy(id = "gridCheckAutomationTester")
    private WebElement professionAutomationTester;

    @FindBy(id = "selectContinents")
    private WebElement continents;

    @FindBy(id = "selectSeleniumCommands")
    private WebElement seleniumCommands;

    @FindBy(id = "chooseFile")
    private WebElement file;

    @FindBy(css = ".btn-primary")
    private WebElement signInBtn;

    @FindBy(id = "validator-message")
    private WebElement validatorMsg;

    public FormPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public FormPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public FormPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public FormPage setAge(int age) {
        this.age.sendKeys(Integer.toString(age));
        return this;
    }

    public FormPage selectRandomSex() {
        getRandomElement(sex).click();
        return this;
    }

    public FormPage selectRandomExperience() {
        getRandomElement(yearsOfExperience).click();
        return this;
    }

    public FormPage selectProfessionAutomationTester() {
        professionAutomationTester.click();
        return this;
    }

    public FormPage selectContinent(String continent) {
        new Select(continents).selectByValue(continent);
        return this;
    }

    public FormPage selectTwoCommands(String firstCommand, String secondCommand) {
        new Select(seleniumCommands).selectByValue(firstCommand);
        new Select(seleniumCommands).selectByValue(secondCommand);
        return this;
    }

    public FormPage uploadFile(String path) {
        File file = new File(path);
        this.file.sendKeys(file.getAbsolutePath());
        return this;
    }

    public FormPage clickSignInBtn() {
        signInBtn.click();
        return this;
    }

    public String getValidatorMsg() {
        return validatorMsg.getText();
    }

    private WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        return elements.get(random.nextInt(elements.size()));
    }
}
