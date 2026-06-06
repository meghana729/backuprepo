package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
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

public class Createorgwithphonenumber {

		// TODO Auto-generated method stub
		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			FileInputStream fis=new FileInputStream("./configAppData/Commondata1.properties");
			Properties pobj=new Properties();
			pobj.load(fis);
			String BROWSER=pobj.getProperty("browser");
			String URL=pobj.getProperty("url");
			String USERNAME =pobj.getProperty("username");
			String PASSWORD=pobj.getProperty("password");
	//to add a random number
			Random random=new Random();
			int randomint=random.nextInt(1000);
			System.out.println(randomint);
			
			//read testscript data from excel
		FileInputStream fis1=new FileInputStream("./testdata/practice data1.xlsx");
	    Workbook wb=WorkbookFactory.create(fis1);
	    Sheet sh=wb.getSheet("sheet1");
	    Row row=sh.getRow(1);
	     String orgname=row.getCell(1).toString()+randomint;
	     System.out.println(orgname);
	     String shipping_addres=sh.getRow(1).getCell(4).toString();
	     System.out.println(shipping_addres);
	     String phonenumber=sh.getRow(7).getCell(3).toString();
	     System.out.println(phonenumber);
	     wb.close();
	     
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
			driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
			driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//input[@id='submitButton']")).click();
			//navigate to organization module
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
			
			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipping_addres);
			driver.findElement(By.id("phone")).sendKeys(phonenumber);
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
			Thread.sleep(3000);
			//verify header phone number
			String headerinfo=driver.findElement(By.id("dtlview_Phone")).getText();
			System.out.println("header info"+headerinfo);
			if(headerinfo.contains(phonenumber)) {
				System.out.println(phonenumber+"is created==pass");
			}
			else {
				System.out.println(phonenumber+"is not created==fail");
			}
			//to logout
			Actions action=new Actions(driver);
			Thread.sleep(3000);
		 action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
			Thread.sleep(3000);
	
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
			
	}

}
