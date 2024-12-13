package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.Utility;

public class EnrollPage
{
	WebDriver driver;
	public EnrollPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By address=By.id("address");
	private By phone=By.id("phone");
	private By enrollCourse=By.xpath("//div[@class='modal-content']//button[text()='Enroll Now']");
		
	private By totalPrice=By.xpath("//div[@class='modal-content']//b");
	
	private By orderID=By.xpath("//h4[text()='Your order id is ']//b");
	
	public void enterDetailsfor_enrolling(String place,String phnum)
	{
		Utility.findElement(driver, address).sendKeys(place);
		Utility.findElement(driver,phone).sendKeys(phnum);
		Utility.clickElement(driver, enrollCourse, 10);
	}
	
	public int getPrice()
	{
	  String price=	Utility.findElement(driver, totalPrice,20).getText();
	  String priceValue= price.replace("₹","");
	  int Courseprice=Integer.parseInt(priceValue);
	  return Courseprice;
	}
	
	public String getOrderId() 
	{
		return Utility.findElement(driver, orderID,15).getText();
	}
}
