package pageObjects;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesTaxReportPage {
	public WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "SalesTaxReport.xlsx";
	utility.CommonMethods waitForElement = new utility.CommonMethods();

	@FindBy(xpath = "//ul[@class='reporst_lists']/li/a[text()='Sales Tax']")
	WebElement salesTaxReportLink;

	@FindBy(xpath = "//input[@value='Load Data']")
	WebElement loadDataBtn;

	@FindBy(xpath = "//button[@name='filterDropDown']")
	WebElement dateRangeDrpDwn;

	@FindAll(@FindBy(xpath = "//li[@ng-repeat='date in ReportsService.DateRangeArray']/a"))
	List<WebElement> dateRangeLi;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement gridFooter;

	@FindBy(xpath = "//button[@class='hfc_bgbut tooltip-bottom']")
	WebElement downloadIcon;

	@FindBy(xpath = "//label[text()='Zip/Postal']")
	WebElement zipCodeHeader;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement totalItems;

	@FindAll(@FindBy(xpath = "//table[@role='grid']/tbody/tr"))
	List<WebElement> tableRows;

	@FindBy(xpath = "//a[@title='Go to the next page']")
	WebElement moveToNextPage;

	@FindBy(xpath = "//th[@data-title='Zip/Postal']/a/span")
	WebElement zipCodeFilterIcon;

	@FindBy(xpath = "//span[@class='k-input' and text()='Contains'][1]")
	WebElement containsDrpDwn;

	@FindAll(@FindBy(xpath = "//form/div[2]/div/div[2]/ul/li"))
	List<WebElement> containsOption;

	@FindBy(xpath = "//input[@class='k-textbox' and @data-bind='value:filters[0].value']")
	WebElement containsTxtBox;

	@FindBy(xpath = "//button[@class='k-button k-primary']")
	WebElement filterBtn;

	public SalesTaxReportPage() {
		driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openSaleTax() throws InterruptedException {
		salesTaxReportLink.click();
		waitForElement.elementToBeClickable(loadDataBtn);
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
				loadDataBtn.click();
				waitForElement.pageWait(gridFooter);
				Thread.sleep(4000);
				break;
			}
		}
	}

	public void downloadFile() throws InterruptedException {
		waitForElement.downloadReport(downloadIcon);
	}

	public void sortByZipCode() {
		int columnNumber = 1;
		waitForElement.reportSortByFirstName(zipCodeHeader, totalItems, tableRows, moveToNextPage, columnNumber);
	}

	public void filterByZipCode() throws InvalidFormatException, IOException, InterruptedException {
		String filterByText = waitForElement.getExcelData(filePath, fileName).get("FilterByZipCode");
		waitForElement.reportFilterByFirstName(zipCodeFilterIcon, containsDrpDwn, containsOption, containsTxtBox,
				filePath, fileName, filterBtn, totalItems, tableRows, moveToNextPage, filterByText);
	}
}
