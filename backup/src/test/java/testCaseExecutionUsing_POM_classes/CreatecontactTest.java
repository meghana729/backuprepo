package testCaseExecutionUsing_POM_classes;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.genericwebdriverutility.Utilityclassobject;
import com.comcast.crm.objectrepositoryutility.Contact_page;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganization_page;
import com.comcast.crm.objectrepositoryutility.Home_page;
import com.comcast.crm.objectrepositoryutility.Login_page;
import com.comcast.crm.objectrepositoryutility.Organization_page;

import genericbaseutility.Base2;
import genericbaseutility.Baseutility;
import junit.framework.Assert;
@Listeners(ListnerUtility.ListnerImplementationClass.class)
public class CreatecontactTest extends Baseutility {
	
	
@Test(groups="smokeTest")
public void createcontacttest() throws EncryptedDocumentException, IOException {
	Utilityclassobject.gettest().log(Status.INFO,"read data from excel");
	String lastname=elib.getDataFromExcel("sheet1", 1, 5)+jlib.getRandomNumber();
	//step2 navigate to contact model
	
	//step3:click on "create contact" button
	Home_page hp1=new Home_page(driver);
	 hp1.getContactlink().click();
	 Utilityclassobject.gettest().log(Status.INFO,"navigate to create contact page");
	//Reporter.log("login failed");
	
	//step 3:click on"create contact button
	Contact_page cp=new Contact_page(driver);
	cp.createcontact(lastname);
	//verify expected result-header message
	String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	//as its a dynamic data we use contains function
	boolean status1=headerinfo.contains(lastname);
	Assert.assertEquals(status1,true);
	//verify  lastname text field info expected result
	
	String actlastname=driver.findElement(By.id("mouseArea_Last Name")).getText();
	SoftAssert soft=new SoftAssert();
	soft.assertEquals(actlastname, lastname);
	
	}


@Test(groups="regressionTest")
public void createcontactwithsupportdate() throws EncryptedDocumentException, IOException, InterruptedException {

	// read testscript data from excel
	String lastname=elib.getDataFromExcel("sheet1", 1, 5);
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
}
@Test(groups="regressionTest")
public void createcontactwithorg() throws Throwable, IOException{
	
	
	String orgname=elib.getDataFromExcel("sheet1", 1, 2)+jlib.getRandomNumber();
	String shipping_addres=elib.getDataFromExcel("sheet1", 1, 4);
			String lastname=elib.getDataFromExcel("sheet1", 1, 5);
			Home_page hp=new Home_page(driver);
			hp.getOrglink().click();
			Organization_page oop=new Organization_page(driver);
			oop.getCreateneworgbtn().click();
			CreatingNewOrganization_page  cnp=new CreatingNewOrganization_page (driver);
			cnp.createorg(orgname, shipping_addres);
			 Thread.sleep(2000);
			Contact_page cp=new Contact_page(driver);
			cp.getContactlink().click();
			
			cp.createcontactwithorg(lastname, orgname);
			
			String headerinfo1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerinfo1.contains(lastname)) {
				System.out.println(lastname+"is created==pass");
			}
			else {
				System.out.println(lastname+"is not created==fail");
			}
			
			//verify header lastname info expected result
			String actorgname1=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			System.out.println(actorgname1);
			if(actorgname1.trim().equalsIgnoreCase(orgname)) {
				System.out.println(orgname+"is created==pass");
			}
			else {
				System.out.println(orgname+"is not created==fail");
			}
		
		

			
}
	
}