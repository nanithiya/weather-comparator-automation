package com.apiconfigs;

// api endpoints can be configured here.
public final class ApiEndpoints {
	private final static String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";

	public final static String GET_WEATHER_BY_CITY_NAME = "?q=%s&units=metric&appid=" + API_KEY;
}
