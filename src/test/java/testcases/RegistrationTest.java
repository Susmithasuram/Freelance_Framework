package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationTest extends BaseClass{

	
	
	@Test(description = "This test will verify Home Page scenario",groups={"Smoke"},dataProvider = "newUsers",dataProviderClass = CustomDataProvider.class)
	public void validateHomePage(String username, String password, String email, String intrest, String gender, String state, String hobb) {
	LoginPage lp = new LoginPage(driver);	
	RegistrationPage registerUser = lp.registerUser();
	String text = registerUser.createUser(username, password, email, intrest, gender, state, hobb);
	Assert.assertTrue(text.contains("Signup successfully"));
		
		
	}
	
	
}
