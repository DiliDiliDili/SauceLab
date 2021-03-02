package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	By username = By.cssSelector("#user-name");
	By password = By.cssSelector("#password");
	By loginBtn = By.cssSelector("#login-button");
	
		
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver = driver;
	}

	
	
	
	public WebElement Username() {
		return driver.findElement(username);
	}
	
	public WebElement Password() {
		return driver.findElement(password);
	}
	
	public WebElement LoginBtn() {
		return driver.findElement(loginBtn);
	}


}
