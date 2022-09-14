package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FormPage;

import static org.assertj.core.api.Assertions.assertThat;

public class FormPOPTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.FORM_MSG})
    void shouldSendFormWithSuccess(String validatorMessage) {
        driver.get(DataProvider.FORM_URL);
        FormPage formPage = new FormPage(driver);

        formPage.setFirstName(DataProvider.FORM_FIRST_NAME);
        formPage.setLastName(DataProvider.FORM_LAST_NAME);
        formPage.setEmail(DataProvider.FORM_EMAIL);
        formPage.selectRandomSex();
        formPage.setAge(DataProvider.FORM_AGE);
        formPage.selectRandomExperience();
        formPage.selectProfessionAutomationTester();
        formPage.selectContinent("europe");
        formPage.selectTwoCommands("switch-commands", "wait-commands");
        formPage.uploadFile(DataProvider.FILE_PATH);
        formPage.clickSignInBtn();

        assertThat(formPage.getValidatorMsg()).isEqualTo(validatorMessage);
    }
}
