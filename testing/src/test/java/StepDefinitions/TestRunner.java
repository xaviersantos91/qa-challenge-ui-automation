package StepDefinitions;

import org.junit.Before;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features", 
		glue={"StepDefinitions"},
		tags="@MainScenario")
public class TestRunner{
	
	 @Before
	    public void setUp() {
	    	DriverManager webtest = new DriverManager();
			webtest.StartDriver("Chrome");
	    }
	
}