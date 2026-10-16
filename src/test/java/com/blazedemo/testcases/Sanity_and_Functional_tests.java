package com.blazedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

public class Sanity_and_Functional_tests extends BaseClass{
	@BeforeMethod(alwaysRun = true)
    public void navigateToHome() throws InterruptedException {
        driver.get("https://blazedemo.com");
        Thread.sleep(2000); 
        hp = new HomePage(driver);// Re-initialize driver 
    }

    @Test(priority = 1, groups = {"smoke"})
    public void verifyHomepageAndDropdowns() {
        System.out.println("TC01: Smoke Test .");
   
        String currentUrl = hp.getAppurl(); 
        
        Assert.assertTrue(currentUrl.contains("blazedemo.com"), "Homepage failed to load!");
        System.out.println("Homepage verified successfully.");
    }

    @Test(priority = 2, groups = {"functional"})
    public void verifyFlightSearch() throws InterruptedException {
        System.out.println("TC02: Functional Search Test .");
        //Search_flight returns Reserve Page object
        ReservePage rp = hp.Search_Flight("Paris", "New York");
        Assert.assertTrue(rp.getReservePageURL().contains("reserve"), "Failed to navigate to Reserve page!");
        System.out.println("Flight search navigation verified successfully.");
    }

    @Test(priority = 3, groups = {"functional"})
    public void verifySingleFlightBooking() throws InterruptedException {
        System.out.println("TC03: Functional Booking Test .");
        //another confirmation verification
          ConfirmationPage cp = hp.Search_Flight("Paris", "New York")
                                .selectFlight()
                                .getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", 
                                              "Visa", "456774333", "11", "2029", "Zohan K");
        
        Assert.assertTrue(cp.getConfirmationHeading().contains("Thank you"), "Booking failed!");
        System.out.println("Single flight booking verified successfully.");
    }
    @Test(priority = 4, groups = {"negative"})
    public void verifyNegativeBlankCreditCard() throws InterruptedException {
        System.out.println("TC04: Negative Blank Field Check");
        
        ReservePage rp = hp.Search_Flight("Paris", "New York");
        PurchasePage pp = rp.selectFlight();
        
        // Passing an empty string for the credit card number
        pp.getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", "Visa", "", "11", "2029", "Zohan K");
        
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("confirmation")) {
            System.out.println("Bug Tracked-BlazeDemo form submitted successfully despite blank credit card fields!");
            Assert.fail("Form processed a blank credit card field.");
        }
    }

    @Test(priority = 5, groups = {"negative"})
    public void verifyNegativeInvalidCardCharacters() throws InterruptedException {
        System.out.println("TC05: Negative Invalid Characters Check ");
        
        ReservePage rp = hp.Search_Flight("Paris", "New York");
        PurchasePage pp = rp.selectFlight();
        
        // Passing alphabet characters inside the credit card text box ("ABC123XYZ")
        pp.getUSerDataa("Zohan", "502 Solapur", "Solapur", "MH", "4133", "Visa", "ABC123XYZ", "11", "2029", "Zohan K");
        
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("confirmation")) {
            System.out.println("Bug Tracked--BlazeDemo processed form successsfully for -alphanumeric text entry!");
            Assert.fail("Non-numeric text value enetered for credit card");
        }
    }

    @Test(priority = 6, groups = {"negative"})
    public void verifyNegativeSameCitySelection() throws InterruptedException {
        System.out.println("TC06: Negative Same City Check");
        
        // Selecting "Paris" as both the departure and arrival destination
        hp.Search_Flight("Paris", "Paris");
        
        if (driver.getCurrentUrl().contains("reserve")) {
            System.out.println("Bug Tracked--BlazeDemo allowed flight searches with identical source and destination choices.");
            Assert.fail("Booking proceeded with same source and destination cities!");
        }
    }
}
