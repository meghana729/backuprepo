package com.comcast.crm.genericfileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
public String getDataFromJsonfile(String key) throws IOException, ParseException {
	FileReader filer=new FileReader("./configAppData/Ccommondatajson.JSON");
	
	JSONParser parser=new JSONParser();
Object obj=	parser.parse(filer);
JSONObject map=(JSONObject)obj;
String data=(String)map.get(key);
return data;
	
}
}
