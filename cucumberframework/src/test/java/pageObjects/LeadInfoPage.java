package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LeadInfoPage {
	public WebDriver driver;
	utility.CommonMethods objWait;
	
	
	/* Web Elements in Lead Information page */
	
	@FindBy(css="i[class='fa fa-exchange']")
	WebElement convertLead;
	
	@FindBy(css="button[class='btn btn-primary cancel_but']")
	WebElement pageLoad;
	
	
	public LeadInfoPage(){
		this.driver=LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	/* Method for Converting a Lead */
	
	public void convertLead() throws InterruptedException {
		//driver.navigate().refresh();
		objWait=new utility.CommonMethods();
		objWait.pageWait(convertLead);
		//Thread.sleep(1000);
		convertLead.click();
		objWait.pageWait(pageLoad);
	}

}
