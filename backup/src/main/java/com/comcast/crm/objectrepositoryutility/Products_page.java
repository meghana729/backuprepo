package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products_page {
@FindBy(xpath="//img[@alt='Create Product...'")
private WebElement createproductinmgbtn;
public WebElement getcreateproductinmgbtn() {
	return createproductinmgbtn;
}

}
