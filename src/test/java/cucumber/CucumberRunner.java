package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



	@CucumberOptions(features="src/test/java/cucumber", glue="MyProject.SeleniumFramework.StepDefination", monochrome=true,
			tags="@errorvalidation" ,plugin= {"html:target/cucumber.html"})
	public class CucumberRunner extends AbstractTestNGCucumberTests{ 
		
	}

