package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcurementPage {

	private WebDriver driver;
	private String accountName = ConvertLeadPage.accountName;
	public static String user = LoginPage.un;
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

	@FindBy(xpath = "//input[@id='Check1']")
	WebElement openMpoChkbox;

	@FindAll(@FindBy(xpath = "//tr[@role='row']"))
	List<WebElement> noOfTableRow;

	@FindBy(xpath = "//select[@ng-model='MPO.CancelReason']")
	WebElement cancelReasonDrpDown;

	@FindAll(@FindBy(xpath = "//select[@ng-model='MPO.CancelReason']/option"))
	List<WebElement> cancelReasonValue;

	@FindBy(xpath = "//label[@class='ldet_label ng-binding' and text()='Canceled']")
	WebElement confirmCancelledStatus;

	@FindBy(xpath = "//a[@class='hquote_hover ng-binding'][1]")
	WebElement salesOrderLnkBtn;

	@FindBy(xpath = "//i[@class='fa fa-exchange']")
	WebElement convertMpoOrderIcon;

	@FindBy(xpath = "//label[@class='ldet_label ng-binding' and text()='Open']")
	WebElement confirmOpenStatus;

	@FindBy(xpath = "//button[@id='expandbtn']")
	WebElement expandAllBtn;

	@FindBy(xpath = "//input[@ng-if='Permission.SubmitOrder']")
	WebElement cancelVendorPOBtn;

	@FindBy(xpath = "//select[@ng-model='VPOCancelReasonValue']")
	WebElement cancelReasonCodeDrpDown;

	@FindBy(xpath = "//select[@ng-model='VPOCancelReasonValue']/option[@label='Customer Cancellation']")
	WebElement vpoCustomerCancellationOption;

	@FindBy(xpath = "//button[@id='cancelbutton']")
	WebElement okayBtn;

	@FindBy(xpath = "//button[@id='collapsebtn']")
	WebElement collapseBtn;

	@FindBy(xpath = "//label[@ng-show='MPO.POStatusId!=10']")
	WebElement statusText;

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

	public void cancelMPOOrder() throws InterruptedException {
		openMpoChkbox.click();
		// dynamicTable = driver.findElement(By.xpath("//tr[@role='row']/td"));
		waitForElement.elementToBeClickable(dynamicTable);
		Thread.sleep(3000);
		int customerInfoRowIncrement = 1;
		for (WebElement tableRow : noOfTableRow) {
			WebElement customerInfoRow = driver
					.findElement(By.xpath("//tr[" + customerInfoRowIncrement + "]/td[5]/a/span[1]"));
			String textOfCustomerInfo = customerInfoRow.getText();
			// String originalCustomer = "Demo Test 20200511 12:51 PM";
			if (accountName.equalsIgnoreCase(textOfCustomerInfo)) {
				WebElement mpoOrderId = driver
						.findElement(By.xpath("//tr[" + customerInfoRowIncrement + "]/td[2]/span/a"));
				mpoOrderId.click();
				waitForElement.elementToBeClickable(cancelReasonDrpDown);
				break;
			}
			customerInfoRowIncrement++;
		}
		cancelReasonDrpDown.click();
		Thread.sleep(1000);
		for (WebElement cancelReasonValueSelect : cancelReasonValue) {
			String actualTxt = cancelReasonValueSelect.getText();
			String ExpectedTxt = "Customer Cancellation";
			if (ExpectedTxt.equalsIgnoreCase(actualTxt)) {
				cancelReasonValueSelect.click();
				Thread.sleep(4000);
				driver.switchTo().alert().accept();
				waitForElement.pageWait(confirmCancelledStatus);
				break;
			}
		}
	}

	public void convertIntoOpenOrder() throws InterruptedException {
		String statusTxt = statusText.getText();
		if (statusTxt.equalsIgnoreCase("Canceled")) {
			salesOrderLnkBtn.click();
			waitForElement.elementToBeClickable(convertMpoOrderIcon);
			Thread.sleep(3000);
			convertMpoOrderIcon.click();
			waitForElement.elementToBeClickable(confirmOpenStatus);
			Thread.sleep(3000);
		}
	}

	public void cancelVendorPOOrder() throws InterruptedException {
		openMpoChkbox.click();
		waitForElement.elementToBeClickable(dynamicTable);
		int customerInfoRowIncrement = 1;
		for (WebElement tableRow : noOfTableRow) {
			WebElement customerInfoRow = driver
					.findElement(By.xpath("//tr[" + customerInfoRowIncrement + "]/td[5]/a/span[1]"));
			String textOfCustomerInfo = customerInfoRow.getText();
			// String originalCustomer = "Demo Test 20200511 12:21 PM";
			if (accountName.equalsIgnoreCase(textOfCustomerInfo)) {
				WebElement mpoOrderId = driver
						.findElement(By.xpath("//tr[" + customerInfoRowIncrement + "]/td[2]/span/a"));
				mpoOrderId.click();
				waitForElement.elementToBeClickable(cancelReasonDrpDown);
				break;
			}
			customerInfoRowIncrement++;
		}
		expandAllBtn.click();
		Thread.sleep(2000);
		while (cancelVendorPOBtn.isDisplayed() == true) {
			Coordinates coordinate = ((Locatable) cancelVendorPOBtn).getCoordinates();
			coordinate.inViewPort();
			waitForElement.scrollBy();
			cancelVendorPOBtn.click();
			Thread.sleep(2000);
			waitForElement.elementToBeClickable(cancelReasonCodeDrpDown);
			cancelReasonCodeDrpDown.click();
			Thread.sleep(1000);
			vpoCustomerCancellationOption.click();
			Thread.sleep(1000);
			okayBtn.click();
			waitForElement.elementToBeClickable(cancelReasonDrpDown);
			collapseBtn.click();
			waitForElement.elementToBeClickable(expandAllBtn);
			expandAllBtn.click();
			Thread.sleep(2000);
			if (cancelVendorPOBtn.isDisplayed() == true) {
				Coordinates coordinate2 = ((Locatable) cancelVendorPOBtn).getCoordinates();
				coordinate2.inViewPort();
				waitForElement.scrollBy();
			}
		}
	}
}
