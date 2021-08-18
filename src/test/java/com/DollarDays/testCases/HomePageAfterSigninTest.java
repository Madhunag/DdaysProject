package com.DollarDays.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ConstantsUtils;
import com.DollarDays.utilities.XLUtils;


public class HomePageAfterSigninTest extends BaseClass
{
	public WebDriver driver;
	public static Logger logger =LogManager.getLogger("DollarDays"); 
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
			logger.info("WebPage is closed.");
			System.out.println("WebPage is closed.");
	}
	
	@Test(priority=0, description="This testcase verifies user is able to login from home page with valid credentials.")
	public void TC_Login()
	{
//		HomePage hp= new HomePage(driver);
		navigateToHomePage();
		invokeLogintoDD(username1,password1); //username1,password1,accountName1 are fetched from data.properties file in the BaseClass
		boolean SignedIn=checkSignedIn(accountName1);
		Assert.assertEquals(SignedIn, true,"Could not Login to Dollardays.Testcase failed.");
		logger.info("Logged in successfully to dollardays WebPage.");
	}

	@Test(priority=1,dependsOnMethods = {"TC_Login"}, description="This testcase verifies the Home Page Title is correct after user signs in.")
	public void TC_verifyUserHomePageTitle()
	{  		
//		HomePage hp= new HomePage(driver);
		String actualUserHomeTitle   = hp.getHomePageTitle();
		logger.debug("HomePageTitle is fetched.");
		Assert.assertEquals(actualUserHomeTitle,ConstantsUtils.HOMEPAGETITLE,"UserHomePage title is not as expected.Title Verification failed.");
		logger.info("Verified UserHomePage title successfully.");
	}
	
	@Test(priority=2,dependsOnMethods = {"TC_Login"}, description="This testcase verifies the user dropdown list displays expected values.")
	public void TC_CheckUserDropDownList()
	{ 
//		HomePage hp= new HomePage(driver);
		String UserAccountName=hp.getTextAccountNameIcon();
		logger.debug("Clicking on User Name Icon to view the dropdownlist.");
		hp.clickAccountNameIcon();
		boolean wishlistChk=false;
		logger.debug("Fetching the user account dropdownlist.");
		List<String> actList= hp.getSignInUserDropDownList();
		logger.debug("Checking if MyWishlist is present in the dropdown list.");
		if (actList.contains("My Wishlist"))
		{
			wishlistChk=true;
		}
//		logger.debug("Clicking on User Name Icon again to hide the dropdownlist.");
//		hp.clickAccountNameIcon();
		logger.debug("Fetching Expected Values for User dropdown list from ConstantsUtils File.");
	    String  strUserDDList = ConstantsUtils.USERDDLIST;
	    String  strUserDDListWithWish = ConstantsUtils.USERDDLISTWISH;
		logger.debug("Converting string to array.");
		String[] strArr= {};
	    if (wishlistChk == true)
	    {
	    	strArr = strUserDDListWithWish.split(",");
	    }
	    else
	    {
	    	strArr = strUserDDList.split(",");
	    }
	    logger.debug("Converting Array to List. ");
		//List <String> expList = Arrays.asList(strArr);
		List<String> expList = new ArrayList<String>();
		expList.add(UserAccountName);//Adding Accountusername as the first element of the list
		for (String s : strArr )
		{
			expList.add(s);
		}
		logger.debug("Asserting Actual and Expected value of User DropDown List are matching.");
		Assert.assertEquals(actList, expList,"Verification of  User DropDown List failed. ");
	    logger.info("Verified User Dropdown List successfully.");
	}

	
	@Test(dataProvider="getuserlinks" , priority=3,dependsOnMethods = {"TC_Login"}, description="This testcase verifies the the navigation of links displayed in the user list .")
	public void TC_verifyUserDropDownUrl(String linkText) throws InterruptedException
	{ 	
		
//		HomePage hp = new HomePage(driver);
		navigateToHomePage();
        String actualUrl=null;
		logger.debug("Clicking on User Name Icon to view the dropdown list.");
		hp.clickAccountNameIcon();
    	
        switch(linkText)
    	{ 
	      case "Accounts":  		 
	    	  logger.debug("Clicking on Accounts Link in the user dropdown list.");
	    	  hp.clickAccountsLink();
	    	  actualUrl=getPageUrl();
		      Assert.assertEquals(actualUrl, ConstantsUtils.ACCOUNTSURL,"Accounts link url verification failed.");
		      logger.info("Accounts link url verified successfully");
	      break;
	    		
	      case "Order History":
	    	   logger.debug("Clicking on Order History Link in the user dropdown list");
	    	   hp.clickOrderHistoryLink();
			   actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.ORDERHISTORYURL,"Order History link url verification failed.");
			   logger.info("Order History link url verified successfully.");
	      break;
	    	  
	      case "My Wishlist":
	    	     logger.debug("Clicking on My Wishlist Link in the user dropdown list");
	    	     hp.clickWishlistLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.WISHLISTURL,"My Wishlist link url verification failed.");
				 logger.info("My Wishlist link url verified successfully.");
			break;
			
	        case "Favorites":
	    	     logger.debug("Clicking on Favorites Link in the user dropdown list.");
	        	 hp.clickFavoritesLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.FAVORITESURL,"Favorites link url verification failed.");
				 logger.info("Favorites link url verified successfully.");
			break;		
			
	        case "Update Profile":
	    	     logger.debug("Clicking on Update Profile Link in the user dropdown list.");
	        	 hp.clickUpdateProfileLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.PROFILEURL,"Update Profile link url verification failed.");
				 logger.info("Update Profile link url verified successfully.");
			break;			
			
	        case "Address Book":
	    	     logger.debug("Clicking on Address Book Link in the user dropdown list.");
		    	 hp.clickAddressBookLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.ADDRESSBOOKURL,"Address Book link url verification failed.");
				 logger.info("Address Book link url verified successfully.");
			break;	
			
	        case "Wallet":
	    	     logger.debug("Clicking on Wallet Link in the user dropdown list.");
		    	 hp.clickWalletLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.WALLETURL,"Wallet link url verification failed.");
				 logger.info("Wallet link url verified successfully.");
			break;	
			
	        case "Tax Exempt":
	    	     logger.debug("Clicking on Tax Exempt Link in the user dropdown list.");
		    	 hp.clickTaxExemptLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.TAXEXEMPTURL,"Tax Exempt link url verification failed.");
				 logger.info("Tax Exempt link url verified successfully.");		    	 
			break;	
			
	        case "Request a Quote":
	    	     logger.debug("Clicking on Request a Quote Link in the user dropdown list.");
		    	 hp.clickRequestQuoteLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.REQUESTQUOTEURL,"Request a Quote link url verification failed.");
				 logger.info("Request a Quote link url verified successfully.");	    	 
			break;	
			
	        case "Contact Us":
	    	     logger.debug("Clicking on Contact Us Link in the user dropdown list.");
	        	 hp.clickContactUsLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.CONTACTUSURL,"Contact Us link url verification failed.");
				 logger.info("Contact Us link url verified successfully.");		    	 
			break;	
			
	        case "Sign Out":
	    	     logger.debug("Clicking on SignOut Link in the user dropdown list.");
	        	 hp.clickSignOutLink();
	        	 String strAccountName =hp.getTextAccountNameIcon();
				 if (strAccountName.equals("Sign in"))
				   {
						Assert.assertTrue(true);	
						logger.info("Sign Out link navigation verified successfully.");
				   }
				 else 
				   {
						Assert.assertTrue(false,"Sign Out link navigation verification failed .   ");
				   }		    	 
			break;			
    	}
  
    }

	@DataProvider(name="getuserlinks")
	public String[] getuserlinks() 
	{	
		//HomePage hp = new HomePage(driver);
		navigateToHomePage();
		logger.debug("Clicking on User Name Icon to view the dropdown list.");
		hp.clickAccountNameIcon();
		logger.debug("Fetching the user dropdown list values.");
		String arr[] = hp.getSignInUserDropDownLinksArray();
		logger.debug("Clicking on User Name Icon to hide the dropdown list.");
		hp.clickAccountNameIcon();
		return arr;
	}	
	
}

