package stepDefinitions;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static ProductPage productPage;

    public static BagPage bagPage;

    public static CheckOutInfoPage checkOutInfoPage;

    public static CheckoutOverViewPage checkoutOverViewPage;

    public static OrderConfirmationPage orderConfirmationPage;

   public Properties configProp;

}
