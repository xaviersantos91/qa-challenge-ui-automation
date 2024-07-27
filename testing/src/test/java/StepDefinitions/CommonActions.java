package StepDefinitions;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class CommonActions extends DriverManager {
    
	public static WebElement root = null;
	public static WebElement root1, root2, root3, root4, root5, root6 = null;
	
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
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
	
	public static void ValidateImei(String imei) {
		//validar que tem 15 numeros
	    if (imei.length() != 15)
	        fail("imei muito pequeno, logo falso");
	    else
	    	assertTrue("imei tem 15 digitos conforme deve ser", true);

	    //validar que não tem letras  
        boolean isNumeric = imei.matches("\\d+");
        if(isNumeric)
        	assertTrue("imei tem apenas numeros conforme deve ser", true);
        else
        	fail("imei não pode conter letras, logo falso");
        
	    //guardar o ultimo numero
	    int last_number = imei.charAt(14) - 48;

	    //duplicar cada segundo numero
	    //sumar todos os numeros
	    int curr;
	    int sum = 0;
	    for (int i = 0; i < 14; i++) {
	        curr = imei.charAt(i) - 48;
	        if (i % 2 != 0){
	            curr = 2 * curr;
	            if(curr > 9) {
	                curr = (curr / 10) + (curr - 10);
	            }
	            sum += curr;
	        }
	        else {
	            sum += curr;
	        }

	    }

	    //arredondar para multimo de 10 mais proximo
	    int round = sum % 10 == 0 ? sum : ((sum / 10 + 1) * 10);
	    
	    if(round - sum == last_number)
	    	assertTrue("O numero arredondado menos a soma deve ser igual ao ultimo digito do imei -> " + round + " - " + sum + " = " + last_number, true);
	    else
	    	fail("O numero arredondado menos a soma deve ser igual ao ultimo digito do imei -> " + round + " - " + sum + " = " + last_number);
		
	}
	
	public static WebElement ShadowRootToTheForm() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("edi-checkout"))));
        
		root1 = driver.findElement(By.cssSelector("edi-checkout"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
        
		root2 = root1.getShadowRoot().findElement(By.cssSelector("div.frank-container > neon-animated-pages"));
		
		wait.until(ExpectedConditions.visibilityOf(root2)).getShadowRoot();
		
		root3 = root2.findElement(By.cssSelector("neon-animatable > edi-user-details"));
		
		wait.until(ExpectedConditions.visibilityOf(root3)).getShadowRoot();
		
		root4 = root3.getShadowRoot().findElement(By.cssSelector("div > form#driverDetailsForm > #contact-details > edi-device-details"));
		
		wait.until(ExpectedConditions.visibilityOf(root4)).getShadowRoot();
		
		return root4;
	}
	
	public static void FillInImei(String imei) {
		
		WebElement shadow_root_form = ShadowRootToTheForm();
		
		WebElement shadow_host = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > div:nth-child(7)"));
	
		WebElement shadow_host_2 = shadow_host.findElement(By.cssSelector("div > #imei"));

		WebElement input_imei = shadow_host_2.getShadowRoot().findElement(By.cssSelector("fieldset > input#imei"));
						 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input_imei);

        input_imei.sendKeys(String.valueOf(imei));
		
	}
	
	public static void FillInDeviceNameAndModel(String device_name, String device_model) {
		
		WebElement shadow_root_form = ShadowRootToTheForm();
		
		//device brand input	
		root1 = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > div:nth-child(2) > edi-input"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
				
		WebElement input_device_brand = root1.getShadowRoot().findElement(By.cssSelector("fieldset > input#device\\[make\\]"));
		
		wait.until(ExpectedConditions.visibilityOf(input_device_brand));
		 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input_device_brand);

        input_device_brand.sendKeys(String.valueOf(device_name));
        
      //device model input
        root1 = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > div:nth-child(4) > edi-input"));
		
		wait.until(ExpectedConditions.visibilityOf(root1)).getShadowRoot();
		
        WebElement input_device_model = root1.getShadowRoot().findElement(By.cssSelector("fieldset > input#device\\[model\\]"));
		
		wait.until(ExpectedConditions.visibilityOf(input_device_model));
		 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input_device_model);

        input_device_model.sendKeys(String.valueOf(device_model));        
		
	}
	
public static void FillInAnswersToTheQuestionaire(String first_answer, String second_answer, String third_answer) {
		
		WebElement shadow_root_form = ShadowRootToTheForm();
		
		//first question - answer
		WebElement shadow_host_first_question = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > edi-radio-button#noDamage"));
		
		wait.until(ExpectedConditions.visibilityOf(shadow_host_first_question)).getShadowRoot();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shadow_host_first_question);
		
		WebElement first_question_yes = shadow_host_first_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > button#button-Y"));
		WebElement first_question_no = shadow_host_first_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > button#button-N"));
				 
        switch (first_answer)
		{
		case "Yes":
			first_question_yes.click();
			System.out.println("1 - Yes");
			break;
		case "No":
			first_question_no.click();
			System.out.println("1 - No");
			break;
		default:
			first_question_yes.click();
			break;
		}
        
        //second question - answer
  		WebElement shadow_host_second_question = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > edi-radio-button#lessThan14Days"));
  		
  		wait.until(ExpectedConditions.visibilityOf(shadow_host_second_question)).getShadowRoot();
  		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shadow_host_second_question);
  		
  		WebElement second_question_yes = shadow_host_second_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > #button-Y"));
  		WebElement second_question_no = shadow_host_second_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > #button-N"));
  				 
        
        switch (second_answer)
		{
		case "Yes":
			second_question_yes.click();
			System.out.println("2 - Yes");
			break;
		case "No":
			second_question_no.click();
			System.out.println("2 - No");
			break;
		default:
			second_question_yes.click();
			break;
		}
        
        if(third_answer != "null" && second_answer.equals("No")) {
        	//second question - answer
      		WebElement shadow_host_third_question = shadow_root_form.getShadowRoot().findElement(By.cssSelector("fieldset > div > div > edi-radio-button#lessThan18Months"));
      		
      		wait.until(ExpectedConditions.visibilityOf(shadow_host_third_question)).getShadowRoot();
      		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shadow_host_third_question);
      		
      		WebElement third_question_yes = shadow_host_third_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > #button-Y"));
      		WebElement third_question_no = shadow_host_third_question.getShadowRoot().findElement(By.cssSelector("fieldset > div > #button-N"));
            
            switch (third_answer)
    		{
    		case "Yes":
    			third_question_yes.click();
    			System.out.println("3 - Yes");
    			break;
    		case "No":
    			third_question_no.click();
    			System.out.println("3 - No");
    			break;
    		default:
    			third_question_yes.click();
    			break;
    		}
        }
    
	}
}
