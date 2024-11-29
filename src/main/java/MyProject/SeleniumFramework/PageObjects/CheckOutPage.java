package MyProject.SeleniumFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class CheckOutPage extends AbstractClassUtility {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//section[contains(@class,'ta-results')]/button[2]")
	WebElement Selectcountry;
	@FindBy(css="a[class*='action__submit']")
	WebElement PlaceOrder;
	
	By resultdropdown=By.cssSelector(".ta-results");
	
	public OrderSuccessPage PlaceOrder(String Countryname) {
		country.sendKeys("ind");
		WaitforElementtoappear(resultdropdown);
		Selectcountry.click();
		javascriptexecuter();
		WaitforElementtoappearBy(resultdropdown);
		PlaceOrder.click();
		OrderSuccessPage orderpage=new OrderSuccessPage(driver);
		return orderpage;
	}

}
