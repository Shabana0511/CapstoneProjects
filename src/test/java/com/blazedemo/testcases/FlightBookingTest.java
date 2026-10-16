package com.blazedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

public class FlightBookingTest extends BaseClass {
	@Test
    public void verifyCompleteFlightBookingFlow() throws InterruptedException {
        System.out.println("-Starting End-to-End Flight Booking Test -");

        //STAGE 1: Home Page Action
         System.out.println("Home Page URL: " + hp.getAppurl());
        
        //STAGE 2: Search & Navigate to Reserve Page
        // This action returns the ReservePage object
        ReservePage rp = hp.Search_Flight("Boston", "New York");
        
        // Verify Reserve Page state
        Assert.assertTrue(rp.getReservePageURL().contains("reserve"), "Failed to land on Reserve Page!");
        System.out.println("Reserve Page Heading: " + rp.getReservePageHeading());

        //STAGE 3: Select Flight & Navigate to Purchase Page
        // Chaining the action method that returns the fresh PurchasePage object
        PurchasePage pp = rp.selectFlight();
        
        // Verify Purchase Page state
        Assert.assertTrue(pp.getAppUrl().contains("purchase"), "Failed to land on Purchase Page!");
        System.out.println("Purchase Page Heading: " + pp.PurchaseHeading());
        pp.getDetails(); // Prints flight details to the console

        // STAGE 4: Fill Checkout Form & Navigate to Confirmation Page
             ConfirmationPage cp = pp.getUSerDataa(
            "Zohan", 
            "502 Solapur", 
            "Solapur", 
            "MH", 
            "4133", 
            "Visa", 
            "456774333", 
            "Nov", 
            "2029", 
            "Aru Zohu Kibu"
        );

        // 5. STAGE 5: Final Confirmation Assertions
        Assert.assertEquals(cp.getPageTitle(), "BlazeDemo Confirmation", "Confirmation Page title mismatch!");
        
        String finalHeading = cp.getConfirmationHeading();
        System.out.println("Final Success Message: " + finalHeading);
        Assert.assertTrue(finalHeading.contains("Thank you for your purchase"), "Success heading mismatch!");

        // Capture the system generated booking ID
        String bookingId = cp.getConfirmationId();
        System.out.println("Booking successfully completed! Order ID: " + bookingId);
        
        System.out.println("- End-to-End Flight Booking Test Passed-");
    }
}
