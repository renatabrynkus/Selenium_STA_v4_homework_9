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

import java.time.Duration;
import java.util.List;

public class AccordionTests extends BaseTest {

    @Test
    void accordionTest() {
        driver.get(DataProvider.ACCORDION_URL);
        List<WebElement> sectionsList = driver.findElements(By.cssSelector(".ui-accordion-header"));

        for (WebElement section : sectionsList) {
            String ariaStatus = section.getAttribute("aria-expanded");

            if (!ariaStatus.equals("true")) {
                section.click();
            }
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfAllElements(section.findElements(By.cssSelector("*"))));

            String sectionId = "#" + section.getAttribute("id");
            String nextSectionId = generateNextSectionId(sectionId);

            WebElement nextSectionElement = driver.findElement(By.cssSelector(nextSectionId));
            printAllSubsections(nextSectionElement, nextSectionId);
        }
    }

    private String generateNextSectionId(String sectionId) {
        int currentId = Character.getNumericValue(sectionId.charAt(7)) + 1;
        return sectionId.substring(0, 7) + currentId;
    }

    private void printAllSubsections(WebElement nextSectionElement, String nextSectionId) {
        String newSelector = nextSectionId + " > *";
        List<WebElement> allChildren = nextSectionElement.findElements(By.cssSelector(newSelector));
        for (WebElement child : allChildren) {
            System.out.println(child.getAttribute("textContent"));
        }
    }
}