package com.ui_automation.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;


public class MainPage
{
	
    // Locators
    private static By devide_price = By.xpath("//edi-device-protection-form");
    private static By devide_price_form = By.xpath("//form[@id='DeviceProtectionForm']");
    public static List<WebElement> dropdown_options ;
    public static WebElement dropdown;
    public static WebDriverWait wait;
    
	public MainPage(WebDriver driver)
	{
		
	}
	
	public static void isPriceDropDownVisible(WebDriver driver) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"))));
        
		WebElement root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"));
                
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
                
		WebElement root2 = root1.getShadowRoot().findElement(By.cssSelector("#DeviceProtectionForm > edi-device-detection"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
		
		dropdown = root2.getShadowRoot().findElement(By.cssSelector("style + .container > div > div.container__price > edi-dropdown"));
        				//.findElement(By.cssSelector(".container > input#planTypeInput"))
        				
		wait.until(ExpectedConditions.visibilityOf(dropdown)).getShadowRoot();
		/*
		WebElement root4 = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset"));
				
		wait.until(ExpectedConditions.visibilityOf(root4));
			    
	    root4.click();
	    */
	    if(dropdown.isDisplayed())
	    	assertTrue("Drop-down list of price ranges must be visible - The drop-down is visible", true);
	    else
	    	fail("Drop-down list of price ranges must be visible - The drop-down is NOT visible");
	    	//assertTrue("Drop-down list of price ranges must be visible - The drop-down is NOT visible", false); 
    	
	}
	
	// depends on the previous execution of method isPriceDropDownVisible
	public static void PickRandomPrice(WebDriver driver) {
		
			dropdown.click();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 	wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list"))));
		    
		    dropdown_options = dropdown.getShadowRoot().findElements(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list > li"));
		    wait.until(ExpectedConditions.visibilityOfAllElements(dropdown_options));
		    
		    Random rand = new Random();
		    int randomNumber = rand.nextInt((dropdown_options.size()));
		    System.out.println(randomNumber);
		    wait.until(ExpectedConditions.visibilityOf(dropdown_options.get(randomNumber)));
		    dropdown_options.get(randomNumber).click();
		    
		    try
			{
				Thread.sleep(Duration.ofSeconds(5));
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
