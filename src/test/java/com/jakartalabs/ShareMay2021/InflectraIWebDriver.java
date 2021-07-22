package com.jakartalabs.ShareMay2021;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public interface InflectraIWebDriver {
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	String HTTP_PROXY = "yy15366:Yeye0521@proxy.dnroot.net:8000";

	public void createwebDriver();

	public void quitwebDriver(ITestResult itr);
}
