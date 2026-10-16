package com.blazedemo.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utilities.ExcelReader;

public class DataDrivenFlightBookingTest extends BaseClass {
	   //Reset to the Home Page before EVERY row run
    @BeforeMethod(alwaysRun = true)
    public void resetToHomePage() throws InterruptedException {
        driver.get("https://blazedemo.com");
        Thread.sleep(2000); //this line added to overcome "Rate Exceed" issue on browser
        hp = new HomePage(driver); // Re-initialize homepage elements
    }

    @DataProvider(name = "BookingUserData")
    public Object[][] getBookingData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/FlightBookingUserData.xlsx";
        String sheetName = "bookingTestData"; 
        return ExcelReader.getExcelData(filePath, sheetName); //ExcelReader is my ExcelReading utility
    }

    // TC:Multiple bookings with different data sets
    @Test(dataProvider = "BookingUserData", priority = 1, groups = {"data-driven"})
    public void verifyMultipleBookings(
            String source, String destination, String name, String address, String city, 
            String state, String zip, String cardType, String cardNumber, String expMonth, 
            String expYear, String nameOnCard) throws InterruptedException {
        
        // for negative check 
    	//if card number is null/empty/invalid  or source destination countries are same
    	boolean isNegative = (cardNumber == null || cardNumber.trim().isEmpty()) || 
                             cardNumber.matches(".*[a-zA-Z].*") || 
                             source.trim().equalsIgnoreCase(destination.trim());
        if (isNegative) return;

        System.out.println("Data-driven Booking for " + name + " ---");
        
     
        ConfirmationPage cp = hp.Search_Flight(source, destination)
                                .selectFlight()
                                .getUSerDataa(name, address, city, state, zip, cardType, cardNumber, expMonth, expYear, nameOnCard);
        
        Assert.assertTrue(cp.getConfirmationHeading().contains("Thank you"));
    }

    // Negative Test Cases
    //if card number is null/empty/invalid  or source destination countries are same
    @Test(dataProvider = "BookingUserData", priority = 2, groups = {"negative"})
    public void verifyNegativeValidationScenarios(
            String source, String destination, String name, String address, String city, 
            String state, String zip, String cardType, String cardNumber, String expMonth, 
            String expYear, String nameOnCard) throws InterruptedException {
        
    	//negative tc- check for any blank field
        boolean hasBlankField = (name == null || name.trim().isEmpty()) || 
                                (address == null || address.trim().isEmpty()) ||
                                (cardNumber == null || cardNumber.trim().isEmpty());

        // Same departure and destination city
        if (source.trim().equalsIgnoreCase(destination.trim())) {
            System.out.println("-Negative Same City Check -");
            hp.Search_Flight(source, destination);
            if (driver.getCurrentUrl().contains("reserve")) {
                System.out.println("Bug Tracked-BlazeDemo allowed matching cities."); //just to inform
                // failing as per requirement
                Assert.fail("Booking proceeded with identical cities!");
            }
            return;
        }

        // Navigate to purchase page for form validation checks
        ReservePage rp = hp.Search_Flight(source, destination);
        PurchasePage pp = rp.selectFlight();
        pp.getUSerDataa(name, address, city, state, zip, cardType, cardNumber, expMonth, expYear, nameOnCard);
        String currentUrl = driver.getCurrentUrl();

        // Blank credit card / Blank fields
        if (hasBlankField) {
            System.out.println("Running TC05: Negative Blank Field Check ");
            if (currentUrl.contains("confirmation")) {
                System.out.println("Bug Tracked-BlazeDemo allowed empty forms to process."); //just for information
                //failing as per requirement
                Assert.fail("Form submitted despite blank fields!");
            }
        } 
        // Invalid credit card characters
        else if (cardNumber.matches(".*[a-zA-Z].*")) { 
            System.out.println("-Negative Invalid Characters Check ");
            if (currentUrl.contains("confirmation")) {
                System.out.println("Bug Tracked-BlazeDemo bypassed credit card format checks.");
                Assert.fail("Non-numeric card bypassed format checks!");
            }
        }
    }
}

