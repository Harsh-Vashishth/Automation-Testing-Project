pjkmnackage hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import base.Base;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import java.io.*;
public class ScenarioHook extends Base{
	@Before
	public void setupA(){
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
		  if (!scenario.isFailed()) {
	            byte[] screenshot =
	                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	            Allure.addAttachment(
	                    "Screenshot",
	                    new ByteArrayInputStream(screenshot)
	            );
	        }
	    
		driver.quit();
	}
	@BeforeAll
	public static void suiteStart() {
		System.out.println("Test suite started");
	}
	@AfterAll
	public static void suiteEnd() {
		System.out.println("Test suite ended");
	}
	
	
}
