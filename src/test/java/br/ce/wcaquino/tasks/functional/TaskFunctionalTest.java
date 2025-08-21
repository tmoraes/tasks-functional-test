package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskFunctionalTest {

	 
	
	public WebDriver getDriver(String url) {
		System.setProperty("webdriver.chrome.driver", "/Users/thiagomoraes/Workspaces/eclipse-workspace/devops/seleniumDrivers/chromedriver-mac-x64/chromedriver");
		WebDriver driver =  new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	
	
		
	
	
	@Test
	public void testSave_TaskSuccessful() {

		WebDriver driver = null;
		try {
			driver = getDriver("http://localhost:8001/tasks/");
					
			WebElement element = driver.findElement(By.id("addTodo"));	
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			element = driver.findElement(By.id("task"));		
			element.sendKeys("Teste via Selenium");
	
			element = driver.findElement(By.id("dueDate"));		
			element.sendKeys("11/12/2025");
	
			element = driver.findElement(By.id("saveButton"));		
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			element = driver.findElement(By.id("message"));		
			Assert.assertEquals("Success!", element.getText());
		}
		catch(Exception e) {Assert.fail(e.getMessage());}
		finally { if(driver!=null) driver.quit(); }
			
	}
	
	@Test
	public void testSave_PastDueDate_DoNotAddTask() {

		WebDriver driver = null;
		try {
			driver = getDriver("http://localhost:8001/tasks/");
			

			WebElement element = driver.findElement(By.id("addTodo"));	
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			element = driver.findElement(By.id("task"));		
			element.sendKeys("Teste via Selenium");
	
			element = driver.findElement(By.id("dueDate"));		
			element.sendKeys("01/01/2025");
	
			element = driver.findElement(By.id("saveButton"));		
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			element = driver.findElement(By.id("message"));		
			Assert.assertEquals("Due date must not be in past", element.getText());
		}
		catch(Exception e) {Assert.fail(e.getMessage());}
		finally { if(driver!=null) driver.quit(); }
		
	}
	
	@Test
	public void testSave_TaskWithoutDescription_DoNotAddTask() {

		WebDriver driver = null;
		try {
			driver = getDriver("http://localhost:8001/tasks/");
			

			WebElement element = driver.findElement(By.id("addTodo"));	
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			element = driver.findElement(By.id("task"));		
			//element.sendKeys("");
	
			element = driver.findElement(By.id("dueDate"));		
			element.sendKeys("11/12/2025");
	
			element = driver.findElement(By.id("saveButton"));		
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			element = driver.findElement(By.id("message"));		
			Assert.assertEquals("Fill the task description", element.getText());
		}
		catch(Exception e) {Assert.fail(e.getMessage());}
		finally { if(driver!=null) driver.quit(); }
		
	}	
	
	@Test
	public void testSave_TaskWithoutDueDate_DoNotAddTask() {

		WebDriver driver = null;
		try {
			driver = getDriver("http://localhost:8001/tasks/");
			

			WebElement element = driver.findElement(By.id("addTodo"));	
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			element = driver.findElement(By.id("task"));		
			element.sendKeys("Teste via Selenium");
	
			element = driver.findElement(By.id("dueDate"));		
			//element.sendKeys("11/12/2025");
	
			element = driver.findElement(By.id("saveButton"));		
			element.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			element = driver.findElement(By.id("message"));		
			Assert.assertEquals("Fill the due date", element.getText());
		}
		catch(Exception e) {Assert.fail(e.getMessage());}
		finally { if(driver!=null) driver.quit(); }
		
	}	
	
}
