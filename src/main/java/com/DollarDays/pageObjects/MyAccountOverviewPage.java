package com.DollarDays.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DollarDays.utilities.ElementUtils;

public class MyAccountOverviewPage 
{

	WebDriver driver;
	ElementUtils elementUtils ;
	public MyAccountOverviewPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  //(argument driver,this)
		elementUtils = new ElementUtils(driver);
	}	
	
	
	
	//Name in PERSONAL INFORMATION of Account Overview Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/section/div/div[3]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/h4[1]")
	private WebElement wbPersonalInfoName;
	public String getTextPersonalInfoName()
	{
		return elementUtils.getElementText(wbPersonalInfoName);    
	}	
	
	//Primary Phone in PERSONAL INFORMATION of Account Overview Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/section/div/div[3]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/h4[2]")
	private WebElement wbPersonalInfoPrimaryPh;
	public String getTextPersonalInfoPrimaryPh()
	{
		return elementUtils.getElementText(wbPersonalInfoPrimaryPh);    
	}	
	
	//Secondary  in PERSONAL INFORMATION of Account Overview Page
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/section/div/div[3]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/h4[3]")
	private WebElement wbPersonalInfoSecondaryPh;
	public String getTextPersonalInfoSecondaryPh()
	{
		return elementUtils.getElementText(wbPersonalInfoSecondaryPh);    
	}		
}
