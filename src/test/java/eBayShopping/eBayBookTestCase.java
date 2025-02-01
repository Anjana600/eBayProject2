package eBayShopping;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class eBayBookTestCase {
	
	

	public static void main(String[] args) throws InterruptedException, AWTException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		 try {
	            // Set implicit wait and maximize window
	            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	            driver.manage().window().maximize();

	            // Open eBay or any shopping website
	            driver.get("https://www.ebay.com/");

	            // Search for a product (e.g., "book")
	            WebElement searchBox = driver.findElement(By.xpath("//input[@name='_nkw']"));
	            searchBox.sendKeys("book");

	            WebElement searchBtn = driver.findElement(By.xpath("//span[@class='gh-search-button__label']"));
	            searchBtn.click();

	            // Click on the first product
	            Thread.sleep(3000); // Wait for results to load
	            
	            scrollPageDown(driver);
	            WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='s-item__image-wrapper image-treatment'])[3]//img"));
	            WebDriverWait wait=new WebDriverWait(driver,30);
	            wait.until(ExpectedConditions.visibilityOf(firstProduct)).click();
	            

	            // Switch to the new tab (if the product opens in a new tab)
	            for (String winHandle : driver.getWindowHandles()) {
	                driver.switchTo().window(winHandle);
	            }

	            // Add the product to cart
	            Thread.sleep(3000); // Wait for product page to load
	            scrollPageDown(driver);
	            
	            WebElement addToCartBtn = driver.findElement(By.xpath("//a[@id='atcBtn_btn_1']"));
	            addToCartBtn.click();

	            // Wait for cart update
	            Thread.sleep(5000);

	            // Verify the cart displays the correct number of items
	            WebElement cartCount = driver.findElement(By.xpath("//span[@class='gh-cart__icon']"));
	            String itemCount = cartCount.getText();

	            if (!itemCount.isEmpty() && Integer.parseInt(itemCount) > 0) {
	                System.out.println("Test Passed: Cart has been updated and displays " + itemCount + " item(s).");
	            } else {
	                System.out.println("Test Failed: Cart is empty.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            Thread.sleep(3000);
	            driver.quit();
	        }
	}
		 /**
			 * used to scroll Up by using Robot class
			 * @throws AWTException 
			 */
			public static void scrollPageDown(WebDriver driver) throws AWTException {
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);

}
}
