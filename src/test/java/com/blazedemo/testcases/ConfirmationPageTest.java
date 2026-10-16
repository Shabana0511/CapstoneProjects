package com.blazedemo.testcases;

import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class ConfirmationPageTest extends BaseClass {
	
	@Test
	public void verifySuccessfulOrderConfirmation() throws InterruptedException {
	// 1. Step through the POM chain to dynamically land on the final Confirmation Page
	 ConfirmationPage cp = hp.Search_Flight("Paris", "New York").selectFlight()
	                                .getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", 
	                                              "CC", "456774333", "Nov", "2016", "Aru Zohu Kibu");

	        // 2. Verify Page Title
	        String title = cp.getPageTitle();
	        System.out.println("Confirmation Page Title: " + title);
	        Assert.assertEquals(title, "BlazeDemo Confirmation", "Title verification failed!");

	        // 3. Verify Success Message Heading
	        String heading = cp.getConfirmationHeading();
	        System.out.println("Success Heading: " + heading);
	        Assert.assertTrue(heading.contains("Thank you for your purchase"), "Heading verification failed!");

	        // 4. Capture and display the generated Booking ID
	        String bookingId = cp.getConfirmationId();
	        System.out.println("Generated Order ID is: " + bookingId);
	       
	    }
	

}
