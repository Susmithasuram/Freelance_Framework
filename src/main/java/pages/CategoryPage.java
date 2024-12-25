package pages;

import java.sql.Driver;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.Utility;

public class CategoryPage
{
	
	WebDriver driver;
	String categoryName;
	
	public CategoryPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	By addNewCategory=By.xpath("//button[normalize-space()='Add New Category']");

	By updateBtn=By.xpath("//tr//td[text()='ETL']//following::button[2]");
	
	By categories=By.xpath("//tr//td[1]");
	
	By deleteCategory=By.xpath("//button[text()='Delete']");
	public void switchToCategoryWidow()
	{
		String parent=driver.getWindowHandle();
		Set<String> allWindows= driver.getWindowHandles();
		for(String child:allWindows)
		{
			if (parent!=child) 
			{
				driver.switchTo().window(child);
			}
		}
		
	}
	
	
	public void clickAddNewCategory() 
	{
		Utility.clickElement(driver, addNewCategory);
	}
	
	public void enterCategory(String category)
	{
		Alert alt= Utility.switchToAlert(driver,10);
		categoryName=category;
		alt.sendKeys(categoryName);
		alt.accept();
	}
	
	public void updateCategory(String category,String updateCategory)
	{
		Utility.clickElement(driver, By.xpath("//tr//td[text()='"+category+"']//following::button[2]"));
        Alert alt=Utility.switchToAlert(driver, 15);
        alt.sendKeys(updateCategory);
        alt.accept();
		
	}
	
	public boolean isCategoryUpdated(String updateCategoryName)
	{
		//return Utility.CheckValueIsPresentInList(driver,categories,updateCategoryName);
		
		return Utility.checkPresenceOfValue(driver, By.xpath("//tr//td[text()='"+updateCategoryName+"']"), updateCategoryName);
	}
	
	public void deleteCategory(String updateCategoryName)
	{
		Utility.clickElement(driver, By.xpath("//tr//td[text()='"+updateCategoryName+"']//following::button[1]"));
		Utility.clickElement(driver, deleteCategory, 15);
	}
	
	public boolean isCategoryDeleted(String updateCategoryName)
	{
		return Utility.checkValueIsNotPresent(driver, By.xpath("//tr//td[text()='"+updateCategoryName+"']"));

	}
	
}
