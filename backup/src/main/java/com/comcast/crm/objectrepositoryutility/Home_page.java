package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_page {
WebDriver driver;
public Home_page(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(linkText="Organizations")
private WebElement orglink;
public WebElement getOrglink() {
	return orglink;
}

@FindBy(linkText="Products")
private WebElement productslink;
public WebElement getProductslink() {
	return productslink;
}

@FindBy(linkText="Contacts")
private WebElement contactlink;
public WebElement getContactlink() {
	return contactlink;
}


@FindBy(linkText="More")
private WebElement morelink;
public WebElement getMorelink() { 
	return morelink;
}


@FindBy(linkText="Campaigns")
private WebElement campaginlink;
public WebElement getCampaginlink() {
	return campaginlink;
}



@FindBy(linkText="Sign Out")
private WebElement signoutlink;
public WebElement getSignoutlink() {
	return signoutlink;
}

@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement addminimg;
public WebElement getAddminimg() {
	return addminimg;
}

public void navigatetologout() {
	Actions action = new Actions(driver);
	action.moveToElement(addminimg).build().perform();

	driver.findElement(By.linkText("Sign Out")).click();
}

public void navigatetocampaginpage() {
	Actions action = new Actions(driver);
	action.moveToElement(morelink).build().perform();

	driver.findElement(By.linkText("Campaigns")).click();
	
	

	
}

}
