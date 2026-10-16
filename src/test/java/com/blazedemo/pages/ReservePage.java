package com.blazedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.blazedemo.testcases.BaseClass;


public class ReservePage  {

	private WebDriver driver;
    private WebDriverWait wait;

    public ReservePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath ="//div[contains(@class, 'container')]//h3")
    WebElement ReservePageText;
    
    public String getReservePageTitle() throws InterruptedException {
    	Thread.sleep(2000);
        return driver.getTitle();
    }
    
    public String getReservePageURL() {
        return driver.getCurrentUrl();
    }
    
    public String getReservePageHeading() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(ReservePageText));
        Thread.sleep(2000);
        return ReservePageText.getText();
    }
    
    public PurchasePage selectFlight() throws InterruptedException {
        // 1. Wait until the first flight row selection button is completely visible on screen
        WebElement firstFlightBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//table[@class='table']//tbody/tr[1]//input[@type='submit']")
            
        ));
        
        // 2. Explicitly wait for it to be clickable before performing the action hook
        wait.until(ExpectedConditions.elementToBeClickable(firstFlightBtn)).click();
        Thread.sleep(2000);
        return new PurchasePage(driver);
    }
    
	
}
