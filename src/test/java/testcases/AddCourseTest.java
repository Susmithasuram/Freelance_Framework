package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.CoursesPage;
import pages.HomePage;
import pages.LoginPage;

public class AddCourseTest extends BaseClass
{
	HomePage home;
	@Test(dataProvider = "login",dataProviderClass = CustomDataProvider.class,priority = 0)
	public void validLoginTest(String userName,String password) 
	{
		LoginPage login=new LoginPage(driver);
		home= login.loginToApp(userName, password);
		Assert.assertTrue(home.getWelcomeMsg().contains("Welcome"));
	}
	
	@Test(priority = 1,dataProvider ="AddCourseDetails",dataProviderClass = CustomDataProvider.class)
	public void addCourse(String filePath,String courseName,String description,String instructor,String priceValue,
			String startDateValue,String endDateValue,String categoryName)
	{
		CoursesPage course= home.clickOnManageCourse();
		
		Assert.assertTrue(course.getUrl().contains("manage"));
		
		//List<String> namesBeforeSort= course.getCourseNamesInAscOrderBeforeSorting();
		
	    //course.sortByName("Name");
	    
	    //List<String> namesAfterSort=course.getCourseNamesAfterSorting();
	    
	    //Assert.assertTrue(namesBeforeSort.equals(namesAfterSort));
		
		course.clickOnAddCourse();
		
		course.enterCourseDetails(filePath,courseName,description,instructor,priceValue,startDateValue,endDateValue,categoryName);
	
		
		Assert.assertTrue(course.verifyCourseIsAdded(courseName));
	
	}

}
