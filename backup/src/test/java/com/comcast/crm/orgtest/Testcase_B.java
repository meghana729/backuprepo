package com.comcast.crm.orgtest;






	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.Properties;
	import java.util.Random;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;

	public class Testcase_B {

		public static void main(String[] args) throws IOException, Throwable {
			
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
						String industry=elib.getDataFromExcel("sheet1", 4, 3);
						String type=elib.getDataFromExcel("sheet1", 4, 4);
	    System.out.println(industry);
	  
	   // String type1=type.trim();
	    
	  //launch chrome
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
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
		WebElement websel= driver.findElement(By.name("industry"));
		Select sel=new Select(websel);
		sel.selectByVisibleText(industry);
		WebElement websel2= driver.findElement(By.name("accounttype"));
		
		Select sel2=new Select(websel2);

		sel2.selectByVisibleText(type);
		
		
		
		}
		
		}

