package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTests{
	ExtentReports extent;
	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {		
		lp.LogInToApplication("avinash0062@gmail.com", "avinash0062@123");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());	
	}
	
	@Test
	public void ProductErrorValidation() {
		String productRequired = "ADIDAS ORIGINAL";
		ProductCatalog pc = lp.LogInToApplication("avinash0062@gmail.com", "Avinash0062@1");
		pc.addToCart(productRequired);
		CartPage cp = pc.goToCartPage();
		
		Boolean match = cp.matchProduct("ADIDAS ORIGINALs");
		Assert.assertFalse(match);
	}
}