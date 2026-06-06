 package com.comcast.crm.genericwebdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {
	public void waitforpagetoload(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
public void switchToTabOnUrl(WebDriver driver,String partialurl) {
	Set<String> set =driver.getWindowHandles();
	Iterator <String> it=set.iterator();
	while(it.hasNext()) {
		String windowid=it.next();
		driver.switchTo().window(windowid);
		String acturl=driver.getCurrentUrl();
		if(acturl.contains(partialurl)) {
			break;}
	}
	}
public void switchToTabOnTitle(WebDriver driver,String partialtitle) {
	Set<String> set =driver.getWindowHandles();
	Iterator <String> it=set.iterator();
	while(it.hasNext()) {
		String windowid=it.next();
		driver.switchTo().window(windowid);
		String acturl=driver.getTitle();
		if(acturl.contains(partialtitle)) {
			break;}
	}
	}

public void SwitchToFrame(WebDriver driver,int index){
	driver.switchTo().frame(index);}

public void SwitchToFrame(WebDriver driver,String nameid){
	driver.switchTo().frame(nameid);}

public void SwitchToFrame(WebDriver driver,WebElement element){
	driver.switchTo().frame(element);}

public void SwitchToAlertandAccept(WebDriver driver){  
	driver.switchTo().alert().accept();}


public void SwitchToAlertandCancel(WebDriver driver){  
	driver.switchTo().alert().dismiss();
	}

public void select(WebElement element,String text) {
	Select sel=new Select(element);
	sel.selectByVisibleText(text);
}

public void select(WebElement element,int index) {
	Select sel=new Select(element);
	sel.selectByIndex(index);
}

public void mousemoveonelement(WebDriver driver,WebElement element) {
	Actions act=new Actions(driver);
	act.moveToElement(element).perform();
	
}
public void doubleclick(WebDriver driver,WebElement element) {
	Actions act=new Actions(driver);
	act.doubleClick(element).perform();
	
}










}
	
	


