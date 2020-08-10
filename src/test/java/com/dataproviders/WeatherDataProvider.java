package com.dataproviders;

import org.testng.annotations.DataProvider;

public class WeatherDataProvider {

	// Provides city name for UI search and API endpoint
	@DataProvider(name = "CityNameDP")
	public static Object[][] CityNameDP() {
		Object[][] data = { { "Mumbai" } };
		return data;
	}
}
