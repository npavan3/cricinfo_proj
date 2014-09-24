package com.espn.cricinfo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testUtils.Helper;

public class cricinfo extends Helper {
 
  @BeforeMethod
  public void beforeresults() 
  {
	    h.browser();
		lo.debug("Opening Browser");
		h.maxbrowser();
		lo.debug("Maximizing Browser");
  }

  @Test
  public void results() throws Exception 
  {
	  lo.debug("Opening URL");
	  driver.get(con.getProperty("url"));
	  
	  Actions a = new Actions(driver);
	  a.moveToElement(driver.findElement(By.className(or.getProperty("homebtn_classname")))).build().perform();
	  
	  if (driver.findElements(By.id(or.getProperty("resultscontainer_id"))).size() == 0)
	  {
		lo.debug("Verifying results container");  
		Assert.fail("Results container not available");
	  }
	  
	  if (driver.findElements(By.linkText(or.getProperty("results_link"))).size() == 0) 
	  {  
		Assert.fail("Results tab not available");
	  }
	  lo.debug("Clicking results tab");
	  driver.findElement(By.id(or.getProperty("resultscontainer_id"))).findElement(By.id(or.getProperty("restab2_id"))).findElement(By.linkText(or.getProperty("results_link"))).click();
	  h.sleep(2);
	  
	  List<WebElement> lis = driver.findElement(By.id(or.getProperty("resulttab_id"))).findElement(By.id(or.getProperty("international_id"))).findElements(By.tagName(or.getProperty("link_tagname")));
	  Reporter.log("No.of Results:"+lis.size()+"<br>");
	  lo.debug("Reading available links");
	  lo.debug("Getting HREF attributes to links");
	  lo.debug("Clicking on links");
	  lo.debug("Reading current page url");
	  for (int i = 0; i < lis.size(); i++)
	  {
		  List<WebElement> lis1 = driver.findElement(By.id(or.getProperty("resulttab_id"))).findElement(By.id(or.getProperty("international_id"))).findElements(By.tagName(or.getProperty("link_tagname")));
		  
		  String s = lis1.get(i).getAttribute("href");
		  lis1.get(i).click();
		  String s1 = driver.getCurrentUrl();
		  if (s.equalsIgnoreCase(s1))
		  {
			  Reporter.log("Navigated to correct link");
			System.out.println("URL Passed");
		  }
		  else
		  {
			  Reporter.log("Navigated to incorrect link");
			  System.out.println("URL Failed");
			  h.screenshot("Results error");
		  }
		  driver.navigate().back();
		  h.sleep(2);
		  
		  Actions a1 = new Actions(driver);
		  a1.moveToElement(driver.findElement(By.className(or.getProperty("homebtn_classname")))).build().perform();
		  
		  h.sleep(2);
		  driver.findElement(By.id(or.getProperty("resultscontainer_id"))).findElement(By.id(or.getProperty("restab2_id"))).findElement(By.linkText(or.getProperty("results_link"))).click();
	  }	  
	  lo.debug("closing browser");
	  driver.close();
  }
  
}
