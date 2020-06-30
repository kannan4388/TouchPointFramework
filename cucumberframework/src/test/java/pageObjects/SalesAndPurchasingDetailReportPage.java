package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
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

	@FindBy(xpath = "//label[text()='First Name']")
	WebElement firstNameHeader;

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
				waitForElement.elementToBeClickable(gridFooterItems);
				break;
			}
		}
	}

	public void getDownloadedDocumentName() throws InterruptedException, IOException {
		// Map<String,Object> prefs = new HashMap<String, Object>();
		// folder = new File(UUID.randomUUID().toString());
		// folder.mkdir();
		downloadIcon.click();
		Thread.sleep(10000);
		// System.out.println(folder);
		File listOfFiles[] = folder.listFiles();
		// make sure the directory is not empty
		Assert.assertTrue(listOfFiles.length > 0);

		for (File file : listOfFiles) {
			// make sure that downloaded file is not empty
			Assert.assertTrue(file.length() > 0);
		}
		for (File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
	}

	public String sortByFirstName() throws InterruptedException {
		// pagenationDropDown.click();
		// Thread.sleep(3000);
		firstNameHeader.click();
		int rowIncre = 1;
		ArrayList<String> arrStringFN = new ArrayList<String>();
		String txtNoOfRecords = totalItems.getText();
		if (!txtNoOfRecords.equalsIgnoreCase("No items to display")) {
			String[] arrayOfTxtCount = txtNoOfRecords.split("of");
			String numberOfItemsTxt = arrayOfTxtCount[1];
			String[] txtArrayItemsOf = numberOfItemsTxt.split("items");
			String sizeOfTable = txtArrayItemsOf[0].toString().trim();
			int sizeOfTableCnt = 0;
			sizeOfTableCnt = Integer.parseInt(sizeOfTable);
			int tableCount = 1;
			int table = 1;
			for (table = tableCount; tableCount <= sizeOfTableCnt;) {
				for (WebElement rowIdentity : tableRows) {
					WebElement individualFirstName = driver
							.findElement(By.xpath("//table[@role='grid']/tbody/tr[" + rowIncre + "]/td/span"));
					String firstNameTxt = individualFirstName.getText();
					// System.out.println(firstNameTxt);
					arrStringFN.add(firstNameTxt);
					if (rowIncre >= 25) {
						if (moveToNextPage.isEnabled() == true) {
							moveToNextPage.click();
							rowIncre = 0;
						}
					}
					rowIncre++;
					tableCount++;
				}
			}
		}
		waitForElement.isSorted(arrStringFN);
		if (waitForElement.isSorted(arrStringFN) == true) {
			System.out.println("First Name is in sort order");
		} else {
			System.out.println("First Name not in sort order");
		}

		return arrStringFN.toString();

		// System.out.println(arrStringFN);
	}

	public void filterByFirstName() throws InterruptedException, InvalidFormatException, IOException {
		firstNameFilterIcon.click();
		Thread.sleep(1000);
		containsDrpDwn.click();
		Thread.sleep(1000);
		for (WebElement containsOpt : containsOption) {
			String txtOfContains = containsOpt.getText();
			if (txtOfContains.equalsIgnoreCase("Is equal to")) {
				containsOpt.click();
				Thread.sleep(1000);
				containsTxtBox.click();
				Thread.sleep(1000);
				filterByText = waitForElement.getExcelData(filePath, fileName).get("FilterByTextEquals");
				containsTxtBox.sendKeys(filterByText);
				Thread.sleep(1000);
				filterBtn.click();
				Thread.sleep(1000);
				break;
			}
		}
		int rowIncre = 1;
		ArrayList<String> arrStringFN = new ArrayList<String>();
		String txtNoOfRecords = totalItems.getText();
		if (!txtNoOfRecords.equalsIgnoreCase("No items to display")) {
			String[] arrayOfTxtCount = txtNoOfRecords.split("of");
			String numberOfItemsTxt = arrayOfTxtCount[1];
			String[] txtArrayItemsOf = numberOfItemsTxt.split("items");
			String sizeOfTable = txtArrayItemsOf[0].toString().trim();
			int sizeOfTableCnt = 0;
			sizeOfTableCnt = Integer.parseInt(sizeOfTable);
			int tableCount = 1;
			int table = 1;
			String actualFilterTableRows = null;
			for (table = tableCount; tableCount <= sizeOfTableCnt;) {
				for (WebElement rowIdentity : tableRows) {
					WebElement individualFirstName = driver
							.findElement(By.xpath("//table[@role='grid']/tbody/tr[" + rowIncre + "]/td/span"));
					String firstNameTxt = individualFirstName.getText();
					// System.out.println(firstNameTxt);
					if (!firstNameTxt.equalsIgnoreCase(filterByText)) {
						actualFilterTableRows = "Filter By First Name not working corrrectly";
					}
					arrStringFN.add(firstNameTxt);
					if (rowIncre >= 25) {
						if (moveToNextPage.isEnabled() == true) {
							moveToNextPage.click();
							rowIncre = 0;
						}
					}
					rowIncre++;
					tableCount++;
				}
			}
			System.out.print(actualFilterTableRows);
		}

	}
}
