package dias.mobile.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private final IOSDriver driver;

    public LoginPage(IOSDriver driver) {
        this.driver = driver;
    }

    private final By continueWithoutLoginButton = AppiumBy.name("Üye Olmadan Devam et");

    public boolean isLoginPromptVisible() {
        return !driver.findElements(continueWithoutLoginButton).isEmpty();
    }

    public void tapContinueWithoutLogin() {
        driver.findElement(continueWithoutLoginButton).click();
    }
}
