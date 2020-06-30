package pageObjects;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MeasurementPage {

	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "Measurement.xlsx";
	private WebDriver driver;
	utility.CommonMethods drpDownData = new utility.CommonMethods();

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
		String user = LoginPage.un;
		int measurementRow = 1;
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
				roomDrpDown = driver.findElement(By.xpath("//input[@id='roomLocation" + rowLocation + "']"));
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
				WinDrpDown = driver.findElement(By.xpath("//input[@id='windowLocation" + rowLocation + "']"));
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
				mountTypeDrpDown = driver.findElement(By.xpath("//input[@id='mount" + rowLocation + "']"));
				mountTypeDrpDown.click();
				// int mountSize = mountType.size();
				// int mountSize = 10;
				List<WebElement> mountSize1 = driver
						.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
				int sizeOfMount = mountSize1.size();
				System.out.println(sizeOfMount);
				for (int k = 1; k <= sizeOfMount; k++) {
					String mountText = driver.findElement(By.xpath(
							"//ul[@id='mount" + rowLocation + "_listbox' and @aria-hidden='false']/li[" + k + "]"))
							.getText().trim();
					System.out.println(mountText);
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
				widthDrpDown = driver.findElement(By.xpath("//input[@id='widthFraction" + rowLocation + "']"));
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
				heightRangeDrpDown = driver.findElement(By.xpath("//input[@id='heightFraction" + rowLocation + "']"));
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
				if (measurementRow <= 3) {
					WebElement editIcon = driver.findElement(
							By.xpath("//tr[" + measurementRow + "]/td/ul[@ng-if='Permission.EditMeasurements']/"
									+ "li[@class='dropdown note1 ad_user']"));
					editIcon.click();
					Thread.sleep(2000);
					tlEditIcon.click();
					Thread.sleep(2000);
				}
				// rowLocation++;
			}

		}
	}

	public void saveMeasurement() throws InterruptedException {
		String user = LoginPage.un;
		if (user.equalsIgnoreCase("bbtestus")) {
			saveMeasure.click();
			Thread.sleep(2000);
			// drpDownData = new utility.CommonMethods();
			drpDownData.pageWait(editIconClick);
			Thread.sleep(3000);
		}
		if (user.equalsIgnoreCase("tltestus")) {
			saveMeasure.click();
			Thread.sleep(2000);
			drpDownData.pageWait(tlAddRowIcon);
			Thread.sleep(3000);
		}
	}
}
