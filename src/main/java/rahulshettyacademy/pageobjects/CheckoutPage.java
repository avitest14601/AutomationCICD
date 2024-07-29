package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;

	CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;
	public void selectCountrySuggestion(String country) {
		selectCountry.sendKeys(country);
		WaitForElementToAppear(By.cssSelector("[class*='ta-results']"));
	}

	@FindBy(xpath = "//span[normalize-space(text())='India']")
	WebElement country;
	public void countrySelection() {
		country.click();
	}

	@FindBy(css = ".action__submit")
	WebElement submitButton;
	public ThankYouPage clickOnSubmitButton() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitButton);
		ThankYouPage thankYouPage = new ThankYouPage(driver);
		return thankYouPage;
	}
}
