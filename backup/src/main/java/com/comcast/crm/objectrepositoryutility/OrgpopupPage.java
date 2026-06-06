package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgpopupPage {
	WebDriver driver;
	public OrgpopupPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		}
	
	
	@FindBy(id="search_txt")
	private WebElement searchbtn;

	public WebElement getSearchbtn() {
		return searchbtn;
	}
	
	@FindBy(xpath="//select[@name='search_field']")
	private WebElement optionsdd;

	public WebElement getOptionsdd() {
		return optionsdd;
	}
	@FindBy(name="search")
	private WebElement searchnow;
	public WebElement getSearchnow() {
		return searchnow;
	}
	public void verifyorg(String orgname) {
		
	driver.findElement(By.xpath(("//a[text()='"+orgname+"']"))).click();
	}


	
}
