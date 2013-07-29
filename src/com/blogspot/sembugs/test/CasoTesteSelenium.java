package com.blogspot.sembugs.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.server.SeleniumServer;

import testlink.api.java.client.TestLinkAPIResults;

import com.blogspot.sembugs.util.IConstantes;
import com.blogspot.sembugs.mantis.MantisReport;
import com.blogspot.sembugs.testlink.ResultadoExecucao;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import junit.framework.TestCase;


public class CasoTesteSelenium extends TestCase implements IConstantes {
	
	//Selenium selenium;
	//SeleniumServer server;
	  WebDriver dr;
	String serverHost = "localhost";
	int serverPort = 4444;
	String browserStartCommand = "*firefox";
	String browserURL = "http://www.flipkart.com/";
	 
	boolean error;
	String msgErro;
	String evidenceErro;
	
	
	
	public void setUp() throws Exception {
	//	selenium = new DefaultSelenium(serverHost, serverPort, browserStartCommand, browserURL);
		//server = new SeleniumServer();
		
		//server.start();
		//selenium.start();
		dr= new FirefoxDriver();
		dr.get("http://www.flipkart.com/");
		
	}
	
	@Test
	
	public void testPesquisaLivro() throws Exception {
		String result = null;
		String msg = null;
		
		
		
		try {
			//dr.findElement(By.id("fk-top-search-box")).sendKeys("camera");
			//dr.findElement(By.xpath("//*[@id='fk-header-search-form']/table/tbody/tr/td[2]/div/input")).click();
			//dr.wait(3000);
			//selenium.open("/");
		//	selenium.type("fk-top-search-box", "camera");
			//selenium.click("//*[@id='fk-header-search-form']/table/tbody/tr/td[2]/div/input");
			//selenium.click("//ul[@id='nav']/li[1]/ul/li[2]/ul/li[1]/a/span");
			//selenium.waitForPageToLoad("30000");
			//String expected = dr.findElement(By.xpath("//*[@id='searchCount']/span[2]")).getText();
			//System.out.println(expected);
			//Assert.assertEquals("Showing 3869 products",expected);
			dr.findElement(By.id("fk-top-search-box")).sendKeys("camera");
			dr.findElement(By.xpath("//*[@id='fk-header-search-form']/table/tbody/tr/td[2]/div/input")).click();
			String expected = dr.findElement(By.xpath("//*[@id='searchCount']/span[2]")).getText();
			System.out.println(expected);
			Assert.assertEquals("3869",expected);
		    result = TestLinkAPIResults.TEST_PASSED;
		
		} catch (Throwable e) {
			result = TestLinkAPIResults.TEST_FAILED;
			reportError(e);
			msg = e.getMessage();
			e.printStackTrace();
		} finally {
			if (error) {  
				
				 String bugID = MantisReport.reporIssue ("Error in Test Case Book Search", "Error in validating or validating any", "Functional", msgErro, evidenceErro, "CasoTesteSelenium");  
			     ResultadoExecucao.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST, BUILD, msg, result, Integer.parseInt (bugID));
			     CasoTesteSelenium.fail (msgErro);  
			}  
			
			else{
				 ResultadoExecucao.reportTestCaseResult(PROJET_TESTLINK, PLAN, CASE_TEST, BUILD, msg, result, -1);
			}
		}
	}
	private void reportError(Throwable e) {		
		error = true;
		msgErro = e.getMessage();
		System.out.println(msgErro);
		e.printStackTrace();
		//evidenceErro = selenium.captureEntirePageScreenshotToString("background=#FFFFFF");
		
	}	
	
	public void tearDown() throws Exception {
		//selenium.stop();
		//server.stop();
		dr.quit();
	}
}
