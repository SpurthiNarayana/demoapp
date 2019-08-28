package demoapp.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




public class Demoapptest {
	public WebDriver driver;
	
	@BeforeTest
	 public void setup() throws InterruptedException
    {


        System.setProperty("webdriver.chrome.driver" ,"/home/qapitol/chromedriver");
        ChromeOptions o = new ChromeOptions();
        driver = new ChromeDriver(o);
       o.addArguments("window-size=1024,768");
    
      }
	@Test(priority=1)
	public void testTitle() throws InterruptedException
     {
   	    driver.get("http://localhost:8080/");
         assertEquals("Articles", driver.getTitle());
    }
	@Test(priority=2)
	public void createArticle() throws InterruptedException
   {
 	    driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();	
 	    driver.findElement(By.xpath("//input[@id='title']")).click();
 	   driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Article 10");
 	   
 	 
	    driver.findElement(By.xpath("//input[@id='author']")).click();
	   driver.findElement(By.xpath("//input[@id='author']")).sendKeys("Spurthi");
	   
		 
	    driver.findElement(By.xpath("//input[@id='content']")).click();
	   driver.findElement(By.xpath("//input[@id='content']")).sendKeys("My Article 10 ...");

	    driver.findElement(By.xpath("//input[@id='save']")).click();
  }
	@Test(priority=3)
	public void editarticle() throws InterruptedException
   {
		 driver.findElement(By.xpath("//*[@id=\"data\"]/tr[2]/td[5]/a")).click();
//		 driver.findElement(By.xpath("//input[@id='title']")).click();
		 driver.findElement(By.xpath("//input[@id='title']")).sendKeys(" edit");
		 driver.findElement(By.xpath("//input[@id='author']")).sendKeys("\b\b\b\b\b\b\b edited");
		 driver.findElement(By.xpath("//input[@id='content']")).sendKeys(" edit");
		 driver.findElement(By.xpath("//input[@id='save']")).click(); 
		 Thread.sleep(1000);
  }
	@Test(priority=4)
	public void editedarticle() throws InterruptedException
   {
		
		String s1= driver.findElement(By.xpath("//tr[2]//td[2]")).getText();
		assertEquals(s1,"My Article edit");
		String s2= driver.findElement(By.xpath("//td[contains(text(),'edited')]")).getText();
		assertEquals(s2,"edited");
		String s3= driver.findElement(By.xpath("//td[contains(text(),'Article content .... edit')]")).getText();
		assertEquals(s3,"Article content .... edit");
      
  }
	
	@Test(priority=5)
	public void deletearticle() throws NoAlertPresentException,InterruptedException
   {
		//delete 5th row elements
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tr[5]//td[6]//a[1]")).click();
		Thread.sleep(1000);
		String s=driver.switchTo().alert().getText();
		assertEquals(s,"article deleted");
		driver.switchTo().alert().accept();
  }
	
}
