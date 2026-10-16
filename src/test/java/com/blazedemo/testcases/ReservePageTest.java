package com.blazedemo.testcases;

import org.testng.annotations.Test;

import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

import org.testng.Assert;
//import org.testng.annotations.BeforeTest;

public class ReservePageTest extends BaseClass{
	ReservePage rp;
	 @Test(priority=1)
	 public void getReservePageTitle() throws InterruptedException
	 {
		 //hp is homepage class object
		 rp = hp.Search_Flight("Paris", "New York");
		 String title = rp.getReservePageTitle();
	        System.out.println("Page Title: " + title);
	 }
	 @Test(priority=2)
	 public void getReservePageURL()
	 {  
		 String url = rp.getReservePageURL();
	        System.out.println("Page URL: " + url);
	 }
	 @Test(priority=3)
	 public void verifyReservePageHeading() throws InterruptedException
	 {
		 
		 String s =rp.getReservePageHeading();
		 Assert.assertTrue(s.contains("Flights from Paris to New York"));
		 
	 }
	 @Test(priority=4)
	 public void BookFlight() throws InterruptedException
	 {
		 rp.selectFlight();
		 Assert.assertTrue(driver.getCurrentUrl().contains("purchase"), "Failed to navigate to the Purchase Page!");
	        System.out.println("Successfully moved to Purchase Page!");
		 //return new PurchasePage(driver);
	 }
	 
	 
}
