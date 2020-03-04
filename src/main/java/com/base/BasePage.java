/**
 * @author UmaMaheswararao
 */

package com.base;

import static com.util.ActionUtil.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@Listeners(com.Listeners.TestListeners.class)
public class BasePage {

	public static Properties prop;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> tlExtentTestNode = new ThreadLocal<ExtentTest>();
	public static String testDataFilePath = "./src/main/java/com/testdata/TestData.xlsx";
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		initialize_properties();
		initilizeExtentReport();
	}
	
	@AfterMethod
	public void tearDown() {
		quit();
	}
	
	@AfterSuite
	public static void afterSuite() throws Exception {
	}
	
	public static Properties initialize_properties() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void launchApplication() throws Exception{
		DesktopOptions options = new DesktopOptions();
		options.setApplicationPath("C:\\Users\\Mahesh\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\RemotePC\\RemotePC.lnk");
		setDriver(new WiniumDriver(new URL("http://localhost:9999"), options));
	}	
	
	public static void initilizeExtentReport() throws Exception {
		String timeStamp = getCurrentDateAndTime();
		extent = new ExtentReports();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\ExtentReports\\Reports\\Automation_Report-" + timeStamp + ".html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\ExtentReports\\ConfigFile\\html-config.xml");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", prop.getProperty("os"));
		extent.setSystemInfo("Environment", prop.getProperty("envirionment"));
		extent.setSystemInfo("Browser", prop.getProperty("browser"));
	}


	public static WebDriver getDriver() {
		return tldriver.get();
	}

	public static void setDriver(WebDriver driver) {
		tldriver.set(driver);
	}
	
	public static void start()  {
		if(getDriver()==null)
			try {
			new BasePage();
			}
		catch(Exception e) {
			
		}
	}

	public static void quit() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
	
	
	
	



}
