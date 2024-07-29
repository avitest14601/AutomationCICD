package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage lp = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("avinash0062@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Avinash0062@1");
		driver.findElement(By.id("login")).click();
		
		String productRequired = "ADIDAS ORIGINAL";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productRequired)).findFirst().orElse(null);
		//System.out.print(prod.getText());
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));
		Boolean match = cartProducts.stream().anyMatch(item->item.getText().equals(productRequired));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[normalize-space(text())=\"Checkout\"]")).click();
		driver.findElement((By.xpath("//input[@placeholder='Select Country']"))).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		
		driver.findElement(By.xpath("//span[normalize-space(text())='India']")).click();
		
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		Boolean thankYouPage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(thankYouPage);
		driver.close();
	}
}