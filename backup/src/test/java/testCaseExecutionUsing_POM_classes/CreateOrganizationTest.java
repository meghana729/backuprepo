package testCaseExecutionUsing_POM_classes;

	import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
	import java.util.Properties;
	import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.genericfileutility.ExcelUtility;
	import com.comcast.crm.genericfileutility.FileUtility;
	import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.genericwebdriverutility.WebdriverUtility;
import com.comcast.crm.objectrepositoryutility.Contact_page;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganization_page;
import com.comcast.crm.objectrepositoryutility.Home_page;
import com.comcast.crm.objectrepositoryutility.Login_page;
import com.comcast.crm.objectrepositoryutility.OrganizationInformation_page;
import com.comcast.crm.objectrepositoryutility.Organization_page;
import com.comcast.crm.objectrepositoryutility.OrgpopupPage;

import genericbaseutility.Baseutility; 

	public class CreateOrganizationTest extends Baseutility {

	@Test(groups="smokeTest")
	public void createorg() throws EncryptedDocumentException, IOException {
		String orgname=elib.getDataFromExcel("sheet1", 1, 2)+jlib.getRandomNumber();
		String shipping_addres=elib.getDataFromExcel("sheet1", 1, 4);
				String lastname=elib.getDataFromExcel("sheet1", 1, 5);
				Home_page hp=new Home_page(driver);
				hp.getOrglink().click();
				Organization_page oop=new Organization_page(driver);
				oop.getCreateneworgbtn().click();
				CreatingNewOrganization_page  cnp=new CreatingNewOrganization_page (driver);
				cnp.createorg(orgname, shipping_addres);

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
	
	@Test(groups="regressionTest")
	public void createorgwithType() throws EncryptedDocumentException, IOException, Throwable {
		String orgname = elib.getDataFromExcel("sheet1", 1, 3) + jlib.getRandomNumber();
		String shipping_addres = elib.getDataFromExcel("sheet1", 1, 4);
		String lastname = elib.getDataFromExcel("sheet1", 1, 5);
		String industry = elib.getDataFromExcel("sheet1", 4, 3);
		//String type = elib.getDataFromExcel("sheet1", 4, 4);
		Home_page hp = new Home_page(driver);
		hp.getOrglink().click();
		 Organization_page op=new  Organization_page(driver);
		 op.getCreateneworgbtn().click();
		CreatingNewOrganization_page cno = new CreatingNewOrganization_page(driver);
		cno.createorg(orgname, shipping_addres, industry);
		Thread.sleep(2000);
		OrganizationInformation_page oip = new OrganizationInformation_page(driver);
		String actorgname = oip.getHeadermsg().getText();
		Thread.sleep(2000);
		if (actorgname.contains(orgname)) {
			System.out.println(orgname + "name is verified ==pass");
		} else {
			System.out.println(orgname + "name is not verified ==fail");
		}
	}
		
	
	
@Test(groups="regressionTest")
public void createorgeithphonenumber() throws InterruptedException, EncryptedDocumentException, IOException {
	String orgname = elib.getDataFromExcel("sheet1", 1, 3) + jlib.getRandomNumber();
	String shipping_addres = elib.getDataFromExcel("sheet1", 1, 4);
	String phonenum=elib.getDataFromExcel("sheet1", 7, 3);
	Home_page hp = new Home_page(driver);
	hp.getOrglink().click();
	 Organization_page op=new  Organization_page(driver);
	 op.getCreateneworgbtn().click();
	CreatingNewOrganization_page cno = new CreatingNewOrganization_page(driver);
	cno.createorgwithphonenumber(orgname, shipping_addres, phonenum);
	Thread.sleep(2000);
	OrganizationInformation_page oip = new OrganizationInformation_page(driver);
	String actphonenumber=oip.getphoneheader().getText();
	if (actphonenumber.contains(phonenum)) {
		System.out.println(phonenum + "name is verified ==pass");
	} else {
		System.out.println(phonenum + "name is not verified ==fail");
	}
	
}
		
	}

