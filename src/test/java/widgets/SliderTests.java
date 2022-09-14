package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class SliderTests extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {50, 80, 80, 20, 0})
    void checkSlider(int value) {
        driver.get(DataProvider.SLIDER_URL);
        WebElement slider = driver.findElement(By.cssSelector("#custom-handle"));
        slider.getText();
        moveSlider(slider, value);
        assertThat(Integer.parseInt(driver.findElement(By.cssSelector("#custom-handle")).getText())).isEqualTo(value);
    }

    void moveSlider(WebElement slider, int number) {
        for (int i = 0; i < number; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
    }
}
