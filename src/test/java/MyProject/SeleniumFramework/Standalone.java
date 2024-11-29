package MyProject.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String email1 = "chk255@gmail.com";
		String pwd1 = "Ckumar@2426";
		String myProduct1 = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launching Application
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();

		// Login
		WebElement enterUid = driver.findElement(By.id("userEmail"));
		enterUid.sendKeys(email1);

		WebElement EnterPwd = driver.findElement(By.id("userPassword"));
		EnterPwd.sendKeys(pwd1);

		WebElement clickButton = driver.findElement(By.cssSelector("input[type='submit']"));
		clickButton.click();

		// Product Page

		WebElement successToaster = driver.findElement(By.cssSelector("div[aria-label='Login Successfully']"));

		wait.until(ExpectedConditions.visibilityOf(successToaster));

		Assert.assertTrue(successToaster.isDisplayed());
		Assert.assertEquals(successToaster.getText(), "Login Successfully");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		WebElement prod = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(myProduct1))
				.findFirst().orElse(null);

		//.card-body button:last-of-type
		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		String toastMsg = driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']")).getText();

		Assert.assertEquals(toastMsg, "Product Added To Cart");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		WebElement cartButton = driver.findElement(By.cssSelector("button[routerlink*='cart']"));

		cartButton.click();

		// Cart Page

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(myProduct1));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")).click();

		// CheckoutPage

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/button[2]")).click();

		js.executeScript("window.scrollBy(1085, 851)");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();

		// Order Success Page

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

		WebElement heroMsg = driver.findElement(By.xpath("//h1"));
		String Successmsg = heroMsg.getText();
		Assert.assertEquals(Successmsg, "THANKYOU FOR THE ORDER.");

		driver.close();


	}

}
