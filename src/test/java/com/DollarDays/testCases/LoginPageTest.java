package com.DollarDays.testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.pageObjects.LoginPage;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ConstantsUtils;

public class LoginPageTest extends BaseClass 
{

	public WebDriver driver;
	public static Logger logger =LogManager.getLogger("DollarDays"); 
	boolean SignedIn=false;
	LoginPage lp;
	HomePage hp;

	@BeforeClass
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		hp= new HomePage(driver);

	}

	@AfterClass
	public void teardown()
	{
		driver.close();
		logger.info("WebPage is closed");
		System.out.println("WebPage is closed");
	}
	

	
	@Test(priority=0, description=" TestCase verifies LoginPage Title is displayed correctly.")
	public void TC_verifyLoginPageTitle() throws InterruptedException
	{

		logger.debug("Clicking  SigninIcon and then SignIn option from the DropDown List to navigate to Login Page");
		hp.goToLoginPage();
		if (getPageUrl().equals(ConstantsUtils.SIGNINURL)) //Checking if current in Signin Page
		{
			String actualLoginTitle=getPageTitle();
			String expectedLoginPageTitle =ConstantsUtils.LOGINPAGETITLE;
			Assert.assertEquals(actualLoginTitle, expectedLoginPageTitle,"LoginPage title is not as expected.Title Verification failed.");
			logger.info("Verified loginpage title successfully");
		}
		else
		{
			logger.info("Did not navigate to Login Page correctly");
			Assert.assertTrue(false, "Did not navigate to Login Page correctly");
		}
	}
	
	@Test(priority=1, description=" TestCase verifies Email Address and Password fields are mandatory in LoginPage.")
	public void TC_verifyLoginPageMandatoryfields() throws InterruptedException 
	{
		lp= new LoginPage(driver);
		String chkEmail = lp.checkRequiredEmail(); //returns true if it is a mandatory field else returns null
		String chkPwd   = lp.checkRequiredPwd();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(chkEmail,"true","Email Address field is not marked as mandatory field. ");
		softAssert.assertEquals(chkPwd,"true","Password is not marked as mandatory field.  ");
		softAssert.assertAll();

	}	
	
	@Test(priority=2, description=" TestCase verifies the navigation of Forgot password link .")
	public void TC_verifyForgotEmailPasswordLink() throws InterruptedException
	{
		logger.debug("Clicking  the link - Forgot your Email or Password in Login Page");
		lp.clickForgotLink();
		String actUrl=getPageUrl();
		Assert.assertEquals(actUrl, ConstantsUtils.FORGOT_EMAIL_PWD_URL,"ForgotEmailorPassword Link verification failed.");
		logger.info("Verified Forgot Your Email or Password Link successfully");
		
	}	
	
	
	@Test(priority=3, description=" TestCase verifies functionality of CreateAccount button .")
	public void TC_verifyCreateAccountButton() throws InterruptedException
	{
		driver.get(ConstantsUtils.SIGNINURL);
		logger.debug("Clicking  the Create Account button in Login Page");
		lp.clickCreateAcctBtn();
		String actUrl=getPageUrl();
		Assert.assertEquals(actUrl, ConstantsUtils.CREATEACCOUNTURL," Click CreateAccount button  verification failed.");
		logger.info("Verified the functionality of CreateAccount button ");
	}	
	
	@Test(priority=4, description= "Testcase checks if the user is able to login successfully with valid username/password combination" )
	public void TC_logintoDD() throws InterruptedException
	{		
		navigateToHomePage();
		driver.manage().deleteAllCookies();
		logger.debug("Clicking  SigninIcon and then SignIn option from the DropDown List to navigate to Login Page");
		hp.goToLoginPage();
		//username1,password1,accountName1 are fetched from data.properties file in the BaseClass
		logger.info("Entering valid credentials and clicking Signin Button.");
		lp.setEmailTxt(username1);
		lp.setPwdTxt(password1);
		lp.clickSignInBtn();
		SignedIn=checkSignedIn(accountName1);
		Assert.assertEquals(SignedIn, true,"Could not Login to Dollardays with valid credentials.  Testcase failed");
		logger.info("Signed  in to DollarDays successfully.");				
	}
	

}
