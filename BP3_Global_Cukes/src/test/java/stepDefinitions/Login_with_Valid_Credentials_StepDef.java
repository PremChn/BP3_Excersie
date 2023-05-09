package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Login_with_Valid_Credentials_StepDef extends BaseClass{
    @Given("User launches chrome browser")
    public void user_launch_chrome_browser() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String saucedemoURL) {
        driver.get(saucedemoURL);
        driver.manage().window().maximize();
    }

    @And("User enters username as {string} and password as {string}")
    public void user_enters_credentials(String uName, String pass) {
        loginPage.setUserName(uName);
        loginPage.setPassword(pass);
    }

    @And("Click on login button")
    public void user_clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("Page should contain text {string}")
    public void page_should_contain_text(String stringFromHomePage) {
        if(driver.getPageSource().contains("Username and password do not match")){
            Assert.assertTrue(false);
        }else{
            String pageContainsText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(pageContainsText, stringFromHomePage);
        }
    }

    @And("User closes the application")
    public void user_closes_the_application() {
        driver.close();
    }


}
