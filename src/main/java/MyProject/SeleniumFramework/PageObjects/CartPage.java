package MyProject.SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class CartPage extends AbstractClassUtility {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//div[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")
	WebElement checkoutButton;
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

	public boolean cartitem(String myProduct1) {
	boolean match = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(myProduct1));
	
	return match;
	
	}
	
     public CheckOutPage goToCheckoutPage() {
	    checkoutButton.click();
	    CheckOutPage checkout=new CheckOutPage(driver);
	    return checkout;
     }

}
