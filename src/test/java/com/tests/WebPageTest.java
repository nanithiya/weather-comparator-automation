package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dataproviders.WeatherDataProvider;
import com.helpers.StringUtils;
import com.validators.Validator;

public class WebPageTest {

	private WebDriver driver;
	String appURL = "https://ndtv.com";

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

	@BeforeClass
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
	}

	@Test(dataProvider = "CityNameDP", dataProviderClass = WeatherDataProvider.class)
	public void GetWeatherDataFromNDTVPage(ITestContext context, String city) {
		driver.navigate().to(appURL);
		driver.manage().window().maximize();

		subMenu.click();
		weatherMenu.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		String searchXpath = "//*[@class='searchBox']";

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchXpath)));
		searchTextbox.clear();
		searchTextbox.click();
		searchTextbox.sendKeys(city);

		String dropdownCityXpath = "//*[@for='" + city + "']/input";
		String checked = driver.findElement(By.xpath(dropdownCityXpath)).getAttribute("class");

		if (checked.equalsIgnoreCase("")) {
			driver.findElement(By.xpath(dropdownCityXpath)).click();
		}

		String pinCityXpath = "//*[@title='" + city + "']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pinCityXpath)));
		driver.findElement(By.xpath(pinCityXpath)).click();

		String cityNameXpath = "//*[@title='" + city + "']";
		driver.findElement(By.xpath(cityNameXpath)).getText();

		String expectedCityName = cityText.getText();
		String humidity = humidityText.getText();
		String temprature = tempratureText.getText();

		Validator.ValidateResult(expectedCityName.contains(city), "Invalid city selected");

		context.setAttribute("webTemprature", StringUtils.ExtractDigitsFromString(temprature));
		context.setAttribute("webHumidity", StringUtils.ExtractDigitsFromString(humidity));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
