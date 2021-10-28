package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Servicenow {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Step1: Load ServiceNow application URL
		driver.get("https://dev94626.service-now.com/");

		// Step2: Enter username (Check for frame before entering the username)
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");

		// Step3: Enter password
		driver.findElement(By.id("user_password")).sendKeys("Tarun1997^");

		// Step4: Click Login
		driver.findElement(By.id("sysverb_login")).click();

		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident");

		// Step6: Click “All”
		driver.findElement(By.linkText("All")).click();

		// Step7: Click New button

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();

		driver.switchTo().defaultContent();

		// Step8: Select a value for Caller and Enter value for short_description

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		driver.findElement(By.linkText("Abraham Lincoln")).click();

		driver.switchTo().window(winList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Automation testing");

		// Step9: Read the incident number and save it a variable
		String incNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident number is: " + incNumber);

		// Step10: Click on Submit button
		driver.findElement(By.id("sysverb_insert")).click();

		// Step 11: Search the same incident number in the next search screen as below
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incNumber, Keys.ENTER);

		// Step12: Verify the incident is created successful and take snapshot of the created incident.		
		String incconfirm = driver.findElement(By.linkText(incNumber)).getText();
		if (incconfirm.equals(incNumber)) {
			System.out.println("Incident number matches");
		} else {
			System.out.println("Incident number mismatch");
		}
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("screenshots/snowincident.png");
		FileUtils.copyFile(src, dst);
	}

}