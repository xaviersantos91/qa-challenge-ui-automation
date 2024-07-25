package StepDefinitions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;

public class GenericActions extends DriverManager{

    // Locators
    private static By btn_cookie_close = By.xpath("//div[contains(@id,'close')]");
    private static By btn_cookie_settings = By.xpath("//button[text()='Cookies Settings']");
    private static By btn_cookie_popup = By.xpath("//div[@id='onetrust-banner-sdk']");
    private static By btn_cookie_popup_animation = By.xpath("//div[@id='onetrust-banner-sdk' and contains(@style,'animation')]");
  
    // Constructor
    public GenericActions(WebDriver driver) {
        
    }

    @And("I close the cookies")
    public static void CloseCookies()
	{
    	CommonActions.WaitForElementVisible(driver, btn_cookie_close);
    	CommonActions.WaitForElementToDisappear(driver, btn_cookie_popup_animation);
		CommonActions.WaitForElementVisible(driver, btn_cookie_close);
		CommonActions.ClickElement(driver, btn_cookie_close);
		CommonActions.WaitForElementToDisappear(driver, btn_cookie_popup);
	}
}
