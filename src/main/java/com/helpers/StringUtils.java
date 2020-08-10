package com.helpers;

public class StringUtils {

	public static String ExtractDigitsFromString(String data) {
		return data.replaceAll("\\D+", "");
	}
}
