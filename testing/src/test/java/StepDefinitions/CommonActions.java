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
		//WebElement element = driver.findElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
            );
		/*try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(
	                ExpectedConditions.invisibilityOfElementLocated(locator)
	            );
		}catch (Exception e) {
			assertTrue("Element should not be visible anymore", !element.isDisplayed());
		}*/		 
	}

	public static void ScrollToElement(WebDriver driver, By locator) 
	{
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
	}
}
