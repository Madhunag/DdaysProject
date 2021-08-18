package com.DollarDays.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.pageObjects.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{

	public Properties prop;
	public String baseurl ;		
	public String username1	;	
	public String password1	;
	public String accountName1  ;
	public String browser   ;
	public WebDriver driver;
	public static Logger logger =LogManager.getLogger("DollarDays");


	public WebDriver initializeDriver()
	{	
		//Assigning values to variables using properties file
		//ReadPropertiesUtils readProp= new ReadPropertiesUtils();
		browser     	= ReadPropertiesUtils.getPropValue("browser");	
		baseurl 		= ReadPropertiesUtils.getPropValue("baseurl");
		username1		= ReadPropertiesUtils.getPropValue("username1");
		password1		= ReadPropertiesUtils.getPropValue("password1");
		accountName1 	= ReadPropertiesUtils.getPropValue("accountName1");	
		
		if (browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			System.out.println(" chrome setup");
			driver = new ChromeDriver();
			logger.info("Chrome Driver is initialized");
		}
		else if (browser.equalsIgnoreCase("Firefox"))
		{
		    WebDriverManager.firefoxdriver().setup();
		    System.out.println("setup firefox");
		    driver = new FirefoxDriver();
			logger.info("Firefox Driver is initialized");
		}
		else if (browser.equalsIgnoreCase("Edge"))
		{
		    WebDriverManager.edgedriver().setup();
		    System.out.println("Edge");
		    driver = new EdgeDriver();		
			logger.info("Internet Explorer Driver is initialized");
		}
		
		logger.info("Opening the " + browser + " browser and Navigating to Home page of Dollardays");
		driver.get(baseurl);
		logger.info("Navigated to Homepage of  Dollardays ");
		logger.info("Deleting cookies");
		driver.manage().deleteAllCookies();
		logger.info("Maximize browser window ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
		
	}	
	
	
	public void invokeLogintoDD(String user,String pwd)
	{
		logger.info("Logging into Dollardays Website .");
		HomePage hp =new HomePage(driver); 
		logger.debug("Navigating to Login Page.");
		hp.clickIconSignin();
		hp.clickSignInLink();
		driver.manage().deleteAllCookies();
		LoginPage lp = new LoginPage(driver);
		logger.debug("Clearing and Entering UserEmail and Password");
		lp.setEmailTxt(user);
		lp.setPwdTxt(pwd);
		logger.debug("Clicking Sign in Button");
		lp.clickSignInBtn();
	}
	
	public boolean checkSignedIn(String accountNameValue)
	{
		logger.info("Checking if user has signed in to dollardays WebPage");
		HomePage hp =new HomePage(driver);
		if (!driver.getCurrentUrl().equals(baseurl))
		{
			driver.get(baseurl);
		}
		if (hp.getTextAccountNameIcon().equals(accountNameValue))
		{
			logger.info("User is Signed in");
			return true;
		}
		else
		{
			logger.info("User is not Signed in");
			return false;
		}
	}
	public void navigateToHomePage()
	{
		logger.debug("Refreshing HomePage /Navigating to HomePage");
		driver.get(baseurl);
	}
	
	public void invokeLogoutofDD()
	{
		logger.info(" Invoke Signout from Homepage");
		if (!driver.getCurrentUrl().equals(baseurl))
		{
		logger.debug("Navigating to HomePage to Signout");
		driver.get(baseurl);
		}
		HomePage hp =new HomePage(driver);
		logger.debug("Clicking  UserName Icon in  the home page to view the DropDown List ");
		hp.clickAccountNameIcon();
		hp.clickSignOutLink();
		driver.manage().deleteAllCookies();
	}	
	
	public void navigateToLoginPage()	{   
		logger.info(" Navigating to Sign in page");
		HomePage hp =new HomePage(driver);
		logger.debug("Clicking  SigninIcon in  the home page to view the DropDown List ");
		hp.clickIconSignin();
	logger.info("Clicking SignIn option from the DropDown List in the HomePage");
		hp.clickSignInLink();
		logger.debug("Navigated to Login Page");
		driver.manage().deleteAllCookies();
	}

	
	//Get the title of homepage
	public String getPageTitle()
	{
		return driver.getTitle();
	}	
	
	
	//Get the title of homepage
	public String getPageUrl()
	{
		return driver.getCurrentUrl();
	}	
	
}
