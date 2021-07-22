package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraCompanyProfilePage extends InflectraBasePage {

	public InflectraCompanyProfilePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	String companyProfileName = "//h1[contains(text(),'%s')]";
//	By companyProfile = By.xpath("//h1[contains(text(),'About Inflectra')]");

	public boolean verifyCompanyProfile(String actualCompanyName) {
		By finalXpath = By.xpath(String.format(companyProfileName, actualCompanyName));
		return getText(finalXpath).equals(actualCompanyName);
	}

}
