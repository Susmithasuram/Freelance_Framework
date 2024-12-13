package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.Utility;

public class Cart_page
{

WebDriver driver;
	
	public Cart_page(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By courseAdded=By.xpath("//h2[@class='name']");
	
	private By enroll_btn=By.xpath("//button[text()='Enroll Now']");
	
	 public String getCourseAddedInCart()
	  {
		return  Utility.findElement(driver,courseAdded,15).getText();
	  }
	 
	 public EnrollPage clickEnroll()
		{
			Utility.clickElement(driver, enroll_btn,15);
			return new EnrollPage(driver);
		}
}
