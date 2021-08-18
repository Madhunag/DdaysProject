package com.DollarDays.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DollarDays.utilities.ElementUtils;

public class MyProfilePage 
{
	
	WebDriver driver;
	ElementUtils elementUtils ;
	public MyProfilePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  //(argument driver,this)
		elementUtils = new ElementUtils(driver);
	}	

	//UserAccountName
	@FindBy(xpath="//*[@id='aspnetForm']/header/div[2]/div/div/div[3]/div/ul/li[1]/a/span")
	private WebElement wbAccountNameIcon;	
	public String getTextAccountNameIcon()
	{
		return elementUtils.getElementText(wbAccountNameIcon); //return wbAccountNameIcon.getText()
	}
	public void clickAccountNameIcon()
	{
     	elementUtils.performElementClick(wbAccountNameIcon); // wbAccountNameIcon.click();
	}	
	
		
	//User Dropdownlinks list
	@FindBy(xpath="//ul[@class='dropdown-menu logMenu']/li/a") 
	private List <WebElement> wbUserDDlinks;
	public String[] getUserDropDownLinksArray()
	{
		return elementUtils.getArrayofText(wbUserDDlinks);
	}
	
	
	//Accounts Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Accounts")
	private WebElement wbDDAccountsLink;
	public void clickDropDownAccountsLink()
	{
     	elementUtils.performElementClick(wbDDAccountsLink);
	}	
	
	//Order History Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Order History")
	WebElement wbDDOrderHistoryLink;
	public void clickDropDownOrderHistoryLink()
	{
     	elementUtils.performElementClick(wbDDOrderHistoryLink);
	}	
	
	//My Wishlist Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="My Wishlist")
	private WebElement wbDDWishlistLink;
	public void clickDropDownWishlistLink()
	{
     	elementUtils.performElementClick(wbDDWishlistLink);
	}	
	
	//Favorites Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Favorites")
	private WebElement wbDDFavoritesLink;
	public void clickDropDownFavoritesLink()
	{
     	elementUtils.performElementClick(wbDDFavoritesLink);
	}
		
	//Update Profile Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Update Profile")
	private WebElement wbDDUpdateProfileLink;
	public void clickDropDownUpdateProfileLink()
	{
     	elementUtils.performElementClick(wbDDUpdateProfileLink);
	}	
	
	//Address Book Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Address Book")
	private WebElement wbDDAddressBookLink;
	public void clickDropDownAddressBookLink()
	{
     	elementUtils.performElementClick(wbDDAddressBookLink);
	}	
	
	//Wallet Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Wallet")
	private WebElement wbDDWalletLink;
	public void clickDropDownWalletLink()
	{
     	elementUtils.performElementClick(wbDDWalletLink);
	}	
	
	//Tax Exempt Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Tax Exempt")
	private WebElement wbDDTaxExemptLink;
	public void clickDropDownTaxExemptLink()
	{
     	elementUtils.performElementClick(wbDDTaxExemptLink);
	}	
	
	//Request a Quote Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Request a Quote")
	private WebElement wbDDRequestQuoteLink;
	public void clickDropDownRequestQuoteLink()
	{
     	elementUtils.performElementClick(wbDDRequestQuoteLink);
	}	
	
	//Contact Us Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Contact Us")
	private WebElement wbDDContactUsLink;
	public void clickDropDownContactUsLink()
	{
     	elementUtils.performElementClick(wbDDContactUsLink);
	}
		
	//Sign Out Link in UserAccountNameIcon Dropdown on top right
	@FindBy(linkText="Sign Out")
	private WebElement wbDDSignOutLink;
	public void clickDropDownSignOutLink()
	{
     	elementUtils.performElementClick(wbDDSignOutLink);
	}	
	
	//My Profile Heading
	@FindBy(xpath="//div[@class='heading_my-account']/h2")
	private WebElement wbMyProfileHeading;
	public String getTextMyProfileHeading()
	{
		return elementUtils.getElementText(wbMyProfileHeading);    
	}	
	
	//Get in String Array format the list of  Left Panel Links
	@FindBy(xpath="//ul[@class='hidden-xs']/li/a")
	private List <WebElement> wbleftPanelLinks;
	public String[] getleftPanelLinksArray()
	{
		return elementUtils.getArrayofText(wbleftPanelLinks);
	}
	
	//User Account name displayed in left Panel 
	@FindBy(xpath="//div[@class='account-type']/h6")
	private WebElement wbleftPanelAccountName;
	public String getTextleftPanelAccountName()
	{
		return elementUtils.getElementText(wbleftPanelAccountName);    
	}	
	
	//Account Overview Page Link in Left Panel 
	@FindBy(linkText="Account Overview")
	private WebElement wbleftPanelAccountOverviewLink;	
	public void clickleftPanelAccountOverview()
	{
     	elementUtils.performElementClick(wbleftPanelAccountOverviewLink);
	}	
	
	//My Profile Page Link in Left Panel 
	@FindBy(linkText="My Profile")
	private WebElement wbleftPanelMyProfileLink;	
	public void clickleftPanelMyProfile()
	{
     	elementUtils.performElementClick(wbleftPanelMyProfileLink);
	}	
	
	//My Address Book Page Link in Left Panel 
	@FindBy(linkText="My Address Book")
	private WebElement wbleftPanelMyAddressBook;	
	public void clickleftPanelMyAddressBook()
	{
     	elementUtils.performElementClick(wbleftPanelMyAddressBook);
	}	
		
	//My Wallet Page Link in Left Panel 
	@FindBy(linkText="My Wallet")
	private WebElement wbleftPanelMyWallet;	
	public void clickleftPanelMyWallet()
	{
     	elementUtils.performElementClick(wbleftPanelMyWallet);
	}	
	 
	//Tax Exempt Page Link in Left Panel 
	@FindBy(linkText="Tax Exempt")
	private WebElement wbleftPanelTaxExempt;	
	public void clickleftPanelTaxExempt()
	{
     	elementUtils.performElementClick(wbleftPanelTaxExempt);
	}	
	
	//My Favorites Page Link in Left Panel 
	@FindBy(linkText="My Favorites")
	private WebElement wbleftPanelMyFavorites;	
	public void clickleftPanelMyFavorites()
	{
     	elementUtils.performElementClick(wbleftPanelMyFavorites);
	}	

	//My Wishlist Page Link in Left Panel 
	@FindBy(linkText="My Wishlist")
	private WebElement wbleftPanelMyWishlist;	
	public void clickleftPanelMyWishlist()
	{
     	elementUtils.performElementClick(wbleftPanelMyWishlist);
	}	

	//Order History Page Link in Left Panel 
	@FindBy(linkText="Order History")
	private WebElement wbleftPanelOrderHistory;	
	public void clickleftPanelOrderHistory()
	{
     	elementUtils.performElementClick(wbleftPanelOrderHistory);
	}	
		
	//Log Out Link in Left Panel 
	@FindBy(linkText="Log Out")
	private WebElement wbleftPanelLogOut;	
	public void clickleftPanelLogOut()
	{
     	elementUtils.performElementClick(wbleftPanelLogOut);
	}	
	
	
	// LeftPanel links list
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li") 
	private List <WebElement> wbleftPanelLinksList;
	public List<String> getleftPanelLinksList()
	{
		return elementUtils.getListofText(wbleftPanelLinksList);
	}

	
	//FirstName Textbox in PERSONAL INFORMATION block
	@FindBy(id="ctl00_cphContent_txtFName")
	private WebElement wbTxtFirstName;	
	public void setFirstName(String str)       //Clear any value and Enter the string value in FirstName Textbox
	{
		elementUtils.enterText(wbTxtFirstName, str);
	}	
	public String getValueFirstName()          //get the pre-populated value in First Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtFirstName, "value") ; 
	}
	public String getTooltipFirstName()        //get the value of attribute title in First Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtFirstName, "title") ; 
	}
	public String getPlaceHolderFirstName()    //get the value of attribute placeholder in First Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtFirstName, "placeholder") ; 
	}

	
	
	
	//LastName Textbox in PERSONAL INFORMATION block
	@FindBy(id="ctl00_cphContent_txtLName")
	private WebElement wbTxtLastName;	
	public void setLastName(String str)       //Clear any value and Enter the string value in LastName Textbox
	{
		elementUtils.enterText(wbTxtLastName, str);
	}	
	public String getValueLastName()          //get the pre-populated value in Last Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtLastName, "value");  
	}
	public String getTooltipLastName()        //get the value of attribute title in Last Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtLastName, "title") ; 
	}
	public String getPlaceHolderLastName()    //get the value of attribute placeholder in Last Name Textbox
	{
		return elementUtils.getElementAttribute(wbTxtLastName, "placeholder") ; 
	}	
	
	
	
	//Primary Phone Number Textbox in PERSONAL INFORMATION block
	@FindBy(id="ctl00_cphContent_txtPhoneMain")
	private WebElement wbTxtPrimaryPh;	
	public void setPrimaryPhone(String str)   //Clear any value and Enter the string value in Primary Phone Textbox
	{
		elementUtils.enterText(wbTxtPrimaryPh, str);
	}	
	public String getValuePrimaryPhone()      //get the pre-populated value in Primary phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtPrimaryPh, "value");  
	}
	public String getTooltipPrimaryPhone()    //get the value of attribute title in Primary phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtPrimaryPh, "title") ; 
	}
	public String getPlaceHolderPrimaryPhone()//get the value of attribute placeholder in Primary Phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtPrimaryPh, "placeholder") ; 
	}	
	
	
	
	
	
	//Ext Textbox in PERSONAL INFORMATION block
	@FindBy(id="ctl00_cphContent_txtPhoneMainExt")
	private WebElement wbTxtExt;	
	public void setExtension(String str)       //Clear any value and Enter the string value in Ext Textbox
	{
		elementUtils.enterText(wbTxtExt, str);
	}	
	public String getValueExtension()          //get the pre-populated value in Ext Textbox
	{
		return elementUtils.getElementAttribute(wbTxtExt, "value");  
	}
	public String getTooltipExtension()        //get the value of attribute title in Ext Textbox
	{
		return elementUtils.getElementAttribute(wbTxtExt, "title") ; 
	}
	public String getPlaceHolderExtension()    //get the value of attribute placeholder in Ext Textbox
	{
		return elementUtils.getElementAttribute(wbTxtExt, "placeholder") ; 
	}
	
	
	
	
	//Secondary Phone Number Textbox in PERSONAL INFORMATION block
	@FindBy(id="ctl00_cphContent_txtPhoneBill")
	private WebElement wbTxtSecondaryPh;	
	public void setSecondaryPhone(String str)       //Clear any value and Enter the string value in Secondary Phone Textbox
	{
		elementUtils.enterText(wbTxtSecondaryPh, str);
	}	
	public String getValueSecondaryPhone()          //get the pre-populated value in Secondary Phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtSecondaryPh, "value");  
	}
	public String getTooltipSecondaryPhone()        //get the value of attribute title in Secondary Phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtSecondaryPh, "title") ; 
	}
	public String getPlaceHolderSecondaryPhone()    //get the value of attribute placeholder in Secondary Phone Textbox
	{
		return elementUtils.getElementAttribute(wbTxtSecondaryPh, "placeholder") ; 
	}
	
	
	
	//SAVE CHANGES Button in PERSONAL INFORMATION  block
	@FindBy(id="ctl00_cphContent_btnUpdatePersonalInfo")
	private WebElement wbSaveChangesPersonalInfoBtn;
	public void clickSaveChangesPersonalInfoBtn()
	{
		elementUtils.performElementClickReturn(wbSaveChangesPersonalInfoBtn);
	}	

	//Error Message when FirstName field is empty 
	@FindBy(xpath="//*[@class='col-md-6 col-md-6 clearfix']/div[1]/span")
	private WebElement wbFirstNameReqAlertMsg;	
	public String getTextFirstNameReqAlertMsg()
	{
		return elementUtils.getElementText(wbFirstNameReqAlertMsg);
	}
	

	//Error Message when LastName field is empty 
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/span")
	private WebElement wbLastNameReqAlertMsg;	
	public String getTextLastNameReqAlertMsg()
	{
		return elementUtils.getElementText(wbLastNameReqAlertMsg);
	}	
	
	//Error Message when Primary Phone field is empty 
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[2]/div[2]/div[3]/div/span")
	private WebElement wbPrimPhReqAlertMsg;	
	public String getTextPrimPhReqAlertMsg()
	{
		return elementUtils.getElementText(wbPrimPhReqAlertMsg);
	}	

	//Email Address displayed in LOGIN INFORMATION  block
	@FindBy(xpath="//*[@id='ctl00_cphContent_pnlPassword']/h4/span")
	private WebElement wbUserDisplayedEmail;	
	public String getTextUserDisplayedEmail()
	{
		return elementUtils.getElementText(wbUserDisplayedEmail);   
	}
	
	//Current Password textbox in  LOGIN INFORMATION  block
	@FindBy(id="ctl00_cphContent_txtCurrentPassword")
	private WebElement wbTxtCurrentPwd;	
	public void setCurrentPwd(String pwd)            //Clear any value and Enter the string value in Current password Textbox
	{
		elementUtils.enterText(wbTxtCurrentPwd, pwd);
	}	
	public String getPlaceHolderCurrentPwd()          //get the value of attribute placeholder in Current Password Textbox
	{
		return elementUtils.getElementAttribute(wbTxtCurrentPwd, "placeholder") ; 
	}
	//New Password textbox in  LOGIN INFORMATION  block
	@FindBy(id="ctl00_cphContent_txtNewPassword")
	private WebElement wbTxtNewPwd;	
	public void setNewPwd(String pwd)            //Clear any value and Enter the string value in New password Textbox
	{
		elementUtils.enterText(wbTxtNewPwd, pwd);
	}		
	public String getPlaceHolderNewPwd()          //get the value of attribute placeholder in New Password Textbox
	{
		return elementUtils.getElementAttribute(wbTxtNewPwd, "placeholder") ; 
	}

	//Retype Password textbox in  LOGIN INFORMATION  block
	@FindBy(id="ctl00_cphContent_txtRepeatedPassword")
	private WebElement wbTxtRetypeNewPwd;	
	public void setRetypeNewPwd(String pwd)         //Clear any value and Enter the string value in Retype password Textbox
	{
		elementUtils.enterText(wbTxtRetypeNewPwd, pwd);
	}	
	public String getPlaceHolderRetypePwd()          //get the value of attribute placeholder in Retype New Password Textbox
	{
		return elementUtils.getElementAttribute(wbTxtRetypeNewPwd, "placeholder") ; 
	}

	//SAVE CHANGES Button in LOGIN INFORMATION  block
	@FindBy(id="ctl00_cphContent_btnUpdatePassword")
	private WebElement wbSaveChangePwdBtn;	
	public void clickSaveChangePwdBtn()
	{
		//elementUtils.performElementClick(wbSaveChangePwdBtn);
		elementUtils.performElementClickReturn(wbSaveChangePwdBtn);
	}	
	
	//Error Message when Current Password is empty 
	@FindBy(xpath="//*[@id='ctl00_cphContent_pnlPassword']/div/div[1]/div/span")
	private WebElement wbCurrentPwdReqAlertMsg;	
	public boolean CheckCurrentPwdReqAlertMsg()
	{
		return elementUtils.isElementDisplayed(wbCurrentPwdReqAlertMsg);
	}	

	//Error Message when New Password is empty 
	@FindBy(xpath="//*[@id='ctl00_cphContent_pnlPassword']/div/div[2]/div/span")
	private WebElement wbNewPwdReqAlertMsg ;	
	public boolean CheckNewPwdReqAlertMsg()
	{
		return elementUtils.isElementDisplayed(wbNewPwdReqAlertMsg);
	}	
	
	//Error Message when Retype Password is empty 
	@FindBy(xpath="//*[@id='ctl00_cphContent_pnlPassword']/div/div[3]/div/span")
	private WebElement wbRetypePwdReqAlertMsg ;	
	public boolean CheckRetypePwdReqAlertMsg()
	{
		return elementUtils.isElementDisplayed(wbRetypePwdReqAlertMsg);
	}	
	
	//Alert message displayed when incorrect value is entered for current password
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[1]/div")
	private WebElement wbWrongPwdAlert;	
	public String getTextWrongPwdAlert()
	{
		return elementUtils.getElementText(wbWrongPwdAlert);   
	}

	//Close the alert for incorrect current password
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[1]/a")
	private WebElement wbWrongPwdAlertClose;	
	public void clickWrongPwdAlertClose()
	{
     	elementUtils.performElementClick(wbWrongPwdAlertClose);
	}	

	//Alert message displayed when there is a mismatch between new Password and Retype password values
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[1]/div")
	private WebElement wbPwdMismatchAlert;	
	public String getTextPwdMismatchAlert()
	{
		return elementUtils.getElementText(wbPwdMismatchAlert);   
	}
	
	//Close the alert for mismatch password
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/div[1]/a")
	private WebElement wbPwdMismatchAlertClose;	
	public void clickPwdMismatchAlertClose()
	{
     	elementUtils.performElementClick(wbPwdMismatchAlertClose);
	}	
	
	
	//emails sent to info in EMAIL PREFERENCES block
	@FindBy(xpath="//*[@class='my_profile-right-detials']/h5/span")
	private WebElement wbSendtoUserEmail;	
	public String getTextSendtoUserEmail()
	{
		return elementUtils.getElementText(wbSendtoUserEmail);   
	}
	
	//My Account Terms link in EMAIL PREFERENCES block 
	@FindBy(xpath="//*[@class='Terms-condation']/ul/li[1]/a")
	private WebElement wbAccountTermsLink;	
	public void clickAccountTermsLink()
	{
     	elementUtils.performElementClick(wbAccountTermsLink);
	}	
	
	//Privacy statement in EMAIL PREFERENCES block 
	@FindBy(xpath="//*[@class='Terms-condation']/ul/li[2]/a")
	private WebElement wbPrivacyStmtLink;	
	public void clickPrivacyStatementLink()
	{
     	elementUtils.performElementClick(wbPrivacyStmtLink);
	}	
	
	// Newsletter Subscription Checkbox in EMAIL PREFERENCES block 
	@FindBy(id="ctl00_cphContent_chkOptinBox1")
	private WebElement wbChkboxEmailPref;	
	public void clickChkboxEmailPref()           //Check or uncheck Preference Checkbox by clicking it
	{
     	elementUtils.performElementClick(wbChkboxEmailPref);
	}	
	public boolean isChkboxEmailPrefSelected()   //Check if Email Preference Checkbox is Checked or not
	{
		return 	elementUtils.isElementSelected(wbChkboxEmailPref);
	}	
	
	//SAVE CHANGES button in EMAIL PREFERENCES block 
	@FindBy(id="ctl00_cphContent_btnUpdateNewsletter")
	private WebElement wbSaveChangeEmailPrefBtn;	
	public void clickSaveChangeEmailPrefBtn()
	{
     	elementUtils.performElementClick(wbSaveChangeEmailPrefBtn);
	}
	
	//Okay button in the acceptcookies popup box
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/footer/div[2]/div/p/input")  
	private WebElement wbCookiesPopupOkayBtn;
	public void clickCookiesPopupOkayBtn()
	{
		elementUtils.performElementClick(wbCookiesPopupOkayBtn);
	}
	
	
	
	
}
