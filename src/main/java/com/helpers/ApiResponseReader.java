package com.helpers;

import io.restassured.response.Response;

public class ApiResponseReader {

	// Get the value of actual supplied key from the response
	public static String GetValueFromResponse(Response response, String key) {
		return response.getBody().jsonPath().get(key).toString();
	}
}
