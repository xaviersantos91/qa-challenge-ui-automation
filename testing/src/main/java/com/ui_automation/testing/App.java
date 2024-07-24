package com.ui_automation.testing;

public class App extends WebTest
{
	public static void main(String[] args)
	{

		WebTest webtest = new WebTest();
		webtest.StartDriver("Edge");
		driver.get("https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend");
		
		driver.quit();
	}
}
