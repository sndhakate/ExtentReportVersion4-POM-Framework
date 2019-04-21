package com.nopcommerce.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nopcommerce.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	 
	 @BeforeTest
	 public void setExtent() {
	  // specify location of the report
	  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myExtentReport.html");

	  htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
	  htmlReporter.config().setReportName("Functional Testing"); // Name of the report
	  htmlReporter.config().setTheme(Theme.DARK);
	  
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  
	  // Passing General information
	  extent.setSystemInfo("Host name", "localhost");
	  extent.setSystemInfo("Environemnt", "QA");
	  extent.setSystemInfo("user", "Swapnil Dhakate");
	 }
	 
	 @AfterTest
	 public void endReport() {
	  extent.flush();
	 }
	
	public TestBase() {
	
	try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream("C:\\Maven Projects\\POMnopCommerce\\src\\main\\java"
				+ "\\com\\nopcommerce\\qa\\config\\config.properties");
		prop.load(ip);
	} catch (Exception e) {
		}
	}
		
	public static void initialization() {
		
		log = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			log.info("******************Configure chrome browser for launching****************");
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("Firefox")) {
			log.info("***************Configure firefox browser for launching******************");
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		log.info("**********Maximizing the window");
		log.debug("************Testing window miximize or not*******");
		driver.manage().window().maximize();
		
		log.warn("*********************Deleting the cookies*******************");
		driver.manage().deleteAllCookies();
		
		
		log.info("*************** Waiting for PageLoad******************");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		
		log.info("*************** Implicit Wait ****************");
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
		
		log.info("****************Opening the URL ***********************");
		driver.get(prop.getProperty("url"));
		log.isTraceEnabled();
//		log.fatal("is there any fatal cases");
//		log.warn("is everything running smoothly");
		log.trace("**************is everything running smoothlyss*******************");
			
	}
	
	@AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	  if (result.getStatus() == ITestResult.FAILURE) {
	   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
	   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
	   String screenshotPath = TestBase.getScreenshot(driver, result.getName());
	   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
	  } else if (result.getStatus() == ITestResult.SKIP) {
	   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	  }
	  else if (result.getStatus() == ITestResult.SUCCESS) {
	   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	  }
	 
	 }
	 
	 public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
	  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  TakesScreenshot ts = (TakesScreenshot) driver;
	  File source = ts.getScreenshotAs(OutputType.FILE);
	  
	  // after execution, you could see a folder "FailedTestsScreenshots" under src folder
	  String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
	  File finalDestination = new File(destination);
	  FileUtils.copyFile(source, finalDestination);
	  return destination;
	 }
	}

	

