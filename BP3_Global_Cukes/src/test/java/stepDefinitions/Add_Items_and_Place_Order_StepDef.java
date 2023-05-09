package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;

public class Add_Items_and_Place_Order_StepDef extends BaseClass{

    public static String backPackPrice = null;
    public static String bikeLightPrice = null;

    @Given("User launches browser")
    public void user_launch_browser() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        bagPage = new BagPage(driver);
        checkOutInfoPage = new CheckOutInfoPage(driver);
        checkoutOverViewPage = new CheckoutOverViewPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);

    }

    @When("User navigates to URL {string}")
    public void user_navigates_to_url(String saucedemoURL) {
        driver.get(saucedemoURL);
        driver.manage().window().maximize();
    }

    @Then("User enters login credentials username as {string} and password as {string}")
    public void user_enters_login_credentials(String uName, String pass) {
        loginPage.setUserName(uName);
        loginPage.setPassword(pass);
    }

    @And("User clicks on login button")
    public void user_clicks_on_login_button() {

        loginPage.clickLogin();
    }


    @Given("User in product page and page should contain text {string}")
    public void user_in_product_page_and_page_should_contain_text(String stringInProductPage) {

        if(driver.getPageSource().contains(stringInProductPage)) {
            Assert.assertTrue("Product page loaded successfully",true);
            backPackPrice = productPage.txtProductBackPackPrice.getText().trim();
            bikeLightPrice = productPage.txtProductBikeLightPrice.getText().trim();
        }
    }

    @When("User adds product back pack and bike light to cart")
    public void user_adds_product_to_cart() {
        productPage.addProdBackPack();
        if(productPage.btnRemoveProductBackPack.isDisplayed()){
            Assert.assertTrue("Back Pack Added Successfully", true);
        }
        productPage.addProdBikeLight();
        if(productPage.btnRemoveProductBikeLight.isDisplayed()){
            Assert.assertTrue("Bike Light Added Successfully", true);
        }
    }

    @When("User clicks on the cart button")
    public void user_navigates_to_bag_page() {
        productPage.navigateToBag();
    }

    @Then("Page should contain added items back pack and bike light")
    public void page_should_contain_added_items() {
        if(bagPage.txtAddedBackPackInBag.isDisplayed()){
            Assert.assertTrue("Bag has the added item 'Back Pack'", true);
        }
        if(bagPage.txtAddedBikeLightInBag.isDisplayed()){
            Assert.assertTrue("Bag has the added item 'Bike Light'", true);
        }
    }

    @Then("Price of the items back pack and bike light should be same as product page")
    public void price_of_the_items_in_bag_should_be_same_as_product_page() {
        if(bagPage.txtBackPackPriceInBag.isDisplayed()){
            if(backPackPrice.equals(bagPage.txtBackPackPriceInBag.getText().trim())){
                Assert.assertTrue("Back Pack item price in bag matches with the price in Product page", true);
            }
        }
        if(bagPage.txtBikeLightPriceInBag.isDisplayed()){
            if(backPackPrice.equals(bagPage.txtBikeLightPriceInBag.getText().trim())){
                Assert.assertTrue("Bike Light item price in bag matches with the price in Product page", true);
            }
        }
    }

    @Given("User is in bag page and click on checkout button")
    public void user_is_in_bag_page_and_click_on_checkout_button() {
        bagPage.clickCheckout();
    }

    @When("User lands on checkout page and page should contain text {string}")
    public void user_lands_on_checkout_page_and_page_should_contain_text(String stringCheckoutInfo) {
        if(checkOutInfoPage.labelCheckOutInfo.getText().trim().equals(stringCheckoutInfo)){
            Assert.assertTrue("User navigated to checkout info page", true);
        }

    }

    @Then("User enters {string}, {string} and {string}")
    public void user_enters_and(String firstName, String lastName, String zipCode) {
        checkOutInfoPage.setCustomerInfo(firstName, lastName, zipCode);
    }

    @And("User clicks on the Continue button")
    public void user_clicks_on_the_continue_button()  {
        checkOutInfoPage.checkOutContinue();

    }

    @Given("User is in checkout overview page and page should contain text {string}")
    public void user_is_in_checkout_overview_page_and_page_should_contain_text(String stringCheckoutOverView) {
        if(checkoutOverViewPage.labelCheckOutOverviewInfo.getText().trim().equals(stringCheckoutOverView)){
            Assert.assertTrue("User landed in checkout info page", true);
        }
    }

    @When("User validates item details in checkout overview page")
    public void user_validates_item_details_in_checkout_overview_page() {
        if(checkoutOverViewPage.labelBackPackInCheckOut.isDisplayed()){
            Assert.assertTrue("'Back Pack' is not shown in checkout page", true);
        }
        if(checkoutOverViewPage.labelBikeLightInCheckOut.isDisplayed()){
            Assert.assertTrue("'Bike Light' is not shown in checkout page", true);
        }
    }

    @When("User validates product price, tax and order total")
    public void user_validates_product_price_tax_and_order_total() {
        String backPackPriceAsString = checkoutOverViewPage.labelBackPackPriceInCheckOut.getText().trim();
        String bikeLightPriceAsString = checkoutOverViewPage.labelBikeLightPriceInCheckOut.getText().trim();
        String itemTotalFromAppAsString = checkoutOverViewPage.labelItemTotal.getText().trim();
        String taxAsString = checkoutOverViewPage.labelItemTax.getText().trim();
        String orderTotalFromAppAsString = checkoutOverViewPage.labelOrderTotal.getText().trim();

        double itemTotalFromApp = Double.parseDouble(itemTotalFromAppAsString.substring(13,itemTotalFromAppAsString.length()));
        double backPackPriceInCheckout = Double.parseDouble(backPackPriceAsString.substring(1,backPackPriceAsString.length()));
        double bikeLightPriceInCheckout = Double.parseDouble(bikeLightPriceAsString.substring(1,bikeLightPriceAsString.length()));
        double taxInCheckout = Double.parseDouble(taxAsString.substring(6,taxAsString.length()));
        double orderTotalFromApp = Double.parseDouble(orderTotalFromAppAsString.substring(8,orderTotalFromAppAsString.length()));
        double itemTotal = backPackPriceInCheckout + bikeLightPriceInCheckout;
        double orderTotal = itemTotal + taxInCheckout;

        //To validate item price in checkout page is same as product page
        if(checkoutOverViewPage.labelBackPackPriceInCheckOut.isDisplayed()){
            if(backPackPrice.equals(backPackPriceAsString)){
                Assert.assertTrue("Back Pack item price in checkout matches with the price in Product page", true);
            }
        }
        if(checkoutOverViewPage.labelBikeLightPriceInCheckOut.isDisplayed()){
            if(backPackPrice.equals(bikeLightPriceAsString)){
                Assert.assertTrue("Bike Light item price in checkout matches with the price in Product page", true);
            }
        }

        //To validate item total is matching with the product price in checkout (backpack price + bike light price)
        if(itemTotal == itemTotalFromApp){
                Assert.assertTrue("Item total '" + itemTotal + "' is matching with item total from app '" + itemTotalFromApp + "'", true);
        }

        //To validate order total is matching with the item total + tax
        if(orderTotal == orderTotalFromApp){
            Assert.assertTrue("Order total '" + orderTotal + "' is matching with item total + tax", true);
        }

    }

    @When("User clicks on the finish button")
    public void user_clicks_on_the_finish_button() {
        checkoutOverViewPage.clickFinish();
    }

    @Then("User should see order confirmation text {string}")
    public void user_should_see_order_confirmation_text(String OrderConfirmationText) {
        if(orderConfirmationPage.labelOrderConfirmation.isDisplayed()){
            if(OrderConfirmationText.equals(orderConfirmationPage.labelOrderConfirmation.getText().trim())){
                Assert.assertTrue("Back Pack item price in checkout matches with the price in Product page", true);
            }
        }
        driver.quit();
    }
}