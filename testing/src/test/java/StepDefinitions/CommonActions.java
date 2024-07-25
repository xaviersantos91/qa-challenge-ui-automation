package StepDefinitions;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions extends DriverManager {
    
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // Constructor
    public CommonActions(WebDriver driver) {
        this.driver = driver;
    }

    public static void ClickElement(WebDriver driver, By locator) 
	{
		driver.findElement(locator).click();
	}
	
	public static void WaitForElementVisible(WebDriver driver, By locator) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
            );	 
	}
	
	public static void WaitForElementToDisappear(WebDriver driver, By locator) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
            );	
	}

	public static void ScrollToElement(WebDriver driver, By locator) 
	{
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
	}
	
	public static WebElement RetrieveLastRootToAccessCardDetails(String metric)
	{
		WebElement root = null;
		WebElement root1, root2, root3, root4, root5, root6 = null;
		
		switch (metric)
		{
		case "card_details":
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("edi-checkout"))));
	        
			root1 = driver.findElement(By.cssSelector("edi-checkout"));
			
			wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
	        
			root2 = root1.getShadowRoot().findElement(By.cssSelector("div.frank-container > neon-animated-pages"));
			
			wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
			
			root3 = root2.findElement(By.cssSelector("neon-animatable > edi-product-summary"));
			
			wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
	        
			root4 = root3.getShadowRoot().findElement(By.cssSelector("#productSummary > .product-summary-box > edi-loading"));
			
			wait.until(ExpectedConditions.visibilityOf(root4));
			
			root = root4;
			break;
		case "main_page_future_validations":
		 	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"))));
	        
			root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"));
			
			wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
	        
			root2 = root1.findElement(By.tagName("edi-card-vertical"));
			
			wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
	        
			root3 = root2.getShadowRoot().findElement(By.cssSelector("div.edi-card-vertical-url > edi-card#card"));
			
			wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
	        
			root4 = root3.findElement(By.tagName("edi-card-vertical-content")); 		
			
			wait.until(ExpectedConditions.visibilityOf(root4)).getShadowRoot();
			
			root = root4;
			break;
		case "main_page_select_product":
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"))));
	        
			root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"));
			
			wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
	        
			root2 = root1.findElement(By.tagName("edi-card-vertical"));
			
			wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
	        
			root3 = root2.getShadowRoot().findElement(By.cssSelector("div.edi-card-vertical-url > edi-card#card"));
			
			wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
	        
			root4 = root3.findElement(By.tagName("edi-card-vertical-content")); 
			
			wait.until(ExpectedConditions.visibilityOf(root4)).getShadowRoot();
	        
			root5 = root4.getShadowRoot().findElement(By.cssSelector("div > edi-cta"));
			
			wait.until(ExpectedConditions.visibilityOf(root5)).getShadowRoot();
	        
			root6 = root5.getShadowRoot().findElement(By.cssSelector("div > a"));
			
			root = root6;
			break;
		case "main_page_dropdown":
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"))));
	        
			root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form"));
	                
			wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
	                
			root2 = root1.getShadowRoot().findElement(By.cssSelector("#DeviceProtectionForm > edi-device-detection"));
			
			wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
			
			root3 = root2.getShadowRoot().findElement(By.cssSelector("style + .container > div > div.container__price > edi-dropdown"));
	        								
			wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
			
			root = root3;
			break;
		default:
			break;
		}
		
		return root;
	}
}
