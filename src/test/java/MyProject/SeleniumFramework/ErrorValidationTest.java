package MyProject.SeleniumFramework;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import MyProject.SeleniumFramework.PageObjects.CartPage;
import MyProject.SeleniumFramework.PageObjects.ProductPage;
import MyProject.SeleniumFramework.TestComponenets.BaseTest;

public class ErrorValidationTest extends BaseTest{

	String email1 = "chk255@gmail.com";
	String pwd1 = "Ckumar@2426";
	String myProduct1 = "ZARA COAT 33";
	
	@Test(groups="errorvalidation")
	public void LoginErrorValidation() {
		
		String email1 = "chk2515@gmail.com";
		String pwd1 = "Ckuma1r@2426";
		login.LoginPage(email1, pwd1);
		String Errormsg=login.GetErrorMessage();
		Assert.assertEquals("Incorrect email or password.", Errormsg);
		
	}
	
	@Test
	public void cartpagerrorvalidation() {
		String email1 = "chk255@gmail.com";
		String pwd1 = "Ckumar@2426";
		String myProduct1 = "ZARA COAT 3";
		ProductPage productpage = login.LoginPage(email1, pwd1);
		WebElement successToaster = productpage.validatetoaster();
		Assert.assertTrue(successToaster.isDisplayed());
		Assert.assertEquals(successToaster.getText(), "Login Successfully");
		List<WebElement> products = productpage.GetProductList();
		productpage.SelectProduct(myProduct1);
		CartPage cartpage = productpage.GotoCart();
		boolean match = cartpage.cartitem("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
