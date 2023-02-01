package com.vtiger.qspiders.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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

public class VtigerLaunch {
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
	WebElement organizationsLink = driver.findElement(By.linkText("Organizations"));
	organizationsLink.click();
	WebElement plusimage = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));
	plusimage.click();
	WebElement organizationNameTextFiled = driver.findElement(By.name("accountname"));
	//organizationNameTextFiled.sendKeys("qspiders");
	//fetching data from excel
	FileInputStream efis = new FileInputStream("./src/test/resources/jan5.xlsx");
	Workbook book = WorkbookFactory.create(efis);
	Sheet sh = book.getSheet("sheet1");
	Row row = sh.getRow(0);
	Cell cell = row.getCell(0);
	String orgname = cell.getStringCellValue();
	driver.findElement(By.name("accountname")).sendKeys(orgname);
	//Thread.sleep(3000);
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
