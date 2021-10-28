package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesCher {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		driver.switchTo().frame("frame1");
		driver.findElement(By.tagName("input")).sendKeys("Typing some data");

		driver.switchTo().frame("frame3");
		driver.findElement(By.tagName("input")).click();

		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("frame2");
		WebElement animals = driver.findElement(By.id("animals"));
		Select dropdown1 = new Select(animals);
		dropdown1.selectByVisibleText("Avatar");		

	}

}
