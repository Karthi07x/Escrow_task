package Flipkart;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class T_01 {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[text()='✕']")).click();
		WebElement text = driver.findElement(By.name("q"));
		text.sendKeys("Bluetooth Speakers",Keys.ENTER);
		
		//Filter
		
		WebElement brand = driver.findElement(By.xpath("//div[text()='Brand']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
		driver.findElement(By.xpath("//div[text()='boAt']")).click();
		WebElement rating = driver.findElement(By.xpath("//div[text()='Customer Ratings']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", rating);
		js.executeScript("arguments[0].click();", rating);
		
		driver.findElement(By.xpath("//div[text()='4★ & above']")).click();
		WebElement price = driver.findElement(By.xpath("//div[contains(text(),'Price -- Low to High')]"));
		wait.until(ExpectedConditions.elementToBeClickable(price));
		js.executeScript("arguments[0].click();", price);
		Thread.sleep(2000);
		WebElement product = driver.findElement(By.xpath("(//a[@target='_blank'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(product));
		js.executeScript("arguments[0].click()", product);
		
		Set<String> window = driver.getWindowHandles();
		for (String s : window) {
			driver.switchTo().window(s);
			
		}
		List<WebElement> cashback = driver.findElements(By.xpath("//div[contains(text(),'Cashback')]"));
		System.out.println("Number of Offers :"+cashback.size());
		if (driver.findElements(By.xpath("//div[text()='Add to cart']")).size() > 0) {
		    driver.findElement(By.xpath("//div[text()='Add to cart']")).click();
		    WebElement cart = driver.findElement(By.xpath("//div[text()='Go to cart']"));
		    wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Price Details')]")));

		    TakesScreenshot ts = (TakesScreenshot) driver;
		    File temp = ts.getScreenshotAs(OutputType.FILE);
		    File dest = new File("./Screenshot/cart_result.png");
		    FileHandler.copy(temp, dest);
		    System.out.println("Product added to cart");
		    

		} 
		else {
		    System.out.println("Product unavailable — could not be added to cart.");
		    TakesScreenshot ts = (TakesScreenshot) driver;
		    File temp1 = ts.getScreenshotAs(OutputType.FILE);
		    File dest1 = new File("./Screenshot/result.png");
		    FileHandler.copy(temp1, dest1);
		    
		}
		driver.quit();
	}

}
