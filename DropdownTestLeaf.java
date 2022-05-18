package week4.day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownTestLeaf {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Dropdown.html");
		WebElement dropdown1 = driver.findElement(By.id("dropdown1"));
		Select select = new Select(dropdown1);
		select.selectByIndex(1);
		System.out.println("completed");
		WebElement dropdown2 = driver.findElement(By.name("dropdown2"));
		Select select1 = new Select(dropdown2);
		select1.selectByVisibleText("Appium");
		WebElement dropdown3 = driver.findElement(By.id("dropdown3"));
		Select select2 = new Select(dropdown3);
		select2.selectByValue("1");
		WebElement dropdown4 = driver.findElement(By.className("dropdown"));
		Select select3 = new Select(dropdown4);
		List<WebElement> options = select3.getOptions();
		options.size();
		System.out.println(options.size());
		for (WebElement o : options) {
			System.out.println(o.getText());
			// System.out.println(o.getSize());
			if (o.getText().equals("Selenium")) {
				select3.selectByVisibleText(o.getText());
			}

		}
		driver.findElement(By.xpath("(//select)[5]")).sendKeys("Selenium", Keys.ENTER);
		WebElement dropdown5 = driver.findElement(By.xpath("(//select)[6]"));
		Select select4 = new Select(dropdown5);
		select4.selectByValue("1");
		select4.selectByVisibleText("Appium");
		driver.close();
	}
}
