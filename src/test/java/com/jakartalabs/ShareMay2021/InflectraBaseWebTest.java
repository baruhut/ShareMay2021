package com.jakartalabs.ShareMay2021;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jakartalabs.ShareMay2021.utils.DataUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InflectraBaseWebTest implements InflectraIWebDriver {
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<WebDriverWait>();

	@Override
	@BeforeMethod
	public void createWebDriver() {
		WebDriverManager.chromedriver().proxy(HTTP_PROXY).setup();
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		driver.get().manage().window().maximize();
		driver.get().get(DataUtils.getDataFromExcel("Config", "BaseUrl"));
		explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));

		/*
		 * WebDriverManager.chromedriver().setup(); driver.set(new ChromeDriver());
		 * driver.get().manage().window().maximize();
		 * driver.get().get(DataUtils.getDataFromExcel("Config", "BaseUrl"));
		 * explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));
		 */
	}

	@Override
	@AfterMethod
	public void quitWebDriver(ITestResult itr) {
		if (itr.getStatus() == ITestResult.CREATED) {
			takesScreenshot(itr);
		}
		driver.get().quit();
		driver.remove();

	}

	private void takesScreenshot(ITestResult itr) {
		File src = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

		DateFormat dateFormat = new SimpleDateFormat(" MM-dd-yyyy hh-mm-ss");
		Date date = new Date();
		File destination = new File(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ itr.getName() + dateFormat.format(date) + ".png");

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
