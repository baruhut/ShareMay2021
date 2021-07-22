package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraProfilePage extends InflectraBasePage {

	public InflectraProfilePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	String profileUsername = "//span[contains(text(),'%s')]";

	public boolean verifyProfileName(String actualProfileName) {
		By finalXpath = By.xpath(String.format(profileUsername, actualProfileName));
		return getText(finalXpath).equals(actualProfileName);
	}

}
