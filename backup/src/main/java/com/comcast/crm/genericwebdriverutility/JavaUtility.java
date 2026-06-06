package com.comcast.crm.genericwebdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
public int getRandomNumber(){
	Random random=new Random();
	int randomNumber=random.nextInt(10000);
	
	return randomNumber; 
}    

public String getSystemDateyyyyddmm() {
	Date dateobj=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(dateobj);
	return date;
}
public String getRequiredDateyyyyddmm(int days) {
	Date dateobj=new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	String date1=sim.format(dateobj);
	Calendar cal=sim.getCalendar();
    cal.add(Calendar.DAY_OF_MONTH,days);
	String reqDate=sim.format(cal.getTime());
	//System.out.println("reqdate"+reqDate);
	return reqDate;
	
}
}
