package mx.com.microsoft.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

	public static WebDriver driver = null;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver105.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}

}
