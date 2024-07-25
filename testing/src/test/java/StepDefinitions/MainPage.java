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
		
		dropdown = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_dropdown");		
						
	    if(dropdown.isDisplayed())
	    	assertTrue("Drop-down list of price ranges must be visible - The drop-down is visible", true);
	    else
	    	fail("Drop-down list of price ranges must be visible - The drop-down is NOT visible");
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
		    
		    WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_future_validations");
		    WebElement root5 = shadow_root.getShadowRoot().findElement(By.cssSelector("div > edi-cta[disabled='false']"));
		    
		    dropdown_options.get(randomNumber).click();
		    
		    String loading = root5.getAttribute("disabled");
		    if (loading.equals("true"))
		    	wait.until(ExpectedConditions.visibilityOf(root5));
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
	    WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_future_validations");
	    WebElement root5 = shadow_root.getShadowRoot().findElement(By.cssSelector("div > edi-cta[disabled='false']"));
	    dropdown_options.get(i).click();
	    
	    String loading = root5.getAttribute("disabled");
	    if (loading.equals("true"))
	    	wait.until(ExpectedConditions.visibilityOf(root5));
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
		
		WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_select_product");	
		shadow_root.click();
		
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
        
        WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_future_validations");
        
        //product name
		WebElement product_name_element = shadow_root.getShadowRoot().findElement(By.cssSelector("div > header > div > p.card-title"));
		product_name_saved = product_name_element.getText();
		
		//current price
		WebElement root5 = shadow_root.getShadowRoot().findElement(By.cssSelector("div > div.price-container > h3.edi-card-vertical__price > edi-counter"));
		wait.until(ExpectedConditions.visibilityOf(root5)).getShadowRoot();	
				
		WebElement current_price_element_1 = root5.getShadowRoot().findElement(By.cssSelector("span#counter > span.counter-val"));
		WebElement current_price_element_2 = root5.findElement(By.cssSelector("span[slot='post-counter']"));
		current_price_saved = current_price_element_1.getText()+current_price_element_2.getText();
		        
	}
}
