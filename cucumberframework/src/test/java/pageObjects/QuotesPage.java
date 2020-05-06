package pageObjects;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuotesPage {
	private static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	private static String fileName = "Quotes.xlsx";
	private WebDriver driver;
	utility.CommonMethods wait;

	@FindBy(xpath = "//li[@class='activelead1']")
	WebElement openQuotes;

	@FindBy(xpath = "//input[@id='btnReset']")
	WebElement waitforClearAll;

	@FindBy(xpath = "//button[@class='plus_but tooltip-bottom']")
	WebElement newQuoteIcon;

	@FindBy(xpath = "//input[@name='Side_mark']")
	WebElement sidemarkTxtBox;

	@FindBy(xpath = "//button[@class='tooltip-bottom hfc_bgbut ng-scope' and @data-tooltip='Save']")
	WebElement saveQuote;

	@FindBy(xpath = "//*[@id='Panelopportunity']/span/span[1]")
	WebElement waitForConfigure;

	@FindBy(xpath = "//button[@class='tooltip-bottom cancel_tpbut quotetooltip_icon ng-scope' and @data-tooltip='Configure Core Products']")
	WebElement waitforSpinner;

	@FindBy(xpath = "//*[@id='VendorGrid']/div[3]/table/tbody/tr[1]/td[1]")
	WebElement waitforConfigurePage;

	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement productFilter;

	@FindAll(@FindBy(xpath = "//*[@id='VendorGrid']/div[3]/table/tbody/tr"))
	List<WebElement> productTable;

	@FindAll(@FindBy(xpath = "//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr"))
	List<WebElement> measureTable;

	@FindBy(xpath = "//span[@class='k-input prdrequired']")
	WebElement productClick;

	@FindBy(xpath = "//span[@class='k-input modelrequired']")
	WebElement modelClick;

	// @FindAll(@FindBy(xpath = "//*[@id='Prompts']/tr[@style='display:
	// table-row;']"))
	@FindAll(@FindBy(xpath = "//*[@id='Prompts']/tr"))
	List<WebElement> mandatoryProd;

	@FindBy(xpath = "//button[@class='tooltip-bottom cancel_tpbut' and @data-tooltip='Return to Quote Document']")
	WebElement waitTillProdLoad;

	@FindBy(xpath = "//button[@data-tooltip='Validate & Create / Update Quote Line']")
	WebElement validateCreateQuote;

	@FindBy(xpath = "//div[text()='Product validated successfully and Quote line created/updated.']")
	WebElement quoteCreated;

	@FindBy(xpath = "//button[@data-tooltip='Return to Quote Document']")
	WebElement returnQuote;

	@FindBy(xpath = "//button[@data-tooltip='Edit Quote']")
	WebElement editQuoteIcon;

	@FindBy(xpath = "//select[@name='Quote_status']")
	WebElement quoteStatusDrp;

	@FindAll(@FindBy(xpath = "//select[@name='Quote_status']/option"))
	List<WebElement> statusText;

	@FindBy(xpath = "//button[@data-tooltip='Create Sales Order']")
	WebElement createSalesOrderIcon;

	public QuotesPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openQuotes() throws InterruptedException {
		// openQuotes.click();
		// wait=new utilities.CommonMethods();
		// wait.pageWait(waitforClearAll);
		newQuoteIcon.click();
		Thread.sleep(5000);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {

		}
		// Thread.sleep(5000);
		// driver.navigate().refresh();
		wait = new utility.CommonMethods();
		wait.pageWait(sidemarkTxtBox);
		/*
		 * driver.navigate().refresh(); try { driver.switchTo().alert().accept(); }
		 * catch (Exception e) {
		 * 
		 * } wait.pageWait(sidemarkTxtBox);
		 */
		Thread.sleep(2000);
		// sidemarkTxtBox.sendKeys("55205");
		// Thread.sleep(500);
		saveQuote.click();
		Thread.sleep(4000);
		wait.pageWait(waitForConfigure);
		wait.scrollUptoBottom();
		Thread.sleep(1000);
		wait.pageWait(waitforSpinner);
		Thread.holdsLock(1000);
		waitforSpinner.click();
		wait.pageWait(waitforConfigurePage);
		Thread.sleep(2000);
	}

	public void configureCoreProduct() throws InterruptedException, AWTException, IOException {
		File file = new File(filePath + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if (fileExtension.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			wb = new XSSFWorkbook(fis);
			// Sheet sh=wb.getSheet(0);
		} else if (fileExtension.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			wb = new HSSFWorkbook(fis);
		}
		// Read sheet inside the workbook by its name
		Sheet sh = wb.getSheetAt(0);
		// Find number of rows in excel file
		int rowCount = sh.getLastRowNum();
		// Create a loop all around the rows in excel sheet
		// for (int r = 1; r <= rowCount;) {

		for (int m = 1; m <= rowCount; m++) {
			Row row = sh.getRow(m);
			String vendorName = row.getCell(0).getStringCellValue().toUpperCase().trim();
			// System.out.println(vendorName);
			String productCategory = row.getCell(1).getStringCellValue().toUpperCase().trim();
			// System.out.println(productCategory);
			String measurement = row.getCell(2).getStringCellValue().toUpperCase().trim();
			// System.out.println(measurement);
			String productDrp = row.getCell(3).getStringCellValue().toUpperCase().trim();
			// System.out.println(productDrp);
			String modelDrp = row.getCell(4).getStringCellValue().toUpperCase().trim();
			// System.out.println(modelDrp);
			wait = new utility.CommonMethods();
			// String excelProdFilter = "Window Shadings".toUpperCase();
			productFilter.clear();
			Thread.sleep(500);
			productFilter.sendKeys(productCategory);
			Thread.sleep(500);
			int productSize = productTable.size();
			for (int i = 1; i <= productSize; i++) {
				String productText = driver
						.findElement(By.xpath("//*[@id='VendorGrid']/div[3]/table/tbody/tr[" + i + "]/td[1]"))
						.getText();
				// String excelProduct = "CUSTOM BRANDS GROUP - ONEHD";
				if (productText.equalsIgnoreCase(vendorName)) {
					WebElement srollProd = driver
							.findElement(By.xpath("//*[@id='VendorGrid']/div[3]/table/tbody/tr[" + i + "]/td[1]"));
					// wait.scrollElseIf(srollProd);
					// wait.scrollDown(srollProd);
					Thread.sleep(500);
					srollProd.click();
					Thread.sleep(1000);
					break;
				}
			}

			/* Measure Table value selection */
			int measureSize = measureTable.size();
			for (int j = 1; j <= measureSize; j++) {
				String mountText = driver
						.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + j + "]/td[2]"))
						.getText();
				// String excelMount = "Partial IB";
				if (mountText.equalsIgnoreCase(measurement)) {
					driver.findElement(
							By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + j + "]/td[2]")).click();
					Thread.sleep(1000);
					break;
				}
			}

			/* Product drop down value selection */
			Coordinates coordinateNext = ((Locatable) productClick).getCoordinates();
			coordinateNext.inViewPort();
			Thread.sleep(1000);
			wait.scrollBy();
			productClick.click();
			Thread.sleep(1500);
			// String productDrpDown = "WINDOW SHADINGS".toUpperCase();
			WebElement prodElement = driver
					.findElement(By.xpath("//*[@id='prdDownloadlist_listbox']/li[text()='" + productDrp + "']"));
			// wait.scrollElseIf(prodElement);
			prodElement.click();
			Thread.sleep(1000);
			/* Model drop down value selection */
			Coordinates coordinateModel = ((Locatable) modelClick).getCoordinates();
			coordinateModel.inViewPort();
			Thread.sleep(1000);
			wait.scrollBy();
			modelClick.click();
			Thread.sleep(500);
			// String modelDrpDown = "Window Shadings Powerwand".toUpperCase();
			WebElement modelElement = driver
					.findElement(By.xpath("//*[@id='modelDownloadlist_listbox']/li[text()='" + modelDrp + "']"));
			wait.scrollElseIf(modelElement);

			/* Mandatory Product Detail fields data filling */
			wait.pageWait(waitTillProdLoad);
			int productDetailsSize = mandatoryProd.size();
			for (int k = 1; k <= productDetailsSize; k++) {
				WebElement inputProd;
				WebElement dropdownProd;
				String xpathOfField = "";
				String attrOfFirstProd = "";
				String attrOfReq = "";
				String styleOfTr = driver.findElement(By.xpath("//*[@id='Prompts']/tr[" + k + "]"))
						.getAttribute("style");
				if (styleOfTr.equalsIgnoreCase("display: table-row;")) {
					if (driver.findElements(By.xpath("//*[@id='Prompts']/tr[" + k + "]/td[1]/input")).size() != 0) {
						inputProd = driver.findElement(By.xpath("//*[@id='Prompts']/tr[" + k + "]/td[1]/input"));
						xpathOfField = inputProd.getAttribute("id");
						inputProd = driver.findElement(By.xpath("//*[@id='Prompts']/tr[" + k + "]/td[1]/input"));
						attrOfFirstProd = inputProd.getAttribute("class");
						attrOfReq = inputProd.getAttribute("placeholder");
					}
					if (xpathOfField.isEmpty()) {
						dropdownProd = driver.findElement(By.xpath("//*[@id='Prompts']/tr[" + k + "]/td[1]/div"));
						xpathOfField = dropdownProd.getAttribute("id");
						attrOfFirstProd = dropdownProd.getAttribute("class");
						attrOfReq = dropdownProd.getAttribute("placeholder");
					}
					String actualClass = "prompt form-control frm_controllead kendoioTex errorTP "
							+ "required_field prompt kendoiodrpdn";
					String actualReq = "Required";
					String attrDrp = "select2-container prompt kendoiodrpdn errorTP required_field prompt kendoiodrpdn";
					String attDrp2 = "select2-container prompt kendoiodrpdn select2dropdown "
							+ "errorTP required_field prompt kendoiodrpdn";
					String drpDownTxt = "PLEASE MAKE A SELECTION";
					WebElement txtBox = driver.findElement(By.xpath("//*[@id='" + xpathOfField + "']"));
					// wait.scrollDown(txtBox);
					Thread.sleep(500);
					String mandDrp = txtBox.getText();
					// String style=firstProd.getAttribute("id");
					if (attrOfFirstProd.equalsIgnoreCase(actualClass)
							&& attrOfReq.equalsIgnoreCase(actualReq) == true) {
						Coordinates coordinateTxtBox = ((Locatable) txtBox).getCoordinates();
						coordinateTxtBox.inViewPort();
						Thread.sleep(1000);
						wait.scrollBy();
						Thread.sleep(500);
						txtBox.click();
						Thread.sleep(500);
						String type = txtBox.getAttribute("type");
						if (type.equalsIgnoreCase("text")) {
							txtBox.sendKeys("testTxtBox");
						} else if (type.equalsIgnoreCase("number")) {
							txtBox.sendKeys("25");
						}
					} else if (attrOfFirstProd.equalsIgnoreCase(attrDrp) || attrOfFirstProd.equalsIgnoreCase(attDrp2)
							&& drpDownTxt.equalsIgnoreCase(mandDrp) == true) {
						txtBox = driver.findElement(By.xpath("//*[@id='" + xpathOfField + "']"));
						WebElement scroll = driver.findElement(By.xpath("//*[@id='Prompts']/tr[" + k + "]/td"));
						Coordinates coordinateScroll = ((Locatable) scroll).getCoordinates();
						coordinateScroll.inViewPort();
						Thread.sleep(1000);
						wait.scrollBy();
						Thread.sleep(1000);
						// Actions actions = new Actions(driver);
						// actions.moveToElement(txtBox).click().build().perform();
						// WebElement txtBoxMove = driver.findElement(By.xpath("//*[@id='Prompts']/tr["
						// + k + "]/td"));
						Coordinates coordinateTxtBox = ((Locatable) txtBox).getCoordinates();
						coordinateTxtBox.inViewPort();
						Thread.sleep(1000);
						wait.scrollBy120();
						// txtBox.click();
						Actions actions = new Actions(driver);
						actions.moveToElement(txtBox).click().build().perform();
						Thread.sleep(1000);
						String splitXpath = xpathOfField.split("_")[1];
						// System.out.println(splitXpath);
						WebElement drpDownClick = driver
								.findElement(By.xpath("//*[@id='" + splitXpath + "']/option[2]"));
						drpDownClick.click();
						Thread.sleep(1000);
						Actions closeDrpDown = new Actions(driver);
						WebElement txt = driver.findElement(By.xpath("//*[@id='" + xpathOfField + "']"));
						closeDrpDown.moveToElement(txt).click().build().perform();
						Thread.sleep(1000);
					}
				}
			}
			validateCreateQuote.click();
			wait.pageWait(quoteCreated);
			wait.pageWait(returnQuote);
			Thread.sleep(2000);
		}

	}

	public void returnToQuote() throws InterruptedException {
		returnQuote.click();
		wait = new utility.CommonMethods();
		wait.pageWait(editQuoteIcon);
	}

	public void editQuote() throws InterruptedException {
		editQuoteIcon.click();
		wait = new utility.CommonMethods();
		wait.pageWait(quoteStatusDrp);
	}

	public void updateQuoteStatus() throws InterruptedException {
		quoteStatusDrp.click();
		Thread.sleep(1500);
		// int quoteStatusDrpSize=statusText.size();
		for (WebElement expectedStr : statusText) {
			if (expectedStr.getText().contains("Accepted")) {
				expectedStr.click();
				Thread.sleep(800);
				break;
			}
		}
	}

	public void saveQuote() throws InterruptedException {
		saveQuote.click();
		// Thread.sleep(4000);
		wait = new utility.CommonMethods();
		wait.elementToBeClickable(createSalesOrderIcon);
	}
}
