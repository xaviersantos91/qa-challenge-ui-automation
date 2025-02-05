package StepDefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


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
		
		WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("card_details");
		        
		WebElement root5 = shadow_root.findElement(By.cssSelector("div > div.price > div.final-price-wrapper > p"));
		
		try {
			wait.until(ExpectedConditions.visibilityOf(root5));
			assertTrue("A página deve estar totalmente carregada - A página está totalmente carregada", true);
		}catch (Exception e) {
			fail("A página deve estar totalmente carregada - A página NÃO está totalmente carregada");
		}		
					
	}
	
	@And("I validate that the url contains {string} and the same utm_source as the previous page")
	public static void ValidateUrlValues(String value) {
		String utm_source_original = MainPage.utm_source;
		String url = driver.getCurrentUrl();
		String utm_source_new = "";
		String[] parts = url.split("\\?");

        for (String part : parts) {
            if(part.contains("utm_source")) {
            	String[] parts2 = part.split("\\&");
            	for(String part2 : parts2) {
            		if(part2.contains("utm_source")) {
            			utm_source_new = part2;
            			break;
            		}
            	}             
            }
        }
				
		if(utm_source_original.equals(utm_source_new))
			assertTrue("The value of utm_source most be kept between page transition - The original value -> " + utm_source_original + " is equal to the new value -> " + utm_source_new, true);
		else
			fail("The value of utm_source most be kept between page transition - The original value -> " + utm_source_original + " is not the same as the new value -> " + utm_source_new);
		
		if(url.contains(value))
			assertTrue("The url must contains the value " + value + " the url contains the value -> " + url, true);
		else
			fail("The url must contains the value " + value + " the url doesn't contains the value -> " + url);
		
	}
	
	@Then("I validate that the current price and product name are in accordance with the ones shown on the previous page")
	public static void ValidateCurrentPriceAndProductName() {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement shadow_root = null;
		String product_name = "";
		String current_price = "";
		try
		{
			shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("card_details");
			WebElement product = shadow_root.findElement(By.cssSelector("div > p.title"));
			wait.until(ExpectedConditions.textToBePresentInElement(product, MainPage.product_name_saved));
			Report.WriteToFile(Report.filename, "O nome do produto está visivel", true);
			product_name = shadow_root.findElement(By.cssSelector("div > p.title")).getText();
			WebElement price = shadow_root.findElement(By.cssSelector("div > div.price > div.final-price-wrapper > p.final-price"));
			wait.until(ExpectedConditions.textToBePresentInElement(price, MainPage.current_price_saved));
			current_price = shadow_root.findElement(By.cssSelector("div > div.price > div.final-price-wrapper > p.final-price")).getText();
			Report.WriteToFile(Report.filename, "O preço do produto está visivel", true);
		} catch (Exception e)
		{
			Report.WriteToFile(Report.filename, "O nome do produto ou o valor não coincidem com os escolhidos", false);
		}
			
		if(MainPage.current_price_saved.equals(current_price)) {
			assertTrue("The current price in the previous page was " + MainPage.current_price_saved + " and in the Checkout page card is equal -> " + current_price, true);
			Report.WriteToFile(Report.filename, "The current price in the previous page was " + MainPage.current_price_saved + " and in the Checkout page card is equal -> " + current_price, true);
		}
		else {
			Report.WriteToFile(Report.filename, "The current price in the previous page was " + MainPage.current_price_saved + " and in the Checkout page card is different -> " + current_price, false);	
			fail("The current price in the previous page was " + MainPage.current_price_saved + " and in the Checkout page card is different -> " + current_price);
		}
			
		if(MainPage.product_name_saved.equals(product_name)) {
			assertTrue("The product name in the previous page was " + MainPage.product_name_saved + " and in the Checkout page Card Title is equal -> " + product_name, true);
			Report.WriteToFile(Report.filename, "The product name in the previous page was " + MainPage.product_name_saved + " and in the Checkout page Card Title is equal -> " + product_name, true);
		}
		else {
			Report.WriteToFile(Report.filename, "The product name in the previous page was " + MainPage.product_name_saved + " and in the Checkout page Card Title is different -> " + product_name, false);	
			fail("The product name in the previous page was " + MainPage.product_name_saved + " and in the Checkout page Card Title is different -> " + product_name);
		}
		
	}
	
	@And("I validate the {string}, the Contract Start Date and {string}")
	public static void ValidateProviderContractStartDateAndContractRenewal(String provider, String contract_renewal) {
		
		WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("card_details");
		
		//validation of the provider
		String provider_retrieved = shadow_root.findElement(By.cssSelector("#providerName")).getText();
		
		if(provider_retrieved.equals(provider)) {
			assertTrue("The Provider name must always be " + provider + " and in the Checkout page card is equal -> " + provider_retrieved, true);
			Report.WriteToFile(Report.filename, "The Provider name must always be " + provider + " and in the Checkout page card is equal -> " + provider_retrieved, true);
		}else {
			Report.WriteToFile(Report.filename, "The Provider name must always be " + provider + " and in the Checkout page card is equal -> " + provider_retrieved, false);
			fail("The Provider name must always be " + provider + " and in the Checkout page card is equal -> " + provider_retrieved);
		}
		//validation of the contract renewal
		String contract_renewal_retrieved = shadow_root.findElement(By.cssSelector("span#subscriptionRenewal")).getText();
		
		if(contract_renewal_retrieved.equals(contract_renewal)) {
			assertTrue("The Contract Renewal must always be " + contract_renewal + " and in the Checkout page card is equal -> " + contract_renewal_retrieved, true);
			Report.WriteToFile(Report.filename, "The Contract Renewal must always be " + contract_renewal + " and in the Checkout page card is equal -> " + contract_renewal_retrieved, true);
		}else {
			Report.WriteToFile(Report.filename, "The Contract Renewal must always be " + contract_renewal + " and in the Checkout page card is equal -> " + contract_renewal_retrieved, false);
			fail("The Contract Renewal must always be " + contract_renewal + " and in the Checkout page card is equal -> " + contract_renewal_retrieved);
		}
		//validation of the current date
		String current_start_date_retrieved = shadow_root.findElement(By.cssSelector("span#subscriptionStartDate")).getText();
        ZonedDateTime thailandDateTime = ZonedDateTime.now(ZoneId.of("Asia/Bangkok"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH);
        String thailand_current_date = thailandDateTime.format(formatter);
        
        if(current_start_date_retrieved.equals(thailand_current_date)) {
			assertTrue("The Current date must be in Thailand Timezone and so like this -> " + thailand_current_date + " and the Current date in the Checkout page card is -> " + current_start_date_retrieved, true);
			Report.WriteToFile(Report.filename, "The Current date must be in Thailand Timezone and so like this -> " + thailand_current_date + " and the Current date in the Checkout page card is -> " + current_start_date_retrieved, true);
        }else {
        	Report.WriteToFile(Report.filename, "The Current date must be in Thailand Timezone and so like this -> " + thailand_current_date + " and the Current date in the Checkout page card is -> " + current_start_date_retrieved, false);
			fail("The Current date must be in Thailand Timezone and so like this -> " + thailand_current_date + " and the Current date in the Checkout page card is -> " + current_start_date_retrieved);
		}
	}
	@Then ("I insert an {string} and validate if it is valid")
	public static void InsertAndValidateIMEI(String imei) {
		
		CommonActions.ValidateImei(imei);
	    CommonActions.FillInImei(imei);
	    
	}
	
	@And("I insert the {string} and {string}")
	public static void InsertDeviceBrandAndModel(String device_brand, String device_model) {
		
		CommonActions.FillInDeviceNameAndModel(device_brand, device_model);
	   	    
	}
	
	 @And("I answer the questionnaire with the following {string} {string} {string}")
	 public static void AnswerQuestionnaire(String first_answer, String second_answer, String third_answer) {
		 
		 CommonActions.FillInAnswersToTheQuestionaire(first_answer, second_answer ,third_answer);
		 
	 }
}
