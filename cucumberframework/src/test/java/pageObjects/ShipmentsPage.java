package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShipmentsPage {

	private WebDriver driver;

	utility.CommonMethods waitForElement = new utility.CommonMethods();

	@FindBy(xpath = "//a[text()='Shipments']")
	WebElement shipmentMenu;

	@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']")
	WebElement shipmentPageLoad;

	@FindBy(xpath = "//select[@ng-model='Procurement.Id']")
	WebElement dashboardFilterDrpDown;

	@FindBy(xpath = "//option[@label='Last 180 days (rolling)']")
	WebElement select180Days;

	@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']")
	WebElement dynamicTableLoad;

	public ShipmentsPage() {
		driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openShipment() throws InterruptedException {
		shipmentMenu.click();
		waitForElement.elementToBeClickable(dashboardFilterDrpDown);
		dashboardFilterDrpDown.click();
		Thread.sleep(1000);
		select180Days.click();
		waitForElement.pageWait(dynamicTableLoad);
	}
}
