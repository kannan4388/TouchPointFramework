package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesAndPurchasingDetailReportPage {
	private WebDriver driver;
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
		Thread.sleep(2000);
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

	public HashMap<Integer, String> sortByFirstName() throws InterruptedException {
		// pagenationDropDown.click();
		// Thread.sleep(3000);
		firstNameHeader.click();
		int rowIncre = 1;
		HashMap<Integer, String> arrStringFN = new HashMap<Integer, String>();
		for (WebElement rowIdentity : tableRows) {
			WebElement individualFirstName = driver
					.findElement(By.xpath("//table[@role='grid']/tbody/tr[" + rowIncre + "]/td/span"));
			String firstNameTxt = individualFirstName.getText();
			System.out.println(firstNameTxt);

			arrStringFN.put(0, firstNameTxt);
			if (rowIncre >= 25) {
				if (moveToNextPage.isEnabled() == true) {
					moveToNextPage.click();
					rowIncre = 0;
				}
			}
			rowIncre++;
		}
		return arrStringFN;
	}
}
