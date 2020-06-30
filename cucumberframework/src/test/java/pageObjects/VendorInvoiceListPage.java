package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInvoiceListPage {

	private WebDriver driver;

	utility.CommonMethods waitForElement = new utility.CommonMethods();

	@FindBy(xpath = "//a[text()='Vendor Invoice / PPV List']")
	WebElement vendorInvoiceMenuIcon;

	@FindBy(xpath = "//input[@id='btnReset']")
	WebElement clearAllFilterBtn;

	@FindBy(xpath = "//select[@ng-model='Id']")
	WebElement dashboardFilterDrpDown;

	@FindBy(xpath = "//option[@label='Last 180 days (rolling)']")
	WebElement select180Days;

	public VendorInvoiceListPage() {
		driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openVendorInvoice() {

	}
}
