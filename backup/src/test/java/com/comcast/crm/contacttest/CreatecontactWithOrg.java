package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.genericwebdriverutility.WebdriverUtility;
import com.comcast.crm.objectrepositoryutility.Contact_page;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganization_page;
import com.comcast.crm.objectrepositoryutility.Home_page;
import com.comcast.crm.objectrepositoryutility.Login_page;
import com.comcast.crm.objectrepositoryutility.Organization_page;

public class CreatecontactWithOrg {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		JavaUtility jlib=new JavaUtility();
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebdriverUtility wlib=new WebdriverUtility();
	//read common data from properties
    String BROWSER = flib.getDataFromPropertiesFile("browser");
	String URL =flib.getDataFromPropertiesFile("url");
	String USERNAME = flib.getDataFromPropertiesFile("username");
	String PASSWORD = flib.getDataFromPropertiesFile("password");
	// read testscript data from excel
	String orgname=elib.getDataFromExcel("sheet1", 1, 3)+jlib.getRandomNumber();
	String lastname=elib.getDataFromExcel("sheet1", 1, 5);
	String shipping_addres=elib.getDataFromExcel("sheet1", 1, 5); 
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
Login_page lp=new Login_page(driver);
lp.loginToapp(URL, USERNAME, PASSWORD);
//step2-navigate to organization module
Home_page hp=new Home_page(driver);
hp.getOrglink().click();
//step3-click on create organization
Organization_page op=new Organization_page(driver);
op.getCreateneworgbtn().click();

//step 4:enter all the details and create new organization
CreatingNewOrganization_page cnp=new CreatingNewOrganization_page(driver);
cnp.createorg(orgname, shipping_addres);
//verify expected result-header message
String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if(headerinfo.contains(orgname)) {
	System.out.println(orgname+"is created==pass");
}
else {
	System.out.println(orgname+"is not created==fail");
}System.out.println("=======");
//verify header orgname info expected result
String actorgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
if(actorgname.contains(orgname)) {
	System.out.println(orgname+"is created==pass");
}
else {
	System.out.println(orgname+"is not created==fail");
}

//step 5-navigate to contact module
hp.getContactlink().click();
//step 6-click on "create contact button
Contact_page cp=new Contact_page(driver);
cp.getCreateContactLink().click();
//step 7-enter all the details and create new contact
cp.getLastnameEdt().sendKeys(lastname);
cp.getSavebtn().click();
cp.getCreateContactLink().click();
cp.createcontactwithorg(lastname, orgname);
String headerinfo1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if(headerinfo1.contains(lastname)) {
	System.out.println(lastname+"is created==pass");
}
else {
	System.out.println(lastname+"is not created==fail");
}
System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//verify header lastname info expected result
String actorgname1=driver.findElement(By.id("mouseArea_Organization Name")).getText();
System.out.println(actorgname1);
if(actorgname1.trim().equalsIgnoreCase(orgname)) {
	System.out.println(orgname+"is created==pass");
}
else {
	System.out.println(orgname+"is not created==fail");
}
Home_page hp1=new Home_page(driver);
hp1.getContactlink().click();
Contact_page cp1=new Contact_page(driver);
cp1.createcontactwithorg(lastname, orgname);

hp.navigatetologout();
driver.quit();

	}

}
