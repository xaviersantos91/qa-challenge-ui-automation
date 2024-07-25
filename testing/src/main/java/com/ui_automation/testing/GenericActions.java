package com.ui_automation.testing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenericActions {
    private WebDriver driver;

    // Locators
    private static By btn_cookie_close = By.xpath("//div[contains(@id,'close')]");
    private static By btn_cookie_settings = By.xpath("//button[text()='Cookies Settings']");
    private static By btn_cookie_popup = By.xpath("//div[@id='onetrust-banner-sdk']");
    private static By btn_cookie_popup_animation = By.xpath("//div[@id='onetrust-banner-sdk' and contains(@style,'animation')]");
  
    // Constructor
    public GenericActions(WebDriver driver) {
        this.driver = driver;
    }

    public static void CloseCookies(WebDriver driver)
	{
    	CommonActions.WaitForElementVisible(driver, btn_cookie_close);
    	CommonActions.WaitForElementToDisappear(driver, btn_cookie_popup_animation);
		CommonActions.WaitForElementVisible(driver, btn_cookie_close);
		CommonActions.ClickElement(driver, btn_cookie_close);
		CommonActions.WaitForElementToDisappear(driver, btn_cookie_popup);
	}
}
