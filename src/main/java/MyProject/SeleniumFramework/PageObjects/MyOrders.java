package MyProject.SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class MyOrders extends AbstractClassUtility {
	
	
	WebDriver driver;

	public MyOrders(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productName;
	
	public boolean MyOrderitem(String myproduct1) {
		
		boolean match=productName.stream().anyMatch(p->p.getText().equalsIgnoreCase(myproduct1));
		return match;
	}

}
