package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericbaseutility.Base2;

public class Invoice extends Base2{
@Test
public void createinvoiceTest() {
	System.out.println("execute createinvoicetest");
	String acttitle=driver.getTitle();
	Assert.assertEquals(acttitle,"login");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
	
}

@Test
public void createinvoicewithcontactTest() {
	System.out.println("execute createinvoicewithcontacttest");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
	
}
}
