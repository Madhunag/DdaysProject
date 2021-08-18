package com.DollarDays.listeners;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ScreenshotUtils;


public class Listeners implements ITestListener
{

	public static Logger logger =LogManager.getLogger("DollarDays");// BaseClass.class.getName()
	ExtentTest test;
	WebDriver driver;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	ScreenshotUtils screenshotUtils= new ScreenshotUtils(driver);
	

	public void onTestStart(ITestResult result) {
		String testMethodName        = result.getMethod().getMethodName();
		String testMethodDescription = result.getMethod().getDescription();
		test= extent.createTest(testMethodName);
		extentTest.set(test);
		extentTest.get().log(Status.INFO, testMethodName + " Test is starting. " + testMethodDescription);
		logger.info(testMethodName + " Test is starting. "  + testMethodDescription );
	}

	public void onTestSuccess(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		extentTest.get().log(Status.PASS, testMethodName + " Test Passed");
		logger.info(testMethodName + " Test is passed");
		//WebDriver driver =null;
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		
		} catch(Exception e)
		{
			
			logger.info(e.getMessage());
		}
		try {
			extentTest.get().addScreenCaptureFromPath(screenshotUtils.getScreenShot(testMethodName,driver), result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {		
		String testMethodName =result.getMethod().getMethodName();
		logger.error(testMethodName + " test failed.  " + result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		extentTest.get().log(Status.INFO, testMethodName + " test failed. ");
//		WebDriver driver =null;
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		
		} catch(Exception e)
		{
			
			logger.info(e.getMessage());
		}
		try {
			extentTest.get().addScreenCaptureFromPath(screenshotUtils.getScreenShot(testMethodName,driver), result.getMethod().getMethodName());

			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
	String testMethodName =result.getMethod().getMethodName();
	extentTest.get().log(Status.INFO, testMethodName + " Test was Skipped. " + result.getThrowable());
	extentTest.get().skip(result.getThrowable());
	logger.warn(testMethodName + " Test was skipped. " + result.getThrowable());	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
