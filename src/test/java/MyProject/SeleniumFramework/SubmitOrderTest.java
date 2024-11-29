package MyProject.SeleniumFramework;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MyProject.SeleniumFramework.PageObjects.CartPage;
import MyProject.SeleniumFramework.PageObjects.CheckOutPage;
import MyProject.SeleniumFramework.PageObjects.Login;
import MyProject.SeleniumFramework.PageObjects.MyOrders;
import MyProject.SeleniumFramework.PageObjects.OrderSuccessPage;
import MyProject.SeleniumFramework.PageObjects.ProductPage;
import MyProject.SeleniumFramework.TestComponenets.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String email1 = "chandan255fst@gmail.com";
	String pwd1 = "Ckumar@2426";
	String myProduct1 = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"purchase"})

	public void SubmitOrder(HashMap<String,String> inputdata) throws IOException {
		//String CountryName = "ind";
		ProductPage productpage = login.LoginPage(inputdata.get("email"), inputdata.get("password"));
		WebElement successToaster = productpage.validatetoaster();
		Assert.assertTrue(successToaster.isDisplayed());
		Assert.assertEquals(successToaster.getText(), "Login Successfully");
		List<WebElement> products = productpage.GetProductList();
		productpage.SelectProduct(inputdata.get("product"));
		CartPage cartpage = productpage.GotoCart();
		boolean match = cartpage.cartitem(inputdata.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkout = cartpage.goToCheckoutPage();
		OrderSuccessPage orderpage = checkout.PlaceOrder("ind");
		String successmsg = orderpage.ordersuccess();
		Assert.assertEquals(successmsg, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void verifyorderitem() {
		ProductPage productpage = login.LoginPage(email1, pwd1);
		MyOrders myorder=productpage.Orders();
		Assert.assertTrue(myorder.MyOrderitem(myProduct1));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		//return  new Object[][] {{"chk255@gmail.com","Ckumar@2426","ZARA COAT 3"},{"chandan255fst@gmail.com","Ckumar@2426","ADIDAS ORIGINAL"}};
		 
//		HashMap<String,String> map1=new HashMap();
//		
//		map1.put("email", "chk255@gmail.com");
//		map1.put("password", "Ckumar@2426");
//		map1.put("product", "ZARA COAT 3");
//		
//        HashMap<String,String> map2=new HashMap();
//		
//		map2.put("email", "chandan255fst@gmail.com");
//		map2.put("password", "Ckumar@2426");
//		map2.put("product", "ADIDAS ORIGINAL");
		//return new Object[][] {{map1},{map2}};	
		List<HashMap<String,String>> data=getDataJSONtoMap(System.getProperty("user.dir")+"//src//test//java//MyProject//SeleniumFramework//Data//getData.json");
		
			return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
}
