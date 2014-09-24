package com.espn.cricinfo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import testUtils.Helper;

public class TestBase
{
  	public static WebDriver driver;
  	public static Properties con, or;
  	public static FileInputStream fis, fis1, fis2;
  	public static Workbook w;
  	public static Sheet sh;
  	public static Helper h;
  	public static File f;
  	public static Logger lo;
  	
  @BeforeSuite
  public void beforesuite() throws Exception 
  {
	  con = new Properties();
	  fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
	  con.load(fis);
	  
	  or = new Properties();
	  fis1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\or.properties");
	  or.load(fis1);
	  
	  fis2 = new FileInputStream(System.getProperty("user.dir")+"\\src\\TestData\\testdata.xls");
	  w = Workbook.getWorkbook(fis2);
	  sh = w.getSheet(0);
	  
	  h = new Helper();
	  
	  lo = Logger.getLogger("devpinoyLogger");
	  
	  
  }
}
