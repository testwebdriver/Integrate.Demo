package com.blogspot.sembugs.test;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import testlink.api.java.client.TestLinkAPIResults;

import com.blogspot.sembugs.mantis.MantisReport;
import com.blogspot.sembugs.testlink.ResultExe;
import com.blogspot.sembugs.util.IConstantes;

public class spTestCart extends TestCase implements IConstantes {
	  
	    WebDriver dr;
		
		boolean error;
		String msgError;
		String evidenceErro;
		
		
		
		@BeforeClass		
		public void setUp() throws Exception {
		
			dr= new FirefoxDriver();
			dr.get("http://www.sneakpeeq.com/");
			
		}
		
		
		@Test
		
		public void testcart() throws Exception {
			String result = null;
			String msg = null;
			try{
				   Assert.assertEquals("sneakpeeq | Style, Home and Gourmet Food Discoveries You'll Love", dr.getTitle());
				   WebDriverWait wait = new WebDriverWait(dr,20);
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
				   //Thread.sleep(4000);
				   WebElement signin = dr.findElement(By.linkText("Sign In"));
				   signin.click();
				   for (String handle : dr.getWindowHandles()) {
						  dr.switchTo().window(handle);
						}
				   Assert.assertEquals(dr.getTitle(),"Facebook");
				   WebElement textboxEmail = dr.findElement(By.id("email"));
				   textboxEmail.sendKeys("*********************");
				   WebElement textboxPass = dr.findElement(By.id("pass"));
				   textboxPass.sendKeys("************");
				   WebElement loginButton = dr.findElement(By.id("loginbutton"));
				   loginButton.click();
				   Assert.assertEquals(dr.getTitle(),"Log in with Facebook");
				   WebElement skipButton = dr.findElement(By.name("__SKIP__"));
				   skipButton.click();
				   for (String handle : dr.getWindowHandles()) {
						  dr.switchTo().window(handle);
						}
				   
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selector-gifts")));
				   Assert.assertEquals("sneakpeeq | Style, Home and Gourmet Food Discoveries You'll Love", dr.getTitle());
				   Actions action = new Actions(dr);
				   WebElement beauty = dr.findElement(By.id("selector-beauty"));
				   action.moveToElement(beauty).perform();
				  
				   dr.findElement(By.xpath("//*[@id='vertical-beauty']/li[3]/a")).click();
				   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='store-page']/section[1]/div/div[1]/hgroup/h1")));
				   Assert.assertTrue(dr.findElement(By.xpath("//*[@id='store-page']/section[1]/div/div[1]/hgroup/h1")).getText().contains("TRUTH ART BEAUTY"));
				   WebElement product = dr.findElement(By.xpath("//img[@alt='Better Sleep Eye Balm']"));
				   product.click();
				   Assert.assertEquals("0", dr.findElement(By.id("cart-count")).getText());
				   Thread.sleep(2000);
				   WebElement peeqButton = dr.findElement(By.id("peeq"));
				   peeqButton.click();
				   for (String handle : dr.getWindowHandles()) {
						  dr.switchTo().window(handle);
						}
				   
				   System.out.println(dr.getTitle());
				   
				   dr.findElement(By.name("__SKIP__")).click();
				  
				   for (String handle : dr.getWindowHandles()) {
						  dr.switchTo().window(handle);
						}
				   
				   WebElement addCart = dr.findElement(By.xpath("//*[@id='back']/button"));
				   addCart.click();
				  
				   Assert.assertEquals("1", dr.findElement(By.id("cart-count")).getText());
				   result = TestLinkAPIResults.TEST_PASSED;
				   
			}catch (Throwable e) {
				result = TestLinkAPIResults.TEST_FAILED;
				reportError(e);
				msg = e.getMessage();
				e.printStackTrace();
			} finally {
				if (error) {  
					
					 String bugID = MantisReport.reporIssue ("Error in Test Case spTestCart: cart count value did not increment after adding items into cart ", "Error in validating or validating any", "Functional", msgError, evidenceErro, "spTestCart");  
				     ResultExe.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST2, BUILD, msg, result, Integer.parseInt (bugID));
				     spTestCart.fail (msgError);  
				}  
				
				else{
					 ResultExe.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST2, BUILD, msg, result, -1);
				}
			}
		}
		private void reportError(Throwable e) {		
			error = true;
			msgError = e.getMessage();
			System.out.println(msgError);
			e.printStackTrace();
			
			
		}	
		
		@AfterClass
		public void tearDown() throws Exception {
		
			dr.quit();
		}
}
