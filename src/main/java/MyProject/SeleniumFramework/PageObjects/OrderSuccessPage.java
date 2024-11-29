package MyProject.SeleniumFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class OrderSuccessPage extends AbstractClassUtility{
	
	WebDriver driver;
	public OrderSuccessPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1")
	WebElement heroMsg;
	By hero=By.xpath("//h1");
	
	public String ordersuccess() {
		
	WaitforElementtoappear(hero);
	WebElement heroMsg = driver.findElement(By.xpath("//h1"));
	String Successmsg = heroMsg.getText();
	return Successmsg;
	
	}

	
}
