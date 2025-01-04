package helper;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.swing.text.Highlighter;

import org.apache.commons.math3.stat.descriptive.StatisticalMultivariateSummary;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataprovider.ConfigReader;

public class Utility 
{
	
	
	public static void waitForSeconds(int seconds)
	{
		try 
		{
			Thread.sleep(seconds*500);
		} catch (InterruptedException e) 
		{
			
		}	
		
	}
	
	public static String getScreenshotAsBase64(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot;
	}
	
	public static WebElement highlightElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red')", element);
		
		waitForSeconds(1);
		
		js.executeScript("arguments[0].setAttribute('style','border:2px solid black')", element);
		
		return element;
	}
	
	
	
	public static String getCurrentDateTime()
	{
		Date currentDate=new Date();
		
		SimpleDateFormat customFormat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		String newDate=customFormat.format(currentDate);
		
		return newDate;
	}
	
	
	public static WebElement findElement(WebDriver driver,By locator,int time)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
	
		String highLight=ConfigReader.getValue("highlightelement");
		if(highLight.equalsIgnoreCase("true"))
		{
			highlightElement(driver, element);
		}
		return element;
	}
	
	
	public static WebElement findElement(WebDriver driver,By locator)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
	
		String highLight=ConfigReader.getValue("highlightelement");
		if(highLight.equalsIgnoreCase("true"))
		{
			highlightElement(driver, element);
		}
		return element;
	}
	
	public static List<WebElement>  findElements (WebDriver driver,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		List<WebElement> allElements= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
		return allElements;
	}
	
	public static void selectValueFromList(WebDriver driver,By locator,String valueToSearch)
	{
		List<WebElement> allvalues= driver.findElements(locator);
		 for(WebElement ele:allvalues)
	        {
			 String value=ele.getText();
			 
			 int actualdatevalue=Integer.parseInt(value);
			 
			 System.out.println("date value "+value);
			 	int expecteddatevalue=(int )Double.parseDouble(valueToSearch);
	        	if(actualdatevalue==expecteddatevalue)
	        	{
	        		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(ele)).click();
	        		//System.out.println("LOG INFO : Clicked on "+valueToSearch);
	        		break;
	        		
	        		
	        	}
	        }
		
	}
	
	public static boolean checkPresenceOfValue(WebDriver driver,By locator,String updatedValue)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		boolean status= wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, updatedValue));
		
		return status;
	}
	
	public static boolean checkValueIsNotPresent(WebDriver driver,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		boolean status= wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		return status;
	}
	public static boolean CheckValueIsPresentInList(WebDriver driver,By locator,String valueToSearch)
	{
		boolean status =false;
		
		List<WebElement> allvalues= findElements(driver, locator);
		 for(WebElement ele:allvalues)
	        {
	        	String value=ele.getText();
	        	if(value.equals(valueToSearch))
	        	{
	        		highlightElement(driver, ele);
                    System.out.println(value+" value is present");
                    status=true;
	        		break;
	        		
	        		
	        	}
	        }
		
		return status;
	}
	
	
	public static void clickElement(WebDriver driver,By locator,int time)
	{
		WebElement element=null;
		try 
		{
			element=findElement(driver, locator,time);
			
			String highLight=ConfigReader.getValue("highlightelement");
			if(highLight.equalsIgnoreCase("true"))
			{
				highlightElement(driver, element);
			}
			
			element.click();
		}
		catch(Exception e)
		{
			System.out.println("LOG:INFO - Normal Click Failed - Trying With Actions Class Click");
			
			Actions act=new Actions(driver);
			
			try 
			{
				act.scrollToElement(element).click().build().perform();
				
				
				
			} catch (Exception e1) 
			{
				System.out.println("LOG:INFO - Actions Click Failed - Trying With JS Click");
				
				JavascriptExecutor js=(JavascriptExecutor)driver;
				
				js.executeScript("arguments[0].scrollIntoView(true)", element);
				js.executeScript("arguments[0].click()", element);
				
				
			}
			
		}
		
		
	}
	
	public static void clickElement(WebDriver driver,By locator)
	{
		WebElement element=null;
		try 
		{
			element=findElement(driver, locator);
			
			element.click();
		}
		catch(Exception e)
		{
			System.out.println("LOG:INFO - Normal Click Failed - Trying With Actions Class Click");
			
			Actions act=new Actions(driver);
			
			try 
			{
				act.moveToElement(element).click().build().perform();
				
			} catch (Exception e1) 
			{
				System.out.println("LOG:INFO - Actions Click Failed - Trying With JS Click");
				
				JavascriptExecutor js=(JavascriptExecutor)driver;
				
				js.executeScript("arguments[0].click()", element);
			}
			
		}
		
		
	}
	/***
	 * Select by Visible Text
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void selectValue(WebDriver driver,By locator, String text) 
	{
		
		Select sel = new Select(findElement(driver, locator));
		sel.selectByVisibleText(text);
	}
	
	public static void hoverOverElement(WebDriver driver,By locator,int time)
	{
		Actions act=new Actions(driver);
		act.moveToElement(findElement(driver,locator,time)).perform();;
		
	}
	
	public static  Alert switchToAlert(WebDriver driver,int time)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		Alert alt= driver.switchTo().alert();
		
		return alt;
	}

}
