package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganization_page   {
	WebDriver driver;
	public CreatingNewOrganization_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);}
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(xpath="//textarea[@name='ship_street']")
	private WebElement ship;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebtn;
	
	@FindBy(name="industry")
	private WebElement industrybtn;
	@FindBy(id="phone")
	private WebElement phonenumber;
	public WebElement getphonenumber() {
		return phonenumber;
	}

	
	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}


	public WebElement getShip() {
		return ship;
	}


	public WebElement getSavebtn() {
		return savebtn;
	}


	public WebElement getIndustrybtn() {
		return industrybtn;
	}
	
public void createorg(String orgname,String shipping_addres) {
	orgnameEdt.sendKeys(orgname);
	ship.sendKeys(shipping_addres);
	savebtn.click();}

public void createorg(String orgname,String shipping_addres,String industry) {
	orgnameEdt.sendKeys(orgname);
	ship.sendKeys(shipping_addres);
	Select sel=new Select(industrybtn);
	sel.selectByVisibleText(industry);
	
	savebtn.click();}
	

public void createorgwithphonenumber(String orgname,String shipping_addres,String phonenum) {
	orgnameEdt.sendKeys(orgname);
	ship.sendKeys(shipping_addres);
	phonenumber.sendKeys(phonenum);
	savebtn.click();
}

}