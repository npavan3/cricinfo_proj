package com.espn.cricinfo;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.Helper;

public class Poll extends Helper
{
 
	@BeforeMethod
	public void beforepoll()
	{
		h.browser();
		lo.debug("Opening Browser");
		h.maxbrowser();
		lo.debug("Maximizing Browser");
	}
  @Test
  public void poll() throws Exception
  {
	  	lo.debug("Opening URL");
	    driver.get(con.getProperty("url"));
		h.sleep(4);
		lo.debug("Verifying Poll");
		List<WebElement> lis = driver.findElement(By.id(or.getProperty("pollcontents_id"))).findElements(By.tagName(or.getProperty("label_tagname")));
		if (lis.size() == 0)
		{
			lo.debug("Poll Options not available");
			Assert.fail("Poll Options not available");
		}
		Reporter.log("Available Poll options<br>");
		for (int i = 0; i < lis.size(); i++) 
		{
			
			//System.out.println(lis.get(i).getText());
			Reporter.log(lis.get(i).getText()+"<br>");
		}
		
		lo.debug("Selecting option randomly");
		Random r = new Random();
		int i = r.nextInt(lis.size());
		lis.get(i).click();
		Reporter.log("<br>Option Selected- : "+lis.get(i).getText()+"<br>");
		//System.out.println("Option Selected- "+ i+1 +": "+lis.get(i).getText());
		h.sleep(4);
		lo.debug("Submitting Poll");
		driver.findElement(By.id(or.getProperty("submitpoll_id"))).click();
		h.sleep(4);
		lo.debug("Reading Poll results");
		List<WebElement> lis1 = driver.findElement(By.id(or.getProperty("pollresult_id"))).findElements(By.tagName(or.getProperty("pollresult_td_tagname")));
		Reporter.log("<br>Poll Results<br><br>");
		for (int j = 0; j < lis1.size(); j++) 
		{
			//System.out.println(lis1.get(j).getText());	
			Reporter.log(lis1.get(j).getText()+"<br>");
		}	 
		lo.debug("closing browser");
		driver.close();
  	}
}
