package StepDefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.junit.internal.runners.statements.Fail;
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
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    private static String value_selected;
    public static String utm_source;
    public static String current_price_saved;
    public static String product_name_saved;
    public static String dropdown_text_default, dropdown_text_selected;
       
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
		
		String url = driver.getCurrentUrl();
        String[] parts = url.split("\\?");

        for (String part : parts) {
            if(part.contains("utm_source")) {
            	utm_source = part;
                break;
            }
        }
        
		dropdown = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_dropdown");	
		
		try {
			wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected"))));
			Report.WriteToFile(Report.filename, "A dropdown está visivel", true);
		}catch (Exception e) {
			Report.WriteToFile(Report.filename, "A dropdown não está visivel", true);
		}
		
		dropdown_text_default = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected")).getText();
								
	    if(dropdown.isDisplayed()) {
	    	Report.WriteToFile(filename, "Drop-down list of price ranges must be visible - The drop-down is visible", true);
	    	assertTrue("Drop-down list of price ranges must be visible - The drop-down is visible", true);
	    }
	    	
	    else {
	    	Report.WriteToFile(filename, "Drop-down list of price ranges must be visible - The drop-down is NOT visible", true);
	    	fail("Drop-down list of price ranges must be visible - The drop-down is NOT visible");
	    }
	    	
	}
	
	@And("I capture the utm_source")
	public static void CaptureUtmSource() {
		
		String url = driver.getCurrentUrl();
        String[] parts = url.split("\\?");

        for (String part : parts) {
            if(part.contains("utm_source")) {
            	utm_source = part;
            	Report.WriteToFile(filename, "Foi capturado o utm_source " + utm_source, true);
    	    	assertTrue("Foi capturado o utm_source " + utm_source, true);
                break;
            }
        }
        
        if(utm_source == ""){
        	Report.WriteToFile(filename, "Não foi capturado o utm_source", false);
	    	fail("Não foi capturado o utm_source");
        }
        
	}
	
	
	@When("The price picker shows I choose a random value from it")
	public static void PickRandomPrice() {
		
			CommonActions.ClickElement(dropdown, "O dropdown de preços foi clicado", "Erro ao clicar no dropdown de preços");
			Random rand = new Random();
			int randomNumber = 0;
			try
			{
				wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list"))));
			    
			    dropdown_options = dropdown.getShadowRoot().findElements(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list > li"));
			    wait.until(ExpectedConditions.visibilityOfAllElements(dropdown_options));
			    Report.WriteToFile(filename, "As opções de preço do dropdown estão visiveis", true);
			    randomNumber = rand.nextInt((dropdown_options.size()));
			    wait.until(ExpectedConditions.visibilityOf(dropdown_options.get(randomNumber)));
			    value_selected = dropdown_options.get(randomNumber).getText();
			    Report.WriteToFile(filename, "A opção de preço pretendida está visiveis -> " + value_selected, true);
			  
			} catch (Exception e)
			{
				Report.WriteToFile(filename, "Erro ao escolher o preço da lista de opções do dropdown", false);
			}
		 	
		    dropdown_options.get(randomNumber).click();
		    Report.WriteToFile(filename, "Foi escolhida a opção " + value_selected, true);
		    	    
	}
	
	@When("The price picker shows I choose a {string} from it")
	public static void PickPriceProvided(String number) {
		
		float number_converted = Float.valueOf(number.replace(",", ".").toString());
		CommonActions.ClickElement(dropdown, "Clicamos no dropdown de preço", "Falhou o click no dropdown de preço");		
		
		try
		{
			wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list"))));
		    dropdown_options = dropdown.getShadowRoot().findElements(By.cssSelector("fieldset > div.form-group > div > div.form-list-wrapper > ul#list > li"));
		    wait.until(ExpectedConditions.visibilityOfAllElements(dropdown_options));
		    wait.until(ExpectedConditions.visibilityOf(dropdown_options.get(0)));
		    Report.WriteToFile(filename, "As opções estão visiveis", true);
		} catch (Exception e)
		{
			Report.WriteToFile(filename, "As opções não estão visiveis ", false);
		}
	 	
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
	    
	    value_selected = dropdown_options.get(i).getText();
	    CommonActions.ClickElement(dropdown_options.get(i), "Foi escolhida a opção " + value_selected, "Houve um problema ao selecionar a opção " + value_selected);		
	}
	
	@Then("I close the browser and driver")
	public static void CloseBrowserAndDriver() {
		driver_manager.CloseDriverAndBrowser();
	}
	
	@And("I validate the choice selected is visible in the dropdown")
	public static void ValidateValuePickedIsDisplayedCorrectly() {
		try
		{
			wait.until(ExpectedConditions.visibilityOf(dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected"))));
			WebElement dropdown_text = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected"));
			wait.until(ExpectedConditions.textToBePresentInElement(dropdown_text, value_selected));
			dropdown_text_selected = dropdown.getShadowRoot().findElement(By.cssSelector("fieldset > div.form-group > span#selected")).getText();
		} catch (Exception e)
		{
			Report.WriteToFile(filename, "Erro ao escolher opção de preço", false);
		}
	
		if(value_selected.equals(dropdown_text_selected)) {
			Report.WriteToFile(filename, "O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text_selected, true);
			assertTrue("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text_selected, true);
		}
			
		else {
			Report.WriteToFile(filename, "O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text_selected, false);
			fail("O valor escolhido -> " + value_selected + " deve ser igual ao valor presente -> " + dropdown_text_selected);
		}

			
	}
	
	@Then("I select the product")
	public static void SelectProduct() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_select_product");	
		try
		{
			wait.until(ExpectedConditions.visibilityOf(shadow_root));
			wait.until(ExpectedConditions.elementToBeClickable(shadow_root));
			Report.WriteToFile(filename, "O botão Select está clicável depois de se ter feito a escolha do preço no dropdown", true);
		} catch (Exception e)
		{
			Report.WriteToFile(filename, "Erro ao validar que o botão Select ficou clicável depois de escolhido o preço no dropdown", false);
			fail("Erro ao validar que o botão Select ficou clicável depois de escolhido o preço no dropdown");
		}
	
		CommonActions.ClickElement(shadow_root, "O botão de selecionar produto foi clicado", "O botão de selecionar produto não foi clicado");		
	}
	
	@And("I capture the values in this page that will be used in future validations")
	public static void CaptureValuesToValidateInFuture() {
				
	    WebElement shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_future_validations");
	    WebElement root = shadow_root.getShadowRoot().findElement(By.cssSelector("div > div.price-container > h3.edi-card-vertical__price > edi-counter"));
		wait.until(ExpectedConditions.visibilityOf(root)).getShadowRoot();		
		WebElement price_element = root.getShadowRoot().findElement(By.cssSelector("span#counter"));
		
		try {
			if(wait.until(ExpectedConditions.attributeContains(price_element, "class", "animating"))==true){
				wait.until(ExpectedConditions.attributeToBe(price_element, "class", "edi-counter__counter ascend"));
				Report.WriteToFile(Report.filename, "Foi esperado que o valor monetário da opção selecionada pare a animação que o altera até chegar ao valor final", true);
			}
		}catch (Exception e) {
			Report.WriteToFile(Report.filename, "Erro ocorreu ao esperar que o valor pare de mudar " + e.getMessage(), false);
		}; 
		        
        shadow_root = CommonActions.RetrieveLastRootToAccessCardDetails("main_page_future_validations");
        
        //product name
		WebElement product_name_element = shadow_root.getShadowRoot().findElement(By.cssSelector("div > header > div > p.card-title"));
		product_name_saved = product_name_element.getText();
		
		//current price
		root = shadow_root.getShadowRoot().findElement(By.cssSelector("div > div.price-container > h3.edi-card-vertical__price > edi-counter"));
		wait.until(ExpectedConditions.visibilityOf(root)).getShadowRoot();		
				
		WebElement current_price_element_1 = root.getShadowRoot().findElement(By.cssSelector("span#counter > span.counter-val"));
		WebElement current_price_element_2 = root.findElement(By.cssSelector("span[slot='post-counter']"));
		current_price_saved = current_price_element_1.getText()+current_price_element_2.getText();
		
	}
}
