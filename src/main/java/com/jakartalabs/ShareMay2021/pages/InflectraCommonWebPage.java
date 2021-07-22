package com.jakartalabs.ShareMay2021.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InflectraCommonWebPage extends InflectraBasePage {

	public InflectraCommonWebPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	public final String getCurrentUrlFromWebPage() {
		return getCurrentUrl();
	}

}
