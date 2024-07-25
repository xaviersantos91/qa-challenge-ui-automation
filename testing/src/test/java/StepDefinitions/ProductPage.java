package StepDefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductPage extends DriverManager
{
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    
    public ProductPage()
	{
    	
	}
    
	public ProductPage(WebDriver driver)
	{
		ProductPage.driver = driver;
	}
	
	@And("I wait for the page to load completly")
	public static void WaitForPageToLoadCompletly() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("edi-checkout"))));
        
		WebElement root1 = driver.findElement(By.cssSelector("edi-checkout"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
        
		WebElement root2 = root1.getShadowRoot().findElement(By.cssSelector("div.frank-container > neon-animated-pages"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
        
		WebElement root3 = root2.findElement(By.cssSelector("neon-animatable > edi-product-summary"));
		
		wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
        
		WebElement root4 = root3.getShadowRoot().findElement(By.cssSelector("#productSummary > .product-summary-box > edi-loading"));
		
		wait.until(ExpectedConditions.visibilityOf(root4));
        
		WebElement root5 = root4.findElement(By.cssSelector("div > div.price > div.final-price-wrapper > p"));
		
		try {
			wait.until(ExpectedConditions.visibilityOf(root5));
			assertTrue("A página deve estar totalmente carregada - A página está totalmente carregada", true);
		}catch (Exception e) {
			fail("A página deve estar totalmente carregada - A página NÃO está totalmente carregada");
		}		
		
			
	}
	
}
