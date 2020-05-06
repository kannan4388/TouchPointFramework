package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateMpoOrderPage{
	private WebDriver driver;
	utility.CommonMethods waitCursor;
	
	@FindBy(xpath="//button[@data-tooltip='Create MPO']")
	WebElement mpoOrderIcon;
	
	@FindBy(xpath="//button[@data-tooltip='Submit Purchase Order']")
	WebElement purchaseOrderIcon;
	
	public CreateMpoOrderPage() {
		this.driver=LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openMpoOrder() throws InterruptedException {
		mpoOrderIcon.click();
		waitCursor=new utility.CommonMethods();
		waitCursor.pageWait(purchaseOrderIcon);
	}
}
	
