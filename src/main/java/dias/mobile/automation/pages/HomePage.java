package dias.mobile.automation.pages;

import dias.mobile.automation.utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HomePage {

    private final IOSDriver driver;
    private final Actions actions;

    public HomePage(IOSDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private final By searchBox = AppiumBy.name("Neyi ucuza almak istiyorsun?");

    public void tapSearchBox() {
        if (actions.isClickable(searchBox)){
            driver.findElement(searchBox).click();
        }else {
            throw new RuntimeException(searchBox+" Not Clickable!");
        }

    }
}
