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

public class SalesSummaryReportPage {

	private WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "SalesSummaryReport.xlsx";
	File folder = LoginPage.folder;
	utility.CommonMethods waitElement = new utility.CommonMethods();

	@FindBy(xpath = "//button[@ng-click='ReportHeaderService.gotohome()']")
	WebElement reportHomeBtn;

	@FindBy(xpath = "//ul[@class='reporst_lists']/li/a[text()='Sales Summary']")
	WebElement salesSummaryReportLink;

	@FindBy(xpath = "//span[@aria-owns='SalePersonList_listbox']")
	WebElement salesPersonDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@id='SalePersonList_listbox']/li"))
	List<WebElement> salesPersonLi;

	@FindBy(xpath = "//span[@aria-owns='CommercialTypeList_listbox']")
	WebElement commercialDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@id='CommercialTypeList_listbox']/li"))
	List<WebElement> commercialDrpDwnLi;

	@FindBy(xpath = "//button[@name='filterDropDown']")
	WebElement dateRangeDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@class='dropdown-menu reportsdatarange_grid show']/li"))
	List<WebElement> dateRangeLi;

	@FindBy(xpath = "//input[@value='Load Data']")
	WebElement loadDataBtn;

	@FindBy(xpath = "//div[@class='k-loading-image']")
	WebElement loadCursorIcon;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement gridFooter;

	@FindBy(xpath = "//button[@class='hfc_bgbut tooltip-bottom']")
	WebElement downloadIcon;

	@FindBy(xpath = "//label[text()='First Name']")
	WebElement firstNameHeader;

	@FindBy(xpath = "//span[@class='k-pager-info k-label']")
	WebElement totalItems;

	@FindAll(@FindBy(xpath = "//table[@role='grid']/tbody/tr"))
	List<WebElement> tableRows;

	@FindBy(xpath = "//a[@title='Go to the next page']")
	WebElement moveToNextPage;

	@FindBy(xpath = "//a[@class='k-grid-filter'][1]")
	WebElement firstNameFilterIcon;

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

	public SalesSummaryReportPage() {
		driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openSalesSummary() throws InterruptedException {
		salesSummaryReportLink.click();
		waitElement.elementToBeClickable(salesPersonDrpDwn);
		Thread.sleep(2000);

	}

	public void loadData() throws InterruptedException {
		dateRangeDrpDwn.click();
		waitElement.elementToBeClickable(commercialDrpDwn);
		for (WebElement lastYearLi : dateRangeLi) {
			String actualLiText = lastYearLi.getText();
			String expectedLiText = "Last Year";
			if (actualLiText.equalsIgnoreCase(expectedLiText)) {
				lastYearLi.click();
				waitElement.elementToBeClickable(loadDataBtn);
				Thread.sleep(1000);
				break;
			}
		}
		loadDataBtn.click();
		try {
			waitElement.elementToBeClickable(firstRow);
		} catch (Exception e) {

		}
		waitElement.pageWait(gridFooter);
		Thread.sleep(3000);
	}

	public void getDownloadedDocumentName() throws InterruptedException, IOException {
		waitElement.downloadReport(downloadIcon);
	}

	public void sortByFirstName() {
		int columnNumber = 1;
		waitElement.reportSortByFirstName(firstNameHeader, totalItems, tableRows, moveToNextPage, columnNumber);
	}

	public void filterByFirstName() throws InvalidFormatException, IOException, InterruptedException {
		String filterByText = waitElement.getExcelData(filePath, fileName).get("FilterByTextEquals");
		waitElement.reportFilterByFirstName(firstNameFilterIcon, containsDrpDwn, containsOption, containsTxtBox,
				filePath, fileName, filterBtn, totalItems, tableRows, moveToNextPage, filterByText);
	}
}
