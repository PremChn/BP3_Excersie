package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

    public WebDriver ldriver;
    public OrderConfirmationPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    @CacheLookup
    public static WebElement labelCheckoutComplete;

    @FindBy(xpath = "//*[@id=\"checkout_complete_container\"]/h2")
    @CacheLookup
    public static WebElement labelOrderConfirmation;
}
