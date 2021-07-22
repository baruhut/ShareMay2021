package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraAboutPage extends InflectraBasePage {

	public InflectraAboutPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	String companyProfileLink = "(//a[contains(text(),'%s')])[2]";

	public void clickOnCompanyProfile(String companyName) {
		By finalXpath = By.xpath(String.format(companyProfileLink, companyName));
		clickAndWait(finalXpath);
	}
}