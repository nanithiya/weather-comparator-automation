package com.apiconfigs;

import java.util.HashMap;
import java.util.Map;

public class ApiHeaders {

	// default api headers
	public static Map<String, String> DefaultHeaders() {
		Map<String, String> defaultHeaders = new HashMap<String, String>();
		defaultHeaders.put("Content-Type", "application/json");
		return defaultHeaders;
	}
}
