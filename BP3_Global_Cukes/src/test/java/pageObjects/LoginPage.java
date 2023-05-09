package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver ldriver;

    public LoginPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(id = "user-name")
    @CacheLookup
    WebElement txtBoxUserName;

    @FindBy(id = "password")
    @CacheLookup
    WebElement txtBoxPassword;

    @FindBy(id = "login-button")
    @CacheLookup
    WebElement btnLogin;

    public void setUserName(String userName){
        txtBoxUserName.clear();
        txtBoxUserName.sendKeys(userName);
    }

    public void setPassword(String Password){
        txtBoxPassword.clear();
        txtBoxPassword.sendKeys(Password);
    }

    public void clickLogin(){

        btnLogin.click();
    }


}
