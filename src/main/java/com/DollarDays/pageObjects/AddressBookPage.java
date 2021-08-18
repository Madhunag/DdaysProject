package com.DollarDays.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import com.DollarDays.utilities.ElementUtils;



public class AddressBookPage 
{
	public WebDriver driver;
	ElementUtils elementUtils ;
	public AddressBookPage(WebDriver driver)
	{
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);  //(argument driver,this)
		elementUtils = new ElementUtils(driver);
		
	}	
	
	//My Account Title
	@FindBy(xpath="/html/body/form/div[5]/div[1]/section/div/div[1]/h2[1]")
	private WebElement wbaddressBookTitle;
	public String getAddressBookPageTitle()
	{
	 return elementUtils.getElementText(wbaddressBookTitle);
	} 
	
	//MY ADDRESS BOOK Header
	@FindBy(xpath="/html/body/form/div[5]/div[1]/section/div/div[2]/h2")
	private WebElement wbaddressBookHeader;
	public String getAddressBookPageHeader()
	{
	 return elementUtils.getElementText(wbaddressBookHeader);
	}
	
	
	
//	@FindBy(xpath="//h5[text()='+ Add a new ship-to address']")
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div")
	private WebElement wbaddNewShipToAddressLink;
	public void clickAddNewShipToAddressLink() throws InterruptedException
	{
		/*new WebDriverWait(driver, 15).until(
	      ExpectedConditions.elementToBeClickable(wbaddNewShipToAddressLink));*/
	
		/*JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].elementUtils.performElementClick(wbaddNewShipToAddressLink)");*/
		
		
		/*Actions actions = new Actions(driver);
	   actions.moveToElement(wbaddNewShipToAddressLink);
		actions.perform();*/
	
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wbaddNewShipToAddressLink);
		Thread.sleep(500); 
	    elementUtils.performElementClick(wbaddNewShipToAddressLink);
	}
	
	//+ADD A NEW SHIP TO ADDRESS  Link Display
	public boolean checkAddNewShipToAddressLinkDisplay()
	{
		return elementUtils.isElementDisplayed(wbaddNewShipToAddressLink);
	}
	
	//AddressBook LeftPanel LinkTexts List
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li")
	private List<WebElement> wbABLeftPaneLinkTexts;
	public List<String> getAddressBookLeftPanelLinkTexts()
	{
	 return  elementUtils.getListofText(wbABLeftPaneLinkTexts);
	}
	
	
	//AddressBook LeftPanel LinkTexts List Converted to Array
	public String[] getAddressBookLeftPanelLinkTextsArray()
	{
		return elementUtils.getArrayofText(wbABLeftPaneLinkTexts);
	}
		
	//Account Overview Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[1]/a/span")
	private WebElement wbAccountOverviewLink;
	public void clickAccountOverviewLink()
	{
		elementUtils.performElementClick(wbAccountOverviewLink);
	}
	
	//MyWishList Link
	@FindBy(xpath="//*[@id='aspnetForm']/header/div[2]/div/div/div[3]/div/ul/li[1]/ul/li[4]/a")
	private WebElement wbMyWishListLink;
	public void clickMyWishListLink()
	{
		elementUtils.performElementClick(wbMyWishListLink);
	}
	
	//My Profile Link in AddressBook page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[2]/a/span")
	private WebElement wbMyProfileLink;
	public void clickMyProfileLink()
	{
		elementUtils.performElementClick(wbMyProfileLink);
	}
	
	//My Address Book Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[3]/a/span")
	private WebElement wbMyAddressBookLink;
	public void clickMyAddressBookLink()
	{
		elementUtils.performElementClick(wbMyAddressBookLink);
	}
	
	//My Wallet Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[4]/a/span")
	private WebElement wbMyWalletLink;
	public void clickMyWalletLink()
	{
		elementUtils.performElementClick(wbMyWalletLink);
	}
	
	//Tax Exempt Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[5]/a/span")
	private WebElement wbTaxExemptLink;
	public void clickTaxExemptLink()
	{
		elementUtils.performElementClick(wbTaxExemptLink);
	}
	
	//My Favorites Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[1]/div/div/ul/li[6]/a/span")
	private WebElement wbMyFavoritesLink;
	public void clickMyFavoritesLink()
	{
		elementUtils.performElementClick(wbMyFavoritesLink);
	}
	
	//Order History Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div/section/div/div[3]/div/div[1]/div/div/ul/li[7]/a/span")
	private WebElement wbOrderHistoryLink;
	public void clickOrderHistoryLink()
	{
		elementUtils.performElementClick(wbOrderHistoryLink);
	}
	
	//Log Out Link in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div/section/div/div[3]/div/div[1]/div/div/ul/li[8]/a/span")
	private WebElement wbLogOutLink;
	public void clickLogOutLink()
	{
		elementUtils.performElementClick(wbLogOutLink);
	}
	
	
	//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/div/ul/li[1]/a'
    //Edit  Link in Address Book Page
    @FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/div/ul/li[1]/a")
	private WebElement wbEditShipToAdddressLink;
	public  void clickEditLink()
	{
		elementUtils.performElementClick(wbEditShipToAdddressLink);
	}
	
    //Set As Default Link in Address Book Page
    @FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/div/ul/li[3]/a")
    private  WebElement wbSetAsDefaultLink;
	public void clickSetAsDefaultLink()
	{
		
		elementUtils.performElementClick(wbSetAsDefaultLink);
	}
	
	//Delete Link In Address Book Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/div/ul/li[2]/a")
	private  WebElement wbDeleteAddressLink;
	public void clickDeleteAddressLink()
	{
		elementUtils.performElementClick(wbDeleteAddressLink);
	}
	
	//checkMark on the right top corner of the AddressBox in AddressBook Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/div[1]/i")
	private  WebElement wbSavedAddressCheckMark;
	public boolean checkCheckMarkDisplayInAddressBookPage()
	{
		return  elementUtils.isElementDisplayed(wbSavedAddressCheckMark);
	}

	//Address Text in Account overview page
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/section/div/div[3]/div/div[2]/div/div[2]/div[1]/div/p")
	private  WebElement wbSavedShipToAddressTextInAccountOverviewPage;
	public String getSavedAddressTextInAccountOverviewPage()
	{
		return elementUtils.getElementText(wbSavedShipToAddressTextInAccountOverviewPage);
	}
	
	//Address Text in Address Book Page
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]/div/p")
	private WebElement wbSavedShipToAddresstextInAddressBookPage;
	public  String getSavedAddressTextInAddressBookPage()
	{
		return elementUtils.getElementText(wbSavedShipToAddresstextInAddressBookPage);
	}
	
  	//AddressBox display
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div[1]/section/div/div[3]/div/div[2]/div/section/div[1]/div[2]")
	private WebElement wbShipToAddressBoxDisplay;
	public boolean checkSavedAddressBoxDisplay()
	{
		return elementUtils.isElementDisplayed(wbShipToAddressBoxDisplay);
	}
	
	//ConfirmationPopupBox
	@FindBy(xpath="//*[@id='confirmationBox']/div/div")
	private	 WebElement wbConfirmationBox;
	public boolean checkConfirmationBoxDisplay()
	{
	   return elementUtils.isElementDisplayed(wbConfirmationBox);
	}
	
	//YesButton in ConfirmationPopupBox
	@FindBy(xpath="//*[@id='confirmationBox']/div/div/div[3]/button[1]")
	private WebElement wbYesButton;
	public void clickYesButtonInConfirmationBox()
	{
		elementUtils.performElementClick(wbYesButton);
	}
	
	//NoButton in ConfirmationBox
	@FindBy(xpath="//*[@id='confirmationBox']/div/div/div[3]/button[2]")
	private WebElement wbNoButton;
	public void clickNoButtonInConfirmationBox()
	{
		elementUtils.performElementClick(wbNoButton);
	}
	
	//Country dropdown
	@FindBy(xpath="//*[@id=\'ctl00_cphContent_ddlcountry\']")
	WebElement wbCountry;
	public boolean checkCountryIsADropdown()
	{
		return elementUtils.isElementDropDown(wbCountry);
	}
	
	//Country's dropdown  default value
	public String getCountryDropdownDefaultValue()
	{
		return elementUtils.getDropdownFirstSelectedOption(wbCountry);
    }
	
	//selecting country option from country dropdown list by visible text
	public Select getCountryDropdownValueByVisibleText(String str)
	{
    return elementUtils.getDropdownValueByVisibleText(wbCountry,str);
    }
	
	//selecting country option from country dropdown list by index
	public Select getCountryDropdownValueByIndex(int index)
	{
    return elementUtils.getDropdownValueByIndex(wbCountry,index);
    }
	
	//State dropdownlist options
	@FindBy(xpath="//*[@id='ctl00_cphContent_ddlshipstate']")
	private WebElement wbStateDropdownListOptions;
	public List<String> getStateDropdownListOptions()
	{
		return elementUtils.getDropdownListOptions(wbStateDropdownListOptions);
    }
	
	//commercial location with shipping dock radio button
	@FindBy(xpath="//*[@id='divTxtShipAddr']/div/div[12]/div/ul/li[1]/label")
	private WebElement wbReceivTypeWthCommShipDock;
	public void clickReceivTypeCommWthShipDock()
	{
		elementUtils.performElementClick(wbReceivTypeWthCommShipDock);
	}
	
	//commercial location without shipping dock radio button
	@FindBy(xpath="//*[@id='divTxtShipAddr']/div/div[12]/div/ul/li[2]/label")
	private WebElement wbReceivTypeWthoutCommShipDock;
	public void clickReceivTypeCommWithoutShipDock()
	{
		elementUtils.performElementClick(wbReceivTypeWthoutCommShipDock);
	}
	
	//residential location radio button
	@FindBy(xpath="//*[@id=\"divTxtShipAddr\"]/div/div[12]/div/ul/li[3]/label")
	private WebElement wbReceivTypeResidential;
	public void clickReceivTypeResidential()
	{
		elementUtils.performElementClick(wbReceivTypeResidential);
	}
	
	//Company Name Alert
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_rfvName\"]")
	private WebElement wbcompanyNameAlert;
	public String getcompanyNameAlertMsg()
	{
		return elementUtils.getElementText(wbcompanyNameAlert);
	}
	
		
	//save changes button
	@FindBy(xpath="//*[@id='ctl00_cphContent_btnAddAddress']")
	private WebElement wbSaveChangesBtn;
	public void clickSaveChangesButton()
	{
		elementUtils.performElementClick(wbSaveChangesBtn);
	}
	
	//First name alert message
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtfirstname-error\"]")
    private WebElement wbFirstNameAlert;
	public String getFirstNameAlertMsg()
	{
		return elementUtils.getElementText(wbFirstNameAlert);
    }
	
	//Last name alert message
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtlastname-error\"]")
	private WebElement wbLastNameAlert;
	public String getLastNameAlertMsg()
	{
    	return elementUtils.getElementText(wbLastNameAlert);
	}
	
	//Street Address alert message
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtAddrshipaddr1-error\"]")
	private WebElement wbStreetAddressAlert;
	public String getStreetAddressAlertMsg()
	{
	   return elementUtils.getElementText(wbStreetAddressAlert);
	}
	
	//Country alert message
	@FindBy(xpath="//*[@id='ctl00_cphContent_ddlcountry-error']")
	private WebElement wbCountryAlert;
	public String getCountryAlertMsg()
	{
		return elementUtils.getElementText(wbCountryAlert);
	}
	
	//City alert message
    @FindBy(xpath="//*[@id=\"ctl00_cphContent_txtAddrshipcity-error\"]")
	private WebElement wbCityAlert;
	public String getCityAlertMsg()
	{
	 return elementUtils.getElementText(wbCityAlert);
	}
	
	//state alert message
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtshipstate-error\"]")
	private WebElement wbStateAlert;
	public String getStateAlertMsg()
	{
	    return elementUtils.getElementText(wbStateAlert);
    }
	
	//phone alert message
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtAddrshipphone-error\"]")
	private WebElement wbPhonenoAlert;
	public String getPhonenoAlertMsg()
	{
		return elementUtils.getElementText(wbPhonenoAlert);
	}
	
	//zipcode alert message
	@FindBy(xpath="//*[@id=\"txtAddrshipzip-error\"]")
	private WebElement wbZipcodeAlert;
	public String getZipcodeAlertMsg()
	{
        return elementUtils.getElementText(wbZipcodeAlert);
	}
	
	//FirstName textbox
	@FindBy(xpath="//*[@id=\'ctl00_cphContent_txtfirstname\']")
	private WebElement wbFirstName;
	public void setFirstNameText(String str)
	{
		elementUtils.enterText(wbFirstName,str);
	}
	
	//getting the firstname entered value
    public String getFirstNameAttributeValue()
	{
	    return elementUtils.getElementAttribute(wbFirstName,"value");
	}
    
    //getting firstname attribute's maxlength
    public String getFirstNameAttrMaxlength()
    {
    	return elementUtils.getElementAttribute(wbFirstName,"maxlength");
    }
	
    //LastName textbox
    @FindBy(xpath="//*[@id=\"ctl00_cphContent_txtlastname\"]")
	private WebElement wbLastName;
	public void setLastNameText(String str)
	{
		elementUtils.enterText(wbLastName,str);
	}
	
	//getting the lastname entered value
	public String getLastNameAttributeValue()
	{
	    return elementUtils.getElementAttribute(wbLastName,"value");
    }
	
	//getting the lastname attribute's maxlength
	public String getLastNameAttrMaxlength()
	{
	    return elementUtils.getElementAttribute(wbLastName,"maxlength");
    }
	
	
    //street address textbox
	@FindBy(xpath="//*[@id='ctl00_cphContent_txtAddrshipaddr1']")
	private WebElement wbStreetAddress;
	public void setStrtAddrText(String str)
	{
		elementUtils.enterText(wbStreetAddress,str);
    }
	
	//getting street address entered value
	public String getStrtAddrAttributeValue()
	{
	   return elementUtils.getElementAttribute(wbStreetAddress,"value");
	}
	
	
	//company name textbox
	@FindBy(xpath="//*[@id='ctl00_cphContent_txtAddrshipcompany']")
	private WebElement wbCompanyName;
	public void setCompanyNameText(String str)
	{
		elementUtils.enterText(wbCompanyName,str);
    }
	
	//getting company name entered value
	public String getCompanyNameAttrValue()
	{
	return elementUtils.getElementAttribute(wbCompanyName,"value");
    }
	

	//Apt textbox
	@FindBy(xpath="//*[@id='ctl00_cphContent_txtAddrshipaddr2']")
	private WebElement wbApt;
	public void setAptText(String str)
	{
		elementUtils.enterText(wbApt,str);
    }
	
	//getting Apt entered value
	public String getAptAttributeValue()
	{
		return elementUtils.getElementAttribute(wbApt,"value");
	}
	
	
	//City textbox
	@FindBy(xpath="//*[@id=\'ctl00_cphContent_txtAddrshipcity\']")
	private WebElement wbCity;
	public void setCityText(String str)
	{
		elementUtils.enterText(wbCity,str);
    }
	
	//getting city attribute'-----------------+s value
	public String getCityAttr()
	{
		return elementUtils.getElementAttribute(wbCity,"value");
     }
	

	//selectting state dropdown value by visibleText method
	@FindBy(xpath="//*[@id=\'ctl00_cphContent_ddlshipstate\']")
	private WebElement wbState;
	public Select getStateByVisibleText(String str)
	{
		return elementUtils.getDropdownValueByVisibleText(wbState,str);
    }
	
	//Selecting state dropdown value by index method
    public void getStateByindex(int index)
	{
		 Select dropdownvalue=elementUtils.getDropdownValueByIndex(wbState,index);
    }
	

	
    //Getting state's dropdown list
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_ddlshipstate\"]")
	private WebElement wbStateDropdownList;
	public List<String> getStateDropdownList()
	{
		return elementUtils.getDropdownListOptions(wbStateDropdownList);

    }
	
	//Phoneno textbox
	@FindBy(xpath="//*[@id=\'ctl00_cphContent_txtAddrshipphone\']")
	private WebElement wbPhoneNumber;
	public void setPhoneNumberText(String str)
	{
		elementUtils.enterText(wbPhoneNumber,str);
    }

    //Ext textbox
	@FindBy(xpath="//*[@id=\"ctl00_cphContent_txtAddrshipphoneExt\"]")
	private WebElement wbExt;
	public void setExtText(String str)
	{
		elementUtils.enterText(wbExt,str);
    }
	
	//Zipcode textbox
	@FindBy(xpath="//*[@id=\"txtAddrshipzip\"]")
	private WebElement wbZipCode;
	public void setZipCodeText(String str)
	{
		elementUtils.enterText(wbZipCode,str);
    }
	
	//residential location radio button
	@FindBy(xpath="//*[@id='divTxtShipAddr']/div/div[12]/div/ul/li[3]/label")
	private WebElement wbResReceivType;
	public void clickResReceivType()
	{
		elementUtils.performElementClick(wbResReceivType);
    }
	
	//cookies popup box
	@FindBy(xpath="/html/body/form/footer/div[2]/div/p/input")
	private WebElement wbcookiesPopupBox;
	public void closeCookiesPopupBox()
	{
		elementUtils.performElementClick(wbcookiesPopupBox);
	}
	
	// close Alert 
	@FindBy(xpath="//*[@id='aspnetForm']/header/div[1]/h5")
	private WebElement wbAlert;
	public void closeAlert()
	{
		elementUtils.performElementClick(wbAlert);
	}
	
	//getting Name in Account Overview Page 
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/section/div/div[3]/div/div[2]/div/div[2]/div[1]/div/h6")
	private WebElement wbAccountOverviewName;
	public String getAccountOverviewName()
	{
		return elementUtils.getElementText(wbAccountOverviewName);
	}
	
	////getting Address in Account Overview Page 
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/section/div/div[3]/div/div[2]/div/div[2]/div[1]/div/p")
	private WebElement wbAccountOverviewAddress;
	public String getAccountOverviewAddress()
	{
		return elementUtils.getElementText(wbAccountOverviewAddress);
	}
}
	
	
	

	
	
	
	
		
	
	

	
   
	
		
	
	

	
	
	
 


	
	
	
	
	
