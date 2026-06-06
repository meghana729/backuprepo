package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.Contact_page;
import com.comcast.crm.objectrepositoryutility.Home_page; 



public class Testcase_A {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL =flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		// read testscript data from excel
		String orgname=elib.getDataFromExcel("sheet1", 1, 3)+jlib.getRandomNumber();
		String shipping_addres=elib.getDataFromExcel("sheet1", 1, 4);
				String lastname=elib.getDataFromExcel("sheet1", 1, 5);
		
	
		// launch chrome
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		// login into app
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		// navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);

		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipping_addres);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(3000);
		// WebElement sign=driver.findElement(By.xpath("//img[@style='padding:
		// 0px;padding-left:5px']"));
		// verify expected result-header message
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(orgname)) {
			System.out.println(orgname + "is created==pass");
		} else {
			System.out.println(orgname + "is not created==fail");
		}
		System.out.println("=======");
		// verify header orgname info expected result
		String actorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actorgname.contains(orgname)) {
			System.out.println(orgname + "is created==pass");
		} else {
			System.out.println(orgname + "is not created==fail");
		}
		
		Thread.sleep(3000);
		Home_page hp1=new Home_page(driver);
		hp1.getContactlink().click();
		Contact_page cp1=new Contact_page(driver);
		cp1.createcontactwithorg(lastname, orgname);
 
		
		// to logout
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
