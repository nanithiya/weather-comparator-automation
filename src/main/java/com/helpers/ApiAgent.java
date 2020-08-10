package com.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAgent {

	// api get call
	public static Response GetRequest(String url) {
		return RestAssured.given().when().get(url);
	}

	// Likewise We can create methods for post, put and delete requests
}
