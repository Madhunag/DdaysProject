package com.DollarDays.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

//This utility class is used to read the values in Properties file
public class ReadPropertiesUtils
{
	public static String getPropValue(String keyStr)
	{	
		String value ="";
		Properties pro = new Properties();
		try 
		{
			File src = new File("./src/test/resources/config/data.properties"); 
			FileInputStream fis = new FileInputStream(src);
			pro.load(fis);
			value =pro.getProperty(keyStr);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception occured while fetching data from data.properties " + e.getMessage());
		}
		
		return value;
		
	}//getPropValue
	
} //Class
