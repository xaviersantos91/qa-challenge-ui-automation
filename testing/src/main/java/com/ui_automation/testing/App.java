package com.ui_automation.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class App extends DriverManager{
   
    @Before
    public void setUp() {
    	DriverManager webtest = new DriverManager();
		webtest.StartDriver("Chrome");
    }

    @Test
    public void UIAutomation() {
       	driver.get("https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend");
		GenericActions.CloseCookies(driver);
		MainPage.isPriceDropDownVisible(driver);
		MainPage.PickRandomPrice(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}