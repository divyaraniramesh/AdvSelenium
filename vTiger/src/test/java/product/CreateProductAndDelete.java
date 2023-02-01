package product;

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

public class CreateProductAndDelete {
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		
		WebElement ProductsLink = driver.findElement(By.linkText("Products"));
		ProductsLink.click();
		WebElement CreateProductPlusBtn = driver.findElement(By.xpath("//img[@alt='Create Product...']"));
		CreateProductPlusBtn.click();
		WebElement createProductBtn = driver.findElement(By.name("productname"));
		createProductBtn.click();
		//fetching data from excel
		Random ran=new Random();
		int ranNum= ran.nextInt(1000);
			FileInputStream efis = new FileInputStream("./src/test/resources/data.xlsx");
			Workbook book = WorkbookFactory.create(efis);
			Sheet sh = book.getSheet("Product");
			Row row = sh.getRow(0);
			Cell cell = row.getCell(0);
			String prod = cell.getStringCellValue()+ranNum;
			driver.findElement(By.name("productname")).sendKeys(prod);
			//Thread.sleep(3000);
			WebElement saveButton1 = driver.findElement(By.name("button"));
			saveButton1.click();
			new Actions(driver).pause(Duration.ofSeconds(10)).perform();
			//Thread.sleep(5000);
			WebElement deleteButton = driver.findElement(By.name("Delete"));
			deleteButton.click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();
			driver.manage().window().minimize();
		driver.quit();
}
}
