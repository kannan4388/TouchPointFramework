package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SalesPage {
	
	utility.CommonMethods objMethods;
	private WebDriver driver;
	
	//Intializing the page objects
	@FindBy(xpath="//li[@ng-class='NavbarService.SalesSelected']")
	WebElement salesClick;
	
	@FindBy(xpath="//i[@class='far fa-plus-square']")
	WebElement pageLoad;
	
	public SalesPage(){
		this.driver=LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void clickSales() throws InterruptedException {
		salesClick.click();
		objMethods=new utility.CommonMethods();
		objMethods.pageWait(pageLoad);
		/*
		 * WebDriverWait pagLoad=new WebDriverWait(driver,70);
		 * pagLoad.until(ExpectedConditions.elementToBeClickable(pageLoad));
		 */
		Thread.sleep(3000);
	}

}
