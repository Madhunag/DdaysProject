package com.DollarDays.testCases;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.DollarDays.pageObjects.AddressBookPage;
import com.DollarDays.pageObjects.HomePage;
import com.DollarDays.utilities.BaseClass;
import com.DollarDays.utilities.ConstantsUtils;
import com.DollarDays.utilities.XLUtils;



public class AddressBookPageTest extends BaseClass
{
	public WebDriver driver;
	public static Logger logger =LogManager.getLogger("DollarDays"); 
	HomePage hp;
	AddressBookPage addressBook;
	
	@BeforeClass
	public void initialize() throws IOException
	{
	  driver =initializeDriver();
	  hp= new HomePage(driver);
	  addressBook=new AddressBookPage(driver);
	  invokeLogintoDD(username1,password1);
	  navigateToAddressBookPage();
	}
	
	//common method to navigate to AddressBookPage
	public void navigateToAddressBookPage()
	{
		  hp= new HomePage(driver);
		  hp.clickAccountNameIcon();
		  hp.clickAddressBookLink(); 
		  addressBook.closeAlert();
		  addressBook.closeCookiesPopupBox();
	}
	 
   //common method to enter data in AddressBookForm
	public void addressData(String xlfileName,String sheet,int rownum) throws IOException, InterruptedException
	{
		  String[] data=XLUtils.getRowData(xlfileName,sheet,rownum);
		   String firstName=data[0];
		   addressBook.setFirstNameText(firstName);
		   String lastName=data[1];
		   addressBook.setLastNameText(lastName);
		   String companyName=data[2];
		   addressBook.setCompanyNameText(companyName);
		   String streetAddress=data[3];
		   addressBook.setStrtAddrText(streetAddress);
		   String apt=data[4];
		   addressBook.setAptText(apt);
		   String country=data[5];
		   addressBook.getCountryDropdownValueByVisibleText(country);
		   String city=data[6];
		   addressBook.setCityText(city);;
		   String state=data[7];
		   addressBook.getStateByVisibleText(state);
		   String phoneNumber=data[8];
		   addressBook.setPhoneNumberText(phoneNumber);
		   String ext=data[9];
		   addressBook.setExtText(ext);
		   String zipCode=data[10];
		   addressBook.setZipCodeText(zipCode);
		   addressBook.clickResReceivType();
		   System.out.println("Entered AddressBookform values");
		   addressBook.clickSaveChangesButton();
		   Thread.sleep(2000);
	}
	
	//common method for actual saved address
	public List<String> actAddress()
	{
	String actAddress=addressBook.getSavedAddressTextInAddressBookPage();
	System.out.println("Address is saved") ;
    String[] array={};//to declare string array
	array = actAddress.split("\n");//to convert string to string array
	List <String> actAddressList = Arrays.asList(array);//to declare a list and converting string array to list
	System.out.println(actAddressList);//to print list
    return actAddressList;
	}
	
	//common method for expected address that is fetched from excel
	public List<String> expAddress(String xlfileName,String sheet,int colnum) throws IOException
	{   
	 xlfileName="AddressBook.xlsx";
	 String[] expadd=XLUtils.getColumnData(xlfileName,sheet,colnum);
	 List <String> expList = Arrays.asList(expadd);
	 System.out.println(expList);
	 return expList;
	}
 
@Test(priority=1,description="TestCase verifies AddressBookPage Title is displayed correctly.")	
    public void TC_verifyAddressBookPageTitle() throws IOException
    {
	
    Assert.assertEquals(addressBook.getAddressBookPageTitle(),"My Account","AddressBookPage title is not as expected.Title Verification failed.");
    logger.info("Verified AddressBookPage Title successfully.");
    System.out.println("AddressBookPage Title is displayed correctly");
    
    }
		

@Test(priority=2,description="TestCase verifies AddressBookPage Header is displayed correctly.")
	public void TC_verifyAddressBookPageHeading()
	{
	Assert.assertEquals(addressBook.getAddressBookPageHeader(),"MY ADDRESS BOOK","AddressBookPage heading is not as expected.Header Verification failed.");
	logger.info("Verified AddressBookPage Heading successfully.");
	System.out.println("AddressBookPage Heading matches");
	}

@Test(priority=3,description="TestCase verifies Add A NEW SHIP-TO ADDRESS Link is displayed.")
	public void TC_verifyAddNewShipToAddressLinkDisplay()
	{
	boolean addressLinkDisplay=addressBook.checkAddNewShipToAddressLinkDisplay();
    Assert.assertEquals(addressLinkDisplay,true);
    logger.info("AddShipToAddress link is displayed");
	System.out.println("AddShipToAddress link is displayed");
	}
	
@Test(priority=4,description="TestCase verifies AddressBook LeftPanel LinkTexts are displayed correctly.")
	public void TC_verifyAddressBookLeftPanelLinksDisplay() throws IOException
	{
     List<String> actList=addressBook.getAddressBookLeftPanelLinkTexts();
     System.out.println("The actual AddressBookPageLeftPanelaccount links are:\n"+actList);
     logger.debug("The actual AddressBookPageLeftPanelaccount links are:\n"+actList);
     
     String xlfileName="ABLeftPanelExpLinkTexts.xlsx";
     List<String> expList = new ArrayList<String>();
     if(actList.contains("My Wishlist"))
	 {			
   
    	 String[] data=XLUtils.getColumnData(xlfileName, "sheet1", 0);
    	 for (String linkTexts : data) 
	     {
	        expList.addAll(Arrays.asList(linkTexts));
	     }
	 }
 	     
     else  if(!actList.contains("My Wishlist"))
	 {
    	 String[] data=XLUtils.getColumnData(xlfileName, "sheet2", 0);
	     for (String linkTexts : data) 
	     {
	        expList.addAll(Arrays.asList(linkTexts));
	     }
	  }
	     logger.debug("The expected list is:\n"+expList);
         System.out.println("The expected list is:\n"+expList);
         Assert.assertEquals(actList,expList ,"Actual and Expected AddressBookLeftPanelLinkTexts are not matching");
         System.out.println("Expected and Actual AddressBookLeftPaneLinkTexts are matching");
         logger.info("AddressBookLeftPaneLinkTexts are getting displayed as expected");
     }
	
	
@Test(priority=5,dataProvider="getuserlinks",description="TestCase verifies AddressBook LeftPanel Links are navigating correctly.")
     public void TC_verifyAddressBookLinksNavigations(String linkText)
	 {
	  String currentUrl=driver.getCurrentUrl();
	  if (!currentUrl.equals(ConstantsUtils.ADDRESSBOOKURL))
	   {
	      driver.get(ConstantsUtils.ADDRESSBOOKURL);
    	}

     String actUrl=null;
	 String expUrl;
	 switch(linkText)
	 {
	 case  "Account Overview" :
		    addressBook.clickAccountOverviewLink();
			actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.ACCOUNTSURL;
		    Assert.assertEquals(actUrl,expUrl);
		    logger.info("Account Overview link url verified successfully.");
		    break;
	case   "My Profile" :
		    addressBook.clickMyProfileLink();	 
		    actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.PROFILEURL;
			Assert.assertEquals(actUrl,expUrl);
			logger.info("My Profile link url verified successfully.");
			break;
	case    "My Address Book" :
		    addressBook.clickMyAddressBookLink();	 
		    actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.ADDRESSBOOKURL;
		    Assert.assertEquals(actUrl,expUrl);
		    logger.info("My Address Book link url verified successfully.");
		    break;
	case   "My Wallet" :
		    addressBook.clickMyWalletLink();
		    actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.WALLETURL;
		    Assert.assertEquals(actUrl,expUrl);
		    logger.info("My Wallet link url verified successfully.");
			break;
	case   "Tax Exempt" :
		    addressBook.clickTaxExemptLink();
		    actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.TAXEXEMPTURL;
			Assert.assertEquals(actUrl,expUrl);
			logger.info("Tax Exempt link url verified successfully.");
		    break;
    case   "My Favorites" :
    	    addressBook.clickMyFavoritesLink();
    	    actUrl=driver.getCurrentUrl();
    	    expUrl=ConstantsUtils.FAVORITESURL;
			Assert.assertEquals(actUrl,expUrl);
			logger.info("My Favorites link url verified successfully.");
		    break;
  
	case  "My Wishlist" :
		     addressBook.clickMyWishListLink();
		     actUrl=driver.getCurrentUrl();
			 expUrl=ConstantsUtils.WISHLISTURL;
			 Assert.assertEquals(actUrl,expUrl);
			 logger.info("My Wishlist link url verified successfully.");
		  	break;
		    
	case    "Order History" :
		    addressBook.clickOrderHistoryLink();
		    actUrl=driver.getCurrentUrl();
		    expUrl=ConstantsUtils.ORDERHISTORYURL;
		    Assert.assertEquals(actUrl,expUrl);
		    logger.info("Order History link url verified successfully.");
	        break;
	case    "Log Out" :
		    addressBook.clickLogOutLink();
			String strAccountName =hp.getTextAccountNameIcon();
			if(strAccountName.equals("Sign in"))
			{
			  Assert.assertTrue(true);
			  System.out.println("user has successfully logged out");
			  logger.info("User is able to log out successfully");
			  invokeLogintoDD(username1,password1);
			  hp= new HomePage(driver);
			  hp.clickAccountNameIcon();
			  hp.clickAddressBookLink(); 
			 
			}
			else
			{
			  System.out.println("user has not logged out");
			  logger.info("User is  not able to log out successfully");
			  Assert.assertTrue(false);
			}
			break;
	default : Assert.assertTrue(false);
	         break;
				
	  }  
			}

@DataProvider(name="getuserlinks")
     public String[] getuserlinks()
    {
	String[] array=addressBook.getAddressBookLeftPanelLinkTextsArray();
	return array;
	}
		
@Test(priority=6,description="TestCase verifies new address is ADDED correctly in the AddressBook page.")
      public void TC_verifyAddNewShipToAddressLink() throws IOException, InterruptedException
      {
       addressBook.clickAddNewShipToAddressLink();
	   String xlfileName="ABFORMData.xlsx";
	   addressData(xlfileName,"sheet1",1);
	   List<String> actAddedAddressList=actAddress();
	   List<String> expAddedAdressList=expAddress(xlfileName,"sheet1",1);
	   Thread.sleep(1500);
	   Assert.assertEquals(actAddedAddressList,expAddedAdressList,"Actual and Expected Address is  not matching");
	   System.out.println("expected and actual address matching");
	   logger.info("New Address is added succesfully on clicking NewShipToAddressLink");
	   }

@Test(priority=7,dependsOnMethods={"TC_verifyAddNewShipToAddressLink"},description="TestCase verifies saved address is EDITED correctly in the AddressBook page.")
     public void TC_verifyEditLink() throws IOException, InterruptedException
      {	   
	    String xlfileName="ABFORMData.xlsx";
	    addressBook.clickEditLink();
		addressData(xlfileName,"sheet1",2);
		List<String> actEditedAddressList=actAddress();
	    List<String> expEditedAddressList=expAddress(xlfileName,"sheet1",2);
	    Thread.sleep(1500);
	    Assert.assertEquals(actEditedAddressList,expEditedAddressList,"Actual and Expected edited Address lists are not matching");
	    System.out.println("Address is  edited");
	    logger.info("Added Address is  edited succesfully");
      }    

@Test(priority=8,dependsOnMethods={"TC_verifyAddNewShipToAddressLink"},description="TestCase verifies address when setasdefault is saved correctly in the AddressBook page and AccountOverview Page")
      public void TC_verifySetAsDefaultLink() throws IOException, InterruptedException
      {	           
	    SoftAssert softAssert=new SoftAssert();
	    addressBook.clickSetAsDefaultLink();
	 	boolean checkMarkDisplay=addressBook.checkCheckMarkDisplayInAddressBookPage();
	 
	    Thread.sleep(1500);
	    if(checkMarkDisplay==true) 
		{
	    	Assert.assertTrue(true);
	    	logger.info("Address is set as default in AddressBook Page");
	    	System.out.println("Address is set as default in AddressBook Page.");
	    	driver.navigate().to("https://www.dollardays.com/myaccount/account.aspx");
	    	
	 	    String AccountOverviewName=addressBook.getAccountOverviewName();//string 
	 	    
	 	    String[] array={};// declaring string array
	 		array = AccountOverviewName.split(" ");//splitting string to string array
	 		String actNameInAccOvwPage=Arrays.toString(array);//printing string array
	        System.out.println("Name fetched from AccountOverviewPage is :"+actNameInAccOvwPage);
	 	    logger.debug("Name fetched from AccountOverviewPage is :" +actNameInAccOvwPage);
	 	    String  xlfileName="AddressBook.xlsx";
	 		String[] expAccOvwName=XLUtils.getColumnData(xlfileName,"sheet2",0);
	 		String expNameInAccOvwPage=Arrays.toString(expAccOvwName);
	 	    System.out.println("Name fetched from excel is :"+expNameInAccOvwPage);
	 		logger.debug("Name fetched from excel is :"+expNameInAccOvwPage);
	 		softAssert.assertEquals(actNameInAccOvwPage,expNameInAccOvwPage,"Name is not matching");
	 	
	 		
	 		
	 		String AccountOverviewAddress=addressBook.getAccountOverviewAddress();
	 		
	 		String[] array1={};// declaring string array
	 		array1 = AccountOverviewAddress.split("\n");//splitting string to string array
	 		String actAddressInAccOvwPage=Arrays.toString(array1);//printing string array
	    //  List<String> actAddressInAccOvwPage = new ArrayList<String>(Arrays.asList(AccountOverviewAddress.split("\n")));//converting from string to an ArrayList
	 		System.out.println("Address in Account overview page is:"+actAddressInAccOvwPage);
            logger.debug("Address in Account overview page is: "+actAddressInAccOvwPage);
            String[] expAccOvwAdd=XLUtils.getColumnData(xlfileName,"sheet3",0);//string array
	 		String expAddressInAccOvwPage=Arrays.toString(expAccOvwAdd);
	    //  List<String> expAddressInAccOvwPage = Arrays.asList(expAccOvwAdd);  //converting string array to list
	 	    System.out.println("Name fetched from excel is :"   +expAddressInAccOvwPage);
	 		logger.debug("Name fetched from excel is :"+expAddressInAccOvwPage);
	 		softAssert.assertEquals(actAddressInAccOvwPage,expAddressInAccOvwPage,"Address is not matching");
	 		
	 		softAssert.assertAll();
	 		driver.get(ConstantsUtils.ADDRESSBOOKURL);
		}
		 else
		  {
		  logger.info("Address is not set as default in AddressBook Page");
		  System.out.println("Address is not set as default in AddressBook Page");
		  Assert.assertTrue(false);
		  }
      }

@Test(priority=9,dependsOnMethods={"TC_verifyAddNewShipToAddressLink"},description="TestCase verifies saved Address is not deleted in AddressBook Page with Confirmation NO button")
    public void TC_verifyDeleteLinkNoBtn() throws IOException, InterruptedException
    {
	  addressBook.clickDeleteAddressLink();
      boolean confirmationBoxDisplay=addressBook.checkConfirmationBoxDisplay();
      if(confirmationBoxDisplay==true)
      {
       addressBook.clickNoButtonInConfirmationBox();
       Thread.sleep(1500);
	   boolean AddressBoxDisplay=addressBook.checkSavedAddressBoxDisplay();
	  
	  System.out.println("AddressBoxDisplay is : "+AddressBoxDisplay);
      if(AddressBoxDisplay==true)
      {
        Assert.assertTrue(true);
        logger.info("Address is not deleted on clicking no button in confirmation box");
        System.out.println("Address is not deleted on clicking no button in confirmation box");
	  }
      else if(AddressBoxDisplay!=true)
      {
    	logger.info("Address is deleted succesfully on clicking DeleteLinkNoBtn link");
    	System.out.println("Address is deleted");
   	    Assert.assertTrue(false);
   	  }
      }
	  else
	   {
		  logger.info("Confirmation Box  is not displayed ");
		  System.out.println("Confirmation Box is not displayed");
	   }
  }

@Test(priority=10,dependsOnMethods={"TC_verifyAddNewShipToAddressLink"},description="TestCase verifies saved Address is deleted in AddressBook Page with Confirmation YES button")
	 public void TC_verifyDeleteLinkYesBtn() throws IOException, InterruptedException
	 {	           		 
	   addressBook.clickDeleteAddressLink();
	   boolean confirmationBoxDisplay=addressBook.checkConfirmationBoxDisplay();
	   if(confirmationBoxDisplay==true)
	   {
		 addressBook.clickYesButtonInConfirmationBox();
		 Thread.sleep(1500);
	     boolean AddressBoxDisplay=addressBook.checkSavedAddressBoxDisplay();
	     System.out.println("AddressBoxDisplay is : "+AddressBoxDisplay);
         if(AddressBoxDisplay==false)
           {
        	 Assert.assertTrue(true);
        	 logger.info("DeleteLinkYesBtn is verified successfully.");
        	 System.out.println("Address is  deleted on clicking yes button in confirmation box");
             
           }
         else if(AddressBoxDisplay==true)
           {
             Assert.assertTrue(false);
             logger.info("DeleteLinkYesBtn is not  verified successfully.");
    	     System.out.println("Address is not deleted on clicking yes button in confirmation box");
    	   }
        }
	   else
	    {
		   System.out.println("Confirmation Box is not displayed");
	    }
	  }

@Test(priority=11,description="TestCase verifies Country is a dropdownlist")
     public void TC_verifyCountryDropdownIsAList() throws InterruptedException
     {
	   addressBook.clickAddNewShipToAddressLink();
 	//  JavascriptExecutor js = (JavascriptExecutor)driver;
    //  js.executeScript("window.scrollBy(0,+200)");
 	   boolean countrydrpdndisplay=addressBook.checkCountryIsADropdown();
       Assert.assertEquals(countrydrpdndisplay, true,"Country is not a Dropdown List");
       logger.info("Country  is   verified as a DropdownList successfully.");
       System.out.println("Country is a DropdownList");
       Thread.sleep(1000);
     }
      
@Test(priority=12,dependsOnMethods={"TC_verifyCountryDropdownIsAList"},description="TestCase verifies Country's default value is United States of America.")
     public void TC_verifyCountryDropdownDefaultValue() throws InterruptedException
      {
	  String countryDefaultValue=addressBook.getCountryDropdownDefaultValue();
	  Thread.sleep(1500);
      Assert.assertEquals("United States of America", countryDefaultValue, "Default Value is not USA");
      logger.info(countryDefaultValue +" is selected from dropdownlist by default");
	  System.out.println(countryDefaultValue +" is selected from dropdownlist by default");
      }
 
@Test(priority=13,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies State contains a dropdownlist and displays all the state options correctly.")
     public void TC_verifyStateDropdownOptions() throws IOException 
     {
 	  List<String> actStateDropdownOptions=addressBook.getStateDropdownListOptions();
 	  System.out.println(actStateDropdownOptions+"actstateslist is displayed");
 	  String xlfileName="StatesList.xlsx";
	  String[] expStatesList=XLUtils.getColumnData(xlfileName,"sheet1",0);
	  List<String> expStateDropdownOptions=new ArrayList<String>();
	  for (String linkTexts : expStatesList) 
	  {
	    expStateDropdownOptions.addAll(Arrays.asList(linkTexts));
	  }
	  logger.debug(expStateDropdownOptions+"expstateslist is displayed");
	  System.out.println(expStateDropdownOptions+"expstateslist is displayed");
      Assert.assertEquals(actStateDropdownOptions,expStateDropdownOptions,"Actual and Expected states optionsList are not matching");
      logger.info("States List is  displayed as expected");
      }

        
@Test(priority=14,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies Company Name displays alert for commercial with shipping docks.")
   public void TC_verifyReceivTypeWithShippingDock() throws InterruptedException
   {
 	 
	  addressBook.clickReceivTypeCommWthShipDock();
 	  Thread.sleep(1500);
 	  String companyNameAlert= addressBook.getcompanyNameAlertMsg();
 	  Assert.assertEquals(companyNameAlert, "Company name/Organization name is required for commercial shipments");
 	  System.out.println("Alert is displayed for companyname for receiving type Commercial shipments with shipping dock:"+companyNameAlert);
 	  logger.info("Alert is displayed for companyname for receiving type Commercial shipments with shipping dock:\"+companyNameAlert");
}
 
   
@Test(priority=15,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies Company Name displays alert for  commercial  without shipping docks.")
   public void TC_verifyReceivTypeWithoutShippingDock() throws InterruptedException
   {
 	 
	  addressBook.clickReceivTypeCommWithoutShipDock();
 	  Thread.sleep(1500);
 	  String companyNameAlert= addressBook.getcompanyNameAlertMsg();
 	  Assert.assertEquals(companyNameAlert, "Company name/Organization name is required for commercial shipments");
 	  System.out.println("Alert is displayed for companyname for receiving type Commercial shipments without shipping dock:"+companyNameAlert);
 	  logger.info( "Alert is displayed for companyname for receiving type Commercial shipments without shipping dock:"+companyNameAlert);
   }
   
@Test(priority=16,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies display of alert for mandatory textboxes.")
   public void TC_verifyMandatoryFieldsAlertMsgDisplay() throws InterruptedException, IOException
   {
 	  addressBook.getCountryDropdownValueByIndex(0);
 	  Thread.sleep(1500);

 	  addressBook.clickSaveChangesButton();
 	
 	  SoftAssert softAssert=new SoftAssert();
 	  
 	  String firstNameAlert=addressBook.getFirstNameAlertMsg();
 	  String lastNameAlert=addressBook.getLastNameAlertMsg();
 	  String addressAlert=addressBook.getStreetAddressAlertMsg();
 	  String countryAlert=addressBook.getCountryAlertMsg();
 	  String cityAlert=addressBook.getCityAlertMsg();
      String stateAlert=addressBook.getStateAlertMsg();
 	  String phonenoAlert=addressBook.getPhonenoAlertMsg();
      String zipcodeAlert=addressBook.getZipcodeAlertMsg();
       
   
      softAssert.assertEquals(firstNameAlert,"Given name / first name (Required)","firstname alert message is not displayed");
      softAssert.assertEquals(lastNameAlert,"Surname / last name / family name (Required)","lastname  alert message  is not displayed as expected");
      softAssert.assertEquals(addressAlert,"Address (Required)","streetaddress  alert message  is not displayed as expected");
      
      softAssert.assertEquals(countryAlert,"Country (Required)","country  alert message  is not displayed as expected");
      softAssert.assertEquals(cityAlert,"City (Required)","city  alert message  is not displayed as expected");
      softAssert.assertEquals(stateAlert,"This field is required.","state  alert message  is not displayed as expected");
      softAssert.assertEquals(phonenoAlert,("Phone Number (Required)"),"phoneno  alert message  is not displayed as expected");
      softAssert.assertEquals(zipcodeAlert,("Zip code (Required)")," zipcodealert  alert message  is not displayed as expected");
   
     
      softAssert.assertAll();
      
      logger.info("Alert is displayed for empty mandatory fields");
      System.out.println("Alert is displayed for empty mandatory fields");
 	  
      logger.info(firstNameAlert+ " is displayed for empty firstname");
 	  System.out.println(firstNameAlert+ " is displayed for empty firstname");
 	  
 	  logger.info(lastNameAlert +" is displayed for empty lastname");
 	  System.out.println(lastNameAlert +" is displayed for empty lastname");
 	  
 	  logger.info( addressAlert +" is displayed for empty streetaddress");
 	  System.out.println(addressAlert +" is displayed for empty streetaddress");
 	  
 	  logger.info(countryAlert +"is displayed for empty country");
 	  System.out.println (countryAlert +"is displayed for empty country");
 	  
 	  logger.info( cityAlert +" is displayed for empty city");
 	  System.out.println(cityAlert +" is displayed for empty city");
 	  
 	  logger.info(stateAlert +" is displayed for empty State");
 	  System.out.println(stateAlert +" is displayed for empty State");
 	  
 	  logger.info(phonenoAlert+" is displayed for empty phoneno");
 	  System.out.println(phonenoAlert+" is displayed for empty phoneno");
 	  
 	  logger.info( zipcodeAlert +"is displayed for empty zipcode");
 	  System.out.println( zipcodeAlert +"is displayed for empty zipcode");
 		 
 	 }
 
@Test(priority=17,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies FirstName textbox accepts characters until maxlength defined")
  public void TC_verifyFirstNameTextboxMaxLength() throws InterruptedException, IOException
  {
	  
	  String xlfileName="ABFieldsData.xlsx";
	
	  String[] data=XLUtils.getRowData(xlfileName,"sheet1",1);
	  String firstName=data[0];
	  addressBook.setFirstNameText(firstName);
	  
      String firstNameAttrValue=addressBook.getFirstNameAttributeValue();
	  String firstNameAttrlength=addressBook.getFirstNameAttrMaxlength();
	  int maxlength=Integer.parseInt(firstNameAttrlength);
	  System.out.println("Maxlengthdefined for firstname textbox is:"+maxlength);
	  try
	  {
		  if(firstNameAttrValue.length()<=maxlength)
		  {
	  Assert.assertTrue(true);
	  logger.debug("Firstname textbox contains"+firstNameAttrValue.length()+"characters");
	  System.out.println("Firstname textbox contains"+firstNameAttrValue.length()+"characters");
	  logger.info("Firstname textbox is accepting up to max 26 characters");
	  System.out.println("Firstname textbox is accepting up to max 26 characters");
	      }
	  }
	   catch(Exception e)
	  {
		  logger.info("e.getMessage()+\"firstname does not accept more than 26 characters");
		  System.out.println(e.getMessage()+"firstname does not accept more than 26 characters");
	  }
  }
	 
@Test(priority=18,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies LastName textbox accepts characters until maxlength defined")
  public void TC_verifyLastNameTextboxMaxlength() throws InterruptedException, IOException
  {
	 
	  String  xlfileName="ABFieldsData.xlsx";
	  
	  String[] data=XLUtils.getRowData(xlfileName,"sheet1",1);
	  String lastName=data[1];
	  addressBook.setLastNameText(lastName);
	  
      String lastNameAttrValue=addressBook.getLastNameAttributeValue();
	  String lastNameAttrlength=addressBook.getLastNameAttrMaxlength();
	  int maxlength=Integer.parseInt(lastNameAttrlength);
	  System.out.println("Maxlengthdefined for lastname textbox is:"+maxlength);
	  try
	  {
		  if(lastNameAttrValue.length()<=maxlength)
		  {
	  Assert.assertTrue(true);
	  logger.debug("Lasttname textbox contains"+lastNameAttrValue.length()+"characters");
	  System.out.println("Lasttname textbox contains"+lastNameAttrValue.length()+"characters");
	  logger.info("Lasttname textbox is accepting up to max 26 characters");
	  System.out.println("Lasttname textbox is accepting up to max 26 characters");
	      }
	  }
	   catch(Exception e)
	  {
		  System.out.println(e.getMessage()+"lastname does not accept more than 26 characters");
	  }
  }
@Test(priority=19,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies StresstAddress textbox accepts alphanumeric characters.")
  public void TC_verifyStreetAddressTextbox() throws IOException, InterruptedException
  {
	
	String  xlfileName="ABFieldsData.xlsx";
	
	String[] data=XLUtils.getRowData(xlfileName,"sheet1",1);
	String streetAddress=data[2];
	addressBook.setStrtAddrText(streetAddress);
	String stadAttrValue=addressBook.getStrtAddrAttributeValue();
	Thread.sleep(1500);
	Pattern letter  = Pattern.compile("[a-zA-z]");
	Pattern digit   = Pattern.compile("[0-9]");
	Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
	       
	Matcher hasLetter  = letter.matcher(stadAttrValue);
	Matcher hasDigit   = digit.matcher(stadAttrValue);
	Matcher hasSpecial = special.matcher(stadAttrValue);

	if(hasLetter.find() && hasDigit.find() && hasSpecial.find())
	{
	   Assert.assertTrue(true);
	   logger.debug("StreetAddress entered is:" + stadAttrValue);
	   System.out.println("StreetAddress entered is:" + stadAttrValue);
	   logger.info("StreetAddress textbox is accepting both alphanumeric and specialchracters");
	   System.out.println("StreetAddress textbox is accepting both alphanumeric and specialchracters");
    }
	 else
	 {
		logger.info("Street textbox is not accepting both alphanumeric and specialchracters");
		System.out.println("Street textbox is not accepting both alphanumeric and specialchracters");
	    Assert.assertTrue(false);
	    
	 }
	    
	  }
@Test(priority=20,dependsOnMethods={"TC_verifyCountryDropdownDefaultValue"},description="TestCase verifies Apt textbox accepts alphanumeric characters.")
  public void TC_verifyAptStebldgTextbox() throws InterruptedException, IOException
  {
	
	String  xlfileName="ABFieldsData.xlsx";
	
	String[] data=XLUtils.getRowData(xlfileName,"sheet1",1);
	String Aptnum=data[3];
	addressBook.setAptText(Aptnum);
	String aptAttrValue=addressBook.getAptAttributeValue();
	Thread.sleep(1500);
    Pattern letter  = Pattern.compile("[a-zA-z]");
    Pattern digit   = Pattern.compile("[0-9]");
    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
       
    Matcher hasLetter  = letter.matcher(aptAttrValue);
    Matcher hasDigit   = digit.matcher(aptAttrValue);
    Matcher hasSpecial = special.matcher(aptAttrValue);

    if(hasLetter.find() && hasDigit.find() && hasSpecial.find())
    {
      Assert.assertTrue(true);
      logger.debug("Apartment entered is:"+aptAttrValue);
      System.out.println("Apartment entered is:"+aptAttrValue);
      logger.info("Apt textbox is accepting both alphanumeric and specialchracters");
      System.out.println("Apt textbox is accepting both alphanumeric and specialchracters");
    }
    else
    {
      logger.info("Apt textbox is not accepting both alphanumeric and specialchracters");
      System.out.println("Apt textbox is not accepting both alphanumeric and specialchracters");
      Assert.assertTrue(false);
     
    }
    
  }
 
 @AfterClass
public void teardown()
{
driver.close();
logger.info("WebPage is closed");
System.out.println("WebPage is closed");
}
}
  //End of AddressBookPage testcases
