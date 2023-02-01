package Generic_Utility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class WebDriver_Utility {
	public void maximizeScreen(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * wait for page to load before identifying any synchroized element in DOM
	 * @author Admin
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/**
	 * after every action it will wait for next action to perform
	 * @author Admin
	 */
	public void scriptTimeOut(WebDriver driver) {
		driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
	}
	/**
	 * used to wait for element to be clickable in GUI and check for specific element for every 500milliseconds
	 * 
	 */
	public void waitForElementWithCustomTimeOut(WebDriver driver,WebElement Element,int pollingTime) {
		FluentWait wait=new FluentWait(driver);
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
	/**s
	 * used to switch to any window based on window title
	 * @param driver
	 * @author Divyarani
	 * 
	 */
	public void SwitchToWindow(WebDriver driver,String PartialWindowTitle) {
		Set<String> allId=driver.getWindowHandles();
		Iterator<String> it=allId.iterator();
		while(it.hasNext())
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			if(driver.getTitle().contains(PartialWindowTitle)) {
				break;
			}
		}
	}
	
		/**
		 * used to switch to alertWindow and accept(click on ok button)
		 * @param driver
		 * @author Admin
		 */
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * used to switch to AlertWindow and dismiss(click on cancel button)
	 * @param driver
	 * @author Admin
	 */
	public void SwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * used to switch to frame window based on index
	 * @param driver
	 * @author Admin
	 */
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * used to switch to frame window based on id or name attribute
	 * @param driver
	 * @param id_name_attribute
	 * @author Admin
	 */
	public void SwitchToFrame(WebDriver driver,String id_name_Attribute) {
		driver.switchTo().frame(id_name_Attribute);
	}
	/**
	 * USED TO SELECT THE VALUE FROM THE DROPDOWN BASED ON INDEX
	 * @PARAM ELEMENT
	 * @PARAM INDEX
	 * @author Admin
	 */
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * used to select the value from the dropdown based on text
	 * @param element
	 * @param text
	 * @author Admin
	 */
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	/**
	 * used to place mouse cursor on specified element
	 * @param driver
	 * @param element
	 * @author admin
	 */
	public void mouseOverOnElement(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * used to right click on specific element
	 * @param driver
	 * @param element
	 * @author Admin
	 */
	public void rightClickOnElement(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	public void moveByOffset(WebDriver driver,int x, int y) {
		Actions act=new Actions(driver);
		act.moveByOffset(x, y).click().perform();
	}

}
