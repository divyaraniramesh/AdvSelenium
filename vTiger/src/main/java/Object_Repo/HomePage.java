package Object_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Utility.WebDriver_Utility;

public class HomePage {
public HomePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}
//declaration
@FindBy(linkText="Products")
private WebElement productLinkText;

@FindBy(linkText="More")
private WebElement morelink;

@FindBy(linkText="Campaigns")
private WebElement campaignsLinkText;

@FindBy(linkText="Organizations")
private WebElement organizationLinkText;

@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement signoutImg;

@FindBy(linkText="Sign Out")
private WebElement signoutLinkText;

//getter Methods
public WebElement getOrganizationLinkText() {
	return organizationLinkText;
}
public WebElement getSignoutImg() {
	return signoutImg;
}
public WebElement getSigOutLinkText() {
	return signoutLinkText;
}
public WebElement getMoreLink() {
	return morelink;
}
public WebElement getCampaignsLinkText() {
	return campaignsLinkText;
}

//Business Logic for product
public void clickProductLink() {
	productLinkText.click();
}

//Business Logic for More
public void moreLink(WebDriver driver) {
	WebDriver_Utility wlib= new WebDriver_Utility();
	wlib.mouseOverOnElement(driver, morelink);
}

//campaign
public void campaignsLinkText() {
	campaignsLinkText.click();
}
//Organization
public void clickOrganizationLinkText() {
	organizationLinkText.click();
}
//SignOut
public void signoutLink(WebDriver driver) {
	Actions act=new Actions(driver);
	act.moveToElement(signoutImg).perform();
	signoutLinkText.click();
}
}
