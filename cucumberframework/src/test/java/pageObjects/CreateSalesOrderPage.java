package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateSalesOrderPage{
	private WebDriver driver;
	utility.CommonMethods pageload;
	
	@FindBy(xpath="//button[@data-tooltip='Create Sales Order']")
	WebElement createSalesOrderIcon;
	
	@FindBy(xpath="//button[@ng-if='Permission.EditOrder' and @data-tooltip='Save']")
	WebElement saveSalesOrder;
	
	@FindBy(xpath="//button[@data-tooltip='Create MPO']")
	WebElement mpoOrderIcon;
	
	public CreateSalesOrderPage(){
		this.driver=LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openCreateSalesOrder() throws InterruptedException {
		createSalesOrderIcon.click();
		Thread.sleep(2000);
		try {
			driver.switchTo().alert().accept();
		}
		catch(Exception e){
		}
		pageload=new utility.CommonMethods();
		pageload.pageWait(saveSalesOrder);
	}
	
	public void saveSalesOrder() throws InterruptedException {
		saveSalesOrder.click();
		pageload=new utility.CommonMethods();
		pageload.pageWait(mpoOrderIcon);
	}
}
