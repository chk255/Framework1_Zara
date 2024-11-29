package MyProject.SeleniumFramework.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MyProject.SeleniumFramework.PageObjects.CartPage;
import MyProject.SeleniumFramework.PageObjects.CheckOutPage;
import MyProject.SeleniumFramework.PageObjects.Login;
import MyProject.SeleniumFramework.PageObjects.OrderSuccessPage;
import MyProject.SeleniumFramework.PageObjects.ProductPage;
import MyProject.SeleniumFramework.TestComponenets.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends BaseTest {
	
	public Login login;
	public ProductPage productpage;
	public CartPage cartpage;
	public OrderSuccessPage orderpage;
	
	@Given("I landed on ecommerce login page")
		public void I_landed_on_ecommerce_login_page() throws IOException {
		
		login=launchApplication();
	}

	@Given ("^Login With Username (.+) and Password (.+)$")
	public void Login_With_Username_and_Password(String username,String password)
	{
		productpage = login.LoginPage(username,password);
		WebElement successToaster = productpage.validatetoaster();
		Assert.assertTrue(successToaster.isDisplayed());
		Assert.assertEquals(successToaster.getText(), "Login Successfully");
	}

	@When ("^Go to Cart page and add product (.+) to the cart$")
	public void Go_to_Cart_page_and_add_product_to_the_cart(String productname) {
		List<WebElement> products = productpage.GetProductList();
		productpage.SelectProduct(productname);
	}
	
	@And  ("^Checkout product(.+) and Submit Order$")
	public void Checkout_product_and_Submit_Order(String productname) {
		cartpage = productpage.GotoCart();
		boolean match = cartpage.cartitem(productname);
		Assert.assertTrue(match);
		CheckOutPage checkout = cartpage.goToCheckoutPage();
		orderpage = checkout.PlaceOrder("ind");
	}
	@Then ("Validate {string} displayed on Order Success Page.")
	public void Validate_msg_Displayed_on_Order_Success_Page(String string) {
		String successmsg = orderpage.ordersuccess();
		Assert.assertEquals(successmsg, string);
		driver.close();
	}
	
	@When ("^Login With incorrect Username (.+) and Password (.+)$")
	public void Login_With_Username_and_Password_incorrectly(String username,String password)
	{
		productpage = login.LoginPage(username,password);
	}
	@Then ("Validate {string} displayed on Login Page.")
	public void Validate_msg_displayed_on_Login_Page(String string) {
		String Errormsg=login.GetErrorMessage();
		Assert.assertEquals(string, Errormsg);
		driver.close();
	}
}
