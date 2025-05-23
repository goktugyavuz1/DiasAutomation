package dias.mobile.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class SearchPage {

    private final IOSDriver driver;

    public SearchPage(IOSDriver driver) {
        this.driver = driver;
    }

    private final By searchInput = AppiumBy.name("Neyi ucuza almak istiyorsun?");

    public void searchForProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName + "\n");
    }
}
