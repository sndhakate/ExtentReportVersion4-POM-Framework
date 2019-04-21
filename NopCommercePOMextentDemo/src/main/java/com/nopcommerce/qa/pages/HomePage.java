package com.nopcommerce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.qa.base.TestBase;

public class HomePage extends TestBase  {
	
	@FindBy(xpath="//div//div//ul[@class=\"top-menu\"]//li//a[@href=\"/computers\"]")
	WebElement computer;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/electronics\"]")
	WebElement electronics;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/apparel\"]")
	WebElement apparel;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/digital-downloads\"]")
	WebElement digital_downloads;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/books\"]")
	WebElement books;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/jewelry\"]")
	WebElement jewelry;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[@href=\"/gift-cards\"]")
	WebElement giftCards;
	
	@FindBy(xpath="//img[@alt=\"nopCommerce demo store\"]")
	WebElement urlLogo;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyUrlLogo() {
		log.info("verifying nopcommerce url logo dispalyed or not********************");
		boolean logo = urlLogo.isDisplayed();
		return logo;
	}
	
	public String verifyTitle() {		
		log.info("**************Getting url Title*************");
		return driver.getTitle();
	}
		
	public ComputersPage clickOnComputerLink() {
		computer.click();	
		return new ComputersPage();		
	}

}
