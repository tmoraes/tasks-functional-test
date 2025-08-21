package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskFunctionalTest {

	 
	
	public WebDriver getDriver(String url) throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "/Users/thiagomoraes/Workspaces/eclipse-workspace/devops/seleniumDrivers/chromedriver-mac-x64/chromedriver");
		//WebDriver driver =  new ChromeDriver();
		
		
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.setCapability("browserVersion", "100");
		chromeOptions.setCapability("platformName", "mac");
		// Showing a test name instead of the session id in the Grid UI
		//chromeOptions.setCapability("se:name", "My simple test"); 
		// Other type of metadata can be seen in the Grid UI by clicking on the 
		// session info or via GraphQL
		//chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value"); 
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.248:4444/wd/hub"), chromeOptions);
		
        
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//WebDriver driver =  new RemoteWebDriver(new URL("http://192.168.1.248:4444/wd/hub"), capabilities);
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
		catch(Exception e) {Assert.fail(e.getMessage()); e.printStackTrace();}
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
