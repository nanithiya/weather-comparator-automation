package com.dataproviders;

import org.testng.annotations.DataProvider;

public class CompareWeatherDataProvider {

	// Provides Variance data of temprature and humidity
	@DataProvider(name = "VarianceDP")
	public static Object[][] VarianceDP() {
		Object[][] data = { { 2, 15 } };
		return data;
	}
}
