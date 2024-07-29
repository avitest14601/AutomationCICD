package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class StandAloneTest extends BaseTests{
	String productName = "ADIDAS ORIGINAL";
	String country = "India";
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		ProductCatalog pc = lp.LogInToApplication(input.get("email"), input.get("pass"));
		pc.addToCart(input.get("product"));
		CartPage cp = pc.goToCartPage();
		
		Boolean match = cp.matchProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutP = cp.goToCheckoutPage();
		
		checkoutP.selectCountrySuggestion(country);
		checkoutP.countrySelection();
		ThankYouPage typ = checkoutP.clickOnSubmitButton();
		
		Boolean thankYouPageMessage = typ.ValidateThankYouPage();
		Assert.assertTrue(thankYouPageMessage);
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalog pc = lp.LogInToApplication("avinash0062@gmail.com", "Avinash0062@1");
		OrdersPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.getProductMatch(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {		
		List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}

//HashMap <String, String> map = new HashMap<String, String>();
//map.put("email", "avinash0062@gmail.com");
//map.put("pass", "Avinash0062@1");
//map.put("productRequired", "ADIDAS ORIGINAL");
//
//HashMap <String, String> map1 = new HashMap<String, String>();
//map1.put("email", "avinashtiwa@gmail.com");
//map1.put("pass", "Avinash@123");
//map1.put("productRequired", "IPHONE 13 PRO");