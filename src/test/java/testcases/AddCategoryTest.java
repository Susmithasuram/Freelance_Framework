package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.CategoryPage;
import pages.HomePage;
import pages.LoginPage;

public class AddCategoryTest extends BaseClass
{
	HomePage home;

	@Test(description = "This test will verify valid login scenario",groups={"Smoke"},dataProvider = "login",dataProviderClass = CustomDataProvider.class)
	public void validLoginTest(String username,String password)
	{
		LoginPage login=new LoginPage(driver);
		
		home= login.loginToApp(username, password);
		
		Assert.assertTrue(driver.findElement(By.className("welcomeMessage")).isDisplayed());
		
	}
	
	@Test(dependsOnMethods = "validLoginTest",dataProvider = "AddCategoryDetails",dataProviderClass = CustomDataProvider.class)
	public void addCategory(String categoryName,String updateCategory)
	{
		CategoryPage category= home.clickOnManageCategory();
		category.switchToCategoryWidow();
		category.clickAddNewCategory();
		category.enterCategory(categoryName);
		category.updateCategory(categoryName, updateCategory);
		Assert.assertTrue(category.isCategoryUpdated(updateCategory));
		category.deleteCategory(updateCategory);
		Assert.assertTrue(category.isCategoryDeleted(updateCategory));
		System.out.println("Category is deleted");
	}
}
