package com.DollarDays.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementUtils
{
	public static Logger logger =LogManager.getLogger("DollarDays"); 
	WebDriver driver;
	
	//Constructor with driver initialization
	public ElementUtils(WebDriver driver)
	{
		this.driver = driver;
	}

	//To check if a webelement is displayed
	public boolean isElementDisplayed(WebElement wb)
	{	
		boolean displayed=false;
		try {
			wb.isDisplayed();
			displayed=true;
		    }
		catch(Exception  e)
		{		
			//logger.warn(e.getMessage());
			System.out.println(e.getClass().getSimpleName() + "  " + wb.toString() +   " This WebElement is not present/displayed. "  );
			logger.warn(e.getClass().getSimpleName() + "  " + wb.toString() +   " This WebElement is not present/displayed. ");
			displayed=false;
		}
		return displayed;		
	}
	

	//To check if the web element is clickable or not
	public boolean isElementClickable(WebElement wb)
	{
		boolean clickable = false;
		if(isElementDisplayed(wb))	
		{
			try 
			{
				if(wb.isEnabled()) 
				{  clickable=true;}
			}
			catch(Exception e)
			{
				System.out.println(e.getClass().getSimpleName() + "  " + wb.toString() +   " This WebElement is not clickable. "      );
				logger.error(e.getClass().getSimpleName() + "  " + wb.toString() +   " This WebElement is not clickable. ");
				clickable=false;
			}
		}
		return clickable;
	}
	
	//To do click on a webelement
	public void performElementClick(WebElement wb)
	{
		if(isElementClickable(wb)) 
		{
			wb.click();
		}
	}
	
	//To do click on a webelement
	public void performElementClickReturn(WebElement wb)
	{
		if(isElementClickable(wb))
		{
			wb.sendKeys(Keys.RETURN);
		}
	}

	//To get text of a webelement
	public String getElementText(WebElement wb)
	{
		String text="";
		if(isElementDisplayed(wb))
		{
			text= wb.getText();
		}
		return text;
	}
	
	//To clear a textbox webelement
	public void clearText(WebElement wb)
	{
		if(isElementClickable(wb))
		{
			wb.clear();
		}
	}
	
	//To enter a string to a webelement like textbox after clearing it
	public void enterText(WebElement wb,String strValue)
	{
		if(isElementClickable(wb))
		{
			wb.clear();
			wb.sendKeys(strValue);
		}
	}
 
	
	//To get an attribute of a webelement
	public String getElementAttribute (WebElement wb,String attrName)
	{
		String attr="";
		if(isElementDisplayed(wb))
		{
			attr= wb.getAttribute(attrName);
		}
		return attr;
	}
	
	//To check if a  webelement is selected or not
	public boolean isElementSelected(WebElement wb)
	{
		boolean isSelected=false;
		if(isElementDisplayed(wb)) 
		{
			isSelected=wb.isSelected();
		}
		return isSelected;		
	}
	
	//To fetch the title of a web page driver
	public String getWebPageTitle() {
		String title = "";
		title = driver.getTitle();
		return title;
	}
	
	
	//To fetch the title of a web page driver
	public String getWebPageUrl() {
		String url = "";
		url = driver.getCurrentUrl();
		return url;
	}

	
	
	//To fetch the list of texts from webelement list
    public  List<String> getListofText(List<WebElement> wbList)
    {	   	
    	List<String>  actList = new ArrayList<String>();
		if(!wbList.isEmpty())
		{
		    for( WebElement wb: wbList)
			{
		    	actList.add(wb.getText());
				//System.out.println(wb.getText());
			}
		    return actList;
		}
		else 
		{
			System.out.println( "Web Element List is empty or doesnot exist in this page.So could not fetch the text values " );
			logger.warn("Web Element List is empty or doesnot exist in this page.So could not fetch the text values " );
			return actList;
		}

  }

    //To fetch array of texts from webelement list
    public  String[] getArrayofText(List<WebElement> wbList)
    {    	
		 if(!wbList.isEmpty())
		 {	
				List<String> linksList= getListofText(wbList);
				String arr1[] = linksList.toArray(new String[0]); 
			    return  arr1;
		 }
		 else 
		 {
			System.out.println("WebELement List  is empty or doesnot exist in this page.So could not fetch the text values.");
			logger.warn("Web Element List is empty or doesnot exist in this page.So could not fetch the text values " );
			return null;
		 }    	
    }
    
    //To check if the webelement is a dropdown or not
    public boolean isElementDropDown(WebElement wb)
    {
  	  boolean isElementDropDown=false;
    
  	  if(isElementDisplayed(wb))
  	  {
  		isElementDropDown=wb.getTagName().equals("select");
  	  }
  	  return isElementDropDown;

  	 }
    
    //To select the dropdown value using visibleText method
    public Select getDropdownValueByVisibleText(WebElement wb,String str)
    {
        performElementClick(wb);
    	Select dropdownvalue=new Select(wb);
        dropdownvalue.selectByVisibleText(str);
        return dropdownvalue;
    }
       
    //To select the dropdown value using index method
    public Select getDropdownValueByIndex(WebElement wb,int index)
    {
    	performElementClick(wb);
    	Select dropdownvalue = new Select(wb);
       	dropdownvalue.selectByIndex(index);
    	return dropdownvalue;
    }
    
    //To get the default selected value of the dropdown element
    public String getDropdownFirstSelectedOption(WebElement wb)
	 {
        Select dropdownvalue = new Select(wb);
	    WebElement option = dropdownvalue.getFirstSelectedOption();
	    String  SelectedText = option.getText();
		return SelectedText;
	 }
    
    //To get all the options from the dropdownlist
    public  List<String> getDropdownListOptions(WebElement wb)
	 {
      List<String> optionsList = new ArrayList<String>();
      try
      {
      if(isElementDropDown(wb))
      {
      performElementClick(wb);
	  Select se=new Select(wb);
	  List <WebElement> elements = se.getOptions();
      System.out.println("The States List is"+": " + elements.size());
      int listSize = elements.size();
  	
      for(int i = 1; i < listSize ; i++) 
       {
         String optionsValue=elements.get(i).getText();
         optionsList.add(optionsValue);
       }
      }
      else 
	  {
	   System.out.println( "WebELement List list options is empty or doesnot exist in this dropdown.So could not fetch the text values " );
	   logger.warn("WebELement List is empty or doesnot exist in this page.So could not fetch the text values " );
	   }
      }
      
     catch(Exception E)
      {
    	  System.out.println(wb +" is not a dropdown");
      }
      return optionsList;
	 }
}  
	 

    


