package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class addressPage {
	
public WebDriver driver;
	
	By contineBtn = By.cssSelector("[value='CONTINUE']");
	By cart_list = By.cssSelector("[class='cart_list']");
	By finishBtn = By.cssSelector("[class='btn_action cart_button']");
	By menuBtn = By.cssSelector("[class='bm-burger-button']");
	By logout = By.id("logout_sidebar_link");
	By textData = By.cssSelector("[class = 'inventory_item_container'] > div > div > div > button");
	By checkOutBtn = By.cssSelector("[class='btn_action checkout_button']");
	public addressPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver = driver;
	}

	
	
	
	public WebElement contineBtn() {
		return driver.findElement(contineBtn);
	}
	
	public WebElement cart_list() {
		return driver.findElement(cart_list);
	}
	
	public WebElement finishBtn() {
		return driver.findElement(finishBtn);
	}
	
	public WebElement menuBtn() {
		return driver.findElement(menuBtn);
	}
	
	public WebElement logout() {
		return driver.findElement(logout);
	}
}
