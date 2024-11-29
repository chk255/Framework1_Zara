package MyProject.SeleniumFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class Login extends AbstractClassUtility {

	WebDriver driver;

	public Login(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement enterUid;
	@FindBy(id = "userPassword")
	WebElement EnterPwd;
	@FindBy(css = "input[type='submit']")
	WebElement Submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement Errormsg;

	
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	public ProductPage LoginPage(String EmailID, String Pass) {

		enterUid.sendKeys(EmailID);
		EnterPwd.sendKeys(Pass);
		Submit.click();
		ProductPage productpage = new ProductPage(driver);
		return productpage;
	}
	public String GetErrorMessage() {
		
		WaitforElementtolocated(Errormsg);
		String errormsg=Errormsg.getText();
		return errormsg;
	}
	
	

}
