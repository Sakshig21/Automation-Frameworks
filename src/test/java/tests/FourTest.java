package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base {
	public WebDriver driver;
	@Test
public void Testfour() throws IOException, InterruptedException {
	System.out.println("Testfour");
	 driver = initializeBrowser();
	driver.get("http://tutorialsninja.com/demo/ ");
	Thread.sleep(2000);
	Assert.assertTrue(false);
	
}
	@AfterMethod
	public void closingBrowser() {
		driver.close();
	}
}
