package com.comcast.crm.genericwebdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class Utilityclassobject {
/*for paralleexecution as static vraiable has only one static pool which cannot
	be shared,to share static pool*/
public static	ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
public static	ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
public static ExtentTest gettest() {
	return test.get();
}
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
		public static void setDriver(WebDriver actdriver) {
			driver.set(actdriver);
		}
}
