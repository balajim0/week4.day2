package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		// price list
		List<WebElement> pricelist = driver.findElements(
				By.xpath("//span[contains(text(),'OnePlus 9 Pro')]/../../../..//span[@class='a-price-whole']"));
		// name of the product
		List<WebElement> namelist = driver.findElements(By.xpath("//*[contains(@alt,'OnePlus 9 Pro')]"));
		// rating of product
		List<WebElement> rating = driver.findElements(By.xpath(
				"//span[contains(text(),'OnePlus 9 Pro')]/../../../..//span[@class='a-size-base s-underline-text']/../.."));
		System.out.println("Moblie rating: " + rating.get(0).getAttribute("aria-label"));
		String text = "";
		for (WebElement firstprice : pricelist) {
			System.out.println(firstprice.getText());
			text = firstprice.getText();
			break;
		}
		for (WebElement firstprod : namelist) {
			firstprod.click();
			break;
		}
		// windows switch
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandles1);
		String newWindowHandle1 = windowHandlesList1.get(1);
		driver.switchTo().window(newWindowHandle1);
		System.out.println(driver.getCurrentUrl());
		// screenshot of the product
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./report/IMG001.png");
		FileUtils.copyFile(source, destination);
		// add to cart
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(10000);
		// subtotal of cart
		WebElement cartsubtotal = driver.findElement(By.xpath("(//span[@id='attach-accessory-cart-subtotal'])[1]"));
		cartsubtotal.click();
		String subtotal = cartsubtotal.getText();
		System.out.println(subtotal);
		// Verification of price
		if (subtotal.contains(text)) {
			System.out.println("Verified total");
		} else
			System.out.println("not Verified");
	}
}
