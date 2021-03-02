package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
	public WebDriver driver;
	
	By allItems = By.cssSelector("[class='inventory_list']");
	By itemNumber = By.cssSelector("[class='shopping_cart_container'] > a >span");
	By cartListBtn = By.cssSelector("[class='shopping_cart_container']");
	By item = By.cssSelector("[class='inventory_list'] [class='inventory_item']:nth-child(1) div[class='inventory_item_name']");
	By description = By.cssSelector("[class='inventory_item_container']");
	By textData = By.cssSelector("[class = 'inventory_item_container'] > div > div > div > button");
	By checkOutBtn = By.cssSelector("[class='btn_action checkout_button']");
	public Homepage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver = driver;
	}

	
	
	
	public WebElement allItems() {
		return driver.findElement(allItems);
	}
	
	public WebElement checkOutBtn() {
		return driver.findElement(checkOutBtn);
	}
	
	public WebElement item() {
		return driver.findElement(item);
	}

	public WebElement description() {
		return driver.findElement(description);
	}
	
	public WebElement textData() {
		return driver.findElement(textData);
	}
	
	public WebElement itemNumber() {
		return driver.findElement(itemNumber);
	}
	
	public WebElement cartListBtn() {
		return driver.findElement(cartListBtn);
	}
}
