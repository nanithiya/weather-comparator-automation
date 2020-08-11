package com.tests.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseWebTest {
	public static WebDriver driver = null;

	public BaseWebTest() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
