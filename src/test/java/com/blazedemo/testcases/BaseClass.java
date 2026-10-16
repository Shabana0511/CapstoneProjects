package com.blazedemo.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

public class BaseClass {
	
	protected WebDriver driver;
	public ReservePage rp;
	public HomePage hp;
	public PurchasePage pp;
	
	@Parameters({"bname"})
	
	@BeforeClass(alwaysRun = true)
	public void setup(String bname) throws InterruptedException
		{
			switch (bname.toLowerCase().trim())
			{
			case "chrome" :driver=new ChromeDriver();break;
			case "firefox" :driver=new FirefoxDriver();break;
			case "edge" :driver=new EdgeDriver();break;
			default : driver=new EdgeDriver();break;
			
		    }
		    //driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://blazedemo.com/");
			
			hp = new HomePage(driver);
		    
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		if (driver != null) {
            driver.quit();
        }
	}
	

}
