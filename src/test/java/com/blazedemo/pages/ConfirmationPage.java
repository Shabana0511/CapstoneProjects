package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	 protected WebDriver driver;
	    
		public ConfirmationPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
					
		}
		@FindBy(xpath = "//h1[contains(text(),'Thank you')]")
	    WebElement successHeading;

	    @FindBy(xpath = "//table[@class='table']//tr[1]/td[2]")
	    WebElement confirmationId;

	    public String getConfirmationHeading() {
	        return successHeading.getText();
	    }

	    public String getConfirmationId() {
	        return confirmationId.getText();
	    }
	    
	    public String getPageTitle() {
	        return driver.getTitle();
	    }
}
