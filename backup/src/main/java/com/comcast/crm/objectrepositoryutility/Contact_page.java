package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.genericwebdriverutility.JavaUtility;
import com.comcast.crm.genericwebdriverutility.WebdriverUtility;

public class Contact_page extends JavaUtility {
	WebDriver driver;
	public Contact_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);}
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createContactLink;
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;

	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebtn;
	public WebElement getCreateContactLink() {
		return createContactLink;
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	@FindBy(id="jscal_field_support_start_date")
	private WebElement supportStartdateDD;
	@FindBy(id="jscal_field_support_end_date")
	private WebElement supportEndDateDD;
	
	
	public WebElement getSupportStartdateDD() {
		return supportStartdateDD;
	}

	public WebElement getSupportEndDateDD() {
		return supportEndDateDD;
	}
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	public WebElement getContactlink() {
		return contactlink;
	}
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement contactOrgSearch;
	public WebElement getcontactOrgSearch() {
		return contactOrgSearch;
	}
	public void createcontactwithoutsave(String lastname) {
		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
	}
	public void createcontact(String lastname) {
		getCreateContactLink().click();
	lastnameEdt.sendKeys(lastname);
	savebtn.click();
	}
	
	
	public void createcontactwithsuportdata(String lastname,String startdate,String enddate ) throws InterruptedException {

		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
	getSupportStartdateDD().clear();
	startdate=getSystemDateyyyyddmm();
	getSupportStartdateDD().sendKeys(startdate);
	getSupportEndDateDD().clear();
	enddate=getRequiredDateyyyyddmm(30);
	getSupportEndDateDD().sendKeys(enddate);
	savebtn.click();
	Thread.sleep(3000);
	}
	//create contact with organisation
	


public  void createcontactwithorg(String lastname,String orgname) throws InterruptedException {
	getCreateContactLink().click();
	
	lastnameEdt.sendKeys(lastname);
	Thread.sleep(3000);
	getcontactOrgSearch().click();
	WebdriverUtility wlib= new WebdriverUtility();
	wlib.switchToTabOnTitle(driver, "module=Acconts");
	//switch to child window

	OrgpopupPage opop=new OrgpopupPage(driver);
	opop.getSearchbtn().sendKeys(orgname);
	opop.getSearchnow().click();
	opop.verifyorg(orgname);
wlib.switchToTabOnTitle(driver, "module=Contacts");
getSavebtn().click();}
}

	

