package com.ui_automation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverManager
{
	public static WebDriver driver;
	public static MainPage actions;

	public void StartDriver(String browser)
	{
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		
		switch (browser)
		{
		case "Edge":
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
			break;
			
		case "Chrome":
			ChromeOptions chrome_options = new ChromeOptions();
			chrome_options.addArguments("--start-maximized");
			chrome_options.addArguments("--incognito");
			chrome_options.setExperimentalOption("mobileEmulation", mobileEmulation);
	        driver = new ChromeDriver(chrome_options);
	        actions = new MainPage(driver);
			break;
		default:
			break;
		}

	}

}
