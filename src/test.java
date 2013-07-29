import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class test {
	
	 static WebDriver dr;
	
	@BeforeClass
	public static void startUP(){
		
		dr= new FirefoxDriver();
		dr.get("http://www.flipkart.com/");
	}

	@AfterClass
	public static void tearDown(){
		dr.quit();
		
	}
	
	
	@Test
	public void test1(){
		dr.findElement(By.id("fk-top-search-box")).sendKeys("camera");
		dr.findElement(By.xpath("//*[@id='fk-header-search-form']/table/tbody/tr/td[2]/div/input")).click();
		String expected = dr.findElement(By.xpath("//*[@id='searchCount']/span[2]")).getText();
		System.out.println(expected);
		Assert.assertEquals("3869",expected);
	}
}
