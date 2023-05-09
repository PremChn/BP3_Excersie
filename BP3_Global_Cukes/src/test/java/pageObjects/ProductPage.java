package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    public WebDriver ldriver;

    public ProductPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    @CacheLookup
    public static WebElement btnAddToCartProductBackPack;

    @FindBy(id = "remove-sauce-labs-backpack")
    @CacheLookup
    public static WebElement btnRemoveProductBackPack;

    @FindBy(xpath = "//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement txtProductBackPackPrice;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    @CacheLookup
    public static WebElement btnAddToCartProductBikeLight;

    @FindBy(id = "remove-sauce-labs-bike-light")
    @CacheLookup
    public static WebElement btnRemoveProductBikeLight;

    @FindBy(xpath = "//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div")
    @CacheLookup
    public static WebElement txtProductBikeLightPrice;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    @CacheLookup
    public static WebElement linkBag;

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
    public void addProdBackPack(){
        btnAddToCartProductBackPack.click();
    }

    public void addProdBikeLight(){
        btnAddToCartProductBikeLight.click();
    }

    public void navigateToBag() { linkBag.click();}


}
