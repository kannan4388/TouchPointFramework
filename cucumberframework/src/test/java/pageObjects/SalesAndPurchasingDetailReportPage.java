package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesAndPurchasingDetailReportPage {

	private WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "SalesSummaryReport.xlsx";
	private static String filterByText;
	File folder;
	utility.CommonMethods waitForElement = new utility.CommonMethods();

	@FindBy(xpath = "//ul[@class='menu flex tabs1 reports_hfcbtmnav']/li[1]")
	WebElement salesDetailReportMenu;

	@FindBy(xpath = "//input[@class='cancel_but']")
	WebElement reportPageWait;

	@FindBy(xpath = "//button[@name='filterDropDown']")
	WebElement dateRangeDrpDown;

	@FindAll(@FindBy(xpath = "//li[@ng-repeat='date in ReportsService.DateRangeArray']"))
	List<WebElement> dateRangeElements;

	@FindBy(xpath = "//input[@class='cancel_but' and @value='Load Data']")
	WebElement loadDataBtn;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement gridFooterItems;

	@FindBy(xpath = "//button[@class='hfc_bgbut tooltip-bottom']")
	WebElement downloadIcon;

	@FindBy(xpath = "//label[text()='Cust Name']")
	WebElement customerNameHeader;

	@FindBy(xpath = "//span[@class='k-widget k-dropdown k-header']")
	WebElement pagenationDropDown;

	@FindBy(xpath = "//option[@value='all']")
	WebElement selectAllPagination;

	@FindAll(@FindBy(xpath = "//table[@role='grid']/tbody/tr"))
	List<WebElement> tableRows;

	@FindBy(xpath = "//a[@title='Go to the next page']")
	WebElement moveToNextPage;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement totalItems;

	@FindBy(xpath = "//th[3]/a/span[@class='k-icon k-i-filter']")
	WebElement customerNameFilterIcon;

	@FindBy(xpath = "//span[@class='k-input' and text()='Contains'][1]")
	WebElement containsDrpDwn;

	@FindAll(@FindBy(xpath = "//form/div[2]/div/div[2]/ul/li"))
	List<WebElement> containsOption;

	@FindBy(xpath = "//input[@class='k-textbox' and @data-bind='value:filters[0].value']")
	WebElement containsTxtBox;

	@FindBy(xpath = "//button[@class='k-button k-primary']")
	WebElement filterBtn;

	@FindBy(xpath = "//*[@id='gridSalesAndPurchaseSearch']/div[4]/div/div[1]")
	WebElement loadIcon;

	@FindBy(xpath = "//tr[1]/td[1]")
	WebElement firstRow;

	public SalesAndPurchasingDetailReportPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
		folder = LoginPage.folder;
	}

	public void loadDataForSalesDetailReport() throws InterruptedException {
		waitForElement.elementToBeClickable(dateRangeDrpDown);
		// salesDetailReportMenu.click();
		// waitForElement.elementToBeClickable(reportPageWait);
		dateRangeDrpDown.click();
		Thread.sleep(1000);
		String dateRange = "Last Year";
		for (WebElement dateRangeTxt : dateRangeElements) {
			String actualDropDownDateRange = dateRangeTxt.getText();
			if (actualDropDownDateRange.equalsIgnoreCase(dateRange)) {
				dateRangeTxt.click();
				Thread.sleep(1000);
				loadDataBtn.click();
				try {
					waitForElement.elementToBeClickable(firstRow);
				} catch (Exception e) {

				}
				waitForElement.elementToBeClickable(gridFooterItems);
				Thread.sleep(5000);
				break;
			}
		}
	}

	public void getDownloadedDocumentName() throws InterruptedException, IOException {
		waitForElement.downloadReport(downloadIcon);
	}

	public void sortByFirstName() throws InterruptedException {
		int columnNumber = 3;
		waitForElement.reportSortByFirstName(customerNameHeader, totalItems, tableRows, moveToNextPage, columnNumber);
	}

	public void filterByFirstName() throws InterruptedException, InvalidFormatException, IOException {
		String filterByText = waitForElement.getExcelData(filePath, fileName).get("FilterByTextEquals");
		waitForElement.reportFilterByFirstName(customerNameFilterIcon, containsDrpDwn, containsOption, containsTxtBox,
				filePath, fileName, filterBtn, totalItems, tableRows, moveToNextPage, filterByText);
	}
}
