package SauceLab.Automation;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import PageObjectModel.LoginPage;
import Resources.SeleniumConfig;
import Resources.excelFunction;

public class filterTest extends SeleniumConfig {
	public WebDriver driver;
	public WebDriverWait wait;
	excelFunction readData = new excelFunction();
	String baseURL,homePage;
	LoginPage login;
	
	
	
	@BeforeTest
	@Parameters("browser")
	public void browserInitialize(String browser) throws IOException, InterruptedException {
		driver = initializer(browser);
		baseURL = readData.readExcel("B2");
		homePage = readData.readExcel("B3");
		login = new LoginPage(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	}
//	String baseURL = props.getProperty("baseUrl");
//	String dashboard = props.getProperty("dashboard");
	
	@DataProvider(name="UserData")
    public Object[][] getData() throws IOException {
    	Object[][] data = new Object[1][2];
    	//1st Row
    	data[0][0] = readData.readExcel("C2");
    	data[0][1] = readData.readExcel("D2");		
    	return data;
    }
    
	 @Test(dataProvider="UserData",priority=1)
    public void loginTest(String Username, String Password) throws IOException, InterruptedException
    {
       driver.get(baseURL);
       login.Username().sendKeys(Username);
       login.Password().sendKeys(Password);
       login.LoginBtn().click();
       
       Assert.assertEquals(driver.getCurrentUrl(), homePage);
    }
	
	@Test(priority=2)
	public void sortByName_ascending() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='inventory_item']")));
		List<WebElement> el = driver.findElements(By.cssSelector("[class='inventory_item_name']"));
		ArrayList <String> name = new ArrayList<String>();
		ArrayList <String> copy = new ArrayList<String>();
		for(int i=0;i<el.size();i++) {
			String a = el.get(i).getText();
			System.out.println("array a: "+a);
			name.add(a);
			copy.add(a);
		}
		System.out.println("Name array in ascen: "+name);				
		Collections.sort(copy);
		System.out.println("Copy array in ascen: "+copy);
		Assert.assertEquals(name, copy);
			
		
	}
	@Test(priority=3)
	public void sortByName_decending() {
		WebElement selection = driver.findElement(By.cssSelector("[class='product_sort_container']"));
		selection.click();
		selection.sendKeys(Keys.ARROW_DOWN);
		selection.sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='inventory_item']")));
		List<WebElement> el = driver.findElements(By.cssSelector("[class='inventory_item_name']"));
		ArrayList <String> name = new ArrayList<String>();
		ArrayList <String> copy = new ArrayList<String>();
		for(int i=0;i<el.size();i++) {
			String a = el.get(i).getText();
			System.out.println("array a: "+a);
			name.add(a);
			copy.add(a);
		}
		System.out.println("Name array in descen: "+name);				
		Collections.sort(copy, Collections.reverseOrder());
		System.out.println("Copy array in descen: "+copy);
		Assert.assertEquals(name, copy);
			
		
	}
	@Test(priority=4)
	public void sortByPrice_low_to_high() {
		WebElement selection = driver.findElement(By.cssSelector("[class='product_sort_container']"));
		selection.click();
		selection.sendKeys(Keys.ARROW_DOWN);
		selection.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='inventory_item']")));
		List<WebElement> el = driver.findElements(By.cssSelector("[class='inventory_item_price']"));
		ArrayList <Double> name1 = new ArrayList<Double>();
		ArrayList <Double> copy = new ArrayList<Double>();
		for(int i=0;i<el.size();i++) {
			String a = el.get(i).getText();
			a = a.replaceAll("[^0-9]", "");
			
			System.out.println("array a: "+a);
			name1.add(Double.parseDouble(a)/100);
			copy.add(Double.parseDouble(a)/100);
		}
		System.out.println("Name array in l2h: "+name1);
//		name.sort(name);				
		Collections.sort(copy);
		System.out.println("Copy array in l2h: "+copy);
		Assert.assertEquals(name1, copy);
			
		
	}
	@Test(priority=5)
	public void sortByPrice_high_to_low() {
		WebElement selection = driver.findElement(By.cssSelector("[class='product_sort_container']"));
		selection.click();
		selection.sendKeys(Keys.ARROW_DOWN);
		selection.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='inventory_item']")));
		List<WebElement> el = driver.findElements(By.cssSelector("[class='inventory_item_price']"));
		ArrayList <Double> name = new ArrayList<>();
		ArrayList <Double> copy = new ArrayList<>();
		for(int i=0;i<el.size();i++) {
			String a = el.get(i).getText();
			a = a.replaceAll("[^0-9]", "");
			System.out.println("array a: "+Double.parseDouble(a));
			name.add(Double.parseDouble(a)/100);
			copy.add(Double.parseDouble(a)/100);
		}
		System.out.println("Name array in h2l: "+name);		
		Collections.sort(copy, Collections.reverseOrder());
		System.out.println("Copy array in h21: "+copy);
		Assert.assertEquals(name, copy);
			
		
	}
	
	@AfterTest
	   public void teardown() {
		   driver.close();
	   }
     
}
