package widgets;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.YearMonth;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DatepickerTests extends BaseTest {
    Calendar calendar = Calendar.getInstance();
    Random random = new Random();

    @Test
    void pickDate() {
        driver.get(DataProvider.DATEPICKER_URL);
        WebElement datepickerField = driver.findElement(By.cssSelector("#datepicker"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        int currentMonthIndex = calendar.get(Calendar.MONTH);

        //today
        datepickerField.click();
        driver.findElement(By.cssSelector(".ui-datepicker-today")).click();
        assertThat(datepickerField.getAttribute("value")).isEqualTo(today);

        //1st day next month
        int expectedMonthNumber = currentMonthIndex + 2;
        int expectedYear = calendar.get(Calendar.YEAR);
        datepickerField.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ui-datepicker-next")))).click();
        if (!driver.findElement(By.cssSelector(".ui-datepicker-year")).getText().equals(String.valueOf(expectedYear))) {
            expectedYear += 1;
        }
        clickChosenDay(1);
        assertThat(datepickerField.getAttribute("value")).isEqualTo(expectedMonthNumber + "/01/" + expectedYear);

        //last day from January next year
        datepickerField.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ui-datepicker-next"))));
        String nextYear = String.valueOf(calendar.get(Calendar.YEAR) + 1);
        int numberOfClicksForward = moveToFirstMonthNextYear(nextYear);
        clickChosenDay(31);
        assertThat(datepickerField.getAttribute("value")).isEqualTo("01/31/" + nextYear);

        //select the same date again
        datepickerField.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ui-datepicker-next"))));
        clickChosenDay(31);
        assertThat(datepickerField.getAttribute("value")).isEqualTo("01/31/" + nextYear);

        //random day from previous month
        datepickerField.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ui-datepicker-prev"))));
        moveToMonth(numberOfClicksForward);
        int randomDay = generateRandomDay(calendar.get(Calendar.MONTH));
        clickChosenDay(randomDay);
        String randomDayString = String.valueOf(randomDay);
        if (randomDayString.length() == 1) {
            randomDayString = "0" + randomDayString;
        }
        assertThat(datepickerField.getAttribute("value")).isEqualTo("0" + currentMonthIndex + "/" +
                randomDayString + "/" + calendar.get(Calendar.YEAR));

        //random date from last year
        datepickerField.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ui-datepicker-prev"))));
        int chosenMonth = convertMonthStringToInt(moveToRandomMonthPreviousYear()) + 1;
        generateRandomDay(chosenMonth);
        clickChosenDay(randomDay);
        String randomDayString2 = String.valueOf(randomDay);
        if (randomDayString2.length() == 1) {
            randomDayString2 = "0" + randomDayString;
        }

        assertThat(datepickerField.getAttribute("value")).isEqualTo("0" + chosenMonth + "/" +
                randomDayString2 + "/" + (calendar.get(Calendar.YEAR) - 1));
    }

    private void clickChosenDay(int chosenDay) {
        String dayYouWant = String.valueOf(chosenDay);
        List<WebElement> daysList = driver.findElements(By.cssSelector("tr td"));
        for (WebElement day : daysList) {
            if (!day.getAttribute("class").contains("ui-datepicker-other-month") && (day.getText().equals(dayYouWant))) {
                day.click();
                break;
            }
        }
    }

    private int moveToFirstMonthNextYear(String nextYear) {
        int numberOfClicksForward = 1;
        while (!driver.findElement(By.cssSelector(".ui-datepicker-year")).getText().equals(nextYear)) {
            driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
            numberOfClicksForward += 1;
        }
        return numberOfClicksForward;
    }

    private String moveToRandomMonthPreviousYear() {
        String previousYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
        while (!driver.findElement(By.cssSelector(".ui-datepicker-year")).getText().equals(previousYear)) {
            driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
        }
        int randomClicks = random.nextInt(12);
        String chosenMonth = "";
        for (int i = 0; i < randomClicks; i++) {
            driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
            chosenMonth = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
        }
        return chosenMonth;
    }

    private int convertMonthStringToInt(String month) {
        for (int i = 0; i < 12; i++)
            if (month.equals(DataProvider.months[i])) {
                return i;
            }
        return -1;
    }

    private int generateRandomDay(int month) {
        int numberOfDaysInMonth = getNumberOfDaysInMonth(calendar.get(Calendar.YEAR), month);
        int generatedNumber = random.nextInt(numberOfDaysInMonth);
        return generatedNumber;
    }

    private int getNumberOfDaysInMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    private void moveToMonth(int numberOfClicksForward) {
        for (int i = 0; i <= numberOfClicksForward; i++) {
            driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
        }
    }
}
