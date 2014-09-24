package testUtils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.espn.cricinfo.TestBase;

public class Helper extends TestBase
{
	public void browser()	//To select browser
	{
		if (con.getProperty("BrowserType").equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
			
		}
		
		else if (con.getProperty("BrowserType").equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if (con.getProperty("BrowserType").equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			Assert.fail("Select Browser Correctly choose either Firefox or Chrome or IE");
		}
	}
	
	public void maxbrowser()   //To maximize browser
	{
		driver.manage().window().maximize();
	}
	
	public void browsererror() 	//To handle browser errors
	{
		lo.debug("Checking for browser error");
		String currenturl = driver.getTitle();
		if (currenturl.toLowerCase().contains("Problem loading page") || currenturl.toLowerCase().contains("is not available") || currenturl.toLowerCase().contains("cannot display the webpage"))
		  {
			  Assert.fail("Check your Internet Connectivity or URL specfied");
		  }
		  else
		  {
			  return;
		  }
	}
	
	public boolean screenshot(String filename) throws Exception
	  {
		  f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(f, new File(System.getProperty("user.dir")+"\\src\\screenshots\\"+filename+".jpg"));
		return false;
	  }
	
	public void sleep(int seconds)
	{
		try
		{
			Thread.sleep(seconds*1000);
		}
		catch(Exception e)
		{
			
		}
	}

}
