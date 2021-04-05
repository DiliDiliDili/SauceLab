package SauceLab.Automation;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjectModel.Homepage;
import PageObjectModel.LoginPage;
import PageObjectModel.addressPage;
import Resources.SeleniumConfig;
import Resources.excelFunction;

public class addToCart extends SeleniumConfig {
	public WebDriver driver;
	public WebDriverWait wait;
	excelFunction readData = new excelFunction();
	String baseURL,homePage;
	Homepage home;
	LoginPage login;
	addressPage finalPage;
	
	@BeforeTest
	@Parameters("browser")
	public void browserInitialize(String browser) throws IOException, InterruptedException {
		driver = initializer(browser);
		baseURL = readData.readExcel("B2");
		homePage = readData.readExcel("B3");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		home = new Homepage(driver);
		login = new LoginPage(driver);
		finalPage = new addressPage(driver);

	}
	
		
	@DataProvider(name="user")
    public Object[][] userData() throws IOException {
    	Object[][] data = new Object[1][2];
    	//1st Row
    	data[0][0] = readData.readExcel("C2");
    	data[0][1] = readData.readExcel("D2");
    
    	return data;
    }   
	
    @Test(dataProvider="user",priority=1)
    public void addToCartItem (String Username, String Password) throws IOException, InterruptedException
    {
       driver.get(baseURL);
       
       login.Username().sendKeys(Username);
       login.Password().sendKeys(Password);
       login.LoginBtn().click();
       
      
       
       Assert.assertTrue(home.allItems().isDisplayed());
       
       List<WebElement> el = driver.findElements(By.cssSelector("[class= 'btn_primary btn_inventory']"));
       System.out.println("Element "+el.size());
       
       int itemCount = 0;
       
       for(int i=1;i<=el.size();i++) {
    	   
    	   driver.findElement((By.cssSelector("[class='inventory_list'] > div:nth-child("+i+") [class='pricebar']> button"))).click();
    	   String text = driver.findElement((By.cssSelector("[class='inventory_list'] > div:nth-child("+i+") [class='pricebar']> button"))).getText();
    	   
    	   Assert.assertEquals(text,"REMOVE");
    	   
    	   itemCount++;
       }  
       String addedItem = home.itemNumber().getText();
       Assert.assertEquals(addedItem, NumberToTextConverter.toText(itemCount));
       
    }
	
    @Test(priority=2)
    public void productPage() {
    	
    	home.item().click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='inventory_item_container']")));

    	Assert.assertTrue(home.description().isDisplayed());
    	Assert.assertEquals(home.textData().getText(), "REMOVE");
    
    }
    
    @Test(priority=3)
    public void checkoutPage() {
    	home.cartListBtn().click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='btn_action checkout_button']")));
    	home.checkOutBtn().click();
    	Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
    }
    
   @Test(priority=4)
   public void addressPage() throws IOException, InterruptedException {
	   String[] addressDetails = {readData.readExcel("E2"),readData.readExcel("F2"),readData.readExcel("G2")};
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='checkout_info'] > input:nth-child(1)")));
	   Thread.sleep(3000);
	   
	   for(int i=1;i<4;i++){
           WebElement details = driver.findElement(By.cssSelector("[class='checkout_info'] > input:nth-child("+i+")"));
           details.sendKeys(addressDetails[i-1]);
       }
	   
	   finalPage.contineBtn().click();
	   Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='cart_list']")));
	   Assert.assertTrue(finalPage.cart_list().isDisplayed());
	   
   }
   
   @Test(priority=5)
   public void ordercretion() {
	   finalPage.finishBtn().click();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_complete_container")));
	   Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");
	   
   }
   
   @Test(priority=6)
   public void logout() {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='bm-burger-button']"))); 
       
       finalPage.menuBtn().click();
   
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link"))); 

       finalPage.logout().click();
   }
   
   @AfterTest
   public void teardown() {
	   driver.close();
   }
	
}
