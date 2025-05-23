package dias.mobile.automation.drivers;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import dias.mobile.automation.utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriver {

    private static IOSDriver driver;

    public static IOSDriver getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigReader.get("platformName"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigReader.get("platformVersion"));
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigReader.get("deviceName"));
                capabilities.setCapability(MobileCapabilityType.UDID, ConfigReader.get("udid"));
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigReader.get("automationName"));
                capabilities.setCapability("bundleId", ConfigReader.get("bundleId"));
                capabilities.setCapability("noReset", Boolean.parseBoolean(ConfigReader.get("noReset")));

                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Failed to initialize IOSDriver: " + e.getMessage());
            }
        }
        return driver;
    }
}
