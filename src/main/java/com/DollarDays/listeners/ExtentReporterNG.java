package com.DollarDays.listeners;


import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="extentReportDD-"+timeStamp+".html";
		
		String path = System.getProperty("user.dir")+ "\\ExtentReports\\" + repName;
		//String path =System.getProperty("user.dir")+"\\extend-reports\\extendReportDD.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gayathri");
		return extent;
		
	}

}
