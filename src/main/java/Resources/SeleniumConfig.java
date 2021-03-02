package Resources;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumConfig {
	
	public WebDriver driver;
	public Properties props;
	excelFunction data = new excelFunction();
	public WebDriver initializer() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		props = new Properties();
//		FileInputStream fi = new FileInputStream("C:\\Users\\sdili\\eclipse-workspace\\Automation\\src\\main\\java\\Resources\\dataFile.properties");
//		
//		props.load(fi);
//		
		String browserName = data.readExcel("A2");
		System.out.println(browserName);
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\sdili\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = ("\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;

	}

}
