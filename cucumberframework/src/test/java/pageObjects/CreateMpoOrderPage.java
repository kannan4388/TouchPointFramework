package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateMpoOrderPage {
	private WebDriver driver;
	public static String user = LoginPage.un;
	utility.CommonMethods waitCursor = new utility.CommonMethods();

	@FindBy(xpath = "//button[@data-tooltip='Create MPO']")
	WebElement mpoOrderIcon;

	@FindBy(xpath = "//button[@data-tooltip='Submit Purchase Order']")
	WebElement purchaseOrderIcon;

	@FindBy(xpath = "//button[@ng-click='gotoAddCaseEditWithMPO()']")
	WebElement createCaseIcon;

	public CreateMpoOrderPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openMpoOrder() throws InterruptedException {
		if (user.equalsIgnoreCase("bbtestus")) {
			mpoOrderIcon.click();
			waitCursor.pageWait(purchaseOrderIcon);
			Thread.sleep(2000);
		}
		if (user.equalsIgnoreCase("tltestus") || user.equalsIgnoreCase("cctestus")) {
			mpoOrderIcon.click();
			waitCursor.pageWait(createCaseIcon);
			Thread.sleep(2000);
		}
	}
}
