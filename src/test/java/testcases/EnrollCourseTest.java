package testcases;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.Cart_page;
import pages.EnrollPage;
import pages.HomePage;
import pages.LoginPage;

public class EnrollCourseTest extends BaseClass
{

	HomePage home;
	
	@Test(dataProvider = "login",dataProviderClass = CustomDataProvider.class,priority = 0)
	public void validLoginTest(String userName,String password) 
	{
		LoginPage login=new LoginPage(driver);
		home= login.loginToApp(userName, password);
		Assert.assertTrue(home.getWelcomeMsg().contains("Welcome"));
	}

	@Test(dataProvider = "CourseInfo",dataProviderClass =CustomDataProvider.class,priority = 1)
	public void enrollCourse(String courseName,String address,String phone)
	{
		HomePage home=new HomePage(driver);
		home.addCourseToCart(courseName);
		
		Assert.assertTrue(home.getCartCount()>0);
		
		Cart_page cart= home.clickCartButton();
		
		Assert.assertTrue(cart.getCourseAddedInCart().equalsIgnoreCase(courseName));
		
		EnrollPage enroll= cart.clickEnroll();
		
		int price= enroll.getPrice();
		
		System.out.println("Price is "+price);
		
		enroll.enterDetailsfor_enrolling(address, phone);
		
		Assert.assertTrue(!(enroll.getOrderId()==null));
	}
}
