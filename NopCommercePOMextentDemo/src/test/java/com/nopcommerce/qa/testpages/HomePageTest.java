package com.nopcommerce.qa.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nopcommerce.qa.base.TestBase;
import com.nopcommerce.qa.pages.ComputersPage;
import com.nopcommerce.qa.pages.HomePage;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();	
	}
	
	@Test(priority=1)
	public void verufyUrlLogo() {
		test = extent.createTest("verufyUrlLogo");
		boolean logo = homePage.verifyUrlLogo();
		Assert.assertTrue(logo);
		log.info(logo);
	}
	
	
	@Test(priority=2)
	public void verifyPageTitleTest() {
		test = extent.createTest("verifyPageTitleTest");
		String homePageTitle = homePage.verifyTitle();
		Assert.assertEquals(homePageTitle, "nopCommerce demo store");
		log.info(homePageTitle);
	}
	
	@Test(priority=3)
	public void verifyclickOnComputerLinkTest() {
		test = extent.createTest("verifyclickOnComputerLinkTest");
		ComputersPage computerPage = homePage.clickOnComputerLink();
		log.info(computerPage);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();;
	}

}
