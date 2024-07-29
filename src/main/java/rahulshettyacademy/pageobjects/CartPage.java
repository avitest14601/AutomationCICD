package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {
	WebDriver driver;
	public CartPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".infoWrap h3")
	List<WebElement> cartProducts;
	public List<WebElement> getCartProducts() {
		return cartProducts;
	}
	
	public boolean matchProduct(String productRequired) {
		return getCartProducts().stream().anyMatch(item->item.getText().equals(productRequired));
	}
	
	@FindBy(xpath="//button[normalize-space(text())='Checkout']")
	WebElement checkOutButton;
	public CheckoutPage goToCheckoutPage() {
		checkOutButton.click();
		CheckoutPage checkoutP = new CheckoutPage(driver);
		return checkoutP;
	}
}
