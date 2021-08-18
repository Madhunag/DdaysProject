package com.DollarDays.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.pageObjects.MyAccountOverviewPage;
import com.DollarDays.pageObjects.MyProfilePage;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ConstantsUtils;
import com.DollarDays.utilities.ReadPropertiesUtils;
import com.DollarDays.utilities.XLUtils;

public class MyProfilePageTest extends BaseClass
{
	public WebDriver driver;
	public static Logger logger = LogManager.getLogger("DollarDays"); 
	String username2 		    = ReadPropertiesUtils.getPropValue("username2");
	String password2 		    = ReadPropertiesUtils.getPropValue("password2");
	String accountName2		    = ReadPropertiesUtils.getPropValue("accountName2");
	
	@BeforeClass
	public void initialize() throws IOException , InterruptedException
	{
		
		driver =initializeDriver();
		invokeLogintoDD(username2,password2); 
		acceptCookiesPopup();
		gotoMyProfilePage();
		
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
		logger.info("WebPage is closed");
		System.out.println("WebPage is closed");
	}
	
	public void acceptCookiesPopup() throws InterruptedException
	{
		Thread.sleep(1000);
		MyProfilePage myProfile = new MyProfilePage(driver);	
		myProfile.clickCookiesPopupOkayBtn();
		
	}
	
	public void gotoMyProfilePage()
	{
		logger.debug("Clicking  SigninIcon and then Update Profile option from the User DropDown List of HomePage for navigating to  My Profile Page");
		HomePage hp =new HomePage(driver); 
		hp.clickAccountNameIcon();
		hp.clickUpdateProfileLink();
	}
	
	//Returns the String value before x - To extract Primary Phone number from the Primary # field displayed in Account Overview Page
	public String getOnlyPrimaryNum (String fullPhNum)
	{	
		if (fullPhNum.contains("x"))
		{
			String arr[]=fullPhNum.split("x");
			String Prim=arr[0];
			return Prim;
		}
		else 
		{
			return fullPhNum;
		}
	}
	
	//Returns the String value after x - To extract extension number from the Primary # field displayed in Account Overview Page
	public String getOnlyExtNum (String fullPhNum)
	{
		String Ext="";
		if (fullPhNum.contains("x"))
		{
			String arr[]=fullPhNum.split("x");
		    Ext=arr[1];
		}
		return Ext;
	}
	
	
	@Test(priority=0, description="This testcase verifies the title of the page My Profile")
	public void TC_verifyMyProfilePageTitle()
	{   
		if (getPageUrl().equals(ConstantsUtils.PROFILEURL)) //Checking if currently in Profile Page
		{
			String actualTitle=getPageTitle();
			String expectedProfilePageTitle =ConstantsUtils.MYPROFILETITLE;
			Assert.assertEquals(actualTitle, expectedProfilePageTitle,"ProfilePage title is not as expected.Title Verification failed.");
			logger.info("Verified Profilepage title successfully");
		}
		else
		{
			Assert.assertTrue(false, "Did not navigate to Profile Page correctly");
		}
	}
	
	@Test(priority=1 , description="This testcase verifies the heading of My Profile page is as expected.")
	public void TC_verifyMyProfileHeading()
	{   
		MyProfilePage myProfile = new MyProfilePage(driver);	
		String actualHeading  =  myProfile.getTextMyProfileHeading();
		Assert.assertEquals(actualHeading, "MY PROFILE");
		logger.info("MyProfilePage Heading is validated successfully");
	}
	
	@Test(dataProvider="getuserlinks" , priority=2 , description="This testcase verifies the navigation of links displayed in the user dropdown list .")
	public void TC_verifyUserDropDownUrl(String linkText) throws InterruptedException
	{ 	

		String currentUrl=driver.getCurrentUrl();
		if (!currentUrl.equals(ConstantsUtils.PROFILEURL))
		{
			System.out.println("Not in MyProfilePage.Redirect to the MyProfilePage.");
			driver.get(ConstantsUtils.PROFILEURL);
		}
		MyProfilePage myProfile = new MyProfilePage(driver);
        String actualUrl=null;
        myProfile.clickAccountNameIcon();
    	
        switch(linkText)
    	{ 
	      case "Accounts":  		 
	    	  logger.debug("Clicking on Accounts Link in the user dropdown list.");
	    	  myProfile.clickDropDownAccountsLink();
	    	  actualUrl=getPageUrl();
	    	  Assert.assertEquals(actualUrl, ConstantsUtils.ACCOUNTSURL,"Accounts link url verification failed.");
			  logger.info("Accounts link url verified successfully");
	      break;
	    		
	      case "Order History":
	    	   logger.debug("Clicking on Order History Link in the user dropdown list");
	    	   myProfile.clickDropDownOrderHistoryLink();
			   actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.ORDERHISTORYURL,"Order History link url verification failed.");
			   logger.info("Order History link url verified successfully.");
	      break;
	    	  
	      case "My Wishlist":
	    	   logger.debug("Clicking on My Wishlist Link in the user dropdown list");
	    	   myProfile.clickDropDownWishlistLink();
	    	   actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.WISHLISTURL,"My Wishlist link url verification failed.");
			   logger.info("My Wishlist link url verified successfully.");
		  break;
			
	      case "Favorites":
	    	   logger.debug("Clicking on Favorites Link in the user dropdown list."); 
	           myProfile.clickDropDownFavoritesLink();
	           actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.FAVORITESURL,"Favorites link url verification failed.");
			   logger.info("Favorites link url verified successfully.");
		  break;		
			
	      case "Update Profile":
	    	   logger.debug("Clicking on Update Profile Link in the user dropdown list.");
	           myProfile.clickDropDownUpdateProfileLink();
	           actualUrl=getPageUrl();
	     	   Assert.assertEquals(actualUrl, ConstantsUtils.PROFILEURL,"Update Profile link url verification failed.");
			   logger.info("Update Profile link url verified successfully.");
		  break;			
			
	      case "Address Book":
	    	   logger.debug("Clicking on Address Book Link in the user dropdown list.");
	           myProfile.clickDropDownAddressBookLink();
	           actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.ADDRESSBOOKURL,"Address Book link url verification failed.");
			   logger.info("Address Book link url verified successfully.");
		  break;	
			
	      case "Wallet":
	    	   logger.debug("Clicking on Wallet Link in the user dropdown list.");
	           myProfile.clickDropDownWalletLink();
	           actualUrl=getPageUrl();
			   Assert.assertEquals(actualUrl, ConstantsUtils.WALLETURL,"Wallet link url verification failed.");
			   logger.info("Wallet link url verified successfully.");
		  break;	
			
	      case "Tax Exempt":
	    	  logger.debug("Clicking on Tax Exempt Link in the user dropdown list.");
	          myProfile.clickDropDownTaxExemptLink();
		      actualUrl=getPageUrl();
			  Assert.assertEquals(actualUrl, ConstantsUtils.TAXEXEMPTURL,"Tax Exempt link url verification failed.");
			  logger.info("Tax Exempt link url verified successfully.");		    	 
			break;	
			
	        case "Request a Quote":
	        	logger.debug("Clicking on Request a Quote Link in the user dropdown list.");
	        	myProfile.clickDropDownRequestQuoteLink();
		    	actualUrl=getPageUrl();
				Assert.assertEquals(actualUrl, ConstantsUtils.REQUESTQUOTEURL,"Request a Quote link url verification failed.");
				logger.info("Request a Quote link url verified successfully.");	    	 
			break;	
			
	        case "Contact Us":
	        	 logger.debug("Clicking on Contact Us Link in the user dropdown list."); 
	        	 myProfile.clickDropDownContactUsLink();
		    	 actualUrl=getPageUrl();
				 Assert.assertEquals(actualUrl, ConstantsUtils.CONTACTUSURL,"Contact Us link url verification failed.");
				 logger.info("Contact Us link url verified successfully.");		    	 
			break;	
			
	        case "Sign Out":
	        	 logger.debug("Clicking on SignOut Link in the user dropdown list.");
		    	 myProfile.clickDropDownSignOutLink();
		    	 HomePage hp= new HomePage(driver);
	        	 String strAccountName =hp.getTextAccountNameIcon();
				 if (strAccountName.equals("Sign in"))
				   {
						Assert.assertTrue(true);	
						logger.info("Sign Out link navigation verified successfully.");
						logger.debug("Signing in again.");
						invokeLogintoDD(username2,password2);
						acceptCookiesPopup();
						gotoMyProfilePage();
				   }
				 else 
				   {
						Assert.assertTrue(false,"Sign Out link verification failed .   ");
				   }			    	
			break;	
			
    	}
  
    }

	@DataProvider(name="getuserlinks")
	public String[] getuserlinks() 
	{	
		System.out.println("In the dataprovider");
		MyProfilePage myProfile = new MyProfilePage(driver);
		logger.debug("Clicking on User Name Icon to view the dropdown list.");
		myProfile.clickAccountNameIcon();
		logger.debug("Fetching the user dropdown list values.");
		String arr[] = myProfile.getUserDropDownLinksArray();
		logger.debug("Clicking on User Name Icon to hide the dropdown list.");
		myProfile.clickAccountNameIcon();
		return arr;		
	}	

	
	@Test(priority=3, description="This testcase verifies the user name displayed in the left panel")
	public void TC_verifyLeftPanelUserName()
	{   
		MyProfilePage myProfile = new MyProfilePage(driver);	
        String firstName = myProfile.getValueFirstName();
        String lastName  = myProfile.getValueLastName();
	    String userName  = firstName + " " + lastName ;
	    String accountname=myProfile.getTextleftPanelAccountName();
		Assert.assertEquals(accountname, userName);
		logger.info("Verified Account User Name successfully");
	}
	
	@Test(priority=4, description="This testcase verifies the list of links displayed in left panel")
	public void TC_verifyLeftPanelLinks()
	{   
		MyProfilePage myProfile = new MyProfilePage(driver);	
		boolean wishlistChk=false;
		logger.debug("Fetching the Left Panel Link List.");
		List<String> actList= myProfile.getleftPanelLinksList();
		logger.debug("Checking if MyWishlist is present in Left Panel Link List.");
		if (actList.contains("My Wishlist")) //Check if wishlist is present as one of the left panel links
		{
			wishlistChk=true;
		}
		logger.debug("Fetching Expected Values for User dropdown list from ConstantsUtils File.");
	    String  strLeftPanelLinksList         = ConstantsUtils.LEFTPANELLINKSLIST;
	    String  strLeftPanelLinksListWithWish = ConstantsUtils.LEFTPANELLINKSLISTWISH;
		logger.debug("Converting string to array.");
		String[] strArr= {};
	    if (wishlistChk == true)
	    {
	    	strArr = strLeftPanelLinksListWithWish.split(",");
	    }
	    else
	    {
	    	strArr = strLeftPanelLinksList.split(",");
	    }
	    logger.debug("Converting Array to List to get the expected list of values. ");
	    List <String> expList = Arrays.asList(strArr);
		logger.debug("Asserting Actual and Expected value of LeftPanel Links List are matching.");
		Assert.assertEquals(actList, expList,"Verification of  LeftPanel Links List failed. ");
	    logger.info("Verified LeftPanel Links List successfully.");
	}
	
	
	

	@Test(dataProvider="getleftPanellinks" , priority=5, description="This testcase verifies the navigation of links in the left panel.")
	public void TC_verifyLeftPanelLinkNavigation(String linkText) throws InterruptedException
	{ 	
		String currentUrl=driver.getCurrentUrl();
		if (!currentUrl.equals(ConstantsUtils.PROFILEURL))
		{
			System.out.println("Not in MyProfilePage.Redirect to the MyProfilePage.");
			driver.get(ConstantsUtils.PROFILEURL);
		}
		MyProfilePage myProfile = new MyProfilePage(driver);
        String actualUrl=null;      
        
        switch(linkText)
    	{ 
    		case "Account Overview":  	
  	    	     logger.debug("Clicking on AccountOverview Link in the leftpanel.");
  	    	     myProfile.clickleftPanelAccountOverview();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.ACCOUNTSURL,"Leftpanel AccountOverview link verification failed.");
  			     logger.info("AccountOverview link in leftpanel is verified successfully");
	        break;
	      
	        case "My Profile":
	    	     logger.debug("Clicking on My Profile Link in the leftpanel.");
  	    	     myProfile.clickleftPanelMyProfile();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.PROFILEURL,"Leftpanel My Profile link verification failed.");
  			     logger.info("My Profile link in leftpanel is verified successfully");
			break;	
	      
			
	        case "My Address Book":
	    	     logger.debug("Clicking on My Address Book Link in the leftpanel.");
  	    	     myProfile.clickleftPanelMyAddressBook();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.ADDRESSBOOKURL,"Leftpanel My Address Book link verification failed.");
  			     logger.info("My Address Book link in leftpanel is verified successfully");
			break;	
			
	        case "My Wallet":
	    	     logger.debug("Clicking on My Wallet Link in the leftpanel.");
  	    	     myProfile.clickleftPanelMyWallet();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.WALLETURL,"Leftpanel My Wallet link verification failed.");
  			     logger.info("My Wallet link in leftpanel is verified successfully");
			break;	
	      
	        case "Tax Exempt":
	    	     logger.debug("Clicking on Tax Exempt Link in the leftpanel.");
  	    	     myProfile.clickleftPanelTaxExempt();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.TAXEXEMPTURL,"Leftpanel Tax Exempt link verification failed.");
  			     logger.info("Tax Exempt link in leftpanel is verified successfully");
			break;		 

	        case "My Favorites":
	    	     logger.debug("Clicking on My Favorites Link in the leftpanel.");
  	    	     myProfile.clickleftPanelMyFavorites();
  	    	     actualUrl=getPageUrl();
  	    	     Assert.assertEquals(actualUrl, ConstantsUtils.FAVORITESURL,"Leftpanel My Favorites link verification failed.");
  			     logger.info("My Favorites link in leftpanel is verified successfully");
			break;		
			
	        case "My Wishlist":
	    	     logger.debug("Clicking on My Wishlist Link in the leftpanel.");
 	    	     myProfile.clickleftPanelMyWishlist();
 	    	     actualUrl=getPageUrl();
 	    	     Assert.assertEquals(actualUrl, ConstantsUtils.WISHLISTURL,"Leftpanel My Wishlist link verification failed.");
 			     logger.info("My Wishlist link in leftpanel is verified successfully");
			break;					
				
	        case "Order History":
	    	     logger.debug("Clicking on Order History Link in the leftpanel.");
	    	     myProfile.clickleftPanelOrderHistory();
	    	     actualUrl=getPageUrl();
	    	     Assert.assertEquals(actualUrl, ConstantsUtils.ORDERHISTORYURL,"Leftpanel Order History link verification failed.");
			     logger.info("Order History link in leftpanel is verified successfully");
			break;				
	
	        case "Log Out":
	        	 logger.debug("Clicking on Log Out Link in the leftpanel.");
		    	 myProfile.clickleftPanelLogOut();
		    	 HomePage hp= new HomePage(driver);
	        	 String strAccountName =hp.getTextAccountNameIcon();
				 if (strAccountName.equals("Sign in"))
				   {
						Assert.assertTrue(true);	
						logger.info("Log Out Link in the leftpanel verified successfully.");
						logger.debug("Signing in again.");
						invokeLogintoDD(username2,password2);
						acceptCookiesPopup();
						gotoMyProfilePage();
						System.out.println("logged in again " + driver.getCurrentUrl());
				   }
				 else 
				   {
						Assert.assertTrue(false,"Log Out Link in the leftpanel verification failed .   ");
				   }			    	
			break;					
    	}
  
    }

	@DataProvider(name="getleftPanellinks")
	public String[] getleftPanellinks() 
	{				
		System.out.println("In the dataprovider to fetch left panel links");
		MyProfilePage myProfile = new MyProfilePage(driver);
		logger.debug("Fetching the left panel link names.");
		String arr[] = myProfile.getleftPanelLinksArray();
		return arr;		
	}	
	
	
	@Test(priority=6, description="This testcase verifies the user email displayed in the login section of My profile page.")
	public void TC_VerifyDisplayedUserEmail()
	{
		MyProfilePage myProfile = new MyProfilePage(driver);
		String displayedEmail = myProfile.getTextUserDisplayedEmail();
	    Assert.assertEquals(displayedEmail, username2,"UserEmail display in MyProfilePage - verification failed");
	    logger.info("UserEmail display in MyProfilePage is verified successfully");
	} 
	

	
	@Test(priority =7, description="This testcase verifies the placeholder values displayed in the fields of Login information block")
	public void TC_LoginInfoblockPlaceHolderCheck() 
	{
		MyProfilePage myProfile = new MyProfilePage(driver);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(myProfile.getPlaceHolderCurrentPwd()    ,"Current Password","Place Holder for Current password field is not matching the expected value");
		softAssert.assertEquals(myProfile.getPlaceHolderNewPwd()        ,"New Password"    ,"Place Holder for New password field is not matching the expected value");
		softAssert.assertEquals(myProfile.getPlaceHolderRetypePwd()     ,"Re-type new password","Place Holder for Re-type password field is not matching the expected value");
		softAssert.assertAll();
		logger.info("PlaceHolders are displayed as expected for all fields in LOGIN INFORMATION block of My Profile page..");
	}
	
	@Test(dataProvider="PwdChangeData", priority=8, description="This testcase verifies the functionality of password change functionality and display of alert messages if any fields are empty.")
	public void TC_ChangePwdDDT(String currPwd,String newPwd,String retypePwd,String validity) throws InterruptedException
	{
		logger.debug("Refreshing My Profile page"); 
		driver.get(ConstantsUtils.PROFILEURL); //		driver.navigate().refresh();
		logger.debug("NewTestData with currPwd- " + currPwd + " newPwd- " +  newPwd + " retypePwd- " + retypePwd + " " +  validity);
		MyProfilePage myProfile = new MyProfilePage(driver);
		myProfile.setCurrentPwd(currPwd);
		myProfile.setNewPwd(newPwd);
		myProfile.setRetypeNewPwd(retypePwd);
		myProfile.clickSaveChangePwdBtn();
		Thread.sleep(2000);
		if (validity.equals("Valid"))
		 {
			 myProfile.clickleftPanelLogOut();
			 invokeLogintoDD(username2,newPwd);
		     boolean SignedIn= checkSignedIn(accountName2);
			 Assert.assertEquals(SignedIn, true,"Could not Login to Dollardays with new password.  Password change functionality did not work correctly.Testcase failed");
			 logger.info("Signed  in to DollarDays successfully with new password.Password change functionality is verified successfully.");				
			 gotoMyProfilePage();
		 }
		else if (validity.equals("Invalid"))
		 {	 	 
			 if (currPwd.equals("") || newPwd.equals("") || retypePwd.equals("") )
			 {
				 boolean chk1 = false;
				 boolean chk2 = false;
				 boolean chk3 = false;
				 SoftAssert softAssert = new SoftAssert();
				 if (currPwd =="")
				 {
					 chk1=myProfile.CheckCurrentPwdReqAlertMsg();
					 softAssert.assertEquals(chk1,true,"The error msg 'current password is required' is not displayed");
					 logger.info("The error msg 'current password is required' is displayed");
				 }
				 if (newPwd =="")
				 {
					 chk2=myProfile.CheckNewPwdReqAlertMsg();
					 softAssert.assertEquals(chk2,true,"The error msg 'new password is required' is not displayed");
					 logger.info("The error msg 'new password is required' is displayed");
				 }
				 if (retypePwd =="")
				 {
					 chk3=myProfile.CheckRetypePwdReqAlertMsg();
					 softAssert.assertEquals(chk3,true,"The error msg 're-password is required' is not displayed");
					 logger.info("The error msg 're-password is required' is displayed");
				 }
				 softAssert.assertAll();
				 logger.info("Appropriate error messages are displayed when current password or new password or retype password fields are empty");
			 }
			 else if (!newPwd.equals(retypePwd))
				{
				 String actualAlert   =  myProfile.getTextPwdMismatchAlert();
				 String expectedPwdMismatchAlert = "Password and repeated password did not match.";
				 Assert.assertEquals(actualAlert, expectedPwdMismatchAlert,"Alert message is not displayed when password and RetypePassword field are mot matching. ");
				 logger.info("Alert message is displayed when password and RetypePassword field are mot matching.");
				 myProfile.clickPwdMismatchAlertClose();
				}
			 else if (newPwd.equals(retypePwd) && !currPwd.equals("") )
			 {
				String actualAlert   =  myProfile.getTextWrongPwdAlert();
				String expectedPwdWrongAlert = "Your password details are incorrect.";
				Assert.assertEquals(actualAlert, expectedPwdWrongAlert,"Alert message is not displayed when the current password entered is wrong. ");
				logger.info("Alert message is displayed when the current password entered is wrong.");
				myProfile.clickWrongPwdAlertClose();
				Thread.sleep(2000);
			
			 }
		 }
	} //test		
	
	@DataProvider(name="PwdChangeData")
	public String[][] getData() throws IOException
	{			
		//get the data from excel
		logger.info("Fetching test data from Excel File for password change functionality ");
		String fileName     = "ChangePasswordData.xlsx";
		String sheetName    = "Sheet1";
		String PwdChangeDataArray[][]=XLUtils.getSheetData(fileName,sheetName );  //String PwdChangeDataArray[][]={};
		return PwdChangeDataArray;

	}	
	
	
	@Test(priority =9, description="This testcase verifies the value of Send to EMail displayed in EMail Preference block.")
	public void TC_VerifySendtoEmail() throws InterruptedException
	{
		driver.get(ConstantsUtils.PROFILEURL);
		MyProfilePage myProfile = new MyProfilePage(driver);
		String displayedEmail = myProfile.getTextSendtoUserEmail();
		System.out.println("sendToemail is "+ displayedEmail);
		Assert.assertEquals(displayedEmail,username2," Verification of Send to UserEmail id in Email Preference Block of  MyProfilePage failed.  ");
		logger.info("Send to UserEmail id displayed in Email Preference Block of  MyProfilePage is verified successfully");
	} 
	
	
	@Test(priority =10, description="This testcase verifies the My Account Terms& Conditions link.")
	public void TC_AccountTermsLink()
	{
		 driver.get(ConstantsUtils.PROFILEURL);
		 MyProfilePage myProfile = new MyProfilePage(driver);
 	     myProfile.clickAccountTermsLink();
 	     String actualUrl1 =getPageUrl();
		 Assert.assertEquals(actualUrl1,ConstantsUtils.ACCOUNTTERMSURL,"Account terms link in Email Preferences verification failed");
		 logger.info("Account terms link in Email Preferences Block of My ProfilePage  verified successfully");
	}
	
	
	@Test(priority =11, description="This testcase verifies the Privacy and Security Statement link.")
	public void TC_PrivacyLink() throws InterruptedException
	{     
		driver.get(ConstantsUtils.PROFILEURL);
		MyProfilePage myProfile = new MyProfilePage(driver);
 	    myProfile.clickPrivacyStatementLink();
 	    String actualUrl1 =getPageUrl();
 		Assert.assertEquals(actualUrl1,ConstantsUtils.PRIVACYTERMSURL,"Account terms link in Email Preferences verification failed");
 		logger.info("Account terms link in Email Preferences Block of My ProfilePage  verified successfully");		   
	} 
	
	@Test(priority =12, description="This testcase verifies the Newsletter subscription checkbox save changes functionality.")
	public void TC_EmailPrefNewsletterSubcriptionCheck() throws InterruptedException
	{   
		driver.get(ConstantsUtils.PROFILEURL);
		MyProfilePage myProfile = new MyProfilePage(driver);
		if (myProfile.isChkboxEmailPrefSelected())			
		{
			System.out.println("EMail Preference Newsletter subscription checkbox is currently checked, so Unchecking Email Preference checkbox ");
			logger.debug("EMail Preference Newsletter subscription checkbox is currently checked, so Unchecking Email Preference checkbox ");
			myProfile.clickChkboxEmailPref();
			myProfile.clickSaveChangeEmailPrefBtn();
			Thread.sleep(1000);
			myProfile.clickleftPanelLogOut();
			invokeLogintoDD(username2,password2);
			acceptCookiesPopup();
			gotoMyProfilePage();
			System.out.println("logged in again " + driver.getCurrentUrl());
	 		Assert.assertEquals(myProfile.isChkboxEmailPrefSelected(),false,"Email Preference checkbox is displaying as checked.Email Preference Checkbox changes are not saved .Testcase Failed");
	 		logger.info("Email Preference Newsletter subscription checkbox is displaying as unchecked.Verified that Email Preference Checkbox changes are saved successfully.");
			System.out.println("Email Preference Newsletter subscription checkbox is displaying as unchecked.Verified that Email Preference Checkbox changes are saved successfully.");
		}
		else if (!myProfile.isChkboxEmailPrefSelected())
		{
			System.out.println("EMail Preference Newsletter subscription checkbox is currently unchecked, so checking Email Preference checkbox ");
			logger.debug("EMail Preference Newsletter subscription checkbox is currently unchecked, so checking Email Preference checkbox ");
			myProfile.clickChkboxEmailPref();
			myProfile.clickSaveChangeEmailPrefBtn();
			Thread.sleep(1000);
			myProfile.clickleftPanelLogOut();
			invokeLogintoDD(username2,password2);
			acceptCookiesPopup();
			gotoMyProfilePage();
			System.out.println("logged in again " + driver.getCurrentUrl());
	 		Assert.assertEquals(myProfile.isChkboxEmailPrefSelected(),true,"Email Preference checkbox is still displaying as checked.Email Preference Checkbox changes are not saved .Testcase Failed");
	 		logger.info("Email Preference Newsletter subscription checkbox is displaying as checked.Verified that Email Preference Checkbox changes are saved successfully.");
			System.out.println("Email Preference Newsletter subscription checkbox is displaying as checked.Verified that Email Preference Checkbox changes are saved successfully.");
		}
		
			
		
	}
	
	@Test(priority =13, description="This testcase verifies tooltip displayed for fields in the Personal Information block of My Profile page")
	public void TC_PersonalInfoblockTooltipCheck() throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");
		Thread.sleep(5000);
		MyProfilePage myProfile = new MyProfilePage(driver);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(myProfile.getTooltipFirstName()     ,"Given name / first name","Tooltip for FirstName field is not matching the expected value");
		softAssert.assertEquals(myProfile.getTooltipLastName()      ,"Surname / last name / family name","Tooltip for LastName field is not matching the expected value");
		softAssert.assertEquals(myProfile.getTooltipPrimaryPhone()  ,"Main contact number","Tooltip for Primary Phone field is not matching the expected value");
		softAssert.assertEquals(myProfile.getTooltipExtension()     ,"Extension number","Tooltip for Ext field is not matching the expected value");
		softAssert.assertEquals(myProfile.getTooltipSecondaryPhone(),"Secondary contact number","Tooltip for Secondary Phone field is not matching the expected value");
		softAssert.assertAll();
		logger.info("Tooltips are displayed as expected for all fields in PERSONAL INFORMATION block .");
	}
	
	@Test(priority =14, description="This testcase verifies the display of place holder values for fields in the Personal Information block of My Profile page")
	public void TC_PersonalInfoblockPlaceHolderCheck() 
	{
		MyProfilePage myProfile = new MyProfilePage(driver);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(myProfile.getPlaceHolderFirstName()        ,"First Name"            ,"Place Holder for First Name field is not matching the expected value");
		softAssert.assertEquals(myProfile.getPlaceHolderLastName()         ,"Last Name"             ,"Place Holder for Last Name field is not matching the expected value");
		softAssert.assertEquals(myProfile.getPlaceHolderPrimaryPhone()     ,"Phone - Main Line"     ,"Place Holder for Primary Phone field is not matching the expected value");
		softAssert.assertEquals(myProfile.getPlaceHolderSecondaryPhone()   ,"Phone - Cell or Other" ,"Place Holder for Secondary Phone field is not matching the expected value");
		softAssert.assertAll();
		logger.info("PlaceHolders are displayed as expected for all fields in PERSONAL INFORMATION block of My Profile page..");
	}
	
	@Test(priority =15, description="This testcase verifies that error messages are displayed when  fields in the Personal Information block are empty.")
	public void TC_PersonalInfoblockReqFieldError() throws InterruptedException 
	{
		MyProfilePage myProfile = new MyProfilePage(driver);
		myProfile.setFirstName("");
		myProfile.setLastName("");
		myProfile.setPrimaryPhone("");
		myProfile.setExtension("");
		myProfile.setSecondaryPhone("");
		myProfile.clickSaveChangesPersonalInfoBtn();
		driver.switchTo().alert().accept();
		SoftAssert softAssert = new SoftAssert();
		String fnameAlert  = myProfile.getTextFirstNameReqAlertMsg();
		String lnameAlert  = myProfile.getTextLastNameReqAlertMsg();
		String primPhAlert = myProfile.getTextPrimPhReqAlertMsg();
		softAssert.assertEquals(  fnameAlert  ,"First name is required","The error msg 'First name is required' is not displayed correctly");
		softAssert.assertEquals(  lnameAlert  ,"Last name is required","The error msg 'Last name is required' is not displayed correctly");
		softAssert.assertEquals(  primPhAlert ,"Phone is required","The error msg 'Phone is required' is not displayed correctly");
		softAssert.assertAll();
		logger.info("Appropriate error messages are displayed as expected when First Name or Last Name or Primary Phone Number fields are empty");
		
	}
	
	@Test(dataProvider="PersonalInfoUpdateData", priority=16, description="This testcase verifies the update personal information functionality by checking display in Account Overview Page and My Profile page.")
	public void TC_PersonalInfoUpdate(String fName,String lName,String PrimaryPh,String Ext,String SecondaryPh,String ExpPrimPhWithExt,String ExpSecondaryPh) throws InterruptedException 
	{
		logger.info("Updating Personal info with FirstName as " + fName + " LastName as " + lName + " Primary Phone as " + PrimaryPh + " Ext as " + Ext + " Secondary Ph as " + SecondaryPh + " . ");
		driver.get(ConstantsUtils.PROFILEURL);
		MyProfilePage myProfile = new MyProfilePage(driver);
		myProfile.setFirstName(fName);
		myProfile.setLastName(lName);
		myProfile.setPrimaryPhone(PrimaryPh);
		myProfile.setExtension(Ext);
		myProfile.setSecondaryPhone(SecondaryPh);
		myProfile.clickSaveChangesPersonalInfoBtn();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		logger.debug("Navigating to My Account Overview Page to check if chages are updated in personal information block.");
		myProfile.clickleftPanelAccountOverview();
		
		//Fetching actual values from Personal Information block of Account Overview Page
		MyAccountOverviewPage myAccount = new MyAccountOverviewPage(driver);
		String actAOName        = myAccount.getTextPersonalInfoName();
		String actAOPrimaryPh   = myAccount.getTextPersonalInfoPrimaryPh();
		String actAOSecondaryPh = myAccount.getTextPersonalInfoSecondaryPh();
		
		
		//Fetching actual values from Personal Information block of My Profile Page
		logger.debug("Navigating back to My Profile Page to check if the changes made are reflecting as prepopulated values of fields in personal information block.");
		myProfile.clickleftPanelMyProfile();
		String actProfFirstName =  myProfile.getValueFirstName();
		String actProfLastName  =  myProfile.getValueLastName();
		String actProfPrimPh    =  myProfile.getValuePrimaryPhone();	
		String actProfExt       =  myProfile.getValueExtension();
		String actProfSecPh     =  myProfile.getValueSecondaryPhone();
		
		//Expected values for fields in Account Overview Page
		String expName          = fName + " " + lName ;
		//Expected values of Primary Phone Number and Ext in My Profile Page.It is extracted from the Primary # field displayed in Account Overview Page
		String expProfPrimPh    =  getOnlyPrimaryNum(actAOPrimaryPh);
		String expProfExt       =  getOnlyExtNum(actAOPrimaryPh);
		
		JavascriptExecutor js   = (JavascriptExecutor) driver; //Scroll down to view the personal info block
		js.executeScript("window.scrollBy(0,650)", "");
		Thread.sleep(2000);
		SoftAssert softAssert = new SoftAssert();
		//Checking in My Account Overview Page -->ExpPrimPhWithExt,ExpSecondaryPh are input parameters of testclass fetched from excel in the data provider
		softAssert.assertEquals(  actAOName        , expName,"The Name is not saved correctly in My Account Overview Page .");
		softAssert.assertEquals(  actAOPrimaryPh   , ExpPrimPhWithExt,"The Primary Phone is not saved correctly in My Account Overview Page.");
		softAssert.assertEquals(  actAOSecondaryPh , ExpSecondaryPh,"The Secondary Phone is not saved correctly in My Account Overview Page.");
		//Checking in My Profile Page
		softAssert.assertEquals(  actProfFirstName       , fName,"The First Name field is not saved correctly in My Profile Page  .");
		softAssert.assertEquals(  actProfLastName        , lName,"The Last Name field is not saved correctly in My Profile Page  .");
		softAssert.assertEquals(  actProfPrimPh          , expProfPrimPh,"The Primary Phone Number field is not saved correctly in My Profile Page  .");
		softAssert.assertEquals(  actProfExt             , expProfExt,"The Ext. field is not saved correctly in My Profile Page  .");
		softAssert.assertEquals(  actProfSecPh           , actAOSecondaryPh,"The Secondary/Mobile Phone Number field is not saved correctly in My Profile Page  .");
		softAssert.assertAll();
		logger.info("Personal Information Block field changes are updated correctly in Account Overview Page and My Profile Page");
		
	}
	
	
	@DataProvider(name="PersonalInfoUpdateData")
	public String[][] getPersonalInfoData() throws IOException
	{			
		//get the data from excel
		logger.info("Fetching test data from Excel File for password change functionality ");
		String fileName     = "UpdatePersonalInfoData.xlsx";
		String sheetName    = "Sheet1";
		String personalInfoDataArray[][]=XLUtils.getSheetData(fileName,sheetName );  //String PwdChangeDataArray[][]={};
		return personalInfoDataArray;

	}	
	
}


 