package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ThankYouPage extends AbstractComponents{
	WebDriver driver;
	ThankYouPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement thankYouMessage;
	public Boolean ValidateThankYouPage() {
		Boolean EqualMessage = thankYouMessage.getText().equalsIgnoreCase("Thankyou for the order.");
		return EqualMessage;
	}
	
	public Boolean ValidateThankYouPageWithParameters(String message) {
		Boolean EqualMessage = thankYouMessage.getText().equalsIgnoreCase(message);
		return EqualMessage;
	}
}
