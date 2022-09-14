package basic;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FormPage;

import static org.assertj.core.api.Assertions.assertThat;

public class FormFluentPOPTest extends BaseTest {
    FormPage formPage;

    @ParameterizedTest
    @ValueSource(strings = {DataProvider.FORM_MSG})
    void shouldSendFormWithSuccess(String validatorMessage) {
        driver.get(DataProvider.FORM_URL);
        formPage = new FormPage(driver);

        formPage.setFirstName(DataProvider.FORM_FIRST_NAME)
                .setLastName(DataProvider.FORM_LAST_NAME)
                .setEmail(DataProvider.FORM_EMAIL)
                .selectRandomSex()
                .setAge(DataProvider.FORM_AGE)
                .selectRandomExperience()
                .selectProfessionAutomationTester()
                .selectContinent("europe")
                .selectTwoCommands("switch-commands", "wait-commands")
                .uploadFile(DataProvider.FILE_PATH)
                .clickSignInBtn();

        assertThat(formPage.getValidatorMsg()).isEqualTo(validatorMessage);
    }
}
