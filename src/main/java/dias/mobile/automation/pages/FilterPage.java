package dias.mobile.automation.pages;

import dias.mobile.automation.utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class FilterPage {

    private final IOSDriver driver;
    private final Actions actions;

    public FilterPage(IOSDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private final By seeProductsButton = AppiumBy.iOSNsPredicateString("name CONTAINS[c] 'ürünleri gör'");

    public void selectResolutionFormat(String resolution) {
        By option = AppiumBy.name(resolution);
        if (actions.swipeUntilElementVisible(option, 5, "down")) {
            driver.findElement(option).click();
        } else {
            throw new RuntimeException("Element not found after 5 swipe attempts: Filter Button");
        }
    }

    public void tapSeeProductsButton() {

        driver.findElement(seeProductsButton).click();
    }
}
