package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TablePage {
    public TablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> allPeaks;

    public List<RowPage> getAllPeaks() {
        List<RowPage> allPeaksPOP = new ArrayList<>();
        for (WebElement peak : allPeaks) {
            allPeaksPOP.add(new RowPage(peak));
        }
        return allPeaksPOP;
    }

    public void getPeaksWithStateAndHeight(String state, int height) {
        for (RowPage peak : getAllPeaks()) {
            if (peak.getState().contains(state) && peak.getHeight() > height) {
                System.out.println(peak.getName());
            }
        }
    }
}