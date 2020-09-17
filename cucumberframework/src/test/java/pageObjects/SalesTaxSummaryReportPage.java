package pageObjects;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesTaxSummaryReportPage {
	private WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "SalesTaxReport.xlsx";
	utility.CommonMethods cmSalesTaxSum = new utility.CommonMethods();

	@FindBy(xpath = "//ul[@class='reporst_lists']/li/a[text()='Sales Tax Summary']")
	WebElement salesTaxSummaryReportLink;

	@FindBy(xpath = "//input[@value='Load Data']")
	WebElement loadDataBtn;

	@FindBy(xpath = "//button[@name='filterDropDown']")
	WebElement dateRangeDrpDwn;

	@FindAll(@FindBy(xpath = "//li[@ng-repeat='date in ReportsService.DateRangeArray']/a"))
	List<WebElement> dateRangeLi;

	@FindBy(xpath = "//select[@ng-options='value for value in SalesTaxReportType']")
	WebElement selectReportTypeDrpDwn;

	@FindAll(@FindBy(xpath = "//select[@ng-options='value for value in SalesTaxReportType']/option"))
	List<WebElement> selectReportTypeDrpDwnLi;

	@FindBy(xpath = "//tr[2]")
	WebElement tableRow;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement gridFooter;

	@FindBy(xpath = "//button[@class='plus_but tooltip-bottom']")
	WebElement downloadIcon;

	@FindBy(xpath = "//th[@data-title='Special']")
	WebElement zipCodeHeader;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement totalItems;

	@FindAll(@FindBy(xpath = "//table[@role='grid']/tbody/tr"))
	List<WebElement> tableRows;

	@FindBy(xpath = "//a[@title='Go to the next page']")
	WebElement moveToNextPage;

	@FindBy(xpath = "//th[@data-title='Special']/a/span")
	WebElement zipCodeFilterIcon;

	@FindBy(xpath = "//span[@class='k-input' and text()='Contains'][1]")
	WebElement containsDrpDwn;

	@FindAll(@FindBy(xpath = "//form/div[2]/div/div[2]/ul/li"))
	List<WebElement> containsOption;

	@FindBy(xpath = "//input[@class='k-textbox' and @data-bind='value:filters[0].value']")
	WebElement containsTxtBox;

	@FindBy(xpath = "//button[@class='k-button k-primary']")
	WebElement filterBtn;

	@FindBy(xpath = "//tr[1]/td[1]")
	WebElement firstRow;

	public SalesTaxSummaryReportPage() {
		driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openSalesTaxSummary() throws InterruptedException {
		salesTaxSummaryReportLink.click();
		cmSalesTaxSum.elementToBeClickable(loadDataBtn);
		Thread.sleep(3000);
	}

	public void loadData() throws InterruptedException {
		dateRangeDrpDwn.click();
		Thread.sleep(1000);
		for (WebElement dateRange : dateRangeLi) {
			String dateRangeTxt = dateRange.getText();
			String selectDateRange = "Last Year";
			if (dateRangeTxt.equalsIgnoreCase(selectDateRange)) {
				dateRange.click();
				Thread.sleep(1000);
				break;
			}
		}
		selectReportTypeDrpDwn.click();
		Thread.sleep(400);
		for (WebElement selectReportType : selectReportTypeDrpDwnLi) {
			String selectReportTypeTxt = selectReportType.getText();
			String reportType = "Zip";
			if (selectReportTypeTxt.equalsIgnoreCase(reportType)) {
				selectReportType.click();
				Thread.sleep(1000);
				break;
			}
		}
		loadDataBtn.click();
		try {
			cmSalesTaxSum.elementToBeClickable(firstRow);
		} catch (Exception e) {

		}
		cmSalesTaxSum.pageWait(gridFooter);
		// String gridFooterTxt=gridFooter.getText()
		Thread.sleep(4000);
	}

	public void downloadFile() throws InterruptedException {
		cmSalesTaxSum.downloadReport(downloadIcon);
	}

	public void sortByZipCode() {
		int columnNumber = 1;
		cmSalesTaxSum.reportSortByFirstName(zipCodeHeader, totalItems, tableRows, moveToNextPage, columnNumber);
	}

	public void filterByZipCode() throws InvalidFormatException, IOException, InterruptedException {
		String filterByText = cmSalesTaxSum.getExcelData(filePath, fileName).get("FilterByZipCode");
		String numWithoutDecimal = String.valueOf(filterByText).split("\\.")[0];
		cmSalesTaxSum.reportFilterByFirstName(zipCodeFilterIcon, containsDrpDwn, containsOption, containsTxtBox,
				filePath, fileName, filterBtn, totalItems, tableRows, moveToNextPage, numWithoutDecimal);
	}
}
