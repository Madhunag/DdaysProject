package com.DollarDays.pageObjects;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DollarDays.utilities.ElementUtils;

public class LoginPage 
{
	public WebDriver driver;
	ElementUtils elementUtils ;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver, this);  //(argument driver,this)
		elementUtils = new ElementUtils(driver);
	}

	//Login EMail TextBox
	@FindBy(id="inputLoginUsername")
	private WebElement wbEmailTxtBox;	
	
	//Login Password TextBox
	@FindBy(id="inputLoginPassword")
	private WebElement wbPwdTxtBox;	
	
	//Signin Button
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div/div/div/div[2]/div/div[1]/div/div/div[4]/button")
	private WebElement wbSigninBtn;	
	
	//Alert Text(label) Displayed
	@FindBy(xpath="//*[@id='aspnetForm']/div[5]/div/div/div/div[1]/div/div/div/div/div[2]") 
	private WebElement wbAlertLbl;
		
	//Xmark in AlertBox
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/div[5]/div/div/div/div[1]/div/div/div/a")
	private WebElement wbAlertX;	
	
	//Forgot Email or Password Link
	@FindBy(linkText="Forgot your email address or password?")
	private WebElement wbForgotlink;	
	
	//Create Account Button
	@FindBy(linkText="CREATE ACCOUNT")
	private WebElement wbCreateAcctBtn;
	
	public void clearEmailTxt() //clears EMail TextBox webelement
	{
		elementUtils.clearText(wbEmailTxtBox);           //		wbEmailTxtBox.clear();
	}	
	
	public void setEmailTxt(String email) //clears and enters text value to  EMail TextBox webelement
	{
		elementUtils.enterText(wbEmailTxtBox, email);    //		wbEmailTxtBox.sendKeys(email);
	}	
	
	public void clearPwdTxt()
	{
		elementUtils.clearText(wbPwdTxtBox);
	}	
	
	public void setPwdTxt(String pwd)
	{
		elementUtils.enterText(wbPwdTxtBox, pwd);
	}	
	
	public String checkRequiredEmail()
	{
		return elementUtils.getElementAttribute(wbEmailTxtBox, "required");//returns a String true if field has the attribute 'required' , else returns null
	}	
	
	public String checkRequiredPwd()
	{
		return elementUtils.getElementAttribute(wbPwdTxtBox, "required");//returns a String true if field has the attribute 'required' , else returns null
	}	
	
	public void clickSignInBtn()
	{
		elementUtils.performElementClickReturn(wbSigninBtn);           //		wbSigninBtn.sendKeys(Keys.RETURN);
	}	
	
	public String getAlertTxt()
	{
		return elementUtils.getElementText(wbAlertLbl);
	}
	
	public void clickAlertX()
	{
		elementUtils.performElementClick(wbAlertX);
	}	
	
	public void clickForgotLink()
	{
		elementUtils.performElementClick(wbForgotlink);
	}	
	
	public void clickCreateAcctBtn()
	{
		elementUtils.performElementClick(wbCreateAcctBtn);
	}	

}
