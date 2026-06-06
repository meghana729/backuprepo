package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class PracticeRetryAnalyser {
	@Test(retryAnalyzer=ListnerUtility.RetryListnerImplementationClass.class)
	public void activatesim() {
		System.out.println("execut");
		Assert.assertEquals("", "login");
		System.out.println("step-1");
		System.out.println("step_2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
