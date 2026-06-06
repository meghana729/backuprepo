package com.comcast.crm.genericfileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
public String getDataFromExcel(String sheetname,int rownum,int celnum) throws EncryptedDocumentException, IOException {
	FileInputStream fis=new FileInputStream("./testdata\\commondata.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	String data= wb.getSheet(sheetname).getRow(rownum).getCell(celnum).getStringCellValue();
	return data;
}

public int getRowcount(String sheetname) throws Throwable {
	FileInputStream fis=new FileInputStream("./testdata\\practice data1.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	int rowcount=wb.getSheet(sheetname).getLastRowNum();
	return rowcount;
}
public void setDataIntoExcel(String sheetname,int rownum,int celnum,String data) throws Throwable {
	
	FileInputStream fis=new FileInputStream("./testdata\\practice data1.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	wb.getSheet(sheetname).getRow(rownum).createCell(celnum);
	FileOutputStream fos=new FileOutputStream("./testdata\\practice data1.xlsx");
	wb.write(fos);
	wb.close();//bcuz in backend in excel sheet side one object will always be opened not in java pgm side and if you try to open excel manually excel sheet will get crashed
	
}
}
