package com.comcast.crm.contacttest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.Contact_page;

public class Createcontactmodel {
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//create object
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
			String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME =flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		// to add a random number
	    //read testscript data from excel
	String lastname=elib.getDataFromExcel("Sheet1",1, 5)+jlib.getRandomNumber();
     
     //launch browser
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		  //login into app
		driver.manage().window().maximize();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE); 
		File dst = new File("./screenshots/Homepage.png");
		FileHandler.copy(temp, dst);
		
	
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//navigate to contact model
		driver.findElement(By.linkText("Contacts")).click();
		Contact_page cp=new Contact_page(driver);
		cp.createcontact(lastname);
//verify expected result-header message
String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if(headerinfo.contains(lastname)) {
	System.out.println(lastname+"is created==pass");
}
else {
	System.out.println(lastname+"is not created==fail");
}
//verify header lastname info expected result
String actlastname=driver.findElement(By.id("mouseArea_Last Name")).getText();
if(actlastname.contains(lastname)) {
	System.out.println(lastname+"is created==pass");
}
else {
	System.out.println(lastname+"is not created==fail");
}	
//to logout
Actions action=new Actions(driver);
Thread.sleep(3000);
action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
Thread.sleep(3000);

driver.findElement(By.linkText("Sign Out")).click();
driver.quit();

		
}}
