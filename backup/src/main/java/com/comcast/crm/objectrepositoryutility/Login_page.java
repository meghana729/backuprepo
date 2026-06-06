package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.genericwebdriverutility.WebdriverUtility;
public class Login_page  extends WebdriverUtility{
//rule 1 create a sperate java class
//rule 2 object creation
	public WebDriver driver;
	public Login_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	

    @FindBy(name="user_password")
	private  WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	public WebElement getPasswordEdt() {
		return passwordEdt;
	}
	
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	} 
	

	//rule 3:object initialization
	//--done in testscript
	//rule 5-provide action
	
	public void loginToapp(String url,String username,String password) {
		waitforpagetoload(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
}
