package stepDefinations;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.*;
import pageobject.PriceFilterPage;

public class LoginStep extends Base{
	Select select;
	String rangeValue;
	PriceFilterPage priceFilterPage;
	private static final Logger logger = LogManager.getLogger(LoginStep.class);
	@Given("user is on the search screen")
	public void user_is_on_the_search_screen() {
		try {
			logger.debug("Code has reached given method");
			driver.get(this.getUrl());
			logger.debug("URL is launched");
			driver.manage().window().maximize();
			Thread.sleep(5000);
			this.priceFilterPage = new PriceFilterPage(driver);
			this.priceFilterPage.getCheckBox().click();
			logger.debug("checkbox got clicked");
		}
		catch(Exception ex) {
			logger.fatal("Exception is given :"+ex.getMessage());
		}
	}
	@When("dropdown Filter by price is clicked")
	public void dropdown_filter_by_price_is_clicked() {
	    try {
	    	
	    	Thread.sleep(3000);
	    	
	    	select = new Select(this.priceFilterPage.getPriceSelect());
			logger.debug("Price filter is selected");
	    	Thread.sleep(3000);
	    	
	    }
		catch(Exception ex) {
			logger.fatal("Exception is when :"+ex.getMessage());

		}

	}
	@When("Range {string} is selected")
	public void range_is_selected(String range) {
		try {
			
			rangeValue = range;
			select.selectByVisibleText(rangeValue);
			logger.debug("Drop down is selected " + range );

			Thread.sleep(3000);
		}
	    
	    catch(Exception ex) {
			logger.fatal("Exception is when2 :"+ex.getMessage());

		}
	}
	
	@Then("price is validated")
	public void price_is_validated() {
		try {
			
			Thread.sleep(3000);
			OptionalInt result = OptionalInt.empty();;
			List<WebElement> priceList = this.priceFilterPage.getPriceList();
			if(rangeValue.contains("Below")) {
				
				logger.debug("Below started ");
				rangeValue = rangeValue.replaceAll("[^0-9]", "");
				result = priceList.stream().limit(3)
						.map(s->s.getText())
						.map(s->s.replaceAll("[^0-9]", ""))
						.mapToInt(Integer::parseInt)
						.filter(s->s>Integer.parseInt(rangeValue)).findAny();
				System.out.println("Below ended");
				logger.debug("Below ended ");

				
			}
			else if(rangeValue.contains("-")) {
				logger.debug("Between Started");
				System.out.println("Between Started");
				rangeValue = rangeValue.replaceAll("[^0-9-]", "");
				String minValue = rangeValue.split("-")[0];
				String maxValue = rangeValue.split("-")[1];
				result = priceList.stream()
						.map(s->s.getText())
						.map(s->s.replaceAll("[^0-9]", ""))
						.mapToInt(Integer::parseInt)
						.filter(s->s<Integer.parseInt(minValue)||s>Integer.parseInt(maxValue)).findAny();
				System.out.println("Between Ended");
				logger.debug("Between ended ");

				
			}
			else if(rangeValue.contains("Above")) {
				logger.debug("Above Started");

				System.out.println("Above Started");
				rangeValue = rangeValue.replaceAll("[^0-9]", "");
				result = priceList.stream()
						.map(s->s.getText())
						.map(s->s.replaceAll("[^0-9]", ""))
						.mapToInt(Integer::parseInt)
						.filter(s->s<Integer.parseInt(rangeValue)).findAny();
				System.out.println("Above Ended");
				logger.debug("Above ended ");

				
			}
			
			if(result.isPresent()) {
				Assert.assertTrue(false);
			}
		}
		
	   catch(Exception ex) {
			logger.fatal("Exception in :"+ex.getMessage());

		}
	    

	}


}
