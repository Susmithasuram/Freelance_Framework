package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import helper.Utility;

public class HomePage 
{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	private By welcomeMsg=By.className("welcomeMessage");
	
	private By cartCount=By.xpath("//span[@class='count']");
	
	private By cartBtn=By.xpath("//button[text()='Cart']");
	
	
	
	
	
	private By manage=By.xpath("//span[normalize-space()='Manage']");
	
	private By manageCourses=By.xpath("//a[normalize-space()='Manage Courses']");
	
	private By manageCategory=By.xpath("//a[normalize-space()='Manage Categories']");
	
	public String getWelcomeMsg()
	{
		String welcomeMessage=driver.findElement(welcomeMsg).getText();
		
		return welcomeMessage;
	}
	
	  public  void  addCourseToCart(String courseName) 
	  {
		  Utility.clickElement(driver,By.xpath("//h2[text()='"+courseName+"']//following::button[text()='Add to Cart'][1]"),10);
	  }
	  
	  public int getCartCount() 
	  {
		  String cartText= Utility.findElement(driver, cartCount).getText();
		 
		int cartCnt=Integer.parseInt(cartText);
		return cartCnt;
	  }
	  
	  public Cart_page clickCartButton()
	  {
		  
		  Utility.findElement(driver,cartBtn).click();
		  return new Cart_page(driver);
	  }
	
	
	
	
	public Courses clickOnManageCourse()
	{
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(manage)).perform();
		
		driver.findElement(manageCourses).click();
		
		Courses course=PageFactory.initElements(driver, Courses.class);
		
		return course;
	}
	
	public Category clickOnManageCategory()
	{
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(manage)).perform();
		
		driver.findElement(manageCategory).click();
		
		return PageFactory.initElements(driver, Category.class);
	}

}
