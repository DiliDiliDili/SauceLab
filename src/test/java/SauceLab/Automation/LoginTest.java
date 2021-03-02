package SauceLab.Automation;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjectModel.LoginPage;
import Resources.SeleniumConfig;
import Resources.excelFunction;

/**
 * Unit test for simple App.
 */
public class LoginTest  extends SeleniumConfig {
    /**
     * Rigorous Test :-)
     */
	public WebDriver driver;
	excelFunction readData = new excelFunction();
	String baseURL,homePage;
	
	
	
	@BeforeTest
	public void browserInitialize() throws IOException, InterruptedException {
		driver = initializer();
		baseURL = readData.readExcel("B2");
		homePage = readData.readExcel("B3");
	
	}
//	String baseURL = props.getProperty("baseUrl");
//	String dashboard = props.getProperty("dashboard");
	
	@DataProvider(name="UserData")
    public Object[][] getData() throws IOException {
    	Object[][] data = new Object[4][2];
    	//1st Row
    	data[0][0] = readData.readExcel("C2");
    	data[0][1] = readData.readExcel("D2");
    	//2nd Row
    	data[1][0] = readData.readExcel("C3");
    	data[1][1] = readData.readExcel("D3");
    	//3rd Row
    	data[2][0] = readData.readExcel("C4");
    	data[2][1] = readData.readExcel("D4");
    	//4th Row
    	data[3][0] = readData.readExcel("C5");
    	data[3][1] = readData.readExcel("D5");
		
    	return data;
    }
    
	
    @Test
    public void pageload() throws IOException, InterruptedException
    {
       driver.get(baseURL);
       Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }
    
    @Test(dataProvider="UserData")
    public void Logincheck(String Username, String Password) throws IOException, InterruptedException
    {
       driver.get(baseURL);
       LoginPage login = new LoginPage(driver);
       login.Username().sendKeys(Username);
       login.Password().sendKeys(Password);
       login.LoginBtn().click();
       
       Assert.assertEquals(driver.getCurrentUrl(), homePage);
       
    }
    
    @AfterTest
	public void teardown()
	{
		driver.close();
		
	}

}
