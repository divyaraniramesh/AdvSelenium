package Object_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganzationCreatePage {
public OrganzationCreatePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}
//declaration
@FindBy(xpath="//img[@alt='Create Organization...']")
private WebElement organizationcreatepage;

@FindBy(name="accountname")
private WebElement organizationnamefield;

@FindBy(name="button")
private WebElement savebutton;

//getter Methods
public WebElement getSaveButton() {
	return savebutton;
}
public WebElement getOrganizationNameTextField() {
		return organizationnamefield;
	}
public WebElement getOrganizationCreateImage() {
	return organizationcreatepage;
}
//Business Logics
/**
 * this method is used for ClickOn +img
 */
public void clickOrganizaionCreateImage() {
	organizationcreatepage.click();
}
/**
 * this method is used to pass value to organization textfield
 */
public void organizationTextField(String orgName) {
	organizationnamefield.sendKeys(orgName);
}
/**
 * this method is used to save OrgName
 */
public void saveButton() {
	savebutton.click();
}
}















