package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraLoginPage extends InflectraBasePage {

	public InflectraLoginPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	By email = By.xpath("//input[@id='cplMainContent_LoginUser_UserName']");
	By password = By.xpath("//input[@id='cplMainContent_LoginUser_Password']");
	By loginBtn = By.xpath("//input[@id='cplMainContent_LoginUser_btnLogIn']");

	public void login(String userEmail, String userPwd) {
		setText(email, userEmail);
		setText(password, userPwd);
		clickAndWait(loginBtn);
	}

}
