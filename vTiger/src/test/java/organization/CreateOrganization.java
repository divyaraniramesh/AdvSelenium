

package organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import Object_Repo.Login_Page;

public class CreateOrganization {
	public static WebDriver driver;
	public static void main(String[] args) throws IOException, Throwable {
		//FileInputStream fis = new FileInputStream("./src/test/resources/vtigercommondata.properties");
		//Properties pobj = new Properties();
		//pobj.load(fis);
		//String browser = pobj.getProperty("browser");
		//String USERNAME = pobj.getProperty("username");
		//String PASSWORD = pobj.getProperty("password");
		File_Utility flib=new File_Utility();
		String BROWSER = flib.getKeyAndValue("browser");
		if(BROWSER.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
		}else if (BROWSER.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
			
			driver = new FirefoxDriver();
		}else if (BROWSER.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/main/resources/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.maximizeScreen(driver);
		wlib.waitForPageToLoad(driver);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String URL = flib.getKeyAndValue("url");
		String USERNAME = flib.getKeyAndValue("username");
		String PASSWORD = flib.getKeyAndValue("password");
		driver.get(URL);
		
		Login_Page login=new Login_Page(driver);
		login.loginToApp(USERNAME, PASSWORD);
		//driver.get(pobj.getProperty("url"));
		
	    //driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//usernameTextField.clear();
		//usernameTextField.sendKeys(pobj.getProperty("username"));
	    
	    //driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//PASSWORD.clear();
		//passwordTextField.sendKeys(pobj.getProperty("password"));
		WebElement loginButton = driver.findElement(By.id("submitButton"));
		loginButton.submit();
		//new Actions(driver).pause(Duration.ofSeconds(10)).perform();
		WebElement organizationsLink = driver.findElement(By.linkText("Organizations"));
		organizationsLink.click();
		WebElement plusimage = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));
		plusimage.click();
		WebElement organizationNameTextFiled = driver.findElement(By.name("accountname"));
		//organizationNameTextFiled.sendKeys("qspiders");
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRandomNum();
		//Random ran=new Random();
		//int ranNum=ran.nextInt(1000);
		//fetching data from excel
		
		//FileInputStream efis = new FileInputStream("./src/test/resources/data.xlsx");
		//Workbook book = WorkbookFactory.create(efis);
		//Sheet sh = book.getSheet("organization");
		//Row row = sh.getRow(0);
		//Cell cell = row.getCell(0);
		//String orgname = cell.getStringCellValue()+ranNum;
				//Thread.sleep(3000);
		Excel_Utility elib=new Excel_Utility();
		String orgname = elib.getExcelData("Organization", 0, 0)+ranNum;
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		WebElement saveButton = driver.findElement(By.name("button"));
		saveButton.click();
		new Actions(driver).pause(Duration.ofSeconds(10)).perform();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();
			driver.manage().window().minimize();
		driver.quit();
	}
}
