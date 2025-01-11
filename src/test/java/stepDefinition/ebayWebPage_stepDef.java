package stepDefinition;

import com.aventstack.extentreports.ExtentTest;
import hooks.ExtentHooks;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.EbayHomeScreen;
import io.cucumber.java.en.*;
import utilities.CommonFunctions;


public class ebayWebPage_stepDef {

    private final WebDriver driver = ExtentHooks.getDriver();
    private final EbayHomeScreen ebayHomeScreen = new EbayHomeScreen(driver);
    private final ExtentTest test = ExtentHooks.getTest(); // Use ExtentHooks.getTest() here
    private final CommonFunctions commonFunctions = new CommonFunctions(driver);

    @Given("I navigate the URL of the website")
    public void i_navigate_the_url_of_the_website() {
        String expectedUrl = "https://www.ebay.com/";
        driver.get(expectedUrl);
        test.pass("<b>Navigated to Ebay HomePage</b>");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

    }

    @When("I search for the book")
    public void i_search_for_the_book() {
        ebayHomeScreen.text_SearchBox("Books");
        test.pass("Books keyword Searched");
        ebayHomeScreen.btnSearch();
        test.pass("Clicked on Search Button");

    }

    @When("I click on the first book on the list")
    public void i_click_on_the_first_book_on_the_list() {
        ebayHomeScreen.firstItem();
        test.pass("Clicked on First Item");
        commonFunctions.switchToSingleNewTab();
        test.pass("New Tab Switched");
    }

    @When("I click on Add to cart")
    public void i_click_on_add_to_cart() {
        ebayHomeScreen.btnAddToCart();
        test.pass("Clicked on Add to cart button");
    }

    @When("I verify the cart is updated")
    public void i_verify_the_cart_is_updated() {
        String addedItemValue = ebayHomeScreen.txtAddToCart();
        String actualAddedItem = ebayHomeScreen.storedItemText;
        test.info("Actual Product Added: "+actualAddedItem);
        test.info("Product on Final Page: "+actualAddedItem);
        try {
            Assert.assertEquals(actualAddedItem, addedItemValue);
            test.pass("The cart contains the expected item: " + addedItemValue);
        } catch (AssertionError e) {
            test.fail("Assertion failed: The cart does not contain the expected item. Expected: "
                    + actualAddedItem + ", but got: " + addedItemValue);
            throw e; // rethrow to allow cucumber to mark the scenario as failed
        }
    }

    @Then("I display the number of items added in the cart")
    public void i_display_the_number_of_items_added_in_the_cart() {
        String iconCount = ebayHomeScreen.iconAddToCart();
        test.info("The quantity of the cart is: " + iconCount);

    }

}
