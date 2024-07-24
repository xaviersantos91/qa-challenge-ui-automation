package com.ui_automation.testing;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage
{
	
    // Locators
    private static By devide_price = By.xpath("//edi-device-protection-form");
    private static By devide_price_form = By.xpath("//form[@id='DeviceProtectionForm']");
    
	public MainPage(WebDriver driver)
	{
		
	}
	
	public static void isPriceDropDownVisible(WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"))));
        
		WebElement root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"));
                
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
                
		WebElement root2 = root1.getShadowRoot().findElement(By.cssSelector("#DeviceProtectionForm > edi-device-detection"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
		
		WebElement root3 = root2.getShadowRoot().findElement(By.cssSelector("style + .container > div > div.container__price > edi-dropdown"));
        				//.findElement(By.cssSelector(".container > input#planTypeInput"))
        				
		wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
		
		WebElement root4 = root3.getShadowRoot().findElement(By.cssSelector("fieldset"));
		//.findElement(By.cssSelector(".container > input#planTypeInput"))
		
		wait.until(ExpectedConditions.visibilityOf(root4));
		
	    System.out.println(root4.getAttribute("class"));
	    
	    root4.click();
	   	    
	}
	
}
