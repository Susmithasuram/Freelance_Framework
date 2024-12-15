package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.Utility;

public class CoursesPage 
{
	WebDriver driver;
	
	public CoursesPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	

	
	private By name_select=By.xpath("//select[@name='sortField']");
	
	private By cNames=By.xpath("//tbody//tr//td[2]");
	
	
	
	private By addCourse=By.xpath("//button[text()='Add New Course ']");
	
	private By uploadFile=By.xpath("//input[@type='file']");
	
	private By cName=By.xpath("//input[@name='name']");
	
	private By desc=By.id("description");
	
	private By trainer=By.id("instructorNameId");
	
	private By price=By.id("price");
	
	private By strtDate=By.name("startDate");
	
	private By startdates=By.xpath("//div[@role='option' and @aria-disabled='false' ]");
	
	private By endDate=By.name("endDate");
	
	private By endDates=By.xpath("//div[@role='option']");
	
	private By category=By.xpath("//div[text()='Select Category']");
	
	private By categoryName=By.xpath("//button[text()='Selenium']");
	
	private By saveBtn=By.xpath("//button[text()='Save']");
	
	
	
	
	public String getUrl()
	{
		String manageUrl= driver.getCurrentUrl();
		System.out.println("Manage page url"+manageUrl);
		return manageUrl;
	}
	
	
	
	
	public List<String> getCourseNamesInAscOrderBeforeSorting()
	{
		List<WebElement> courseElements= Utility.findElements(driver,cNames);
		
		List<String> courseNamesBeforeSort=new ArrayList<>();
		
	    for(WebElement ele:courseElements)
	    {
	    	String name=ele.getText();
	    	courseNamesBeforeSort.add(name);
	    }
	    System.out.println("Course names before ascending order "+courseNamesBeforeSort);
	    
		Collections.sort(courseNamesBeforeSort);
		
		System.out.println("CourseNames are stored in list in ascending order");
		
		System.out.println("Course Names in ascending order "+courseNamesBeforeSort);
		return courseNamesBeforeSort;
	}

	public void sortByName(String selectValue) 
	{
		Utility.selectValue(driver, name_select,selectValue);
		
	}
	
	public List<String> getCourseNamesAfterSorting()
	{
		List<WebElement> courseElements= Utility.findElements(driver,cNames);
		
		List<String> courseNamesAftersort=new ArrayList<>();
		
		for(WebElement ele:courseElements)
		{
			courseNamesAftersort.add(ele.getText());
		}
		
		System.out.println("CourseNames are stored in list after sorting by name");
		
		System.out.println("Course Names after sorting by name "+courseNamesAftersort);
		return courseNamesAftersort;
	}
	
	
	
	
	
	public void clickOnAddCourse()
	{
		Utility.clickElement(driver, addCourse, 15);
	}
	
	public void enterCourseDetails(String filePath,String courseName,String description,String instructor,String priceValue,
			String startDateValue,String endDateValue,String categoryName)
	{
		Utility.findElement(driver, uploadFile).sendKeys(filePath);
		Utility.findElement(driver, cName, 10).sendKeys(courseName);
		Utility.findElement(driver, desc, 10).sendKeys(description);
		Utility.findElement(driver, trainer,10).sendKeys(instructor);
		Utility.findElement(driver, price, 10).sendKeys(priceValue);
		
		Utility.clickElement(driver, strtDate, 10);
		Utility.selectValueFromList(driver,startdates,startDateValue);
		
		Utility.clickElement(driver, endDate, 10);
		Utility.selectValueFromList(driver,endDates,endDateValue);
		
		Utility.clickElement(driver, category, 10);
        Utility.clickElement(driver,By.xpath("//button[text()='"+categoryName+"']"), 10); 
		
		Utility.clickElement(driver, saveBtn, 10);
		
	}
	
	
	public boolean verifyCourseIsAdded(String courseToBe_added)
	{
		return Utility.CheckValueIsPresentInList(driver, cNames, courseToBe_added);
      
	}
	
	
}
