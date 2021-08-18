package com.DollarDays.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//This utility class is facilitates capturing screenshots 
public class ScreenshotUtils
{
	WebDriver driver;
	public ScreenshotUtils(WebDriver driver) //Constructor
	{
		this.driver = driver;
	}

	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		
		String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source  =ts.getScreenshotAs(OutputType.FILE);
		String destinationFilename = System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+ datetime + ".png";
		File destination=new File(destinationFilename);
		//Copy file at destination
        FileUtils.copyFile(source, destination);
		return destinationFilename;
	}
	
}
