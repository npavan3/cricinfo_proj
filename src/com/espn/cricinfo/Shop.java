package com.espn.cricinfo;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Shop extends Helper
{
 	@BeforeMethod
	public void beforeshoplink()
	{
		h.browser();
		lo.debug("Opening Browser");
		h.maxbrowser();
		lo.debug("Maximizing Browser");
	}
 	
  @Test
  public void shoplink() throws Exception 
  {
	  lo.debug("Opening URL");
	  driver.get(con.getProperty("url"));
	  h.browsererror();
	  lo.debug("Verifying Shop Menu");
      if (driver.findElements(By.linkText(or.getProperty("shop_linktext"))).size() == 0)
      {
    	  lo.debug("Shop Menu not available");  
		  Assert.fail("Shop Menu not available");
      }
    	lo.debug("Verifying HREF attribute");  
	  String s = driver.findElement(By.linkText(or.getProperty("shop_linktext"))).getAttribute(or.getProperty("shop_attribute"));
	  
	  if (s == null)
	  {
		  lo.debug("HREF attribute not available");
		  Assert.fail("HREF not found");
	  }
	  
	  lo.debug("Clicking Shop Link");
	  driver.findElement(By.linkText(or.getProperty("shop_linktext"))).click();
	  h.browsererror();
	  
	  lo.debug("Getting shop page url");
	  String s1 = driver.getCurrentUrl();
	  
	  Reporter.log("Shop link should navigate to :"+s+"<br>");
	  Reporter.log("Shop link navigated to :"+s1+"<br>");
	  
	  if(s1.equalsIgnoreCase(s))
	  {
		  lo.debug("Shop link navigated to shop page");
		  Reporter.log("Pass::Navigated to Shop Link");
	  }
	  else
	  {
		  lo.debug("Shop link not navigated to shop page");
		  Reporter.log("Fail::Not Navigated to Shop Link");
		  h.screenshot("Fail ss");
	  }
	  lo.debug("closing browser");
	  driver.close();  
    }
}
