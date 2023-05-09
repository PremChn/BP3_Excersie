package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInfoPage {
    public WebDriver ldriver;

    public CheckOutInfoPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    @CacheLookup
    public static WebElement labelCheckOutInfo;

    @FindBy(id = "first-name")
    @CacheLookup
    public static WebElement txtFirstName;

    @FindBy(id = "last-name")
    @CacheLookup
    public static WebElement txtLastName;

    @FindBy(id = "postal-code")
    @CacheLookup
    public static WebElement txtZipCode;

    @FindBy(id = "continue")
    @CacheLookup
    public static WebElement btnCheckOutInfoContinue;

    public void setCustomerInfo(String FirstName, String LastName, String ZipCode){
        txtFirstName.clear();
        txtFirstName.sendKeys(FirstName);

        txtLastName.clear();
        txtLastName.sendKeys(LastName);

        txtZipCode.clear();
        txtZipCode.sendKeys(ZipCode);
    }

    public void checkOutContinue(){
        btnCheckOutInfoContinue.click();
    }
}
