package dias.mobile.automation.hooks;

import dias.mobile.automation.drivers.BaseDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public void setUp() {
        BaseDriver.getDriver();
    }

    @After
    public void tearDown() {
        if (BaseDriver.getDriver() != null) {
            BaseDriver.getDriver().quit();
        }
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        try {
            TakesScreenshot ts = (TakesScreenshot) BaseDriver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot - " + scenario.getStatus());
        } catch (Exception e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}
