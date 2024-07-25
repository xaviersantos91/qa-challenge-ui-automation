package StepDefinitions;

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

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;


public class MainPage extends DriverManager
{
	
    // Locators
    private static By devide_price = By.xpath("//edi-device-protection-form");
    private static By devide_price_form = By.xpath("//form[@id='DeviceProtectionForm']");
    public static List<WebElement> dropdown_options ;
    public static WebElement dropdown;
    public static WebDriverWait wait;
    private static String value_selected;
    
    public MainPage()
	{
    	
	}
    
	public MainPage(WebDriver driver)
	{
		MainPage.driver = driver;
	}
	
	private static DriverManager driver_manager = new DriverManager();
	
	@Given("I start the Driver for {string} browser")
	public void StartDriver(String browser_name) {
		driver_manager.StartDriver(browser_name);
	}
	
	@And("I access the {string}")
	public void StartBrowser(String url) {
		driver_manager.StartBrowser(url);
	}
	
	@Given("I wait for the price picker to show up")
	public static void isPriceDropDownVisible() {
		
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
	
	
	@When("The price picker shows I choose a random value from it")
	public static void PickRandomPrice() {
		
			dropdown.click();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 	wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list"))));
		    
		    dropdown_options = dropdown.getShadowRoot().findElements(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list > li"));
		    wait.until(ExpectedConditions.visibilityOfAllElements(dropdown_options));
		    
		    Random rand = new Random();
		    int randomNumber = rand.nextInt((dropdown_options.size()));
		    System.out.println(randomNumber);
		    wait.until(ExpectedConditions.visibilityOf(dropdown_options.get(randomNumber)));
		    
		    value_selected = dropdown_options.get(randomNumber).getText();
		    
		    dropdown_options.get(randomNumber).click();
		    
		    try
			{
				Thread.sleep(Duration.ofSeconds(2));
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@When("The price picker shows I choose a {string} from it")
	public static void PickPriceProvided(String number) {
		
		float number_converted = Float.valueOf(number.replace(",", ".").toString());
				
		dropdown.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 	wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list"))));
	    
	    dropdown_options = dropdown.getShadowRoot().findElements(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list > li"));
	    wait.until(ExpectedConditions.visibilityOfAllElements(dropdown_options));
	    wait.until(ExpectedConditions.visibilityOf(dropdown_options.get(0)));
	    
	    int i = 0;
	    for (WebElement webElement : dropdown_options)
		{
	    	value_selected = webElement.getText();
		    float value1 = Float.valueOf(value_selected.replace(",", ".").split(" ")[1]);
		    float value2 = Float.valueOf(value_selected.replace(",", ".").split(" ")[3]);
			
		    if(number_converted >= value1 && number_converted <= value2)
		    	break;
			i++;
		}
	    
	    dropdown_options.get(i).click();
	    	    
	    try
		{
			Thread.sleep(Duration.ofSeconds(2));
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("I close the browser and driver")
	public static void CloseBrowserAndDriver() {
		driver_manager.CloseDriverAndBrowser();
	}
	
	@And("I validate the choice selected is visible in the dropdown")
	public static void ValidateValuePickedIsDisplayedCorrectly() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected"))));
		String dropdown_text = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected")).getText();
		
		if(value_selected.equals(dropdown_text))
			assertTrue("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text, true);
		else
			fail("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text);
				
	}
		
}
