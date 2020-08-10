package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.LoginPage;

public class CommonMethods {
	// Hook dr=new Hook();
	private WebDriver driver;
	public static Properties prop;

	public CommonMethods() {
		this.driver = LoginPage.getDriver();
	}

	public void pageWait(WebElement pageLoad) throws InterruptedException {
		// Implicit page load wait based on visibility of web element
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(pageLoad));
		Thread.sleep(3000);
	}

	public void elementToBeClickable(WebElement pageLoad) throws InterruptedException {
		// Implicit page load wait based on visibility of web element
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(pageLoad));
		Thread.sleep(3000);
	}

	public void mouseOver(int height, int width) {
		Actions action = new Actions(driver);
		action.moveByOffset(height, width).click().build().perform();
	}

	public String fileReader(String username, String password) throws FileNotFoundException, IOException {
		prop = new Properties();
		/*
		 * File fil= new File("."); System.out.println(fil.getCanonicalPath());
		 */
		FileReader fis = new FileReader(
				new File(".").getCanonicalPath() + "\\src\\test\\java\\config\\config.properties");
		// System.out.println(fis);
		prop.load(fis);
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		return username;
	}

	public boolean web(WebElement test, boolean ele) {
		try {
			test.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void excelFile(String filePath, String fileName, Sheet sh, int rowCount, int columnCount)
			throws IOException {
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
		sh = wb.getSheetAt(0);
		// Find number of rows in excel file
		rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		Row row = sh.getRow(1);
		columnCount = row.getLastCellNum();
	}

	public void excelRead(String filePath, String fileName, String cellData) throws IOException {

		// Create an object of File class to open xlsx file
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
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		// Create a loop all around the rows in excel sheet
		for (int i = 1; i <= rowCount; i++) {
			Row row = sh.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 1; j <= row.getLastCellNum(); j++) {
				// Getting a excel data in string variable
				cellData = row.getCell(j).getStringCellValue();

				break;
			}

		}
		// return cellData;
	}

	public LinkedHashMap<String, String> getExcelData(String filePath, String fileName)
			throws IOException, InvalidFormatException {

		File DriverFile = null;
		Workbook dataWorkbook = null;

		try {
			DriverFile = new File(filePath + fileName);
			dataWorkbook = new XSSFWorkbook(DriverFile);
		} catch (Exception e) {
			// TODO: handle exception
			DriverFile = new File(filePath + fileName);
			InputStream ExcelFileToRead = new FileInputStream(DriverFile);
			;
			dataWorkbook = new HSSFWorkbook(ExcelFileToRead);
		}

		Sheet dataExcelSheet = dataWorkbook.getSheetAt(0);
		// Row dataRow;
		LinkedHashMap<String, String> testCaseHedderData = new LinkedHashMap<String, String>();

		for (int i = 1; i <= dataExcelSheet.getLastRowNum(); i++) {
			// dataRow = dataExcelSheet.getRow(i);
			// String strDataTestcaseIDCol = "";
			/*
			 * try { strDataTestcaseIDCol = dataRow.getCell(0).getStringCellValue(); } catch
			 * (Exception e) { // TODO: handle exception strDataTestcaseIDCol = "";
			 * System.out.println("Excel Sheet contains empty row in row no " + i); }
			 */
			// if (strDataTestcaseIDCol.trim().equals(strTestCaseID)) {
			int cellCount = dataExcelSheet.getRow(0).getLastCellNum();
			for (int j = 0; j < cellCount; j++) {
				String dataHeadder = dataExcelSheet.getRow(0).getCell(j).getStringCellValue().trim();
				String dataValue = "";
				// String cellValue="";
				try {
					// dataValue = dataExcelSheet.getRow(i).getCell(j).getStringCellValue();
					Cell type = dataExcelSheet.getRow(i).getCell(j);
					if (type.getCellType() == CellType.STRING) {
						dataValue = dataExcelSheet.getRow(i).getCell(j).getStringCellValue();
						// i++;
					}
					if (type.getCellType() == CellType.NUMERIC || type.getCellType() == CellType.FORMULA) {
						dataValue = String.valueOf(dataExcelSheet.getRow(i).getCell(j).getNumericCellValue());
						if (DateUtil.isCellDateFormatted(type)) {
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date date = type.getDateCellValue();
							dataValue = df.format(date);
						}
					}

				} catch (Exception e) {
					dataValue = "";
				}
				testCaseHedderData.put(dataHeadder, dataValue);
			}
			i = i + 1;
			break;
		}

		dataWorkbook.close();
		// System.out.println("data sheet value"+testCaseHedderData);
		return testCaseHedderData;
	}

	/* Reuseable Scroll Action */
	public void scrollDown(WebElement scrollElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", scrollElement);
	}

	public void scrollElseIf(WebElement scrollElement) throws InterruptedException {
		if (scrollElement.isDisplayed() == true) {
			scrollElement.click();
			Thread.sleep(800);
		} else {
			scrollDown(scrollElement);
			Thread.sleep(800);
			scrollElement.click();
			Thread.sleep(800);
		}
	}

	public void scrollByElement(WebElement scrollElement) throws InterruptedException {
		if (scrollElement.isDisplayed() == true) {
			// scrollElement.click();
			Thread.sleep(500);
		} else {
			scrollDown(scrollElement);
			Thread.sleep(500);
			// scrollElement.click();
			// Thread.sleep(500);
		}
	}

	public void scrollTillView(WebElement scrollView) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollView);
		Thread.sleep(500);
	}

	public void scrollBy() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(500);
	}

	public void scrollBy120() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		Thread.sleep(500);
	}

	public void scrollUp() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(450,0)");
		Thread.sleep(500);
	}

	public void scrollTopOfPage() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
	}

	public void scrollUptoBottom() throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 10) {
				break;
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)", ""); // y value '400' can be altered
			Thread.sleep(800);
		}
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * 
		 * jse.executeScript(
		 * "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));"
		 * );
		 */
	}

	public void scrollView(WebElement scrollView) throws InterruptedException {
		// WebElement element = driver.findElement(By.xpath("Value"));

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(500);
		// Adjust your page view by making changes right over here
		// (hoverItem.getY()-50)
	}

	public void refreshPage() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	public void ExecutorClick(WebElement click) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", click);
	}

	public void doubleClick(WebElement ele) {
		Actions action = new Actions(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		// used doubleClick(element) method to do double click action
		action.doubleClick(ele).build().perform();
	}

	public String dateFormat(String date) {
		String pattern = " yyyyMMdd hh:mm aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		date = simpleDateFormat.format(new Date());
		return date;
	}

	public boolean isClickable(WebElement webe) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(webe));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollToElement(int x, int y) {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");

	}

	public void dropDownSelectByIndex(WebElement drpDown) {
		Select drpDownByIndex = new Select(drpDown);
		drpDownByIndex.selectByIndex(2);
	}

	public void waitForAlert(WebDriver driver) throws InterruptedException {
		int i = 0;
		while (i++ < 15) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				break;
			} catch (NoAlertPresentException e) {
				Thread.sleep(1000);
				continue;
			}
		}
	}

	public boolean isSorted(List<String> listOfString) {
		if (listOfString.isEmpty() || listOfString.size() == 1) {
			return true;
		}
		Iterator<String> iter = listOfString.iterator();
		String current, previous = iter.next();
		while (iter.hasNext()) {
			current = iter.next();
			previous = previous.toLowerCase();
			current = current.toLowerCase();
			if (previous.compareTo(current) > 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

	public void downloadReport(WebElement downloadIcon) throws InterruptedException {
		File folder = LoginPage.folder;
		File listOfFiles[] = folder.listFiles();
		int SizeOfFile1 = 0;
		for (File file : listOfFiles) {
			// make sure that downloaded file is not empty
			SizeOfFile1 = SizeOfFile1 + 1;
		}
		downloadIcon.click();
		// long filesize1;
		int SizeOfFile2 = 0;
		do {
			Thread.sleep(5000); // wait for 5 seconds
			File listOfFiles2[] = folder.listFiles();
			for (File file : listOfFiles2) {
				// make sure that downloaded file is not empty
				SizeOfFile2 = SizeOfFile2 + 1;
			}
		} while (SizeOfFile1 > SizeOfFile2);
		Thread.sleep(1000);
		File listOfFiles3[] = folder.listFiles();
		for (File file : listOfFiles3) {
			// make sure that downloaded file is not empty
			Assert.assertTrue(file.length() > 0);
		}
		for (File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
	}

	public String reportSortByFirstName(WebElement firstNameHeader, WebElement totalItems, List<WebElement> tableRows,
			WebElement moveToNextPage, int columnNumber) {
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
					WebElement individualFirstName = driver.findElement(
							By.xpath("//table[@role='grid']/tbody/tr[" + rowIncre + "]/td[" + columnNumber + "]/span"));
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
		isSorted(arrStringFN);
		if (isSorted(arrStringFN) == true) {
			System.out.println("First Name is in sort order");
		} else {
			System.out.println("First Name not in sort order");
		}
		return arrStringFN.toString();
	}

	public void reportFilterByFirstName(WebElement firstNameFilterIcon, WebElement containsDrpDwn,
			List<WebElement> containsOption, WebElement containsTxtBox, String filePath, String fileName,
			WebElement filterBtn, WebElement totalItems, List<WebElement> tableRows, WebElement moveToNextPage,
			String filterByText) throws InvalidFormatException, IOException, InterruptedException {
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
			String actualFilterTableRows = "Filter works fine";
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

	public void reportFilterByFirstNameWithInteger(WebElement firstNameFilterIcon, WebElement containsDrpDwn,
			List<WebElement> containsOption, WebElement containsTxtBox, String filePath, String fileName,
			WebElement filterBtn, WebElement totalItems, List<WebElement> tableRows, WebElement moveToNextPage,
			int filterByText) throws InvalidFormatException, IOException, InterruptedException {
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
				containsTxtBox.sendKeys(String.valueOf(filterByText));
				Thread.sleep(1000);
				filterBtn.click();
				Thread.sleep(1000);
				break;
			}
		}
		int rowIncre = 1;
		ArrayList<Integer> arrStringFN = new ArrayList<Integer>();
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
			String actualFilterTableRows = "Filter works fine";
			for (table = tableCount; tableCount <= sizeOfTableCnt;) {
				for (WebElement rowIdentity : tableRows) {
					WebElement individualFirstName = driver
							.findElement(By.xpath("//table[@role='grid']/tbody/tr[" + rowIncre + "]/td/span"));
					int firstNameTxt = Integer.valueOf(individualFirstName.getText());
					// System.out.println(firstNameTxt);
					if ((firstNameTxt ^ filterByText) != 0) {
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
