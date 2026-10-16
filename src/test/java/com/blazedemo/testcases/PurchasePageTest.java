package com.blazedemo.testcases;

import org.testng.annotations.Test;

import com.blazedemo.pages.PurchasePage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class PurchasePageTest extends BaseClass {
	PurchasePage pp;
	@Test
	    public void verifyCompletePurchaseWorkflow() throws InterruptedException {
	        // Navigate from Home to Purchase Page 
	        pp=hp.Search_Flight("Paris", "New York").selectFlight();
	        
	        //Verify Purchase Page URL
	        String url = pp.getAppUrl();
	        System.out.println("Current URL is: " + url);
	        
	        //Verify Purchase Page Heading Text
	        String heading = pp.PurchaseHeading();
	        System.out.println("Heading is: " + heading);
	        
	        //Verify Flight Details
	        pp.getDetails(); 
	        
	        //Enter User Data and Submit Order
	        pp.getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", "CC", "456774333", "Nov", "2016", "Aru Zohu Kibu");
	        
	        //Confirm order landing status
	        Assert.assertTrue(driver.getCurrentUrl().contains("confirmation"), "Purchase Flow Failed to Complete!");
	        System.out.println("Purchase Completed Successfully!");
	        
	        /*driver.get("https://blazedemo.com/purchase.php");
	        pp = new PurchasePage(driver); // Re-initializes page elements cleanly
	        Thread.sleep(2000); 
	        
	        pp.getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", "CC", "", "Nov", "2016", "Aru Zohu Kibu");
	        Assert.assertTrue(driver.getCurrentUrl().contains("confirmation"), "Purchase Flow Failed to Complete!_blank CC");*/
	        
	        System.out.println("Final page title : "+driver.getCurrentUrl());
	    }

}
