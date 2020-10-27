package pageObjects;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.CommonMethods;

public class MeasurementPage extends CommonMethods {

	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "Measurement.xlsx";
	private int gridSize;
	private int measurementRow = 1;
	private String user = LoginPage.un;
	private WebDriver driver;
	utility.CommonMethods drpDownData = new utility.CommonMethods();
	utility.CommonMethods elementWait = new utility.CommonMethods();
	utility.CommonMethods commonMethods = new utility.CommonMethods();
	/* Coded by UmaVasan */
	@FindBy(xpath = "(//i[@class='far fa-caret-square-down fa_icon'])[1]")
	WebElement copyLineEnableIcon;

	@FindBy(xpath = "//input[@id='linenumber']")
	WebElement requiredRow;

	@FindBy(xpath = "//input[@id='linecopy']")
	WebElement requiredTime;

	@FindBy(xpath = "//li[@data-tooltip='Copy']/button[contains(@ng-click,'Copymultipletime')]/i[@class='far fa-check-circle fa_icon']")
	WebElement copyClick;

	@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td/ul/li/div/button[contains(@class,'btn btn-default dropdown-toggle')]")
	WebElement clickToDelete;

	@FindBy(xpath = "//input[@class='k-button btn cancel_but ng-scope' and @ng-if='Permission.AddMeasurements']")
	WebElement addRowButtonTluser;

	WebElement deleteRowWait;

	@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td/ul/li/div/ul/li/a[contains(text(),'Delete')]")
	WebElement clickOnDelete;

	@FindBy(xpath = "//div[@class='col-4 col-sm-4']/span[@class='ng-binding']")
	// @FindBy(xpath = "//div[@class='col-3 col-sm-3 col-md-3
	// total-count-wrapper']/span[@class='ng-binding']")
	WebElement gridRowCount;

	@FindAll(@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']/table[@role='grid']/tbody/tr[@role='row' and not(contains(@style,'display: none'))]"))
	List<WebElement> measurementPageRowGrid;

	/* created by UmaVasan on 07/08/20 */

	@FindBy(xpath = "//li[@rel='tab7' and @ng-show='BrandId!=3']")
	WebElement clickMeasure;

	@FindBy(xpath = "//button[@ng-if='Permission.AddMeasurements' and @ng-click='AddRow()']")
	WebElement addNewRow;

	@FindBy(xpath = "//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr/td[2]/span")
	WebElement roomDrpDown;

	@FindAll(@FindBy(xpath = "//ul[@id='roomLocation0_listbox']/li"))
	List<WebElement> roomDrpDowmLi;

	@FindBy(xpath = "//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr/td[3]/span")
	WebElement WinDrpDown;

	@FindAll(@FindBy(xpath = "//ul[@id='windowLocation0_listbox']/li"))
	List<WebElement> windowLi;

	@FindAll(@FindBy(xpath = "//ul[@id='windowLocation0_listbox' and @aria-hidden='false']/li"))
	List<WebElement> windowDrpDown;

	@FindBy(xpath = "//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr/td[5]/span")
	WebElement mountTypeDrpDown;

	@FindAll(@FindBy(xpath = "//div[@id='mount0-list']/div[@class='k-list-scroller']/ul/li"))
	List<WebElement> mountType;

	@FindBy(xpath = "//input[@id='width0']")
	WebElement width;

	@FindBy(xpath = "//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[1]/td[6]/div/span")
	WebElement widthDrpDown;

	@FindBy(xpath = "(//ul[@id='widthFraction0_listbox']/li[5])[3]")
	WebElement widthRange;

	@FindBy(xpath = "//input[@id='height0']")
	WebElement height;

	@FindBy(xpath = "//tr[1]/td[7]/div/span")
	WebElement heightRangeDrpDown;

	@FindBy(xpath = "(//ul[@id='heightFraction0_listbox']/li[7])[3]")
	WebElement heightRange;

	@FindBy(xpath = "//button[@class='btn btn-default dropdown-toggle']")
	WebElement beforeCloneClick;

	@FindBy(xpath = "//ul[@class='dropdown-menu pull-right']/li[3]")
	WebElement cloneClick;

	@FindBy(xpath = "//button[@ng-click='saveMeasurements()']")
	WebElement saveMeasure;

	@FindBy(xpath = "//button[@class='tooltip-bottom save_tpbut' and @data-tooltip='Edit']")
	WebElement editIconClick;

	@FindBy(xpath = "//button[@class='plus_but tooltip-bottom ng-scope' and @data-tooltip='Add new measurement line']")
	WebElement addNewMeasurementLine;

	@FindBy(xpath = "//input[@value='Add New Row' and @ng-if='Permission.AddMeasurements']")
	WebElement tlAddRowIcon;

	@FindBy(xpath = "//span[@aria-owns='System_listbox']")
	WebElement systemDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@id='System_listbox']/li"))
	List<WebElement> systemDrpDwnLi;

	@FindBy(xpath = "//input[@name='SystemDescription']")
	WebElement systemDesTxtBox;

	@FindBy(xpath = "//span[@aria-owns='storageType_listbox']")
	WebElement typeDrpDwn;

	@FindAll(@FindBy(xpath = "//ul[@id='storageType_listbox']/li"))
	List<WebElement> typeDrpDwnLi;

	@FindBy(xpath = "//input[@name='StorageDescription']")
	WebElement storageDescTxtBox;

	@FindBy(xpath = "//input[@name='Width']")
	WebElement tlWidthTxtBox;

	@FindBy(xpath = "//span[@aria-owns='FranctionalValueWidth_listbox']")
	WebElement widthDrpDwn;

	@FindBy(xpath = "//ul[@id='FranctionalValueWidth_listbox']/li[text()='3/16']")
	WebElement tlWidthFraction;

	@FindBy(xpath = "//input[@id='Height']")
	WebElement tlHeight;

	@FindBy(xpath = "//span[@aria-owns='FranctionalValueHeight_listbox']")
	WebElement tlHeightDrpDwn;

	@FindBy(xpath = "//ul[@id='FranctionalValueHeight_listbox']/li[text()='3/16']")
	WebElement tlHeightFraction;

	@FindBy(xpath = "//input[@name='Depth']")
	WebElement tlDepth;

	@FindBy(xpath = "//span[@aria-owns='FranctionalValueDepth_listbox']")
	WebElement tlDepthDrpDwn;

	@FindBy(xpath = "//ul[@id='FranctionalValueDepth_listbox']/li[text()='3/16']")
	WebElement tlDepthFraction;

	@FindBy(xpath = "//ul[@class='dropdown-menu pull-right show']/li[2]")
	WebElement tlEditIcon;

	public MeasurementPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	/* Coded by UmaVasan */

	public void removeAllFilledRows() throws InterruptedException {
		if (user.equalsIgnoreCase("bbtestus")) {
			elementWait.pageWait(editIconClick);
			Thread.sleep(500);
			editIconClick.click();

			gridSize = measurementPageRowGrid.size();
			System.out.println("Total filled rows in the measurement Page:" + gridSize);
			for (int g = 1; g <= gridSize; g++) {
				elementWait.pageWait(clickToDelete);
				Thread.sleep(1000);
				clickToDelete.click();
				elementWait.pageWait(clickOnDelete);
				Thread.sleep(1000);
				clickOnDelete.click();
				Thread.sleep(500);
			}
			System.out.println("All filled rows deleted successfully");
		}
		if (user.equalsIgnoreCase("tltestus")) {

//			elementWait.pageWait(editIconClick);	
//			Thread.sleep(500);
//			editIconClick.click();

			gridSize = measurementPageRowGrid.size();
			System.out.println("Total filled rows in the measurement Page:" + gridSize);
			for (int g = 1; g <= gridSize; g++) {
				elementWait.pageWait(clickToDelete);
				Thread.sleep(1000);
				clickToDelete.click();
				elementWait.pageWait(tlAddRowIcon);
				Thread.sleep(1000);
				clickOnDelete.click();
				elementWait.pageWait(tlAddRowIcon);
				Thread.sleep(500);

			}
			System.out.println("All filled rows deleted successfully");
			// elementWait.pageWait(gridRowCount);

		}
	}

	public void fillDataAndverifyImageAlertMessage()
			throws InterruptedException, NumberFormatException, IOException, AWTException {

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
		Sheet sh = wb.getSheet("imagesheet");
		// Find number of rows in excel file
		int rowCount = sh.getLastRowNum();

		int rowCount1 = rowCount - 1;
		// System.out.println(rowCount1);
		// Get excel sheet to fetch width,height values
		Sheet sh1 = wb.getSheet("Sheet6");

		// Create a loop all around the rows in excel sheet
		int rowLocation = 0;

		if (user.equalsIgnoreCase("bbtestus")) {
			editIconClick.click();
			drpDownData.pageWait(addNewMeasurementLine);
			// Create an object of File class to open xlsx file
			addNewMeasurementLine.click();
			Thread.sleep(2000);
			WebElement roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation0']"));
			drpDownData.elementToBeClickable(roomDrpDown);
			Thread.sleep(2000);
			for (int i = 1; i <= rowCount1; i++) {
				Row row = sh.getRow(i);
				Row row1 = sh1.getRow(i);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String roomRow = row.getCell(7).getStringCellValue().trim();
				String windowRow = row.getCell(8).getStringCellValue().trim();
				String mountRow = row.getCell(9).getStringCellValue().trim();
				// int widthRow = (int) row.getCell(10).getNumericCellValue();
				// int heightRow = (int) row.getCell(11).getNumericCellValue();
				double widthRow = row1.getCell(1).getNumericCellValue();
				double heightRow = row1.getCell(1).getNumericCellValue();
				String widthFraction = row1.getCell(2).getStringCellValue();
				String heightFraction = row1.getCell(2).getStringCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();

				/* Room drop down selection */
				roomDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[2]/div/span"));
				roomDrpDown.click();
				Thread.sleep(600);
				int roomSize = roomDrpDowmLi.size();
				for (int r = 1; r < roomSize; r++) {
					WebElement roomDrp = driver.findElement(By.xpath("//ul[@id='roomLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + r + "]"));
					String roomTxt = roomDrp.getText().trim();
					if (roomTxt.equalsIgnoreCase(roomRow)) {
						drpDownData.scrollDown(roomDrp);
						Thread.sleep(1000);
						roomDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				// drpDownData.scrollDown(roomDrp);
				Thread.sleep(600);
				/* Window Location drop down selection */
				WinDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[3]/div/span"));
				WinDrpDown.click();
				Thread.sleep(600);
				int windowSize = windowLi.size();
				// System.out.println(windowSize);
				for (int j = 1; j <= windowSize; j++) {
					String windowText = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + j + "]")).getText().trim();
					if (windowRow.equalsIgnoreCase(windowText)) {

						WebElement winDrp = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
								+ "_listbox' and @aria-hidden='false']/li[" + j + "]"));
						drpDownData.scrollDown(winDrp);
						Thread.sleep(1000);
						winDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Mount Type drop down selection */
				mountTypeDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[5]/div/span"));
				mountTypeDrpDown.click();
				// int mountSize = mountType.size();
				// int mountSize = 10;
				List<WebElement> mountSize1 = driver
						.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
				int sizeOfMount = mountSize1.size();
				// System.out.println(sizeOfMount);
				for (int k = 1; k <= sizeOfMount; k++) {
					String mountText = driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.getText().trim();
					// System.out.println(mountText);
					if (mountRow.equalsIgnoreCase(mountText)) {
						driver.findElement(By.xpath(
								"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
								.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Width text box value */
				width = driver.findElement(By.xpath("//input[@id='width" + rowLocation + "']"));
				width.click();
				width.clear();
				Thread.sleep(500);
				width.sendKeys(String.valueOf(widthRow));
				Thread.sleep(500);
				width.sendKeys(Keys.TAB);

				// check width fraction with width value entered
				widthDrpDown = driver.findElement(By.xpath("//input[@id='widthFraction" + rowLocation + "']"));
				// widthDrpDown =
				// driver.findElement(By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
				// + i + "]/td[6]/div/div/span"));
				String actualWidthFraction = widthDrpDown.getAttribute("value");
				// String actualWidthFraction= widthDrpDown.getText();
				Thread.sleep(500);
				if ((String.valueOf(actualWidthFraction)).equalsIgnoreCase((String.valueOf(widthFraction)))) {
					System.out.println("Expected width fraction " + widthFraction
							+ " is equal to Actual width fraction " + actualWidthFraction);
				} else {
					System.out.println("Expected width fraction " + widthFraction
							+ " is not equal to Actual width fraction " + actualWidthFraction);
				}

				// widthDrpDown.click();
//				Thread.sleep(1000);
//				widthRange = driver.findElement(By.xpath("//ul[@id='widthFraction" + rowLocation + "_listbox']/li[5]"));
//				widthRange.click();
				Thread.sleep(600);
				/* Height text box value */
				height = driver.findElement(By.xpath("//input[@id='height" + rowLocation + "']"));
				height.click();
				height.clear();
				Thread.sleep(500);
				height.sendKeys(String.valueOf(heightRow));
				Thread.sleep(500);
				height.sendKeys(Keys.TAB);

				/* Height Range drop down selection */
				heightRangeDrpDown = driver.findElement(By.xpath("//input[@id='heightFraction" + rowLocation + "']"));

				// heightRangeDrpDown = driver.findElement(By.xpath(
				// "//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i +
				// "]/td[7]/div/div/span"));
				String actualHeightFraction = heightRangeDrpDown.getAttribute("value");
				// String actualHeightFraction= heightRangeDrpDown.getText();
				// double HeightFraction = new Double(actualHeightFraction).doubleValue();
				if ((String.valueOf(actualHeightFraction)).equalsIgnoreCase(String.valueOf(heightFraction))) {
					System.out.println("Expected height fraction " + heightFraction
							+ " is equal to Actual width fraction " + actualHeightFraction);
				} else {
					System.out.println("Expected height fraction " + heightFraction
							+ " is not equal to Actual width fraction " + actualHeightFraction);
				}

//				heightRangeDrpDown.click();
//				Thread.sleep(1000);
//				heightRange = driver
//						.findElement(By.xpath("//ul[@id='heightFraction" + rowLocation + "_listbox']/li[3]"));
//				heightRange.click();
//				Thread.sleep(1500);

				/* Upload image */
				try {
					if (i > 1) {
						driver.findElement(
								By.xpath("//tr[" + i + "]/td[8]/div/i[@class='fas fa-times show_cameraicon']")).click();
						Thread.sleep(500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(
						By.xpath("//tr[" + i + "]/td[8]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);

				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + i + "]/td[8]/ul/li/div/ul/li/div/input"));
				// Actions actUploadPhoto = new Actions(driver);
				// actUploadPhoto.moveToElement(uploadPhoto).click().perform();

				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");

				String expectedAlertMsg = "Upload cancelled, The file is too large. Exceeds file size limit";

				if (isAlertPresent()) {

					Alert alert = driver.switchTo().alert();
					String actualAlertMsg = alert.getText();
					Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
					// Assert.assertTrue(true, expectedAlertMsg);
					System.out.println("Warning message " + "[" + actualAlertMsg + "]" + " is verified successfully.");
					alert.accept();
				}

				/* Wait for image wait */
				Thread.sleep(1000);
				if (i < rowCount1) {
					// copy icon click
					driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[10]/ul/li/div/button"))
							.click();
					Thread.sleep(300);
					// copy click
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i
							+ "]/td[10]/ul/li/div/ul/li[1]")).click();
					// Thread.sleep(300);
					WebElement imageUploadCloseIcon = driver.findElement(By.id("photoCameraIcon" + rowLocation + ""));
					if (imageUploadCloseIcon.isDisplayed()) {
						drpDownData.elementToBeClickable(imageUploadCloseIcon);
						Thread.sleep(1000);
					}
					rowLocation++;
				}
				elementWait.pageWait(saveMeasure);
			}
		}

		if (user.equalsIgnoreCase("tltestus")) {

			tlAddRowIcon.click();
			drpDownData.pageWait(systemDrpDwn);
			for (int measurementRow = 1; measurementRow <= rowCount1; measurementRow++) {
				Row row = sh.getRow(measurementRow);
				Row row1 = sh1.getRow(measurementRow);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String system = row.getCell(0).getStringCellValue().trim();
				String systemDescription = row.getCell(1).getStringCellValue().trim();
				String type = row.getCell(2).getStringCellValue().trim();
				String description = row.getCell(3).getStringCellValue().trim();
				// int width = (int) row.getCell(4).getNumericCellValue();
				// int height = (int) row.getCell(5).getNumericCellValue();
				double width = row1.getCell(1).getNumericCellValue();
				double height = row1.getCell(1).getNumericCellValue();
				String widthFraction = row1.getCell(2).getStringCellValue();
				String heightFraction = row1.getCell(2).getStringCellValue();
				int depth = (int) row.getCell(6).getNumericCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();
				Thread.sleep(2000);
				drpDownData.pageWait(systemDrpDwn);
				systemDrpDwn.click();
				Thread.sleep(2000);
				for (WebElement liSystem : systemDrpDwnLi) {
					String systemTxt = liSystem.getText();
					// String system = "Garage";
					if (system.equalsIgnoreCase(systemTxt)) {
						liSystem.click();
						Thread.sleep(1000);
						break;
					}
				}
				systemDesTxtBox.click();
				drpDownData.pageWait(systemDesTxtBox);
				systemDesTxtBox.clear();
				Thread.sleep(2000);
				systemDesTxtBox.sendKeys(systemDescription);
				Thread.sleep(500);
				typeDrpDwn.click();
				Thread.sleep(1000);
				for (WebElement liType : typeDrpDwnLi) {
					String typeTxt = liType.getText();
					// String actualType = "Bikes";
					if (typeTxt.equalsIgnoreCase(type)) {
						liType.click();
						Thread.sleep(2000);
						break;
					}
				}
				Thread.sleep(2000);
				storageDescTxtBox.click();
				Thread.sleep(500);
				storageDescTxtBox.clear();
				Thread.sleep(1000);
				boolean visibilityStorageDescTxtBox = storageDescTxtBox.isEnabled();
				if (visibilityStorageDescTxtBox == true) {
					drpDownData.isClickable(storageDescTxtBox);
					storageDescTxtBox.sendKeys(description);
					Thread.sleep(1000);
				}
				boolean visibilityTlWidthTxtBox = tlWidthTxtBox.isEnabled();
				if (visibilityTlWidthTxtBox == true) {
					tlWidthTxtBox.click();
					drpDownData.elementToBeClickable(tlWidthTxtBox);
					tlWidthTxtBox.clear();
					Thread.sleep(2000);
					tlWidthTxtBox.sendKeys(String.valueOf(width));
					tlWidthTxtBox.sendKeys(Keys.TAB);
					Thread.sleep(500);

					String actualWidthFractiontl = widthDrpDwn.getAttribute("value");

					if ((String.valueOf(actualWidthFractiontl)).equalsIgnoreCase(String.valueOf(widthFraction))) {
						System.out.println("Expected width fraction " + widthFraction
								+ " is equal to Actual width fraction " + actualWidthFractiontl + ".");
					} else {
						System.out.println("Expected width fraction " + widthFraction
								+ " is not equal to Actual width fraction " + actualWidthFractiontl + ".");
					}

				}
				boolean visibilityTlHeight = tlHeight.isEnabled();
				if (visibilityTlHeight == true) {
					tlHeight.click();
					drpDownData.elementToBeClickable(tlHeight);
					tlHeight.clear();
					Thread.sleep(2000);
					tlHeight.sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					tlHeight.sendKeys(Keys.TAB);
					Thread.sleep(1000);
					String actualHeightFractiontl = tlHeightDrpDwn.getAttribute("value");
					// double actualHeightFractiontl = new Double(HeightFractiontl).doubleValue();
					if ((String.valueOf(actualHeightFractiontl)).equalsIgnoreCase(String.valueOf(heightFraction))) {
						System.out.println("Expected height fraction " + heightFraction
								+ " is equal to Actual height fraction " + actualHeightFractiontl + ".");
					} else {
						System.out.println("Expected height fraction " + heightFraction
								+ " is not equal to Actual height fraction " + actualHeightFractiontl + ".");
					}

				}
				boolean visibilityTlDepth = tlDepth.isEnabled();
				if (visibilityTlDepth == true) {
					tlDepth.click();
					drpDownData.elementToBeClickable(tlDepth);
					tlDepth.clear();
					Thread.sleep(2000);
					tlDepth.sendKeys(String.valueOf(depth));
					Thread.sleep(1000);
					drpDownData.pageWait(tlDepthDrpDwn);
					tlDepthDrpDwn.click();
					Thread.sleep(1000);
					tlDepthFraction.click();
					Thread.sleep(1000);
				}
				/* Upload image */
				try {
					if (measurementRow > 1) {
						driver.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/div/i[@class='fas fa-times']"))
								.click();
						Thread.sleep(1500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(By.xpath(
						"//tr[" + measurementRow + "]/td[11]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);
				// drpDownData.pageWait(cameraIcon);
				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/ul/li/div/ul/li/div/input"));

				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");
				Thread.sleep(3000);

				String expectedAlertMsg = "Upload cancelled, The file is too large. Exceeds file size limit";
				if (isAlertPresent()) {
					// Thread.sleep(1000);
					Alert alert = driver.switchTo().alert();
					String actualAlertMsg = alert.getText();
					Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
					// Assert.assertTrue(true, expectedAlertMsg);
					System.out.println("Warning message " + "[" + actualAlertMsg + "]" + " is verified successfully.");
					alert.accept();
				}

				// Thread.sleep(1000);
				if (measurementRow < rowCount1) {
					// copy element icon click

					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/button")).click();
					// copyButton.click();
					Thread.sleep(500);
					// copy element click
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/ul/li[2]")).click();
					Thread.sleep(1500);
				}
				int dynamicRow = measurementRow + 1;
				// WebElement dynamicRowWait = driver.findElement(
				// By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" +
				// dynamicRow + "]"));
				drpDownData.pageWait(tlWidthTxtBox);
				// boolean imageCloseIconVisbility = imageUploadCloseIcon.isEnabled();
				if (driver.findElements(By.xpath("//i[@class='fas fa-times']")).size() != 0) {
					WebElement imageUploadCloseIcon = driver.findElement(By.xpath("//i[@class='fas fa-times']"));
					drpDownData.pageWait(imageUploadCloseIcon);
					Thread.sleep(1000);
					imageUploadCloseIcon = driver.findElement(By.xpath("//i[@class='fas fa-times']"));
					imageUploadCloseIcon.click();
					Thread.sleep(500);
				}

			}
			rowLocation++;
		}
	}

	public void validateRowCount() throws InterruptedException, NumberFormatException {
		Thread.sleep(500);
		String gridrowcount = gridRowCount.getText();
		elementWait.pageWait(gridRowCount);
		String rowCountValue = gridrowcount.substring(13);
		int rowValue = new Integer(rowCountValue).intValue();

		if (user.equalsIgnoreCase("bbtestus")) {
			gridSize = measurementPageRowGrid.size();
			if (gridSize == rowValue) {
				System.out.println(gridSize + " filled row/rows validated successfully");
			}

			if (user.equalsIgnoreCase("tltestus")) {
				gridSize = measurementPageRowGrid.size();
				if (gridSize == rowValue) {
					System.out.println(gridSize + " filled row/rows validated successfully");
				}
			}
		}
	}

	public void addNewRow() throws InterruptedException, InvalidFormatException, IOException {
		int measurementRow = 1;
//	  String sheetName="Sheet1";
//	  Sheet sh=commonMethods.fetchExcelSheet(sheetName, sheetName, sheetName);
		// utility.CommonMethods.fetchExcelData(filePath, fileName, sheetName);
//	//Read excel file to fetch input data
		File file = new File(filePath + fileName);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = null;
		// Choose excel file type by its extension
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if (fileExtension.equals(".xlsx")) {
			wb = new XSSFWorkbook(fis);
		} else if (fileExtension.equals(".xls")) {
			wb = new HSSFWorkbook(fis);
		}
		// Fetch data from first sheet of workbook
		Sheet sh = wb.getSheetAt(0);
		// Get total no of rows in a sheet
		int rowCount = sh.getLastRowNum();

		// validate user by login user name
		if (user.equalsIgnoreCase("bbtestus")) {
			// editIconClick.click();
			drpDownData.pageWait(addNewMeasurementLine);
			// Add new row in a measurement Page
			addNewMeasurementLine.click();
			Thread.sleep(2000);
			WebElement roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation0']"));
			drpDownData.elementToBeClickable(roomDrpDown);
			Thread.sleep(2000);
			int rowLocation = 0;
			int rowNo = 1;
			// Get first row in a work sheet
			Row row = sh.getRow(1);

			// Get input data as a string
//			String roomRow = Room;
//			String windowRow = WindowLocation;
//			String mountRow = MountType;
//			String widthRow = Width1;
//			String heightRow = Height1;
//	// Get input data as a string 
			String roomRow = row.getCell(7).getStringCellValue().trim();
			String windowRow = row.getCell(8).getStringCellValue().trim();
			String mountRow = row.getCell(9).getStringCellValue().trim();
			int widthRow = (int) row.getCell(10).getNumericCellValue();
			int heightRow = (int) row.getCell(11).getNumericCellValue();
			// Room drop down selection
			roomDrpDown = driver.findElement(
					By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + rowNo + "]/td[2]/div/span"));
			roomDrpDown.click();
			Thread.sleep(600);
			int roomSize = roomDrpDowmLi.size();
			for (int r = 1; r < roomSize; r++) {
				WebElement roomDrp = driver.findElement(By.xpath(
						"//ul[@id='roomLocation" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + r + "]"));
				String roomTxt = roomDrp.getText().trim();
				if (roomTxt.equalsIgnoreCase(roomRow)) {
					drpDownData.scrollDown(roomDrp);
					Thread.sleep(1000);
					roomDrp.click();
					Thread.sleep(600);
					break;
				}
			}
			// drpDownData.scrollDown(roomDrp);
			Thread.sleep(600);
			// Window Location drop down selection
			WinDrpDown = driver.findElement(
					By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + rowNo + "]/td[3]/div/span"));
			WinDrpDown.click();
			Thread.sleep(600);
			int windowSize = windowLi.size();
			for (int j = 1; j <= windowSize; j++) {
				String windowText = driver.findElement(By.xpath(
						"//ul[@id='windowLocation" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + j + "]"))
						.getText().trim();
				if (windowRow.equalsIgnoreCase(windowText)) {

					WebElement winDrp = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + j + "]"));
					drpDownData.scrollDown(winDrp);
					Thread.sleep(1000);
					winDrp.click();
					Thread.sleep(600);
					break;
				}
			}
			// Mount Type drop down selection
			mountTypeDrpDown = driver.findElement(
					By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + rowNo + "]/td[5]/div/span"));
			mountTypeDrpDown.click();
			List<WebElement> mountSize1 = driver
					.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));

			int sizeOfMount = mountSize1.size();
			for (int k = 1; k <= sizeOfMount; k++) {
				String mountText = driver
						.findElement(By.xpath(
								"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
						.getText().trim();
				if (mountRow.equalsIgnoreCase(mountText)) {
					driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.click();
					Thread.sleep(600);
					break;
				}
			}
			// * Width text box value
			width = driver.findElement(By.xpath("//input[@id='width" + rowLocation + "']"));
			width.click();
			width.clear();
			Thread.sleep(500);
			width.sendKeys(String.valueOf(widthRow));
			Thread.sleep(500);
			/* Width Range drop down selection */
			widthDrpDown = driver.findElement(
					By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr/td[6]/div/div/span"));
			Thread.sleep(1000);
			widthDrpDown.click();
			Thread.sleep(2000);
			widthRange = driver.findElement(By.xpath(
					"(//ul[@id='widthFraction" + rowLocation + "_listbox' and @data-role='staticlist']/li[5])[2]"));
			widthRange.click();
			Thread.sleep(1000);
			/* Height text box value */
			height = driver.findElement(By.xpath("//input[@id='height" + rowLocation + "']"));
			height.click();
			height.clear();
			Thread.sleep(500);
			height.sendKeys(String.valueOf(heightRow));
			Thread.sleep(500);
			/* Height Range drop down selection */
			heightRangeDrpDown = driver.findElement(By.xpath(
					"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + rowNo + "]/td[7]/div/div/span"));
			heightRangeDrpDown.click();
			Thread.sleep(1000);
			heightRange = driver
					.findElement(By.xpath("(//ul[@id='heightFraction" + rowLocation + "_listbox']/li[3])[2]"));
			heightRange.click();
			Thread.sleep(1500);
		}

		if (user.equalsIgnoreCase("tltestus")) {
			tlAddRowIcon.click();
			drpDownData.pageWait(systemDrpDwn);
			Row row = sh.getRow(measurementRow);
			// Getting a excel data in string variable
			String system = row.getCell(0).getStringCellValue().trim();
			String systemDescription = row.getCell(1).getStringCellValue().trim();
			String type = row.getCell(2).getStringCellValue().trim();
			String description = row.getCell(3).getStringCellValue().trim();
			int width = (int) row.getCell(4).getNumericCellValue();
			int height = (int) row.getCell(5).getNumericCellValue();
			int depth = (int) row.getCell(6).getNumericCellValue();
			Thread.sleep(1000);
			systemDrpDwn.click();
			Thread.sleep(1500);
			for (WebElement liSystem : systemDrpDwnLi) {
				String systemTxt = liSystem.getText();
				// String system = "Garage";
				if (system.equalsIgnoreCase(systemTxt)) {
					liSystem.click();
					Thread.sleep(1000);
					break;
				}
			}
			systemDesTxtBox.clear();
			Thread.sleep(500);
			systemDesTxtBox.sendKeys(systemDescription);
			Thread.sleep(500);
			typeDrpDwn.click();
			Thread.sleep(1000);
			for (WebElement liType : typeDrpDwnLi) {
				String typeTxt = liType.getText();
				// String actualType = "Bikes";
				if (typeTxt.equalsIgnoreCase(type)) {
					liType.click();
					Thread.sleep(2000);
					break;
				}
			}
			storageDescTxtBox.clear();
			Thread.sleep(1000);
			boolean visibilityStorageDescTxtBox = storageDescTxtBox.isEnabled();
			if (visibilityStorageDescTxtBox == true) {
				storageDescTxtBox.sendKeys(description);
				Thread.sleep(1000);
			}
			boolean visibilityTlWidthTxtBox = tlWidthTxtBox.isEnabled();
			if (visibilityTlWidthTxtBox == true) {
				tlWidthTxtBox.clear();
				Thread.sleep(500);
				tlWidthTxtBox.sendKeys(String.valueOf(width));
				Thread.sleep(500);
				widthDrpDwn.click();
				Thread.sleep(1010);
				tlWidthFraction.click();
				Thread.sleep(1000);
			}
			boolean visibilityTlHeight = tlHeight.isEnabled();
			if (visibilityTlHeight == true) {
				tlHeight.clear();
				Thread.sleep(1000);
				tlHeight.sendKeys(String.valueOf(height));
				Thread.sleep(1000);
				tlHeightDrpDwn.click();
				Thread.sleep(1000);
				tlHeightFraction.click();
				Thread.sleep(1000);
			}
			boolean visibilityTlDepth = tlDepth.isEnabled();
			if (visibilityTlDepth == true) {
				tlDepth.clear();
				Thread.sleep(1000);
				tlDepth.sendKeys(String.valueOf(depth));
				Thread.sleep(1000);
				tlDepthDrpDwn.click();
				Thread.sleep(1000);
				tlDepthFraction.click();
				Thread.sleep(500);
			}

		}

	}

	/* Created by UmaVasan on 07/08/20 */

	public void openMeasure() throws InterruptedException {
		// driver.navigate().refresh();
		// drpDownData = new utilities.CommonMethods();
		// drpDownData.pageWait(clickMeasure);
		String user = LoginPage.un;
		if (user.equalsIgnoreCase("bbtestus")) {
			clickMeasure.click();
			// drpDownData = new utility.CommonMethods();
			drpDownData.pageWait(editIconClick);
			Thread.sleep(1500);
		}
		if (user.equalsIgnoreCase("tltestus")) {
			clickMeasure.click();
			drpDownData.pageWait(tlAddRowIcon);
		}
	}

	public void fillData() throws InterruptedException, InvalidFormatException, IOException {

		int measurementRow = 1;
		String sheetName = "Sheet1";
		CommonMethods.fetchExcelSheet(filePath, fileName, sheetName);

//		 Sheet sh=CommonMethods.sh;
//		 //Sheet sh=commonMethods.fetchExcelSheet();
//		 //utility.CommonMethods.fetchExcelData(filePath, fileName, sheetName);
//		 Thread.sleep(1000);
//		File file = new File(filePath + fileName);
//		// Create an object of FileInputStream class to read excel file
//		FileInputStream fis = new FileInputStream(file);
//		Workbook wb = null;
//		// Find the file extension by splitting file name in substring and getting only
//		// extension name
//		String fileExtension = fileName.substring(fileName.indexOf("."));
//		if (fileExtension.equals(".xlsx")) {
//			// If it is xlsx file then create object of XSSFWorkbook class
//			wb = new XSSFWorkbook(fis);
//			// Sheet sh=wb.getSheet(0);
//		} else if (fileExtension.equals(".xls")) {
//			// If it is xls file then create object of HSSFWorkbook class
//			wb = new HSSFWorkbook(fis);
//		}
		// Read sheet inside the workbook by its name
		// Sheet sh = wb.getSheetAt(0);
		// Find number of rows in excel file
		Sheet sh = utility.CommonMethods.sh;
		int rowCount = sh.getLastRowNum();
		// System.out.println(rowCount);
		// Create a loop all around the rows in excel sheet
		if (user.equalsIgnoreCase("bbtestus")) {
			editIconClick.click();
			drpDownData.pageWait(addNewMeasurementLine);
			// Create an object of File class to open xlsx file
			addNewMeasurementLine.click();
			Thread.sleep(2000);
			WebElement roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation0']"));
			drpDownData.elementToBeClickable(roomDrpDown);
			Thread.sleep(2000);
			int rowLocation = 0;
			for (int i = 1; i <= rowCount; i++) {
				Row row = sh.getRow(i);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String roomRow = row.getCell(7).getStringCellValue().trim();
				String windowRow = row.getCell(8).getStringCellValue().trim();
				String mountRow = row.getCell(9).getStringCellValue().trim();
				int widthRow = (int) row.getCell(10).getNumericCellValue();
				int heightRow = (int) row.getCell(11).getNumericCellValue();
				// System.out.println(roomRow);
				/* Room drop down selection */
				roomDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[2]/div/span"));
				roomDrpDown.click();
				Thread.sleep(600);
				int roomSize = roomDrpDowmLi.size();
				for (int r = 1; r < roomSize; r++) {
					WebElement roomDrp = driver.findElement(By.xpath("//ul[@id='roomLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + r + "]"));
					String roomTxt = roomDrp.getText().trim();
					if (roomTxt.equalsIgnoreCase(roomRow)) {
						drpDownData.scrollDown(roomDrp);
						Thread.sleep(1000);
						roomDrp.click();
						Thread.sleep(600);
						break;
					}
				}

				// drpDownData.scrollDown(roomDrp);
				Thread.sleep(600);
				/* Window Location drop down selection */
				WinDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[3]/div/span"));
				WinDrpDown.click();
				Thread.sleep(600);
				int windowSize = windowLi.size();
				// System.out.println(windowSize);
				for (int j = 1; j <= windowSize; j++) {
					String windowText = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + j + "]")).getText().trim();
					if (windowRow.equalsIgnoreCase(windowText)) {

						WebElement winDrp = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
								+ "_listbox' and @aria-hidden='false']/li[" + j + "]"));
						drpDownData.scrollDown(winDrp);
						Thread.sleep(1000);
						winDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Mount Type drop down selection */
				mountTypeDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[5]/div/span"));
				mountTypeDrpDown.click();
				// int mountSize = mountType.size();
				// int mountSize = 10;
				List<WebElement> mountSize1 = driver
						.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
				int sizeOfMount = mountSize1.size();
				// System.out.println(sizeOfMount);
				for (int k = 1; k <= sizeOfMount; k++) {
					String mountText = driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.getText().trim();
					// System.out.println(mountText);
					if (mountRow.equalsIgnoreCase(mountText)) {
						driver.findElement(By.xpath(
								"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
								.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Width text box value */
				width = driver.findElement(By.xpath("//input[@id='width" + rowLocation + "']"));
				width.click();
				width.clear();
				Thread.sleep(500);
				width.sendKeys(String.valueOf(widthRow));
				Thread.sleep(500);
				/* Width Range drop down selection */
				widthDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[6]/div/div/span"));
				widthDrpDown.click();
				Thread.sleep(1000);
				widthRange = driver.findElement(By.xpath("//ul[@id='widthFraction" + rowLocation + "_listbox']/li[5]"));
				widthRange.click();
				Thread.sleep(600);
				/* Height text box value */
				height = driver.findElement(By.xpath("//input[@id='height" + rowLocation + "']"));
				height.click();
				height.clear();
				Thread.sleep(500);
				height.sendKeys(String.valueOf(heightRow));
				Thread.sleep(500);
				/* Height Range drop down selection */
				heightRangeDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[7]/div/div/span"));
				heightRangeDrpDown.click();
				Thread.sleep(1000);
				heightRange = driver
						.findElement(By.xpath("//ul[@id='heightFraction" + rowLocation + "_listbox']/li[3]"));
				heightRange.click();
				Thread.sleep(1500);
				if (i <= 3) {
					/* ... icon click */
					// beforeCloneClick.click();
					driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[10]/ul/li/div/button"))
							.click();
					Thread.sleep(1000);
					// cloneClick.click();
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i
							+ "]/td[10]/ul/li/div/ul/li[1]")).click();
					drpDownData.pageWait(addNewMeasurementLine);
					Thread.sleep(2000);
				}
				rowLocation++;
			}
		}
		if (user.equalsIgnoreCase("tltestus")) {
			int rowLocation = 0;
			tlAddRowIcon.click();
			drpDownData.pageWait(systemDrpDwn);
			for (measurementRow = 1; measurementRow <= rowCount; measurementRow++) {
				Row row = sh.getRow(measurementRow);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String system = row.getCell(0).getStringCellValue().trim();
				String systemDescription = row.getCell(1).getStringCellValue().trim();
				String type = row.getCell(2).getStringCellValue().trim();
				String description = row.getCell(3).getStringCellValue().trim();
				int width = (int) row.getCell(4).getNumericCellValue();
				int height = (int) row.getCell(5).getNumericCellValue();
				int depth = (int) row.getCell(6).getNumericCellValue();
				Thread.sleep(1000);
				systemDrpDwn.click();
				Thread.sleep(1500);
				for (WebElement liSystem : systemDrpDwnLi) {
					String systemTxt = liSystem.getText();
					// String system = "Garage";
					if (system.equalsIgnoreCase(systemTxt)) {
						liSystem.click();
						Thread.sleep(1000);
						break;
					}
				}
				systemDesTxtBox.clear();
				Thread.sleep(500);
				systemDesTxtBox.sendKeys(systemDescription);
				Thread.sleep(500);
				typeDrpDwn.click();
				Thread.sleep(1000);
				for (WebElement liType : typeDrpDwnLi) {
					String typeTxt = liType.getText();
					// String actualType = "Bikes";
					if (typeTxt.equalsIgnoreCase(type)) {
						liType.click();
						Thread.sleep(2000);
						break;
					}
				}
				storageDescTxtBox.clear();
				Thread.sleep(1000);
				boolean visibilityStorageDescTxtBox = storageDescTxtBox.isEnabled();
				if (visibilityStorageDescTxtBox == true) {
					storageDescTxtBox.sendKeys(description);
					Thread.sleep(1000);
				}
				boolean visibilityTlWidthTxtBox = tlWidthTxtBox.isEnabled();
				if (visibilityTlWidthTxtBox == true) {
					tlWidthTxtBox.clear();
					Thread.sleep(500);
					tlWidthTxtBox.sendKeys(String.valueOf(width));
					Thread.sleep(500);
					widthDrpDwn.click();
					Thread.sleep(1000);
					tlWidthFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlHeight = tlHeight.isEnabled();
				if (visibilityTlHeight == true) {
					tlHeight.clear();
					Thread.sleep(1000);
					tlHeight.sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					tlHeightDrpDwn.click();
					Thread.sleep(1000);
					tlHeightFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlDepth = tlDepth.isEnabled();
				if (visibilityTlDepth == true) {
					tlDepth.clear();
					Thread.sleep(1000);
					tlDepth.sendKeys(String.valueOf(depth));
					Thread.sleep(1000);
					tlDepthDrpDwn.click();
					Thread.sleep(1000);
					tlDepthFraction.click();
					// elementWait.pageWait(tlDepthFraction);
					Thread.sleep(1000);
				}
				if (measurementRow < 4) {
					WebElement editIcon = driver.findElement(
							By.xpath("//tr[" + measurementRow + "]/td/ul[@ng-if='Permission.EditMeasurements']/"
									+ "li[@class='dropdown note1 ad_user']"));
					editIcon.click();
					Thread.sleep(2000);
					tlEditIcon.click();
					Thread.sleep(2000);
				}
				rowLocation++;
			}

		}
	}

	public void fillWithImageUpload() throws InterruptedException, IOException {
		String user = LoginPage.un;
		int measurementRow = 1;
//		String sheetName="Sheet2";
//	 	//utility.CommonMethods.fetchExcelData(filePath, fileName, sheetName);
//		Sheet sh=commonMethods.fetchExcelSheet(filePath, fileName, sheetName);
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
		int rowLocation = 0;
		if (user.equalsIgnoreCase("bbtestus")) {
			editIconClick.click();
			drpDownData.pageWait(addNewMeasurementLine);
			// Create an object of File class to open xlsx file
			addNewMeasurementLine.click();
			Thread.sleep(2000);
			WebElement roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation0']"));
			drpDownData.elementToBeClickable(roomDrpDown);
			Thread.sleep(2000);
			for (int i = 1; i <= rowCount; i++) {
				Row row = sh.getRow(i);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String roomRow = row.getCell(7).getStringCellValue().trim();
				String windowRow = row.getCell(8).getStringCellValue().trim();
				String mountRow = row.getCell(9).getStringCellValue().trim();
				int widthRow = (int) row.getCell(10).getNumericCellValue();
				int heightRow = (int) row.getCell(11).getNumericCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();
				// System.out.println(roomRow);
				/* Room drop down selection */
				roomDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[2]/div/span"));
				roomDrpDown.click();
				Thread.sleep(600);
				int roomSize = roomDrpDowmLi.size();
				for (int r = 1; r < roomSize; r++) {
					WebElement roomDrp = driver.findElement(By.xpath("//ul[@id='roomLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + r + "]"));
					String roomTxt = roomDrp.getText().trim();
					if (roomTxt.equalsIgnoreCase(roomRow)) {
						drpDownData.scrollDown(roomDrp);
						Thread.sleep(1000);
						roomDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				// drpDownData.scrollDown(roomDrp);
				Thread.sleep(600);
				/* Window Location drop down selection */
				WinDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[3]/div/span"));
				WinDrpDown.click();
				Thread.sleep(600);
				int windowSize = windowLi.size();
				// System.out.println(windowSize);
				for (int j = 1; j <= windowSize; j++) {
					String windowText = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + j + "]")).getText().trim();
					if (windowRow.equalsIgnoreCase(windowText)) {

						WebElement winDrp = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
								+ "_listbox' and @aria-hidden='false']/li[" + j + "]"));
						drpDownData.scrollDown(winDrp);
						Thread.sleep(1000);
						winDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Mount Type drop down selection */
				mountTypeDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[5]/div/span"));
				mountTypeDrpDown.click();
				// int mountSize = mountType.size();
				// int mountSize = 10;
				List<WebElement> mountSize1 = driver
						.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
				int sizeOfMount = mountSize1.size();
				// System.out.println(sizeOfMount);
				for (int k = 1; k <= sizeOfMount; k++) {
					String mountText = driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.getText().trim();
					// System.out.println(mountText);
					if (mountRow.equalsIgnoreCase(mountText)) {
						driver.findElement(By.xpath(
								"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
								.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Width text box value */
				width = driver.findElement(By.xpath("//input[@id='width" + rowLocation + "']"));
				width.click();
				width.clear();
				Thread.sleep(500);
				width.sendKeys(String.valueOf(widthRow));
				Thread.sleep(500);
				/* Width Range drop down selection */
				widthDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[6]/div/div/span"));
				widthDrpDown.click();
				Thread.sleep(1000);
				widthRange = driver.findElement(By.xpath("//ul[@id='widthFraction" + rowLocation + "_listbox']/li[5]"));
				widthRange.click();
				Thread.sleep(600);
				/* Height text box value */
				height = driver.findElement(By.xpath("//input[@id='height" + rowLocation + "']"));
				height.click();
				height.clear();
				Thread.sleep(500);
				height.sendKeys(String.valueOf(heightRow));
				Thread.sleep(500);
				/* Height Range drop down selection */
				heightRangeDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[7]/div/div/span"));
				heightRangeDrpDown.click();
				Thread.sleep(1000);
				heightRange = driver
						.findElement(By.xpath("//ul[@id='heightFraction" + rowLocation + "_listbox']/li[3]"));
				heightRange.click();
				Thread.sleep(1500);

				/* Upload image */
				try {
					if (i >= 2) {
						driver.findElement(
								By.xpath("//tr[" + i + "]/td[8]/div/i[@class='fas fa-times show_cameraicon']")).click();
						Thread.sleep(500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(
						By.xpath("//tr[" + i + "]/td[8]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);
				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + i + "]/td[8]/ul/li/div/ul/li/div/input"));
				// Actions actUploadPhoto = new Actions(driver);
				// actUploadPhoto.moveToElement(uploadPhoto).click().perform();
				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");
				if (i <= 3) {
					driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[10]/ul/li/div/button"))
							.click();
					Thread.sleep(300);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i
							+ "]/td[10]/ul/li/div/ul/li[1]")).click();
					// Thread.sleep(300);
				}
				WebElement imageUploadCloseIcon = driver.findElement(By.id("photoCameraIcon" + rowLocation + ""));
				drpDownData.elementToBeClickable(imageUploadCloseIcon);
				if (i <= 3) {
					/* ... icon click */
					// beforeCloneClick.click();
					int newRowDelete = i + 1;
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + newRowDelete
							+ "]/td[10]/ul/li/div/button")).click();
					Thread.sleep(1000);
					// cloneClick.click();
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + newRowDelete
							+ "]/td[10]/ul/li/div/ul/li[2]")).click();
					drpDownData.pageWait(addNewMeasurementLine);
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[10]/ul/li/div/button"))
							.click();
					Thread.sleep(1000);
					// cloneClick.click();
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i
							+ "]/td[10]/ul/li/div/ul/li[1]")).click();
					drpDownData.pageWait(addNewMeasurementLine);
					Thread.sleep(2000);
				}
				rowLocation++;
			}
		}
		int newRowDelete = 0;
		if (user.equalsIgnoreCase("tltestus")) {
			// int rowLocation = 0;
			tlAddRowIcon.click();
			drpDownData.pageWait(systemDrpDwn);
			for (measurementRow = 1; measurementRow <= rowCount; measurementRow++) {
				Row row = sh.getRow(measurementRow);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String system = row.getCell(0).getStringCellValue().trim();
				String systemDescription = row.getCell(1).getStringCellValue().trim();
				String type = row.getCell(2).getStringCellValue().trim();
				String description = row.getCell(3).getStringCellValue().trim();
				int width = (int) row.getCell(4).getNumericCellValue();
				int height = (int) row.getCell(5).getNumericCellValue();
				int depth = (int) row.getCell(6).getNumericCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();
				Thread.sleep(1000);
				systemDrpDwn.click();
				Thread.sleep(1500);
				for (WebElement liSystem : systemDrpDwnLi) {
					String systemTxt = liSystem.getText();
					// String system = "Garage";
					if (system.equalsIgnoreCase(systemTxt)) {
						liSystem.click();
						Thread.sleep(1000);
						break;
					}
				}
				systemDesTxtBox.clear();
				Thread.sleep(500);
				systemDesTxtBox.sendKeys(systemDescription);
				Thread.sleep(500);
				typeDrpDwn.click();
				Thread.sleep(1000);
				for (WebElement liType : typeDrpDwnLi) {
					String typeTxt = liType.getText();
					// String actualType = "Bikes";
					if (typeTxt.equalsIgnoreCase(type)) {
						liType.click();
						Thread.sleep(2000);
						break;
					}
				}
				storageDescTxtBox.clear();
				Thread.sleep(1000);
				boolean visibilityStorageDescTxtBox = storageDescTxtBox.isEnabled();
				if (visibilityStorageDescTxtBox == true) {
					storageDescTxtBox.sendKeys(description);
					Thread.sleep(1000);
				}
				boolean visibilityTlWidthTxtBox = tlWidthTxtBox.isEnabled();
				if (visibilityTlWidthTxtBox == true) {
					tlWidthTxtBox.clear();
					Thread.sleep(500);
					tlWidthTxtBox.sendKeys(String.valueOf(width));
					Thread.sleep(500);
					widthDrpDwn.click();
					Thread.sleep(1000);
					tlWidthFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlHeight = tlHeight.isEnabled();
				if (visibilityTlHeight == true) {
					tlHeight.clear();
					Thread.sleep(1000);
					tlHeight.sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					tlHeightDrpDwn.click();
					Thread.sleep(1000);
					tlHeightFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlDepth = tlDepth.isEnabled();
				if (visibilityTlDepth == true) {
					tlDepth.clear();
					Thread.sleep(1000);
					tlDepth.sendKeys(String.valueOf(depth));
					Thread.sleep(1000);
					tlDepthDrpDwn.click();
					Thread.sleep(1000);
					tlDepthFraction.click();
					Thread.sleep(1000);
				}
				// Upload image
				try {
					if (measurementRow >= 2) {
						driver.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/div/i[@class='fas fa-times']"))
								.click();
						Thread.sleep(500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(By.xpath(
						"//tr[" + measurementRow + "]/td[11]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);
				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/ul/li/div/ul/li/div/input"));
				// Actions actUploadPhoto = new Actions(driver);
				// actUploadPhoto.moveToElement(uploadPhoto).click().perform();
				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");
				if (measurementRow <= 3) {
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/button")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/ul/li[2]")).click();
					Thread.sleep(3000);
				}
				WebElement imageUploadCloseIcon = driver.findElement(By.xpath("//i[@class='fas fa-times']"));
				drpDownData.elementToBeClickable(imageUploadCloseIcon);
				if (measurementRow <= 3) {
					// icon click
					// beforeCloneClick.click();
					newRowDelete = measurementRow + 1;
					// deleteRowWait.click();
					deleteRowWait = driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + newRowDelete + "]/td[13]"));
					drpDownData.elementToBeClickable(deleteRowWait);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + newRowDelete
							+ "]/td[13]/ul/li/div/button")).click();
					// drpDownData.pageWait(pageLoad);
					Thread.sleep(1000);
					// cloneClick.click();
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + newRowDelete
							+ "]/td[13]/ul/li/div/ul/li[3]")).click();
					drpDownData.pageWait(saveMeasure);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/button")).click();
					Thread.sleep(1000);
					// cloneClick.click();
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/ul/li[2]")).click();
					drpDownData.pageWait(saveMeasure);
					Thread.sleep(2000);
				}
				rowLocation++;
			}
		}
	}

	public void saveMeasurement() throws InterruptedException {
		String user = LoginPage.un;
		if (user.equalsIgnoreCase("bbtestus")) {
			saveMeasure.click();
			Thread.sleep(2000);
			// drpDownData = new utility.CommonMethods();
			// drpDownData.pageWait(editIconClick);
			// Thread.sleep(5000);
		}
		if (user.equalsIgnoreCase("tltestus")) {
			saveMeasure.click();
			Thread.sleep(2000);
			drpDownData.pageWait(tlAddRowIcon);
			Thread.sleep(5000);
		}
	}

	public void copyLine() throws InterruptedException, IOException {
		if (user.equalsIgnoreCase("bbtestus")) {

//				String sheetName="Sheet1";
//				commonMethods.fetchExcelSheet(filePath, fileName, sheetName);
			// utility.CommonMethods.fetchExcelData(filePath, fileName, sheetName);
			// //Read excel file to fetch input data
			File file = new File(filePath + fileName);
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = null;
			// Choose excel file type by its extension
			String fileExtension = fileName.substring(fileName.indexOf("."));
			if (fileExtension.equals(".xlsx")) {
				wb = new XSSFWorkbook(fis);
			} else if (fileExtension.equals(".xls")) {
				wb = new HSSFWorkbook(fis);
			}
			// Fetch data from first sheet of workbook
			// Workbook wb=null;
			Sheet sh1 = wb.getSheet("Sheet1");
			Sheet sh2 = wb.getSheet("Sheet2");
			// get total row count of sheet
			// int rowCountOfSheet1=sh1.getLastRowNum();
			int rowCount = sh2.getLastRowNum();
			// select first row of sheet
			Row row = sh1.getRow(1);
			// Row sheetRow = sh2.getRow(1);
			gridSize = measurementPageRowGrid.size();
			// System.out.println(gridSize);
			int copyTime = (int) row.getCell(13).getNumericCellValue();
			// int copyRowNo = (int) row.getCell(14).getNumericCellValue();

			copyLineEnableIcon.click();
			Thread.sleep(1000);
			requiredRow.sendKeys(String.valueOf(gridSize));
			requiredTime.sendKeys(String.valueOf(copyTime));
			Thread.sleep(1000);
			copyClick.click();
			drpDownData.elementToBeClickable(copyClick);
			int gridSize1 = measurementPageRowGrid.size();
			Thread.sleep(500);
			int rowLocation = gridSize + 1;
			for (int i = rowLocation; i <= gridSize1; i++) {
				int rowLocation1 = gridSize - 1;
				Row sheetRow = sh2.getRow(i);
				int widthRow = (int) sheetRow.getCell(10).getNumericCellValue();
				int heightRow = (int) sheetRow.getCell(11).getNumericCellValue();
				/* Width text box value */
				width = driver.findElement(By.xpath("//input[@id='width" + gridSize + "']"));
				elementWait.scrollAction(width);
				width.click();
				// width.clear();
				Thread.sleep(500);
				width.sendKeys(String.valueOf(widthRow));
				Thread.sleep(500);
				/* Width Range drop down selection */
				widthDrpDown = driver.findElement(By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
						+ rowLocation + "]/td[6]/div/div/span"));
				elementWait.scrollAction(widthDrpDown);
				widthDrpDown.click();
				Thread.sleep(1000);
				widthRange = driver.findElement(By.xpath("//ul[@id='widthFraction" + gridSize + "_listbox']/li[5]"));
				elementWait.scrollAction(widthRange);
				widthRange.click();
				Thread.sleep(600);
				/* Height text box value */
				height = driver.findElement(By.xpath("//input[@id='height" + gridSize + "']"));
				elementWait.scrollAction(height);
				height.click();
				// height.clear();
				Thread.sleep(500);
				height.sendKeys(String.valueOf(heightRow));
				Thread.sleep(500);
				/* Height Range drop down selection */
				heightRangeDrpDown = driver
						.findElement(By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + rowLocation
								+ "]/td[7]/div/div/span"));
				elementWait.scrollAction(heightRangeDrpDown);
				heightRangeDrpDown.click();
				Thread.sleep(1000);
				heightRange = driver.findElement(By.xpath("//ul[@id='heightFraction" + gridSize + "_listbox']/li[3]"));
				elementWait.scrollAction(heightRange);
				heightRange.click();
				Thread.sleep(1500);
				gridSize++;
				rowLocation++;
			}
			// elementWait.pageWait(saveMeasure);
		}
	}

	public void fillRowDataWithImageUpload() throws InterruptedException, IOException {
		// String user = LoginPage.un;

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
		Sheet sh = wb.getSheet("Sheet2");
		// Find number of rows in excel file
		int rowCount = sh.getLastRowNum();
		int rowCount1 = rowCount - 1;
		// System.out.println(rowCount1);
		// Create a loop all around the rows in excel sheet
		int rowLocation = 0;
		if (user.equalsIgnoreCase("bbtestus")) {
			editIconClick.click();
			drpDownData.pageWait(addNewMeasurementLine);
			// Create an object of File class to open xlsx file
			addNewMeasurementLine.click();
			Thread.sleep(2000);
			WebElement roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation0']"));
			drpDownData.elementToBeClickable(roomDrpDown);
			Thread.sleep(2000);
			for (int i = 1; i <= rowCount1; i++) {
				Row row = sh.getRow(i);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String roomRow = row.getCell(7).getStringCellValue().trim();
				String windowRow = row.getCell(8).getStringCellValue().trim();
				String mountRow = row.getCell(9).getStringCellValue().trim();
				int widthRow = (int) row.getCell(10).getNumericCellValue();
				int heightRow = (int) row.getCell(11).getNumericCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();
				// System.out.println(roomRow);
				/* Room drop down selection */
				roomDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[2]/div/span"));
				roomDrpDown.click();
				Thread.sleep(600);
				int roomSize = roomDrpDowmLi.size();
				for (int r = 1; r < roomSize; r++) {
					WebElement roomDrp = driver.findElement(By.xpath("//ul[@id='roomLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + r + "]"));
					String roomTxt = roomDrp.getText().trim();
					if (roomTxt.equalsIgnoreCase(roomRow)) {
						drpDownData.scrollDown(roomDrp);
						Thread.sleep(1000);
						roomDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				// drpDownData.scrollDown(roomDrp);
				Thread.sleep(600);
				/* Window Location drop down selection */
				WinDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[3]/div/span"));
				WinDrpDown.click();
				Thread.sleep(600);
				int windowSize = windowLi.size();
				// System.out.println(windowSize);
				for (int j = 1; j <= windowSize; j++) {
					String windowText = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
							+ "_listbox' and @aria-hidden='false']/li[" + j + "]")).getText().trim();
					if (windowRow.equalsIgnoreCase(windowText)) {

						WebElement winDrp = driver.findElement(By.xpath("//ul[@id='windowLocation" + rowLocation
								+ "_listbox' and @aria-hidden='false']/li[" + j + "]"));
						drpDownData.scrollDown(winDrp);
						Thread.sleep(1000);
						winDrp.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Mount Type drop down selection */
				mountTypeDrpDown = driver.findElement(
						By.xpath("//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[5]/div/span"));
				mountTypeDrpDown.click();
				// int mountSize = mountType.size();
				// int mountSize = 10;
				List<WebElement> mountSize1 = driver
						.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
				int sizeOfMount = mountSize1.size();
				// System.out.println(sizeOfMount);
				for (int k = 1; k <= sizeOfMount; k++) {
					String mountText = driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.getText().trim();
					// System.out.println(mountText);
					if (mountRow.equalsIgnoreCase(mountText)) {
						driver.findElement(By.xpath(
								"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
								.click();
						Thread.sleep(600);
						break;
					}
				}
				/* Width text box value */
				width = driver.findElement(By.xpath("//input[@id='width" + rowLocation + "']"));
				width.click();
				width.clear();
				Thread.sleep(500);
				width.sendKeys(String.valueOf(widthRow));
				Thread.sleep(500);
				/* Width Range drop down selection */
				widthDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[6]/div/div/span"));
				widthDrpDown.click();
				Thread.sleep(1000);
				widthRange = driver.findElement(By.xpath("//ul[@id='widthFraction" + rowLocation + "_listbox']/li[5]"));
				widthRange.click();
				Thread.sleep(600);
				/* Height text box value */
				height = driver.findElement(By.xpath("//input[@id='height" + rowLocation + "']"));
				height.click();
				height.clear();
				Thread.sleep(500);
				height.sendKeys(String.valueOf(heightRow));
				Thread.sleep(500);
				/* Height Range drop down selection */
				heightRangeDrpDown = driver.findElement(By.xpath(
						"//div[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[7]/div/div/span"));
				heightRangeDrpDown.click();
				Thread.sleep(1000);
				heightRange = driver
						.findElement(By.xpath("//ul[@id='heightFraction" + rowLocation + "_listbox']/li[3]"));
				heightRange.click();
				Thread.sleep(1500);

				/* Upload image */
				try {
					if (i > 1) {
						driver.findElement(
								By.xpath("//tr[" + i + "]/td[8]/div/i[@class='fas fa-times show_cameraicon']")).click();
						Thread.sleep(500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(
						By.xpath("//tr[" + i + "]/td[8]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);

				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + i + "]/td[8]/ul/li/div/ul/li/div/input"));
				// Actions actUploadPhoto = new Actions(driver);
				// actUploadPhoto.moveToElement(uploadPhoto).click().perform();
				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");
				/* Wait for image wait */
				Thread.sleep(3000);
				if (i < rowCount1) {
					driver.findElement(By.xpath(
							"//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[10]/ul/li/div/button"))
							.click();
					Thread.sleep(300);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i
							+ "]/td[10]/ul/li/div/ul/li[1]")).click();
					// Thread.sleep(300);
					WebElement imageUploadCloseIcon = driver.findElement(By.id("photoCameraIcon" + rowLocation + ""));
					drpDownData.elementToBeClickable(imageUploadCloseIcon);
				}
				rowLocation++;
			}
			elementWait.pageWait(saveMeasure);
		}

		if (user.equalsIgnoreCase("tltestus")) {
			// int rowLocation = 0;
			tlAddRowIcon.click();
			drpDownData.pageWait(systemDrpDwn);
			for (measurementRow = 1; measurementRow <= rowCount1; measurementRow++) {
				Row row = sh.getRow(measurementRow);
				// Create a loop to print cell values in a row
				// Getting a excel data in string variable
				String system = row.getCell(0).getStringCellValue().trim();
				String systemDescription = row.getCell(1).getStringCellValue().trim();
				String type = row.getCell(2).getStringCellValue().trim();
				String description = row.getCell(3).getStringCellValue().trim();
				int width = (int) row.getCell(4).getNumericCellValue();
				int height = (int) row.getCell(5).getNumericCellValue();
				int depth = (int) row.getCell(6).getNumericCellValue();
				String imageFileName = row.getCell(12).getStringCellValue().trim();
				Thread.sleep(1000);
				// drpDownData.pageWait(systemDrpDwn);
				systemDrpDwn.click();
				Thread.sleep(2000);
				for (WebElement liSystem : systemDrpDwnLi) {
					String systemTxt = liSystem.getText();
					// String system = "Garage";
					if (system.equalsIgnoreCase(systemTxt)) {
						liSystem.click();
						Thread.sleep(1000);
						break;
					}
				}
				systemDesTxtBox.click();
				drpDownData.pageWait(systemDesTxtBox);
				systemDesTxtBox.clear();
				Thread.sleep(1000);
				systemDesTxtBox.sendKeys(systemDescription);
				Thread.sleep(500);
				typeDrpDwn.click();
				Thread.sleep(1000);
				for (WebElement liType : typeDrpDwnLi) {
					String typeTxt = liType.getText();
					// String actualType = "Bikes";
					if (typeTxt.equalsIgnoreCase(type)) {
						liType.click();
						Thread.sleep(2000);
						break;
					}
				}
				storageDescTxtBox.click();
				Thread.sleep(500);
				storageDescTxtBox.clear();
				Thread.sleep(1000);
				boolean visibilityStorageDescTxtBox = storageDescTxtBox.isEnabled();
				if (visibilityStorageDescTxtBox == true) {
					drpDownData.isClickable(storageDescTxtBox);
					storageDescTxtBox.sendKeys(description);
					Thread.sleep(1000);
				}
				boolean visibilityTlWidthTxtBox = tlWidthTxtBox.isEnabled();
				if (visibilityTlWidthTxtBox == true) {
					tlWidthTxtBox.click();
					drpDownData.elementToBeClickable(tlWidthTxtBox);
					tlWidthTxtBox.clear();
					Thread.sleep(2000);
					tlWidthTxtBox.sendKeys(String.valueOf(width));
					drpDownData.pageWait(widthDrpDwn);
					widthDrpDwn.click();
					Thread.sleep(500);
					drpDownData.pageWait(tlWidthFraction);
					tlWidthFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlHeight = tlHeight.isEnabled();
				if (visibilityTlHeight == true) {
					tlHeight.click();
					drpDownData.elementToBeClickable(tlHeight);
					tlHeight.clear();
					Thread.sleep(2000);
					tlHeight.sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					drpDownData.pageWait(tlHeightDrpDwn);
					tlHeightDrpDwn.click();
					Thread.sleep(1000);
					drpDownData.elementToBeClickable(tlHeightFraction);
					tlHeightFraction.click();
					Thread.sleep(1000);
				}
				boolean visibilityTlDepth = tlDepth.isEnabled();
				if (visibilityTlDepth == true) {
					tlDepth.click();
					drpDownData.elementToBeClickable(tlDepth);
					tlDepth.clear();
					Thread.sleep(2000);
					tlDepth.sendKeys(String.valueOf(depth));
					Thread.sleep(1000);
					drpDownData.pageWait(tlDepthDrpDwn);
					tlDepthDrpDwn.click();
					Thread.sleep(1000);
					tlDepthFraction.click();
					Thread.sleep(1000);
				}
				/* Upload image */
				try {
					if (measurementRow > 1) {
						driver.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/div/i[@class='fas fa-times']"))
								.click();
						Thread.sleep(1500);
					}
				} catch (Exception e) {

				}
				WebElement cameraIcon = driver.findElement(By.xpath(
						"//tr[" + measurementRow + "]/td[11]/ul/li/div/button[@class='plus_but dropdown-toggle']"));
				cameraIcon.click();
				Thread.sleep(500);
				// drpDownData.pageWait(cameraIcon);
				WebElement uploadPhoto = driver
						.findElement(By.xpath("//tr[" + measurementRow + "]/td[11]/ul/li/div/ul/li/div/input"));
				// Actions actUploadPhoto = new Actions(driver);
				// actUploadPhoto.moveToElement(uploadPhoto).click().perform();
				// drpDownData.pageWait(uploadPhoto);

				uploadPhoto.sendKeys(System.getProperty("user.dir") + "\\Images\\" + imageFileName + "");
				if (measurementRow < rowCount1) {
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/button")).click();
					Thread.sleep(300);
					driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr["
							+ measurementRow + "]/td[13]/ul/li/div/ul/li[2]")).click();
					// Thread.sleep(300);
				}
				WebElement imageUploadCloseIcon = driver.findElement(By.xpath("//i[@class='fas fa-times']"));
				drpDownData.pageWait(imageUploadCloseIcon);
				Thread.sleep(2000);

			}
			rowLocation++;
		}
	}

}
