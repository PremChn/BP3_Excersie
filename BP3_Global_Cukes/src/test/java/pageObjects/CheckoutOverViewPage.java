package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverViewPage {
    public WebDriver ldriver;

    public CheckoutOverViewPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    @CacheLookup
    public static WebElement labelCheckOutOverviewInfo;
    @FindBy(xpath = "//*[@id=\"item_4_title_link\"]/div")
    @CacheLookup
    public static WebElement labelBackPackInCheckOut;

    @FindBy(xpath = "//*[@id=\"item_0_title_link\"]/div")
    @CacheLookup
    public static WebElement labelBikeLightInCheckOut;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement labelBackPackPriceInCheckOut;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement labelBikeLightPriceInCheckOut;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")
    @CacheLookup
    public static WebElement labelItemTotal;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")
    @CacheLookup
    public static WebElement labelItemTax;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")
    @CacheLookup
    public static WebElement labelOrderTotal;


    @FindBy(id = "finish")
    @CacheLookup
    public static WebElement btnFinish;

    public void clickFinish(){
        btnFinish.click();
    }



}
