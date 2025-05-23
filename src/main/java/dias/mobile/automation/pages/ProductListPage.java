package dias.mobile.automation.pages;

import dias.mobile.automation.utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListPage {

    private final IOSDriver driver;
    private final Actions actions;

    public ProductListPage(IOSDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private final By filterButton = AppiumBy.name("Filtrele");
    private final By sortDropdown = AppiumBy.name("sort-icon");
    private static final String CELL_STATIC_TEXT_CHAIN_FORMAT =
            "**/XCUIElementTypeCollectionView/XCUIElementTypeCell[%d]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
    private final By goToProductButton = AppiumBy.iOSNsPredicateString("label == 'Ürüne Git'");

    public void tapFilterButton(){
        if (actions.swipeUntilElementVisible(filterButton, 5, "down")) {
            driver.findElement(filterButton).click();
        } else {
            throw new RuntimeException("Element not found after 5 swipe attempts: Filter Button");
        }
    }

    public void sortBy(String sortOptionText) {
        driver.findElement(sortDropdown).click();
        By option = AppiumBy.name(sortOptionText);
        driver.findElement(option).click();
    }

    public void tapNthProduct(int targetIndex) {
        for (int i = 0; i < targetIndex-2; i++) {
            String targetText = getCellText(6);

            while (true) {
                actions.swipeTiny();
                String currentText = getCellText(5);

                if (currentText.equals(targetText)) {
                    break;
                }
            }
        }

        String chain = String.format(CELL_STATIC_TEXT_CHAIN_FORMAT, 6);
        WebElement element = driver.findElement(AppiumBy.iOSClassChain(chain));
        element.click();
    }

    private String getCellText(int index) {
        String chain = String.format(CELL_STATIC_TEXT_CHAIN_FORMAT, index);
        WebElement element = driver.findElement(AppiumBy.iOSClassChain(chain));
        return element.getText();
    }

    public void tapGoToProductButton() {
        driver.findElement(goToProductButton).click();
    }
}
