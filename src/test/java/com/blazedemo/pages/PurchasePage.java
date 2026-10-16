package com.blazedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PurchasePage {
    protected WebDriver driver;
	    
	public PurchasePage(WebDriver driver) throws InterruptedException
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		Thread.sleep(2000);	
	}
	
	@FindBy(xpath="//div[contains(@class,'container')]/h2")
	WebElement PurchaseText;
	
	@FindBy(css="div.container>p:nth-of-type(2)")
	WebElement FlightNumber;
	
	@FindBy(xpath = "//input[@name='inputName']")
	WebElement name;
	
	@FindBy(name="address")
	WebElement address;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="state")
	WebElement state;
	
	@FindBy(id="zipCode")
	WebElement zipCode;
	
	@FindBy(id="cardType")
	WebElement cardType;
	
	@FindBy(name="creditCardNumber")
	WebElement cardNumber;
	
	@FindBy(id="creditCardMonth")
	WebElement cardMonth;
	
	@FindBy(id="creditCardYear")
	WebElement cardYear;
	
	
	@FindBy(id="nameOnCard")
	WebElement nameOnCard;
	
	@FindBy(id="rememberMe")
	WebElement rememberMechkbox;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement purchaseFlightbtn;
	
	@FindBy(xpath="//div[contains(@class,'container')]//h1")
    WebElement confirmText;
	
	
	public String getAppUrl() {
        return driver.getCurrentUrl();
        
    }
	public String PurchaseHeading() throws InterruptedException
	{
		Thread.sleep(2000);
		return PurchaseText.getText();
	}
	public void getDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		System.out.println("Flight: "+FlightNumber.getText());
		

	}
	public ConfirmationPage getUSerDataa(String Name, String Address, String City,
			String State, String ZipCode,String CardType,
			String CardNumber,String CardMonth,String CardYear, String NameOnCard) throws InterruptedException
	{
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inputName']")));
		name.sendKeys(Name);
		address.sendKeys(Address);
		city.sendKeys(City);
		state.sendKeys(State);
		zipCode.sendKeys(ZipCode);
		cardType.sendKeys(CardType);
		cardNumber.sendKeys(CardNumber);
		cardMonth.sendKeys(CardMonth);
		cardYear.sendKeys(CardYear);
		nameOnCard.sendKeys(NameOnCard);
		if(!rememberMechkbox.isSelected())
		{
			rememberMechkbox.click();
		}
		purchaseFlightbtn.click();
		Thread.sleep(2000);
		Assert.assertTrue(confirmText.getText().contains("Thank you"));
		
	
	return new ConfirmationPage(driver);
}
}