package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraHomePage extends InflectraBasePage {

	public InflectraHomePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	String menuLink = "//a[contains(text(),'%s')]";

	public void clickOnMenuItem(String menuName) {

//		By finalXpath = By.xpath(String.format(menuLink, menuName));
		By finalXpath = By.xpath(String.format(menuLink, menuName));

		clickAndWait(finalXpath);
	}

}
