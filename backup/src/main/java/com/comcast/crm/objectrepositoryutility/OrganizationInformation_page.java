package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformation_page {
	WebDriver driver;
	public OrganizationInformation_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headermsg;
	
	public WebElement getHeadermsg() {
		return headermsg;
	}
	@FindBy(id="dtlview_Phone")
	private WebElement phoneheader;
	
	public WebElement getphoneheader() {
		return phoneheader;
	}
	
	

}
