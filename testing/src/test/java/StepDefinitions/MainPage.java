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


public class MainPage extends DriverManager
{
    public static List<WebElement> dropdown_options ;
    public static WebElement dropdown;
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private static String value_selected;
    public static String utm_source;
    public static String current_price_saved;
    public static String product_name_saved;
    
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
		
		wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected"))));
		String dropdown_text = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected")).getText();
		
		if(value_selected.equals(dropdown_text))
			assertTrue("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text, true);
		else
			fail("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text);
				
	}
	
	@Then("I select the product")
	public static void SelectProduct() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"))));
        
		WebElement root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
        
		WebElement root2 = root1.findElement(By.tagName("edi-card-vertical"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
        
		WebElement root3 = root2.getShadowRoot().findElement(By.cssSelector("div.edi-card-vertical-url > edi-card#card"));
		
		wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
        
		WebElement root4 = root3.findElement(By.tagName("edi-card-vertical-content")); 
		
		wait.until(ExpectedConditions.visibilityOf(root4)).getShadowRoot();
        
		WebElement root5 = root4.getShadowRoot().findElement(By.cssSelector("div > edi-cta"));
		
		wait.until(ExpectedConditions.visibilityOf(root5)).getShadowRoot();
        
		WebElement root6 = root5.getShadowRoot().findElement(By.cssSelector("div > a"));
		
		root6.click();
		
	}
	
	@And("I capture the values in this page that will be used in future validations")
	public static void CaptureUtmSource() {
		String url = driver.getCurrentUrl();
        String[] parts = url.split("\\?");

        for (String part : parts) {
            if(part.contains("utm_source")) {
            	utm_source = part;
                break;
            }
        }
        
        //product name
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"))));
        
		WebElement root1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
        
		WebElement root2 = root1.findElement(By.tagName("edi-card-vertical"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
        
		WebElement root3 = root2.getShadowRoot().findElement(By.cssSelector("div.edi-card-vertical-url > edi-card#card"));
		
		wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
        
		WebElement root4 = root3.findElement(By.tagName("edi-card-vertical-content")); 		
		
		wait.until(ExpectedConditions.visibilityOf(root4)).getShadowRoot();
        
		WebElement product_name_element = root4.getShadowRoot().findElement(By.cssSelector("div > header > div > p.card-title"));
		
		product_name_saved = product_name_element.getText();
		
		//current price
		WebElement root5 = root4.getShadowRoot().findElement(By.cssSelector("div > div.price-container > h3.edi-card-vertical__price > edi-counter"));
		
		wait.until(ExpectedConditions.visibilityOf(root5)).getShadowRoot();
		
		WebElement current_price_element_1 = root5.getShadowRoot().findElement(By.cssSelector("span#counter > span.counter-val"));
		WebElement current_price_element_2 = root5.findElement(By.cssSelector("span[slot='post-counter']"));
		
		current_price_saved = current_price_element_1.getText()+current_price_element_2.getText();
		int a = 0;
        
	}
}
