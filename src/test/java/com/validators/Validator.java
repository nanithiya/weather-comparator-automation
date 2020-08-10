package com.validators;

import org.testng.Assert;

public class Validator {

	public static void ValidateResult(Boolean result, String message) {
		Assert.assertTrue(result, message);
	}

	public static void ValidateEqual(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}
}
