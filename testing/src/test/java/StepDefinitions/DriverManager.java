package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Scenario;

public class DriverManager
{
	public static WebDriver driver;
	public static MainPage actions;
	public static String filename;
	 
	public void StartDriver(String browser_name)
	{
		Report.CreateFile("MainScenario");
		filename = Report.filename;		
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		
		switch (browser_name)
		{
		case "Edge":
			try {
				String browser_version = new BrowserVersion().getBrowserVersion();
				System.out.println(browser_version);

				EdgeOptions edge_options = new EdgeOptions();

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserVersion", browser_version);
				edge_options.merge(capabilities);
				edge_options.addArguments("start-maximized");
				edge_options.addArguments("inprivate");
				edge_options.setExperimentalOption("mobileEmulation", mobileEmulation);
				
				driver = new EdgeDriver(edge_options);
				actions = new MainPage(driver);
				Report.WriteToFile(filename, "A driver foi corretamente iniciada", true);
			}catch (Exception e) {
				fail("A driver não foi corretamente iniciada");
				Report.WriteToFile(filename, "A driver não foi corretamente iniciada", false);
			}
			
			break;
			
		case "Chrome":
			try {
				ChromeOptions chrome_options = new ChromeOptions();
				chrome_options.addArguments("--start-maximized");
				chrome_options.addArguments("--incognito");
				chrome_options.setExperimentalOption("mobileEmulation", mobileEmulation);
		        driver = new ChromeDriver(chrome_options);
		        actions = new MainPage(driver);
		        Report.WriteToFile(filename, "A driver foi corretamente iniciada", true);
			}catch (Exception e) {
				fail("A driver não foi corretamente iniciada");
				Report.WriteToFile(filename, "A driver não foi corretamente iniciada", false);
			}
			break;
		default:
			break;
		}

	}
	
	public void StartBrowser(String url) 
	{
		try {
			driver.get(url);	
			Report.WriteToFile(filename, "A url '" + url + "' foi acedida", true);
		}catch (Exception e) {
			fail("A driver não foi corretamente iniciada");
			Report.WriteToFile(filename, "A url '" + url + "' não foi acedida", false);
		}
	}
	
	public void CloseDriverAndBrowser() {
		driver.quit();
		Report.WriteToFile(filename, "Foi encerrada a driver e o browser", true);
	}

}
