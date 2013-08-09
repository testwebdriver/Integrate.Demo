package com.blogspot.sembugs.test;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import testlink.api.java.client.TestLinkAPIResults;

import com.blogspot.sembugs.util.IConstantes;
import com.blogspot.sembugs.mantis.MantisReport;
import com.blogspot.sembugs.testlink.ResultExe;
import com.thoughtworks.selenium.Selenium;



import junit.framework.TestCase;


public class fbTestSelenium extends TestCase implements IConstantes {
	
	 
	  WebDriver dr;
	
	boolean error;
	String msgError;
	String evidenceErro;
	
	
	
	public void setUp() throws Exception {
	
		dr= new FirefoxDriver();
		dr.get("http://www.sneakpeeq.com/");
		
	}
	
	@Test
	
	public void testfbSignup() throws Exception {
		String result = null;
		String msg = null;
		
		
		
		try {
					
			   Assert.assertEquals("sneakpeeq | Style, Home and Gourmet Food Discoveries You'll Love", dr.getTitle());
			   WebDriverWait wait = new WebDriverWait(dr,20);
			   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
			   WebElement signupfacebook = dr.findElement(By.id("login-button"));
			   signupfacebook.click();
			   for (String handle : dr.getWindowHandles()) {
					  dr.switchTo().window(handle);
					}
			   Assert.assertEquals("Sign Up for Facebook | Facebook",dr.getTitle());
		       result = TestLinkAPIResults.TEST_PASSED;
		
		} catch (Throwable e) {
			result = TestLinkAPIResults.TEST_FAILED;
			reportError(e);
			msg = e.getMessage();
			e.printStackTrace();
		} finally {
			if (error) {  
				
				 String bugID = MantisReport.reporIssue ("Error in Test Case Face book sign up: Signup button directs to login page instead of sign up page", "Error in validating or validating any", "Functional", msgError, evidenceErro, "bfTestSelenium");  
			     ResultExe.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST, BUILD, msg, result, Integer.parseInt (bugID));
			     fbTestSelenium.fail (msgError);  
			}  
			
			else{
				 ResultExe.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST, BUILD, msg, result, -1);
			}
		}
	}
	private void reportError(Throwable e) {		
		error = true;
		msgError = e.getMessage();
		System.out.println(msgError);
		e.printStackTrace();
		
		
	}	
	
	public void tearDown() throws Exception {
	
		dr.quit();
	}
}
