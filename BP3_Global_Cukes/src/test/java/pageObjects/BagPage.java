package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BagPage {
    public WebDriver ldriver;

    public BagPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//*[@id=\"item_4_title_link\"]/div")
    @CacheLookup
    public static WebElement txtAddedBackPackInBag;

    @FindBy(xpath = "//*[@id=\"item_0_title_link\"]/div")
    @CacheLookup
    public static WebElement txtAddedBikeLightInBag;

    @FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement txtBackPackPriceInBag;

    @FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement txtBikeLightPriceInBag;

    @FindBy(id = "checkout")
    @CacheLookup
    public static WebElement btnBagCheckout;

    public void clickCheckout(){
        btnBagCheckout.click();
    }
}
