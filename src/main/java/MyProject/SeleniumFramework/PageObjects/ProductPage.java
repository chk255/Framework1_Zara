package MyProject.SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import MyProject.SeleniumFramework.AbstractComponent.AbstractClassUtility;

public class ProductPage extends AbstractClassUtility {
	
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement successToaster = driver.findElement(By.cssSelector("div[aria-label='Login Successfully']"));
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	@FindBy(css="div[aria-label='Login Successfully']")
	WebElement successtoaster;
	@FindBy(css="//div[@aria-label='Product Added To Cart']")
	WebElement toastMsg;
	@FindBy(css=".ng-animating")
	WebElement nganimating;
	
	By productsby=By.cssSelector(".col-lg-4");
	By AddtoCart=By.cssSelector(".card-body button:last-of-type");
	By Toastcontainer=By.cssSelector("#toast-container");
	
	public WebElement validatetoaster() {
		
		WaitforElementtolocated(successtoaster);
		return successtoaster;
	}
	
	public List<WebElement> GetProductList() {
		
		WaitforElementtoappear(productsby);
		return products;
		
	}
	
	public void SelectProduct(String myProduct1) {
		WebElement prod = GetProductList().stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(myProduct1))
				.findFirst().orElse(null);

		prod.findElement(AddtoCart).click();
		WaitforElementtoappear(Toastcontainer);
		//String toastmsg = toastMsg.getText();
		WaitforElementtoappear(Toastcontainer);
		WaitforElementtodisappear(nganimating);
		//Assert.assertEquals(toastMsg, "Product Added To Cart");
		//return toastmsg;


	}

	

}
