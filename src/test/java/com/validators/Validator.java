package com.validators;

import org.testng.Assert;

public class Validator {

	public static void ValidateResultWithErrMessage(Boolean result, String message) {
		Assert.assertTrue(result, message);
	}

	public static void ValidateStringsEqual(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	public static void ValidateIntegersEqual(int actual, int expected) {
		Assert.assertEquals(actual, expected);
	}
}
