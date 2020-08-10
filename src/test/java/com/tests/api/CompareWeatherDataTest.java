package com.tests.api;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.dataproviders.CompareWeatherDataProvider;
import com.validators.Validator;

public class CompareWeatherDataTest {

	// Get variance values (temperature and humidity from data provider
	// (configurable))
	@Test(dataProvider = "VarianceDP", dataProviderClass = CompareWeatherDataProvider.class, description = "Compares weather reporting done by UI and API")
	public void CompareWeather(ITestContext context, int temperatureVariance, int humidityVariance) throws Exception {

		// Get temperature and humidity from the context. (API)
		float apiTemperature = Float.parseFloat((String) context.getAttribute("apiTemperature"));
		float apiHumidity = Float.parseFloat((String) context.getAttribute("apiHumidity"));

		// Get temperature and humidity from the context. (WEB)
		float webTemperature = Float.parseFloat((String) context.getAttribute("webTemprature"));
		float webHumidity = Float.parseFloat((String) context.getAttribute("webHumidity"));

		// Check temperature with variance logic
		boolean tempResult = isVarianceOk(apiTemperature, webTemperature, temperatureVariance);
		String tempErrorMessage = "Temprature difference between UI and API is greater than " + temperatureVariance;

		// assert temperature result.
		Validator.ValidateResult(tempResult, tempErrorMessage);

		// Check humidity with variance logic
		boolean humidityResult = isVarianceOk(apiHumidity, webHumidity, humidityVariance);
		String humidityErrorMessage = "Humidity difference between UI and API is greater than " + humidityVariance;

		// assert humidity result.
		Validator.ValidateResult(humidityResult, humidityErrorMessage);
	}

	// Check the difference between UI and API is greater than expected variance
	private boolean isVarianceOk(float dataFromApi, float dataFromUI, int expectedVariance) {
		return Math.abs(dataFromApi - dataFromUI) <= expectedVariance;
	}
}
