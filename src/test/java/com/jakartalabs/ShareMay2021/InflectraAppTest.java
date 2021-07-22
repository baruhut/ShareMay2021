package com.jakartalabs.ShareMay2021;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.jakartalabs.ShareMay2021.pages.InflectraAboutPage;
import com.jakartalabs.ShareMay2021.pages.InflectraCommonWebPage;
import com.jakartalabs.ShareMay2021.pages.InflectraCompanyProfilePage;
import com.jakartalabs.ShareMay2021.pages.InflectraHomePage;
import com.jakartalabs.ShareMay2021.pages.InflectraLoginPage;
import com.jakartalabs.ShareMay2021.pages.InflectraProfilePage;
import com.jakartalabs.ShareMay2021.utils.DataUtils;

/**
 * Unit test for simple AppEnums.
 */
public class InflectraAppTest extends InflectraBaseWebTest {

	InflectraHomePage homePage = new InflectraHomePage(driver, explicitWait);
	InflectraLoginPage loginPage = new InflectraLoginPage(driver, explicitWait);
	InflectraProfilePage profilePage = new InflectraProfilePage(driver, explicitWait);
	InflectraCommonWebPage commonWebPage = new InflectraCommonWebPage(driver, explicitWait);
	InflectraAboutPage aboutPage = new InflectraAboutPage(driver, explicitWait);
	InflectraCompanyProfilePage companyProfilePage = new InflectraCompanyProfilePage(driver, explicitWait);

	@Test
	public void loginTest() {

		String username = (DataUtils.getDataFromExcel("Config", "Username"));
		;
		String password = (DataUtils.getDataFromExcel("Config", "Password"));
		;
		String name = (DataUtils.getDataFromExcel("Config", "Name"));
		;

		homePage.clickOnMenuItem(AppEnums.MenuNames.LOGIN.toString());
		loginPage.login(username, password);
		assertEquals(profilePage.verifyProfileName(name), true);

	}

	@Test
	public void verifyAboutUrl() throws InterruptedException {

		String companyProfileLink = "Company Profile";
		String companyNameText = "About Inflectra";

		homePage.clickOnMenuItem(AppEnums.MenuNames.ABOUT.toString());
		assertEquals(commonWebPage.getCurrentUrlFromWebPage(), "https://www.inflectra.com/");
		aboutPage.clickOnCompanyProfile(companyProfileLink);

		String companyProfileText = driver.get()
				.findElement(By.xpath(String.format("//h1[contains(text(),'%s')]", companyNameText))).getText();
		System.out.println("Company Profile : " + companyProfileText);
		assertEquals(companyProfileText, companyNameText);

	}
}
