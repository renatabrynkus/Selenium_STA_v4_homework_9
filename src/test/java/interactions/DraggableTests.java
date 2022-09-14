package interactions;

import base.BaseTest;
import base.DataProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class DraggableTests extends BaseTest {

    //niegotowe, miałam problem z przesunięciem z prawego gónego rogu w prawy dolny
    @Test
    void dragSquare() {
        driver.get(DataProvider.DRAGGABLE_URL);
        Actions actions = new Actions(driver);

        WebElement dragMeAround = driver.findElement(By.cssSelector("#draggable"));
        Point position = dragMeAround.getLocation();
        Dimension screenSize = driver.manage().window().getSize();
        int screenHeight = screenSize.getHeight();
        int screenWidth = screenSize.getWidth();

        int leftUpperCornerX = position.getX();
        int leftUpperCornerY = position.getY();

        Dimension dimension = dragMeAround.getSize();
        int dragMeAroundWidth = dimension.width;
        int dragMeAroundHeight = dimension.height;

        int rightUpperCornerX = leftUpperCornerX + dragMeAroundWidth;

        actions.clickAndHold(dragMeAround).perform();
        actions.moveByOffset(screenWidth - rightUpperCornerX, -leftUpperCornerY).perform();

        Point position2 = dragMeAround.getLocation();
        int leftUpperCornerX2 = position2.getX();
        int leftUpperCornerY2 = position2.getY();
        System.out.println("Position 2: " + leftUpperCornerX2 + " " + leftUpperCornerY2);

        //tutaj gubiło mi się 9 pikseli; jeśli w moveByOffset X był ustawiony na 0 to w position3 x = 892, a nie 901 jak w poprzednim
        actions.moveByOffset(9, screenHeight - 2 * dragMeAroundHeight).perform();

        Point position3 = dragMeAround.getLocation();
        int leftUpperCornerX3 = position3.getX();
        int leftUpperCornerY3 = position3.getY();
        System.out.println("Position 3: " + leftUpperCornerX3 + " " + leftUpperCornerY3);

        int halfScreenWidth = screenWidth / 2;
        int halfScreenHeight = screenHeight / 2;
        System.out.println(halfScreenHeight);
        System.out.println(screenHeight);
        System.out.println(halfScreenWidth);
        System.out.println(screenWidth);

        actions.moveByOffset(- (halfScreenWidth - dragMeAroundWidth),  - (halfScreenHeight - dragMeAroundHeight)).perform();

        Point position4 = dragMeAround.getLocation();
        int leftUpperCornerX4 = position4.getX();
        int leftUpperCornerY4 = position4.getY();
        System.out.println(leftUpperCornerX4 + " " + leftUpperCornerY4);
    }
}
