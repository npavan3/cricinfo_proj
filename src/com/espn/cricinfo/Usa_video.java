package com.espn.cricinfo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Usa_video extends Helper
{
	@BeforeMethod
	public void beforevideo()
	{
		h.browser();
		lo.debug("Opening Browser");
		h.maxbrowser();
		lo.debug("Maximizing Browser");
	}
	
  @Test
  public void Video() throws Exception 
  {
	  lo.debug("Opening URL");
	  driver.get(con.getProperty("url"));
	  h.browsererror();
	  lo.debug("Verifying Edition drop down");
	  if (driver.findElements(By.className(or.getProperty("edidropdown_classname"))).size() == 0)
	  {
		  lo.debug("Edition drop down not available");
		  Assert.fail("Choose Edition Drop down not available");
	  }
	  
	  lo.debug("Clicking on Edition drop down");
	  driver.findElement(By.className(or.getProperty("edidropdown_classname"))).click();
	  List<WebElement> lis = driver.findElement(By.className(or.getProperty("listitems_classname"))).findElements(By.tagName(or.getProperty("list_tagname")));
	  if (lis.size() == 0) 
	  {
		  lo.debug("List items in drop down not available");  
		Assert.fail("List items in drop down not available");
	  }
	  
	  lo.debug("Selecting "+sh.getCell(0, 0).getContents()+" Edition");
	  for (int i = 0; i < lis.size(); i++)
	  {
		
		if (lis.get(i).getText().equalsIgnoreCase(sh.getCell(0, 0).getContents()))
		{
			lis.get(i).click();
			break;
		}
	  }
	if (driver.findElements(By.id(or.getProperty("Videoplayer_id"))).size()!= 0)
	{
		lo.debug("Video available");
		System.out.println("Video Available for "+sh.getCell(0, 0).getContents());
		Reporter.log("Video Available for "+sh.getCell(0, 0).getContents());
	}  
	else
	{
		lo.debug("Video not available");
		System.out.println("Video Not Available for "+sh.getCell(0, 0).getContents());
		Reporter.log("Video Not Available for "+sh.getCell(0, 0).getContents());
		h.screenshot("no video");
	}
	lo.debug("closing browser");
	driver.close();
  }
 
}
