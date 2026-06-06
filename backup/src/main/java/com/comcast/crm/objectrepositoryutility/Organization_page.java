package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organization_page {
	WebDriver driver;
	public Organization_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createneworgbtn;
	public WebElement getCreateneworgbtn() {
		return createneworgbtn;
	}
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchdropdown;
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchdropdown() {
		return searchdropdown;
	}
	
	@FindBy(name="submit")
	private WebElement searchbtn;
	public WebElement getSearchbtn() {
		return searchbtn;
	}
	
	
}
