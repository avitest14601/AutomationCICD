package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory=========================================================================================
	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement userEmail;

	// driver.findElement(By.id("userPassword")).sendKeys("Avinash0062@1");
	@FindBy(id = "userPassword")
	WebElement password;

	// driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog LogInToApplication(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		WaitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
