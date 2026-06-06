package com.comcast.crm.contacttest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReport {
	public ExtentReports report;
	@BeforeSuite
	public void configbs() {
		//spark report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./advancereport/report.html");
		spark.config().setDocumentTitle("crm test suite results");
		spark.config().setReportName("crm report");
		spark.config().setTheme(Theme.DARK);
		//add env information &create test
		
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER","CHROME-100");
		report.setSystemInfo("OS","WINDOWS-10");
		
	}
	@AfterSuite
	public void createcontacttest() {
		report.flush();
	}
	
@Test
public void createcontactwithorg() {
	//spark report config
	
	WebDriver driver= new ChromeDriver();
	driver.get("http://49.249.29.4:8888");
	TakesScreenshot edriver =(TakesScreenshot)driver;
	String filepath=edriver.getScreenshotAs(OutputType.BASE64);
	
	ExtentTest test=report.createTest("createcontacttest");
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact page");
	test.log(Status.INFO, "create contact");
	if("hdfc".equals("hfdcc")) {
		test.log(Status.PASS,"contact is created ");
	}
	else {
		test.addScreenCaptureFromBase64String(filepath, "errorfile");
	}

	driver.close();
	
}
@Test
public void createcontactwithphonenumber() {
	
	ExtentTest test=report.createTest("createcontactwithphonenumber");
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact page");
	test.log(Status.INFO, "create contact");
	if("hdfc".equals("hfDc")) {
		test.log(Status.PASS,"contact is created ");
	}
	else {
		test.log(Status.FAIL,"contact is not created ");
	}

	
	
}
}
