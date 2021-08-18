package com.DollarDays.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DollarDays.utilities.ElementUtils;

public class HomePage 																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														
{

	public WebDriver driver;
	ElementUtils elementUtils ;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  //(argument driver,this)
		elementUtils = new ElementUtils(driver);
	}			
		
	//Signin Icon
	@FindBy(xpath="//span[text()='Sign in']")
	private WebElement wbIconSignin;	
	
	//List of links in UserAccountName/Signin Dropdown 
	@FindBy(xpath="//ul[@class='dropdown-menu logMenu']/li") 
	private List <WebElement> wbSigninUserDDlist;
	
	//List of Signin Dropdown links
	@FindBy(xpath="//ul[@class='dropdown-menu logMenu']/li/a") 
	private List <WebElement> wbSigninUserDDlinks;
	
	//SignIn Link in Signin Dropdown
	@FindBy(linkText="Sign In")
	private WebElement wbSignInLink;
	
	//CreateAccount Link in Signin Dropdown
	@FindBy(linkText="Create account")
	private WebElement wbCreateAccLink;
	
	//Help Link in Signin Dropdown
	@FindBy(linkText="Help")
	private WebElement wbHelpLink;
	
	//UserAccountName
	@FindBy(xpath="//*[@id='aspnetForm']/header/div[2]/div/div/div[3]/div/ul/li[1]/a/span")
	private WebElement wbAccountNameIcon;	
	
	//Accounts Link in UserAccountName Dropdown
	@FindBy(linkText="Accounts")
	private WebElement wbAccountsLink;
	
	//Order History Link in UserAccountName Dropdown
	@FindBy(linkText="Order History")
	WebElement wbOrderHistoryLink;
	
	//My Wishlist Link in UserAccountName Dropdown
	@FindBy(linkText="My Wishlist")
	private WebElement wbWishlistLink;
	
	//Favorites Link in UserAccountName Dropdown
	@FindBy(linkText="Favorites")
	private WebElement wbFavoritesLink;
	
	//Update Profile Link in UserAccountName Dropdown
	@FindBy(linkText="Update Profile")
	private WebElement wbUpdateProfileLink;
	
	//Address Book Link in UserAccountName Dropdown
	@FindBy(linkText="Address Book")
	private WebElement wbAddressBookLink;
	
	//Wallet Link in UserAccountName Dropdown
	@FindBy(linkText="Wallet")
	private WebElement wbWalletLink;
	
	//Tax Exempt Link in UserAccountName Dropdown
	@FindBy(linkText="Tax Exempt")
	private WebElement wbTaxExemptLink;
	
	//Request a Quote Link in UserAccountName Dropdown
	@FindBy(linkText="Request a Quote")
	private WebElement wbRequestQuoteLink;
	
	//Contact Us Link in UserAccountName Dropdown
	@FindBy(linkText="Contact Us")
	private WebElement wbContactUsLink;
		
	//Sign Out Link in UserAccountName Dropdown
	@FindBy(linkText="Sign Out")
	private WebElement wbSignOutLink;
	
	//Get the title of page
	public String getHomePageTitle()
	{
		return elementUtils.getWebPageTitle();
	}	
	
	//Get the current url of page
	public String getHomePageUrl()
	{
		return elementUtils.getWebPageUrl();
	}	

	public String getTextIconSignin()
	{
		return elementUtils.getElementText(wbIconSignin);    //return wbIconSignin.getText()
	}	
	
	public void clickIconSignin()
	{
     	elementUtils.performElementClick(wbIconSignin);     //wbIconSignin.click();
	}		
	

	public void clickSignInLink()
	{
     	elementUtils.performElementClick(wbSignInLink);
	}		
	
	public void clickCreateAccLink()
	{
     	elementUtils.performElementClick(wbCreateAccLink);
	}	
	
	public void clickHelpLink()
	{
     	elementUtils.performElementClick(wbHelpLink);
	}	
	
	
	public String getTextAccountNameIcon()
	{
		return elementUtils.getElementText(wbAccountNameIcon); //return wbAccountNameIcon.getText()
	}	
	
	public void clickAccountNameIcon()
	{
     	elementUtils.performElementClick(wbAccountNameIcon);
	}	
	
	public void clickAccountsLink()
	{
     	elementUtils.performElementClick(wbAccountsLink);
	}	
	
	public void clickOrderHistoryLink()
	{
     	elementUtils.performElementClick(wbOrderHistoryLink);
	}	
	
	public void clickWishlistLink()
	{
     	elementUtils.performElementClick(wbWishlistLink);
	}	
	
	public void clickFavoritesLink()
	{
     	elementUtils.performElementClick(wbFavoritesLink);
	}	
	
	public void clickUpdateProfileLink()
	{
     	elementUtils.performElementClick(wbUpdateProfileLink);
	}	
	
	public void clickAddressBookLink()
	{
     	elementUtils.performElementClick(wbAddressBookLink);
	}	
	
	public void clickWalletLink()
	{
     	elementUtils.performElementClick(wbWalletLink);
	}	
	
	public void clickTaxExemptLink()
	{
     	elementUtils.performElementClick(wbTaxExemptLink);
	}	
	
	public void clickRequestQuoteLink()
	{
     	elementUtils.performElementClick(wbRequestQuoteLink);
	}	
	
	public void clickContactUsLink()
	{
     	elementUtils.performElementClick(wbContactUsLink);
	}	
	
	public void clickSignOutLink()
	{
     	elementUtils.performElementClick(wbSignOutLink);
	}	
	
	public List<String> getSignInUserDropDownList()
	{

		return elementUtils.getListofText(wbSigninUserDDlist);
	}
	
	public List<String> getSignInUserDropDownLinksList()
	{
		return elementUtils.getListofText(wbSigninUserDDlinks);
		
	}	
	
	public String[] getSignInUserDropDownLinksArray()
	{

		return elementUtils.getArrayofText(wbSigninUserDDlinks);
	}
	
	
	public void signOutOfDD()
	{
		clickAccountNameIcon();
		clickSignOutLink();
		
	}		
	
	public void goToLoginPage()
	{
		clickAccountNameIcon();
		clickSignInLink();
	}	
	
	
//	//CloseBarPopup
//	@FindBy(xpath="//*[@id='aspnetForm']/header/div[1]/h5")
//	private WebElement wbCloseBarPopup;
	
	//Action on Webelements	
//	public void clickCloseBarPopup()
//	{
//		elementUtils.performElementClick(wbCloseBarPopup);  
//	}	
	
	
}
