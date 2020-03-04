/**
 * @author UmaMaheswararao
 */

package com.testcases;

import static com.pages.LoginPage.*;
import static com.util.ActionUtil.log;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.util.ExcelUtility;

public class LoginPageTest extends BasePage {
	
	public ExcelUtility reader;
	
	@BeforeMethod
	public void setUp() throws Exception {
		start();
		launchApplication();
		reader = new ExcelUtility(testDataFilePath);
	}
	
	@Test
	public void login_functionality_Test() throws Exception{
		verify_login_functionality(prop.getProperty("username"), prop.getProperty("password"));
		log("User Loggedin Successfully & Dash Board Page Opened");
	}
	
	@Test(enabled=true)
	public void login_and_logout_Test() throws Exception {
		verify_login_functionality(prop.getProperty("username"), prop.getProperty("password"));
		verify_logout_functionality();
	}
	
	
	
	
	
	

}
