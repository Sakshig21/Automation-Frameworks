package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Logintest extends Base {
	public WebDriver driver;
 org.apache.logging.log4j.Logger log;
	@Test(dataProvider ="getLoginData")
	
	public void Login(String email,String password,String expectedResult) throws IOException, InterruptedException {
		 driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		log.debug("Clicked on login option");
		Thread.sleep(3000);
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		AccountPage accountpage = new AccountPage(driver);
		//System.out.println(accountpage.editYourAccountInformation().isDisplayed());;
		String actualResult;
		try {
			accountpage.editYourAccountInformation().isDisplayed();
			 log.debug("User got logged in");
			actualResult = "Successfull";
		}catch(Exception e) {	
			log.debug("User didn't log in");
			actualResult = "Failure";
		}
		Assert.assertEquals(actualResult, expectedResult);
		
	}
	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Browser got closed");
		
	}
	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger(Logintest.class.getName());
		driver = initializeBrowser();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"arun.selenium@gmail.com","Arun@12345","Failure"},{"gaikwadsakshi0303@gmail.com","Sakshi@12345","Successfull"}};
		
		return data;
		
	}
}
