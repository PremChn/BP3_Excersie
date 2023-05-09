package testExecutor;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaceOrderTest{
    public static Properties configProp;
    public static WebDriver driver = null;

    public static Logger logger;
    public static void validateOrderPlacement() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
        driver = new ChromeDriver();

        //To launch "Sauce Demo Application"
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        //To enter login credentials
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //To login to application
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);

        //To validate login is successful
        if(driver.getPageSource().contains("Username and password do not match")){
            Assert.assertTrue("Login unsuccessful",false);
        }else{
            String pageContainsText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(pageContainsText, "Products");
        }

        //To add products to cart
        String backPackPrice_In_ProductPage = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText().trim();
        String bikeLightPrice_In_ProductPage = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div")).getText().trim();

        //To add back pack to cart and validate back pack is added successfully
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        if(driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed()){
            Assert.assertTrue("Product 'back pack' added to bag",true);
        }

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        if(driver.findElement(By.id("remove-sauce-labs-bike-light")).isDisplayed()){
            Assert.assertTrue("Product 'bike light' added to bag",true);
        }
        Thread.sleep(2000);

        //To navigate to bag page
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //To validate product 'back pack' is displayed in bag and price is same as product page
        if(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).isDisplayed()){
            Assert.assertTrue("Product 'back pack' displayed in bag",true);
            if(backPackPrice_In_ProductPage.equals(driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).getText().trim())){
                Assert.assertTrue("Back Pack item price in bag matches with the price displayed Product page", true);
            }
        }

        //To validate product 'bike light' is displayed in bag and price is same as product page
        if(driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).isDisplayed()){
            Assert.assertTrue("Product 'bike light' displayed in bag",true);
            if(bikeLightPrice_In_ProductPage.equals(driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")).getText().trim())){
                Assert.assertTrue("Back Pack item price in bag matches with the price displayed Product page", true);
            }
        }
        Thread.sleep(2000);

        //To navigate to checkout info page
        driver.findElement(By.id("checkout")).click();

        //To add user information
        driver.findElement(By.id("first-name")).sendKeys("Premanand");
        driver.findElement(By.id("last-name")).sendKeys("Panneerselvam");
        driver.findElement(By.id("postal-code")).sendKeys("94538");
        Thread.sleep(2000);

        //To navigate to checkout overview page
        driver.findElement(By.id("continue")).click();

        //To validate product, item total, tax and order total is valid in Checkout Overview page
        String backPackPriceAsString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).getText().trim();
        String bikeLightPriceAsString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")).getText().trim();
        String itemTotalFromAppAsString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")).getText().trim();
        String taxAsString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")).getText().trim();
        String orderTotalFromAppAsString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText().trim();

        double itemTotalFromApp = Double.parseDouble(itemTotalFromAppAsString.substring(13,itemTotalFromAppAsString.length()));
        double backPackPriceInCheckout = Double.parseDouble(backPackPriceAsString.substring(1,backPackPriceAsString.length()));
        double bikeLightPriceInCheckout = Double.parseDouble(bikeLightPriceAsString.substring(1,bikeLightPriceAsString.length()));
        double taxInCheckout = Double.parseDouble(taxAsString.substring(6,taxAsString.length()));
        double orderTotalFromApp = Double.parseDouble(orderTotalFromAppAsString.substring(8,orderTotalFromAppAsString.length()));
        double itemTotal = backPackPriceInCheckout + bikeLightPriceInCheckout;
        double orderTotal = itemTotal + taxInCheckout;

        //To validate back pack item price in checkout page is same as product page
        if(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).isDisplayed()){
            if(backPackPrice_In_ProductPage.equals(backPackPriceAsString)){
                Assert.assertTrue("Back Pack item price in checkout matches with the price in Product page", true);
            }
        }

        //To validate bike light item price in checkout page is same as product page
        if(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")).isDisplayed()){
            if(bikeLightPrice_In_ProductPage.equals(bikeLightPriceAsString)){
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

        //To finish order purchase
        driver.findElement(By.id("finish")).click();

        //To validate order has been placed successfully
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).isDisplayed()){
            String OrderConfirmationText = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText().trim();
            Assert.assertEquals(OrderConfirmationText, "Thank you for your order!");
        }

        //To close browser session
        driver.quit();
    }
}