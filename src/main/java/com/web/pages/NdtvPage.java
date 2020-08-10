package com.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;

import com.helpers.StringUtils;

public class NdtvPage extends PageBase {
	public static String appURL = "https://ndtv.com";

	@FindBy(id = "h_sub_menu")
	WebElement subMenu;

	@FindBy(linkText = "WEATHER")
	WebElement weatherMenu;

	@FindBy(xpath = "//*[@class='searchBox']")
	WebElement searchTextbox;

	@FindBy(xpath = "(//span[@class='heading'])[3]")
	WebElement humidityText;

	@FindBy(xpath = "(//span[@class='heading'])[4]")
	WebElement tempratureText;

	@FindBy(xpath = "//span[@style='margin-bottom:10px']")
	WebElement cityText;

	public NdtvPage(WebDriver driver) {
		super(driver);
	}

	public void OpenNDTVUrl() {
		driver.navigate().to(appURL);
		driver.manage().window().maximize();
	}

	public void ClickSubMenu() {
		subMenu.click();
	}

	public void ClickWeatherMenu() {
		weatherMenu.click();
	}

	public void SearchCity(String city) {
		wait.until(ExpectedConditions.elementToBeClickable(searchTextbox));
		searchTextbox.clear();
		searchTextbox.click();
		searchTextbox.sendKeys(city);
	}

	public void SelectCityFromDropDown(String city) {
		String dropdownCityXpath = "//*[@for='" + city + "']/input";
		String checked = driver.findElement(By.xpath(dropdownCityXpath)).getAttribute("class");

		if (checked.equalsIgnoreCase("")) {
			driver.findElement(By.xpath(dropdownCityXpath)).click();
		}
	}

	public void ClickPinCity(String city) {
		String pinCityXpath = "//*[@title='" + city + "']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pinCityXpath)));
		driver.findElement(By.xpath(pinCityXpath)).click();
	}

	public String GetWeatherData(ITestContext context) {
		String humidity = humidityText.getText();
		String temprature = tempratureText.getText();

		context.setAttribute("webTemprature", StringUtils.ExtractDigitsFromString(temprature));
		context.setAttribute("webHumidity", StringUtils.ExtractDigitsFromString(humidity));

		return cityText.getText();
	}

	public String GetHomePageTitle() {
		return driver.getTitle();
	}

	public String GetWeatherPageTitle() {
		return driver.getTitle();
	}
}
