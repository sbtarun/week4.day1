package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrame {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://leafground.com/pages/frame.html");
		WebElement frameElement = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe[1]"));
		driver.switchTo().frame(frameElement);
		driver.findElement(By.id("Click")).click();

		// Taking screenshot
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("screenshots/frame.png");
		FileUtils.copyFile(src, dst);

		driver.switchTo().defaultContent();
		int noOfFrames = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total number of frames is: " + noOfFrames);

	}

}
