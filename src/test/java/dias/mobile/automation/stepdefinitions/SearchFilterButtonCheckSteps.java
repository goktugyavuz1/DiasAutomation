package dias.mobile.automation.stepdefinitions;

import dias.mobile.automation.drivers.BaseDriver;
import dias.mobile.automation.pages.*;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class SearchFilterButtonCheckSteps {

    LoginPage loginPage = new LoginPage(BaseDriver.getDriver());
    HomePage homePage = new HomePage(BaseDriver.getDriver());
    SearchPage searchPage = new SearchPage(BaseDriver.getDriver());
    FilterPage filterPage = new FilterPage(BaseDriver.getDriver());
    ProductListPage productListPage = new ProductListPage(BaseDriver.getDriver());
    ProductPage productPage = new ProductPage(BaseDriver.getDriver());

    @Given("user launches the Akakce app")
    public void userLaunchesTheApp() {
        // Application already launches with BeforeDriver
    }

    @When("user continues without login if prompted")
    public void userTapsOnContinueWithoutLogin()  {
        if (loginPage.isLoginPromptVisible()) {
            loginPage.tapContinueWithoutLogin();
        } else {
            System.out.println("Login page did not appear!");
        }
    }

    @Then("user searches for {string}")
    public void userSearchesForProduct(String productName) {
        homePage.tapSearchBox();
        searchPage.searchForProduct(productName);
    }

    @And("taps the filter button")
    public void tapsFilterButton(){
        productListPage.tapFilterButton();
    }

    @And("selects resolution format {string}")
    public void selectsResolutionFormat(String res1){
        filterPage.selectResolutionFormat(res1);
    }

    @And("taps on the See Products button")
    public void tapsOnSeeProductsButton() {
        filterPage.tapSeeProductsButton();
    }

    @And("selects {string} from sorting options")
    public void selectsSortingOption(String sortText) {
        productListPage.sortBy(sortText);
    }

    @And("taps on the {int}th product")
    public void tapsOnNthProduct(int index) {
        productListPage.tapNthProduct(index);
    }

    @And("user taps on the Go to Product button")
    public void tapsGoToProductButton() {
        productListPage.tapGoToProductButton();
    }

    @Then("verifies the Seller button is visible")
    public void verifiesSellerButton() {
        Assert.assertTrue("Seller button is not visible", productPage.isSellerButtonVisible());
    }
}
