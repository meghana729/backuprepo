package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.Contact_page;
import com.comcast.crm.objectrepositoryutility.Home_page;
import com.comcast.crm.objectrepositoryutility.Login_page;

public class Createcontactwithsupportdate  {
	public static void main(String[] args) throws Throwable {
		
		JavaUtility jlib=new JavaUtility();
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		
	//read common data from properties
    String BROWSER = flib.getDataFromPropertiesFile("browser");
	String URL =flib.getDataFromPropertiesFile("url");
	String USERNAME = flib.getDataFromPropertiesFile("username");
	String PASSWORD = flib.getDataFromPropertiesFile("password");
	// read testscript data from excel
	String lastname=elib.getDataFromExcel("sheet1", 1, 5);
	WebDriver driver=null;
	if(BROWSER.equals("chrome")) {
		driver=new ChromeDriver();
	}
	else if(BROWSER.equals("firefox")) {
		driver=new FirefoxDriver();
		
	}
	else if(BROWSER.equals("edge")) {
		driver=new EdgeDriver();
	}
	else {driver=new ChromeDriver();}

				//login to app
	Login_page lp=new Login_page(driver);
	lp.loginToapp(URL, USERNAME, PASSWORD);
	
				//navigate to contact model
				System.out.println("========================");
				
				Contact_page cp=new Contact_page(driver);
				cp.getContactlink().click();
				cp.getCreateContactLink().click();
				String startdate=jlib.getSystemDateyyyyddmm();
				String enddate=jlib.getRequiredDateyyyyddmm(30);
				
				cp.createcontactwithsuportdata(lastname, startdate, enddate);
		//verify start and end date
	
				String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
				System.out.println("actstar"+actstartdate);
				if(actstartdate.equals(startdate)) {
					System.out.println(startdate+"info is verified===pass");
				}
				else {
					System.out.println(startdate+"info is not verified===fail");
					
				}
				String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actenddate.equals(enddate)) {
					System.out.println(enddate+"info is verified===pass");
				}
				else {
					System.out.println(enddate+"info is not verified===fail");
					
				}
				
		Home_page hp=new Home_page(driver);
		hp.navigatetologout();
		
	}

}
