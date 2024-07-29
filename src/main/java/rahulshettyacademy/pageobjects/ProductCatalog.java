package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	public List<WebElement> getProductsList() {
		WaitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductName(String productRequired) {
		return products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productRequired)).findFirst().orElse(null);
	}
	
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public void addToCart(String productRequired) {
		getProductName(productRequired).findElement(addToCartBy).click();
		WaitForElementToAppear(toastContainer);
		WaitForElementToDisappear(spinner);
	}
}
