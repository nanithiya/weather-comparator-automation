package com.tests.web;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.dataproviders.WeatherDataProvider;
import com.validators.Validator;
import com.web.pages.NdtvPage;

public class NdtvPageTest extends BaseWebTest {

	NdtvPage ndtvpage;
	public String HomePageTitle = "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos";
	public String WeatherPageTitle = "NDTV Weather - Weather in your Indian City";

	@Test(priority = 1, description = "Open NDTV URL")
	public void OpeNDTVUrlTest() {
		ndtvpage = new NdtvPage(driver);
		ndtvpage.OpenNDTVUrl();
		String expectedTitle = ndtvpage.GetHomePageTitle();
		Validator.ValidateStringsEqual(HomePageTitle, expectedTitle);
	}

	@Test(priority = 2, description = "Navigate to weather page")
	public void NavigateToWeatherPageTest() {
		ndtvpage.ClickSubMenu();
		ndtvpage.ClickWeatherMenu();
		String expectedTitle = ndtvpage.GetWeatherPageTitle();
		Validator.ValidateStringsEqual(WeatherPageTitle, expectedTitle);
	}

	@Test(priority = 3, description = "Get weather details of selected city", dataProvider = "CityNameDP", dataProviderClass = WeatherDataProvider.class)
	public void GetWeatherDetailsOfSelectedCityTest(ITestContext context, String city) {
		ndtvpage.SearchCity(city);
		ndtvpage.SelectCityFromDropDown(city);
		ndtvpage.ClickPinCity(city);

		String expectedCityName = ndtvpage.GetWeatherData(context);
		Validator.ValidateResultWithErrMessage(expectedCityName.contains(city), "Invalid city selected");
	}
}
