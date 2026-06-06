package ListnerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.genericwebdriverutility.Utilityclassobject;

import genericbaseutility.Base2;

public class ListnerImplementationClass implements ITestListener,ISuiteListener
{
	public static WebDriver sdriver=null;
	 public static ExtentReports report;
	 ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
		ISuiteListener.super.onStart(suite);
		System.out.println("report configuration");

		String time=new Date().toString().replace(" ", "_").replace(":","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./advancereport/report"+time+".html");
		spark.config().setDocumentTitle("crm test suite results");
		spark.config().setReportName("crm report");
		spark.config().setTheme(Theme.DARK);
		//add env information &create test
		
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER","CHROME-100");
		report.setSystemInfo("OS","WINDOWS-10");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onFinish(suite);
		System.out.println("report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		System.out.println("=============>"+result.getMethod().getMethodName()+">=======STARTED=========");
		test=report.createTest(result.getMethod().getMethodName());//tocature test case name
		Utilityclassobject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"====>started<=====" );
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println();
		System.out.println("=============>"+result.getMethod().getMethodName()+">=======END=========");
		test.log(Status.PASS,result.getMethod().getMethodName()+"====>completed<=====" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		String testname=result.getMethod().getMethodName();
		TakesScreenshot edriver =(TakesScreenshot)sdriver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		
		String time=new Date().toString().replace(" ", "_").replace(":","_");
		test.addScreenCaptureFromBase64String(filepath, testname+"-"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"====>FAILED<=====" );
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		report.flush();
	}

	
	
}