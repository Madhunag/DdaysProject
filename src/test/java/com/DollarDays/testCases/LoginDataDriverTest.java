package com.DollarDays.testCases;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.pageObjects.LoginPage;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ConstantsUtils;
import com.DollarDays.utilities.XLUtils;

public class LoginDataDriverTest extends BaseClass 
{

	public WebDriver driver;
	public static Logger logger =LogManager.getLogger("DollarDays"); 
	boolean SignedIn = false;
	
	@BeforeClass
	public void initialize() throws IOException 
	{
		driver =initializeDriver();
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
		logger.info("WebPage is closed");
		System.out.println("WebPage is closed");
	}

	@Test(dataProvider="LoginData",description= "This Testcase checks for logging in functionality using valid and invalid username/password combinations" )
	public void TC_loginDDT(String user,String pwd,String acctName,String validity)
	{
		HomePage hp= new HomePage(driver);
		String currenturl=getPageUrl();
		if (currenturl.equals(baseurl))
		{   
			driver.manage().deleteAllCookies();
			if(SignedIn== true)
			{	
				logger.debug(" Invoke Signout from Homepage by clicking  SigninIcon and then Sign Out option from the DropDown List");
				hp.signOutOfDD();
			}
			logger.debug("Clicking  SigninIcon and then SignIn option from the DropDown List to navigate to Login Page");
			hp.goToLoginPage();
		}
	
		LoginPage lp= new LoginPage(driver);
		logger.debug("Clearing and Entering UserEmail and Password .");
		//System.out.println("Clearing and Entering UserEmail and Password with user " + user + " password " + pwd + ".");
		lp.setEmailTxt(user);	
		lp.setPwdTxt(pwd);
		logger.debug("Clicking Sign in Button.");
		lp.clickSignInBtn();
	
		currenturl=getPageUrl();
		
		if(validity.equals("Valid"))
		{	
			if (currenturl.equals(baseurl))
			{
				SignedIn=checkSignedIn(acctName);//returns true if user has Signed in and false if user has not sign in to webPage.
				Assert.assertEquals(SignedIn,true,"Could not sign in to DollarDays with valid user " + user + " and valid password " + pwd + ". TestCase Failed.");
				logger.info("Signed in to DollarDays successfully with valid user " + user + " and valid password " + pwd + ". Testcase Passed.");
				//System.out.println("Signed  in to DollarDays successfully with valid user " + user + " and valid password " + pwd + ". Testcase Passed.");
				driver.manage().deleteAllCookies();
			}
			else
			{
				SignedIn=false;
				//System.out.println("Could not sign in to DollarDays with valid user " + user + " and valid password " + pwd + ". TestCase Failed.");
				Assert.assertTrue(false,"Could not sign in to DollarDays with valid user " + user + " and valid password " + pwd + ". TestCase Failed.");
			}
			
		}
		else if (validity.equals("Invalid"))
		{	
			if (currenturl.equals(baseurl))
			{				
				SignedIn=checkSignedIn(acctName);//returns true if user has Signed in and false if user has not sign in to webPage.
				//System.out.println("Signed  in to DollarDays successfully with invalid user " + user + " and invalid password " + pwd + ". Testcase failed.");
				Assert.assertEquals(SignedIn,false,"Signed  in to DollarDays successfully with invalid user " + user + " and invalid password " + pwd + ". Testcase failed.");		
			}
			else
			{
				SignedIn=false;
				logger.info("Could not Sign in to DollarDays  with invalid user " + user + " and invalid password " + pwd +". ");
				//System.out.println("Could not Sign in to DollarDays  with invalid user " + user + " and invalid password " + pwd +".");
				
				if(user!="" && pwd!="")
				{   
					logger.info("Checking if Alert message is displayed when incorrect login or password is entered");
					String actualAlert   =  lp.getAlertTxt();
					String expectedAlert =  "Incorrect login and password combination.";
					//if (actualAlert.equals(ConstantsUtils.LOGINERRORALERT)) 
					if (actualAlert.equals(expectedAlert))	
					{
						Assert.assertTrue(true);
						logger.info("Alert message is displayed for incorrect login and password combination. Testcase passed.");
						//System.out.println("Alert message is displayed for incorrect login and password combination. Testcase passed.");
						lp.clickAlertX();
					}
					else
					{
						//System.out.println("Alert message is not displayed for incorrect login and password combination.Testcase failed.  ");
						Assert.assertTrue(false,"Alert message is not displayed for incorrect login and password combination.Testcase failed. ");
					}
				}
				else
				{	
					//System.out.println(" Either Email or Password field or both fields are empty.Couldnt login to website.TestCase passed.");
					logger.info(" Either Email or Password field or both fields are empty.Couldnt login to website.TestCase passed.");
					Assert.assertTrue(true);
				}
				
	
			}
		}
		
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData1() throws IOException
	{	
		logger.info("Fetching Login data credentials from Excel File.");
		String fileName = "LoginData.xlsx";
		String sheetName    = "Sheet1";
		String loginData[][]=XLUtils.getSheetData(fileName,sheetName );
		//String loginData[][]={};
		return loginData;
	}

}
