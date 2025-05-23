package dias.mobile.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage {

    private final IOSDriver driver;

    public ProductPage(IOSDriver driver) {
        this.driver = driver;
    }

    private final By sellerButton = AppiumBy.iOSNsPredicateString("label == 'Satıcıya Git'");

    public boolean isSellerButtonVisible() {
        WebElement button = driver.findElement(sellerButton);
        return button.isDisplayed();
    }
}
