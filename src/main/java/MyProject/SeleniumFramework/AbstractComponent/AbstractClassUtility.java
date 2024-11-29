package MyProject.SeleniumFramework.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyProject.SeleniumFramework.PageObjects.CartPage;
import MyProject.SeleniumFramework.PageObjects.MyOrders;

public class AbstractClassUtility {
	
	WebDriver driver;
	
	public AbstractClassUtility(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement CartButton;
	@FindBy(css="button[routerlink*='myorders']")
	WebElement Orders;
	
	public void WaitforElementtoappear(By Findby) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	public void WaitforElementtoappearBy(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Findby));
		}
	
	public void WaitforElementtolocated(WebElement Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(Findby));
		}
	public void WaitforElementtodisappear(WebElement Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(Findby));
		}

	public void javascriptexecuter() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1085, 851)");
	}
	public CartPage GotoCart() {
		
		CartButton.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	public MyOrders Orders() {
		
		Orders.click();
		MyOrders myorder=new MyOrders(driver);
		return myorder;
		
	}
}
