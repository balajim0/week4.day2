package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/main");

		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();

		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		// 6.Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();

		// 7.Click on Widget of From Contact
		driver.findElement(By.xpath("//span[contains(text(),'From Contact')]/../..//img")).click();
		String windowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		// print windows ID
		// System.out.println(windowHandles);
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		String newWindowHandle = windowHandlesList.get(1);
		driver.switchTo().window(newWindowHandle);
		driver.manage().window().maximize();

		// 8. Click on First Resulting Contact
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='x-grid3-body']//tr[1]//td[3])[1]//a")).click();
		driver.switchTo().window(windowHandle);
		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//span[contains(text(),'To Contact')]/../..//img")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandles1);
		String newWindowHandle1 = windowHandlesList1.get(1);
		driver.switchTo().window(newWindowHandle1);
		driver.manage().window().maximize();

		// 10. Click on Second Resulting Contact
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='x-grid3-body']//tr[1]//td[3])[2]//a")).click();
		driver.switchTo().window(windowHandle);

		// 11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert alert = driver.switchTo().alert();

		// 12. Accept the Alert
		alert.accept();
		String title = driver.getTitle();
		System.out.println(title);
		// 13. Verify the title of the page
		if (title.contains("View Contact")) {
			System.out.println("contact merged sucessfully");
		}

	}

}
