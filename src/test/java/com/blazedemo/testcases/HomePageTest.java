package com.blazedemo.testcases;

import org.testng.annotations.Test;

import com.blazedemo.pages.ReservePage;
import com.blazedemo.testcases.BaseClass;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
public class HomePageTest extends BaseClass {
	   
  @Test(priority = 1)
  public void validateListOfOptions_Source() throws InterruptedException
  {
	 	
	  int t=hp.getAllDepartureOptions().size();
	  Assert.assertEquals(t, 7);
	  System.out.println("Total Source are: "+t);
  }
  
 
  @Test(priority = 2)
  public void validateListOfOptions_des() throws InterruptedException
  {
	  int t=hp.getAlldesOptions().size();
	  Assert.assertEquals(t, 7);
	  System.out.println("Total Source are: "+t);
  }
 
  @Test(priority = 3)
   public void findFlights() throws InterruptedException 
   {
	  hp.Search_Flight("Paris","New York");
	 String url=driver.getCurrentUrl();
	  Assert.assertTrue(url.contains("reserve"),"Successfully landed to next page");
     // return new ReservePage(driver);
   }

}
