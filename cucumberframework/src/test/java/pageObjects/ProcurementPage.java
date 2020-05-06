package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcurementPage {

	private WebDriver driver;

	utility.CommonMethods waitForElement = new utility.CommonMethods();

	@FindBy(xpath = "//li[@ng-class='NavbarService.OperationSelected']")
	WebElement operationIcon;

	@FindBy(xpath = "//input[@id='btnReset']")
	WebElement clearAllFilterBtn;

	@FindBy(xpath = "//select[@ng-model='Procurement.Id']")
	WebElement dashboardFilter;

	@FindBy(xpath = "//option[@label='Last 180 days (rolling)']")
	WebElement select180Days;

	@FindBy(xpath = "//tr[@role='row']/td")
	WebElement dynamicTable;

	public ProcurementPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openProcurement() throws InterruptedException {
		operationIcon.click();
		waitForElement.pageWait(clearAllFilterBtn);
		dashboardFilter.click();
		Thread.sleep(1000);
		select180Days.click();
		waitForElement.pageWait(dynamicTable);
	}
}
