package MyProject.SeleniumFramework.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MyProject.SeleniumFramework.PageObjects.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Login login;

	public WebDriver initializedriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//MyProject//SeleniumFramework//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		}
		return driver;

	}


	@BeforeMethod(alwaysRun=true)
	public Login launchApplication() throws IOException {

		driver=initializedriver();
		login = new Login(driver);
		login.GoTo();
		return login;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}

	
public List<HashMap<String,String>> getDataJSONtoMap(String filepath) throws IOException {
		
		//read data from json and covert to String
		String JSONContent=    FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//Convert String to HashMap--> jacksonDatabind;
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JSONContent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}

    public String getScreenShot(String testcasename) throws IOException {
    	
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File Source=ts.getScreenshotAs(OutputType.FILE);
    	
    	File file=new File(System.getProperty(("user.dir")+"//report//"+ testcasename + ".png"));
    	FileUtils.copyFile(Source, file);
    	return System.getProperty(("user+dir")+"//report//"+ testcasename + ".png");
    	
    }


}
