package pageobject;

import java.util.List;

import org.openqa.selenium.*;

import base.Base;

public class PriceFilterPage extends Base{
	public PriceFilterPage(WebDriver driver){
		this.driver = driver;
	}
	
	By checkBox = By.xpath("//input[@type='checkbox']"); 
	By priceSelect = By.xpath("(//select)[1]"); 
	By priceList = By.xpath("//p[contains(text(),'₹')]");
	
	public WebElement getCheckBox() {
		return this.driver.findElement(checkBox);
	}
	
	public WebElement getPriceSelect() {
		return this.driver.findElement(priceSelect);
	}
	
	public List<WebElement> getPriceList() {
		return this.driver.findElements(priceList);
	}

}
