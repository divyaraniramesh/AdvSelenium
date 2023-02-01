package campaigns;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic_Utility.WebDriver_Utility;

public class CreateCampWithProduct {
	public static WebDriver driver;
public static void main(String[] args) throws IOException, Throwable {
	FileInputStream fis = new FileInputStream("./src/test/resources/vtigercommondata.properties");
	Properties pobj = new Properties();
	pobj.load(fis);
	String browser = pobj.getProperty("browser");
	if(browser.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
		driver=new ChromeDriver();
	}else if (browser.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
		
		driver = new FirefoxDriver();
	}else if (browser.equals("edge")) {
		System.setProperty("webdriver.edge.driver", "./src/main/resources/msedgedriver.exe");
		driver = new EdgeDriver();
	}
	WebDriver_Utility wlib=new WebDriver_Utility();
	wlib.maximizeScreen(driver);
	//driver.manage().window().maximize();
	wlib.waitForPageToLoad(driver);
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	driver.get(pobj.getProperty("url"));
	WebElement usernameTextField = driver.findElement(By.name("user_name"));
	usernameTextField.clear();
	usernameTextField.sendKeys(pobj.getProperty("username"));
	WebElement passwordTextField = driver.findElement(By.name("user_password"));
	passwordTextField.clear();
	passwordTextField.sendKeys(pobj.getProperty("password"));
	WebElement loginButton = driver.findElement(By.id("submitButton"));
	loginButton.submit();
	//new Actions(driver).pause(Duration.ofSeconds(10)).perform();
	//Thread.sleep(5000);
	WebElement ProductsLink = driver.findElement(By.linkText("Products"));
	ProductsLink.click();
	WebElement CreateProductPlusBtn = driver.findElement(By.xpath("//img[@alt='Create Product...']"));
	CreateProductPlusBtn.click();
	WebElement createProductBtn = driver.findElement(By.name("productname"));
	createProductBtn.click();
	//fetching data from excel
	Random ran=new Random();
	int ranNum= ran.nextInt(1000);
		FileInputStream efis1 = new FileInputStream("./src/test/resources/data.xlsx");
		Workbook book1 = WorkbookFactory.create(efis1);
		Sheet sh1 = book1.getSheet("Product");
		Row row1 = sh1.getRow(0);
		Cell cell1 = row1.getCell(0);
		String prod = cell1.getStringCellValue()+ranNum;
		driver.findElement(By.name("productname")).sendKeys(prod);
		//Thread.sleep(3000);
		WebElement saveButton1 = driver.findElement(By.name("button"));
		saveButton1.click();
		new Actions(driver).pause(Duration.ofSeconds(10)).perform();
		//Thread.sleep(5000);
		WebElement moreButton = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		moreButton.click();
		WebElement campaignwithinmore = driver.findElement(By.linkText("Campaigns"));
		campaignwithinmore.click();
		WebElement campaignbutton = driver.findElement(By.linkText("Campaigns"));
		campaignbutton.click();
		WebElement plusimageCreateCampaign = driver.findElement(By.xpath("//img[@alt='Create Campaign...']"));
		plusimageCreateCampaign.click();
		
		//Random ran=new Random();
		//int ranNum= ran.nextInt(1000);
		//CampaignNameTextFiled.sendKeys("Marketing1");
		//fetching data from excel
		FileInputStream efis = new FileInputStream("./src/test/resources/data.xlsx");
		Workbook book = WorkbookFactory.create(efis);
		Sheet sh = book.getSheet("Campaigns");
		Row row = sh.getRow(0);
		Cell cell = row.getCell(0);
		String Camp = cell.getStringCellValue()+ranNum;
		driver.findElement(By.name("campaignname")).sendKeys(Camp);
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		
		//switching window
		wlib.SwitchToWindow(driver, "Product&action");
//		Set<String> allId=driver.getWindowHandles();
//		Iterator<String> it=allId.iterator();
//		while(it.hasNext()) {
//			String wid=it.next();
//			driver.switchTo().window(wid);
//			String title=driver.getTitle();
//			if(title.contains("Product&action")) {
//				break;
//			}
//		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(prod);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		Thread.sleep(3000);
		//dynamic xpath
		driver.findElement(By.xpath("//a[text()='"+prod+"']")).click();
		
		wlib.SwitchToWindow(driver, "Campaigns&action");
		//window switching back
//		Set<String> allId1=driver.getWindowHandles();
//		Iterator<String> it1=allId1.iterator();
//		while(it1.hasNext()) {
//			String wid1=it1.next();
//			//org//prd
//			driver.switchTo().window(wid1);
//			String title=driver.getTitle();
//			if(title.contains("Campaigns&action")) {
//				break;
//			}
//		}
		WebElement saveButton = driver.findElement(By.name("button"));
		saveButton.click();
		new Actions(driver).pause(Duration.ofSeconds(10)).perform();
		
	driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	driver.findElement(By.linkText("Sign Out")).click();
		driver.manage().window().minimize();
	driver.quit();
	
}
}
