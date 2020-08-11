package com.tests.api;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.apiconfigs.ApiEndpoints;
import com.dataproviders.WeatherDataProvider;
import com.helpers.ApiAgent;
import com.helpers.ApiResponseReader;
import com.validators.Validator;

import io.restassured.response.Response;

public class OpenWeatherAPITest extends BaseAPITest {

	// Get city name from data provider
	@Test(dataProvider = "CityNameDP", dataProviderClass = WeatherDataProvider.class, description = "Get Weather data from API")
	public void GetWeatherDetails(ITestContext context, String city) throws Exception {
		// Format Query URL with city name
		String queryUrl = String.format(ApiEndpoints.GET_WEATHER_BY_CITY_NAME, city);
		// call API to get the response
		Response response = ApiAgent.GetRequest(baseUrl + queryUrl);
		// get city name from the response
		String actualCityName = ApiResponseReader.GetValueFromResponse(response, "name");
		// assert
		Validator.ValidateStringsEqual(actualCityName, city);
		// read temperature and humidity from API response and set in context.
		// Setting value in context will allow to access the value from another
		// test class.
		context.setAttribute("apiTemperature", ApiResponseReader.GetValueFromResponse(response, "main.temp"));
		context.setAttribute("apiHumidity", ApiResponseReader.GetValueFromResponse(response, "main.humidity"));
	}
}
