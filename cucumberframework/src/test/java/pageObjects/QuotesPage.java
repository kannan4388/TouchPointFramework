package pageObjects;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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

import junit.framework.Assert;

public class QuotesPage {
	private static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	private static String fileName = "Quotes.xlsx";
	public static String user = LoginPage.un;
	private WebDriver driver;
	private static DecimalFormat df = new DecimalFormat("0.00");
	utility.CommonMethods wait = new utility.CommonMethods();;

	@FindBy(xpath = "//li[@class='activelead1']")
	WebElement openQuotes;

	@FindBy(xpath = "//input[@id='btnReset']")
	WebElement waitforClearAll;

	@FindBy(xpath = "//button[@class='plus_but tooltip-bottom']")
	WebElement newQuoteIcon;

	@FindBy(xpath = "//input[@name='quote_name']")
	WebElement quoteNameTxtBox;

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

	@FindBy(xpath = "//button[@class='tooltip-bottom hfc_bgbut' and @data-tooltip='Return to Quote Document']")
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

	@FindBy(xpath = "//button[@ng-if='Permission.AddQuotes' and @data-tooltip='Add My Products']")
	WebElement addProductIcon;

	@FindBy(xpath = "//input[@value='Clear All Filters']")
	WebElement clearAllFilterBtn;

	@FindBy(xpath = "//tr[1]/td[1]/label")
	WebElement firstAddProduct;

	@FindBy(xpath = "//tr[2]/td[1]/label")
	WebElement secondAddProduct;

	@FindBy(xpath = "//button[@ng-click='SaveMyProductConfiguration()']")
	WebElement saveAddProduct;

	@FindBy(xpath = "//h3[text()='Quotes']")
	WebElement ccQuotesIcon;

	@FindBy(xpath = "//button[@data-tooltip='Cancel' and @class='tooltip-bottom hfc_bgbut ng-scope']")
	WebElement closeQuotePageIcon;

	@FindBy(xpath = "//button[@data-tooltip='Delete Quote Lines']")
	WebElement tableDeleteIcon;

	@FindBy(xpath = "//span[@aria-owns='quote_discount_listbox']")
	WebElement quoteDiscountDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@id='quote_discount_listbox' and @aria-hidden='false' ]/li"))
	List<WebElement> quoteDiscountDrpDwnLi;

	@FindBy(xpath = "//tr[1]/td[2]/span/label")
	WebElement prodSubTotal;

	@FindBy(xpath = "//tr[2]/td[2]/span/label")
	WebElement totalDiscApplied;

	@FindBy(xpath = "//tr[3]/td[2]/span/label")
	WebElement additionalCharges;

	@FindBy(xpath = "//tr[4]/td[2]/span/label")
	WebElement tax;

	@FindBy(xpath = "//tr[5]/td[2]/span/label")
	WebElement total;

	@FindBy(xpath = "//select[@name='Quote_dis_currency']/option[@selected]")
	WebElement discountType;

	@FindBy(xpath = "//input[@class='k-formatted-value form-control quote-qty k-input']")
	WebElement discountValue;

	@FindBy(xpath = "//label[@class='tooltip-bottom font-weight-normal text-center quotetooltip_icon']/a")
	WebElement discountByProductIcon;

	@FindBy(xpath = "//div[@id='GridGroupDiscount']/div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	WebElement discountByProdCatgy;

	@FindAll(@FindBy(xpath = "//div[@id='GridGroupDiscount']/div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr"))
	List<WebElement> discountByProdCatgyTable;

	@FindBy(xpath = "//div[@id='GridGroupDiscount']/div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[1]/span")
	WebElement productCategory;

	@FindBy(xpath = "//div[@id='GridGroupDiscount']/div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[2]")
	WebElement discountPercentage;

	@FindBy(xpath = "//*[@id='GridGroupDiscount']/div[2]/table/tbody/tr[1]/td[2]/span/span/input[2]")
	WebElement discountTxtBox;

	@FindBy(xpath = "//div[@id='GridGroupDiscount']/div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[3]/span")
	WebElement noOfLines;

	@FindBy(xpath = "//button[@ng-show='QuoteinEditMode']")
	WebElement discProdCatSaveBtn;

	@FindAll(@FindBy(xpath = "//tr[@class='k-master-row ng-scope']"))
	List<WebElement> quoteLines;

	@FindBy(xpath = "//tr[@class='k-master-row k-grid-edit-row']/td[4]")
	WebElement suggestedResale;

	@FindBy(xpath = "//tr[@class='k-master-row k-grid-edit-row']/td[5]/span/span/input")
	WebElement unitPrice;

	public QuotesPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openQuotes() throws InterruptedException {
		if (user.equalsIgnoreCase("bbtestus")) {
			newQuoteIcon.click();
			Thread.sleep(5000);
			try {
				driver.switchTo().alert().accept();
			} catch (Exception e) {

			}
			wait.pageWait(quoteNameTxtBox);
			Thread.sleep(2000);
			saveQuote.click();
			// Thread.sleep(4000);
			wait.pageWait(waitForConfigure);
			editQuote();
			wait.scrollUptoBottom();
			Thread.sleep(1000);
			wait.pageWait(waitforSpinner);
			Thread.holdsLock(1000);
			waitforSpinner.click();
			wait.pageWait(waitforConfigurePage);
			Thread.sleep(2000);
		}
		if (user.equalsIgnoreCase("tltestus")) {
			newQuoteIcon.click();
			Thread.sleep(5000);
			try {
				driver.switchTo().alert().accept();
			} catch (Exception e) {

			}
			wait.pageWait(quoteNameTxtBox);
			Thread.sleep(2000);
			try {
				wait.scrollUptoBottom();
				// wait.ExecutorClick(firstRowChkBox);
				closeQuotePageIcon.click();
				Thread.sleep(500);
				driver.switchTo().alert().accept();
				// wait.elementToBeClickable(quoteNameTxtBox);
				wait.elementToBeClickable(editQuoteIcon);
				editQuoteIcon.click();
				// Thread.sleep(500);
				// driver.switchTo().alert().accept();
				wait.elementToBeClickable(quoteNameTxtBox);
			} catch (Exception e) {

			}
			// saveQuote.click();
			// Thread.sleep(4000);
			// wait.pageWait(waitForConfigure);
			wait.scrollUptoBottom();
			// Thread.sleep(1000);
			// wait.pageWait(addProductIcon);
			// Thread.holdsLock(1000);
		}
		if (user.equalsIgnoreCase("cctestus")) {
			ccQuotesIcon.click();
			wait.elementToBeClickable(newQuoteIcon);
			Thread.sleep(3000);
			newQuoteIcon.click();
			Thread.sleep(5000);
			try {
				driver.switchTo().alert().accept();
			} catch (Exception e) {

			}
			wait.pageWait(quoteNameTxtBox);
			Thread.sleep(2000);
			wait.scrollUptoBottom();
		}
	}

	public void configureCoreProduct() throws InterruptedException, AWTException, IOException {
		if (user.equalsIgnoreCase("bbtestus")) {
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
							.findElement(
									By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + j + "]/td[2]"))
							.getText();
					// String excelMount = "Partial IB";
					if (mountText.equalsIgnoreCase(measurement)) {
						driver.findElement(
								By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + j + "]/td[2]"))
								.click();
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
						} else if (attrOfFirstProd.equalsIgnoreCase(attrDrp)
								|| attrOfFirstProd.equalsIgnoreCase(attDrp2)
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
		if (user.equalsIgnoreCase("tltestus") || user.equalsIgnoreCase("cctestus")) {
			addProductIcon.click();
			wait.pageWait(clearAllFilterBtn);
			Thread.sleep(2000);
			boolean visibleFirstAddProduct = firstAddProduct.isEnabled();
			boolean visibleSecondAddProduct = secondAddProduct.isEnabled();
			if (visibleFirstAddProduct == true) {
				firstAddProduct.click();
				Thread.sleep(500);
			}
			if (visibleSecondAddProduct == true) {
				secondAddProduct.click();
				Thread.sleep(500);
			}
			saveAddProduct.click();
			wait.pageWait(saveQuote);
			Thread.sleep(2000);
			saveQuote.click();
			wait.pageWait(editQuoteIcon);
			Thread.sleep(2000);
		}
	}

	public void returnToQuote() throws InterruptedException {
		String user = LoginPage.un;
		if (user.equalsIgnoreCase("bbtestus")) {
			returnQuote.click();
			wait.pageWait(editQuoteIcon);
			Thread.sleep(2000);
		}
	}

	public void editQuote() throws InterruptedException {
		editQuoteIcon.click();
		wait.pageWait(quoteStatusDrp);
		Thread.sleep(2000);
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
		wait.elementToBeClickable(createSalesOrderIcon);
		Thread.sleep(2000);
	}

	public void discountSetUp() throws InterruptedException {
		/* Created by kannan on 22-09-2020 */
		quoteDiscountDrpDwn.click();
		Thread.sleep(1500);
		for (WebElement discountName : quoteDiscountDrpDwnLi) {
			if (discountName.getText().contains("Milage")) {
				discountName.click();
				Thread.sleep(800);
				break;
			}
		}
		saveQuote.click();
		wait.elementToBeClickable(editQuoteIcon);
		Thread.sleep(2000);
		editQuote();
		// Product sub total field data
		String productSubTotal = prodSubTotal.getText();
		String[] arrProdSubTot = productSubTotal.split("\\$");
		String strProdSubTotal = arrProdSubTot[1].trim();
		Float finalProdSubTotal = Float.valueOf(strProdSubTotal);
		// Total Discount Appiled field data
		String totalDiscountApplied = totalDiscApplied.getText();
		String[] arrTotDiscApp = totalDiscountApplied.split("\\$");
		String[] arrTotDiscApp2 = arrTotDiscApp[1].split("\\)");
		String strTotalDiscApp = arrTotDiscApp2[0].trim();
		Float finalTotalDiscApp = Float.valueOf(strTotalDiscApp);
		// Additional Charges field data
		String addCharges = additionalCharges.getText();
		String[] arrAddCharge = addCharges.split("\\$");
		String strAddCharge = arrAddCharge[1].trim();
		Float finalAddCharges = Float.valueOf(strAddCharge);
		// Tax field data
		String taxDisc = tax.getText();
		String[] arrTax = taxDisc.split("\\$");
		String strTax = arrTax[1].trim();
		Float finalTax = Float.valueOf(strTax);
		// Total Quote field data
		String totalQuote = total.getText();
		String[] arrTotal = totalQuote.split("\\$");
		String strTotal = arrTotal[1].trim();
		Float total = Float.valueOf(strTotal);
		Float actualTotal = finalProdSubTotal - finalTotalDiscApp + finalAddCharges + finalTax;
		// Discount amount calculation
		String discType = discountType.getText();
		String strDiscountValue = discountValue.getAttribute("title");
		Float discountNum = Float.valueOf(strDiscountValue);
		Float discountAmount;
		if (discType.equals("%")) {
			discountAmount = (finalProdSubTotal * discountNum) / 100;
			Assert.assertEquals(discountAmount, finalTotalDiscApp);
			if (actualTotal.equals(total)) {
				System.out.println("Total calculation works fine");
			} else {
				System.out.println("Incorrect Total value");
			}
		} else if (discType.equals("$")) {
			discountAmount = discountNum;
			Assert.assertEquals(discountAmount, finalTotalDiscApp);
			if (actualTotal.equals(total)) {
				System.out.println("Total calculation works fine");
			} else {
				System.out.println("Incorrect Total value");
			}
		}

	}

	/* created by kannan on 23/09/2020 */
	public void groupDiscount() throws InterruptedException {

		// Discount by Product Category screen
		wait.scrollUptoBottom();
		discountByProductIcon.click();
		wait.elementToBeClickable(discountByProdCatgy);
		Double discountPercent = 10.00;
		int sizeOfProductCategory = discountByProdCatgyTable.size();
		if (sizeOfProductCategory >= 1) {
			String strProdCategory = productCategory.getText();
			discountPercentage.click();
			Thread.sleep(500);
			discountTxtBox.sendKeys("10");
			Thread.sleep(500);
			String strNoOfLines = noOfLines.getText().trim();
			int noOfLinesPresent = Integer.valueOf(strNoOfLines);
			discProdCatSaveBtn.click();
			wait.elementToBeClickable(waitforSpinner);
			// End of Product Category screen
			// Quote Lines
			for (WebElement quoteItem : quoteLines) {
				int rowCount = 1;
				String itemColText = driver.findElement(By.xpath("//tr[" + rowCount + "]/td[18]/span")).getText();
				if (itemColText.equalsIgnoreCase(strProdCategory)) {
					WebElement rowClick = driver
							.findElement(By.xpath("//tr[" + rowCount + "]/td[25]/ul/li/div/button"));
					rowClick.click();
					Thread.sleep(1000);
					WebElement editIcon = driver
							.findElement(By.xpath("//tr[" + rowCount + "]/td[25]/ul/li/div/ul/li[2]"));
					editIcon.click();
					Thread.sleep(2000);
					String strSuggestedResale = suggestedResale.getText();
					String[] arrStrSuggestedResale = strSuggestedResale.split("\\$");
					String suggestedResale = arrStrSuggestedResale[1].trim();
					Float fltSuggestedResale = Float.valueOf(suggestedResale);
					String strUnitPrice = unitPrice.getAttribute("title");
					Float fltUnitPrice = Float.valueOf(strUnitPrice);
					Float discPercent = discountPercent.floatValue();
					Float discountAmount = (fltSuggestedResale * discPercent) / 100;
					Float expectedUnitPrice = fltSuggestedResale - discountAmount;
					Assert.assertEquals(expectedUnitPrice, fltUnitPrice);
					System.out.println("Expected unit price and Actual Price are same");
				}
			}
			// End of Quote lines discount calculation
		}
	}
}
