package com.comcast.crm.contacttest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Baseclass {

/*	@BeforeSuite
	public void bs() {
		System.out.println("bs");
	}*/
	@AfterSuite
	public void as() {
		System.out.println("as");
	}
	@BeforeClass
	public void bc() {
		System.out.println("bc");
		
	}
	@BeforeMethod
	public void bm() {
		System.out.println("bm");
	}
	@AfterMethod
	public void am() {
		System.out.println("am");
	}
	@AfterClass
	public void ac() {
	System.out.println("ac");	
	}
	
}
