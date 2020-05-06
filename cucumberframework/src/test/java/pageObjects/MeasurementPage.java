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

	@FindBy(xpath = "//i[@class='far fa-check-circle fa_icon'][1]")
	WebElement saveMeasure;

	@FindBy(xpath = "//button[@class='tooltip-bottom save_tpbut' and @data-tooltip='Edit']")
	WebElement editIconClick;

	@FindBy(xpath = "//button[@class='plus_but tooltip-bottom ng-scope' and @data-tooltip='Add new measurement line']")
	WebElement addNewMeasurementLine;

	public MeasurementPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openMeasure() throws InterruptedException {
		// driver.navigate().refresh();
		// drpDownData = new utilities.CommonMethods();
		// drpDownData.pageWait(clickMeasure);
		clickMeasure.click();
		// drpDownData = new utility.CommonMethods();
		drpDownData.pageWait(editIconClick);
		Thread.sleep(1500);
	}

	public void fillData() throws InterruptedException, InvalidFormatException, IOException {

		editIconClick.click();
		drpDownData.pageWait(addNewMeasurementLine);
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
		int rowCount = sh.getLastRowNum();
		// Create a loop all around the rows in excel sheet
		addNewMeasurementLine.click();
		Thread.sleep(2000);
		WebElement roomDrpDown = driver
				.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr/td[2]/span"));
		drpDownData.elementToBeClickable(roomDrpDown);
		Thread.sleep(2000);
		int rowLocation = 0;
		for (int i = 1; i <= rowCount; i++) {
			Row row = sh.getRow(i);
			// Create a loop to print cell values in a row
			// Getting a excel data in string variable
			String roomRow = row.getCell(0).getStringCellValue().trim();
			String windowRow = row.getCell(1).getStringCellValue().trim();
			String mountRow = row.getCell(2).getStringCellValue().trim();
			int widthRow = (int) row.getCell(3).getNumericCellValue();
			int heightRow = (int) row.getCell(4).getNumericCellValue();
			// System.out.println(roomRow);
			/* Room drop down selection */
			roomDrpDown = driver.findElement(
					By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[2]/span/span"));
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
			/* Window Location drop down selection */
			WinDrpDown = driver.findElement(
					By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[3]/span/span"));
			WinDrpDown.click();
			Thread.sleep(600);
			int windowSize = windowLi.size();
			// System.out.println(windowSize);
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
			/* Mount Type drop down selection */
			mountTypeDrpDown = driver.findElement(
					By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[5]/span/span"));
			mountTypeDrpDown.click();
			// int mountSize = mountType.size();
			// int mountSize = 10;
			List<WebElement> mountSize1 = driver
					.findElements(By.xpath("(//ul[@id='mount" + rowLocation + "_listbox']/li)"));
			int sizeOfMount = mountSize1.size();
			System.out.println(sizeOfMount);
			for (int k = 1; k <= sizeOfMount; k++) {
				String mountText = driver
						.findElement(By.xpath(
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
			widthDrpDown = driver.findElement(
					By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/tbody/tr[" + i + "]/td[6]/div/span"));
			widthDrpDown.click();
			Thread.sleep(1000);
			widthRange = driver
					.findElement(By.xpath("(//ul[@id='widthFraction" + rowLocation + "_listbox']/li[5])[3]"));
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
			heightRangeDrpDown = driver.findElement(By.xpath("//tr[" + i + "]/td[7]/div/span"));
			heightRangeDrpDown.click();
			Thread.sleep(1000);
			heightRange = driver
					.findElement(By.xpath("(//ul[@id='heightFraction" + rowLocation + "_listbox']/li[7])[3]"));
			heightRange.click();
			Thread.sleep(500);
			if (i <= 3) {
				/* ... icon click */
				// beforeCloneClick.click();
				driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/" + "tbody/tr[" + i
						+ "]/td[11]/ul/li/div/button")).click();
				Thread.sleep(500);
				// cloneClick.click();
				driver.findElement(By.xpath("//*[@id='gridEditMeasurements']/div[3]/table/" + "tbody/tr[" + i
						+ "]/td[11]/ul/li/div/ul/li[1]")).click();
				drpDownData.pageWait(addNewMeasurementLine);
				Thread.sleep(2000);
			}
			rowLocation++;
		}

	}

	public void saveMeasurement() throws InterruptedException {
		saveMeasure.click();
		Thread.sleep(2000);
		drpDownData = new utility.CommonMethods();
		drpDownData.pageWait(editIconClick);
		Thread.sleep(3000);
	}
}
