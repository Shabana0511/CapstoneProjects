package com.blazedemo.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage  {

	 private WebDriver driver;
	    private WebDriverWait wait;

	    public HomePage(WebDriver driver) throws InterruptedException {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	        
	        Thread.sleep(2000);
	    }

	    @FindBy(xpath="//select[@name='fromPort']")
	    WebElement sourcedd;
	  
	    @FindBy(xpath="//select[@name='toPort']")
	    WebElement desdd;
	        
	    @FindBy(xpath="//input[@value='Find Flights']")
	    WebElement FindFlightbtn;
	    
	    public String getAppurl()
	    {
	    	return driver.getCurrentUrl();
	    }
	    
	    public List<WebElement> getAllDepartureOptions() throws InterruptedException {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='fromPort']")));
	    	Select source = new Select(sourcedd);
	    	Thread.sleep(2000);
	    	List<WebElement> all=source.getOptions();
	    	return all;
		}
	    public List<WebElement> getAlldesOptions() throws InterruptedException
	    {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='toPort']")));
	    	Select des = new Select(desdd);
		   	Thread.sleep(2000);
	    	List<WebElement> all=des.getOptions();
	    	return all;
	    }
	    

	    public ReservePage Search_Flight(String s, String d) throws InterruptedException {
	        // FIXED: Wait for the dropdown using a raw By locator first to ensure the page has loaded
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='fromPort']")));
	        
	        Select source = new Select(sourcedd);
	        source.selectByValue(s);
	        Thread.sleep(2000);
	        Select des = new Select(desdd);
	        des.selectByValue(d);

	        // FIXED: Explicitly wait for the submission button using a By locator before clicking
	        WebElement actionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Find Flights']")));
	        actionButton.click();
	        
	        return new ReservePage(driver);
	        
	        
	    }


		
}
