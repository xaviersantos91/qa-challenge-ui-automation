package com.ui_automation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebTest
{
	public static WebDriver driver;

	public void StartDriver(String browser)
	{
		switch (browser)
		{
		case "Edge":
			String browser_version = new BrowserVersion().getBrowserVersion();
			System.out.println(browser_version);

			EdgeOptions edge_options = new EdgeOptions();

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserVersion", browser_version);
			edge_options.merge(capabilities);

			// Start Edge maximized
			edge_options.addArguments("start-maximized");

			// Start Edge in private mode
			edge_options.addArguments("inprivate");

			driver = new EdgeDriver(edge_options);
			break;
		
		default:
			break;
		}
		
	}

}
