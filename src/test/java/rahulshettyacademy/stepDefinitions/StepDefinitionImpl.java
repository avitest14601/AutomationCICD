package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class StepDefinitionImpl extends BaseTests{
	public LandingPage lp;
	public ProductCatalog pc;
	public CartPage cp;
	public CheckoutPage checkoutP;
	public ThankYouPage typ;
	
	@Given("I landed on ecommerce website")
	public void ILandedonEcommerceWebsite() throws IOException {
		lp = LaunchApplication();
	}
	
	@Given("^Logged in with useremail (.+) and password (.+)$")
	public void LoggedInWithUseremailAndPassword(String userEmail, String password) {
		pc = lp.LogInToApplication(userEmail, password);
	}
	
	@When("^I add product (.+) in the cart$")
	public void IAddProductInTheCart(String productName) {
		List<WebElement> products = pc.getProductsList();
		pc.addToCart(productName);
	}
	
	@And("^Checkout the product (.+) and submit the order with country as (.+)$")
	public void CheckoutTheProductAndSubmitTheOrderWithCountry(String productName, String country) {
		cp = pc.goToCartPage();
		Boolean match = cp.matchProduct(productName);
		Assert.assertTrue(match);
		
		checkoutP = cp.goToCheckoutPage();
		checkoutP.selectCountrySuggestion(country);
		checkoutP.countrySelection();
	}
	
	@Then("{string} message is displayed on the ConfirmationPage")
	public void ThankYouMessageIsDisplayedOnTheConfirmationPage(String string) {
		typ = checkoutP.clickOnSubmitButton();
		Boolean thankYouPageMessage = typ.ValidateThankYouPageWithParameters(string);
		Assert.assertTrue(thankYouPageMessage);
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void ErrorMessageIsDisplayed(String string) {
		Assert.assertEquals(string, lp.getErrorMessage());
		driver.close();
	}
}
