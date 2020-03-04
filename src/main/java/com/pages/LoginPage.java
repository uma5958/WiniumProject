package com.pages;

import static com.util.ActionUtil.click;
import static com.util.ActionUtil.createNode;
import static com.util.ActionUtil.getElement;
import static com.util.ActionUtil.getWebElement;
import static com.util.ActionUtil.log;
import static com.util.ActionUtil.sendKeys;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;


public class LoginPage {

	// Objects Repository(OR):........................................................................
	static By maximizeWindowBtn = By.name("Maximize");
	static By username = By.id("username");
	static By password = By.id("password");
	static By remindmeCheckBox = By.id("Remember_CheckBox");
	static By loginBtn = By.id("LoginButton");
	static By logOutDropDownButton = By.id("LogoutArrow");
	static By logOutBtn = By.name("Logout");










	// Actions:......................................................................................
	public static void verify_login_functionality(String un, String pwd) throws Exception{
		createNode("Login to site with valid credentials");
		click("maximizeWindowBtn", maximizeWindowBtn);
		sendKeys("username", username, un);
		sendKeys("password", password, pwd);
		click("loginBtn", loginBtn);
		//waitForElementPresent("logOutDD", logOutDD);
		Assert.assertTrue(getWebElement("logOutDD", logOutDropDownButton).isDisplayed(), "Login unsuccessful");
		log("Login successful");
	}

	public static void verify_logout_functionality() throws Exception {
		click("logOutDropDownButton", logOutDropDownButton);
		click("logOutBtn", logOutBtn);
		Assert.assertTrue(getElement("username", username).isDisplayed(), "Logout unsuccessful");
		Reporter.log("Logout successful", true);
	}



}



