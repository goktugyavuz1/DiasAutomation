package dias.mobile.automation.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class Actions {

    private final IOSDriver driver;
    private final WebDriverWait wait;


    public Actions(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void swipe(String direction) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int startX = width / 2;
        int startY;
        int endY;

        if (direction.equalsIgnoreCase("down")) {
            startY = (int) (height * 0.8);
            endY = (int) (height * 0.2);
        } else if (direction.equalsIgnoreCase("up")) {
            startY = (int) (height * 0.2);
            endY = (int) (height * 0.8);
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }




    public boolean swipeUntilElementVisible(By locator, int maxAttempts, String direction) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
           // İf not on the screen swipe!!
        }
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                if (!driver.findElements(locator).isEmpty()) {
                    return true;
                }
            } catch (Exception e) {
                // İf element could not find ignored!!
            }

            swipe(direction);
            attempt++;
        }

        throw new RuntimeException("Element could not be found or is not visible after " + maxAttempts + " swipe attempts: " + locator);
    }

    public void swipeTiny() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.57);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    public boolean isClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
