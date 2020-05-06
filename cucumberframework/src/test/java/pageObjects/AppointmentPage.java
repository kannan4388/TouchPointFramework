package pageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentPage {
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "Appointment.xlsx";
	private WebDriver driver;
	public static String inputTitle = null;
	utility.CommonMethods load = new utility.CommonMethods();

	@FindBy(xpath = "//li[@rel='tab7'][5]")
	WebElement clickAppoinment;

	@FindBy(xpath = "//i[@class='fal fa-clock task_icon']")
	WebElement addAppIcon;

	// a[@id='showBusinessHourButton']
	@FindBy(xpath = "//a[@id='showBusinessHourButton']")
	WebElement appCalendar;

	@FindBy(xpath = "//*[@id='scheduler']/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td")
	WebElement appPageWait;

	// @FindBy(xpath="//*[@id='scheduler']/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td")
	@FindBy(xpath = "//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td")
	WebElement clickOnTimeCalendar;

	@FindBy(xpath = "//input[@name='OpportunityId_input']")
	WebElement opp;

	@FindBy(xpath = "//select[@name='AptTypeEnum']")
	WebElement appType;

	@FindBy(css = "#continue")
	WebElement popupContinue;

	@FindAll(@FindBy(xpath = "//select[@name='AptTypeEnum']/option"))
	List<WebElement> optionAll;

	@FindAll(@FindBy(xpath = "//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div"))
	List<WebElement> divAppointment;

	@FindBy(css = "#calendarStartDate")
	WebElement startDate;

	@FindBy(css = "#calendarEndDate")
	WebElement endDate;

	@FindBy(xpath = "//input[@id='chkEventRecurring']")
	WebElement recurrenceChkBox;

	@FindBy(xpath = "//input[@ng-model='calendarModelObject.EventRecurring.endBy' and @ng-value='2']")
	WebElement endAfterRdoBtn;

	@FindBy(xpath = "//input[@ng-model='calendarModelObject.EventRecurring.endBy' and @ng-value='3']")
	WebElement endByRdoBtn;

	@FindBy(xpath = "//input[@class='calder frm_controllead1 ng-pristine ng-untouched ng-valid ng-not-empty ng-valid-pattern' and "
			+ "@ng-model='calendarModelObject.EventRecurring.EndsAfterXOccurrences']")
	WebElement endAfterTxtBoxClear;

	@FindBy(xpath = "//input[@class='calder frm_controllead1 ng-valid ng-valid-pattern ng-empty ng-dirty ng-valid-parse ng-touched']")
	WebElement endAfterTxtBox;

	@FindBy(xpath = "//input[@id='recurringEndDate']")
	WebElement endByTxtBox;

	@FindBy(css = ".day_event:nth-child(4) > .option-input")
	WebElement allDayEventChkBox;

	@FindBy(xpath = "//button[@class='btn btn-primary tery_sm1 ng-binding ng-scope']")
	WebElement saveRtnAcc;

	@FindBy(xpath = "//button[@ng-click='saveCalendarData();']")
	WebElement appSave;

	@FindBy(css = ".ng-scope>.tery_sm1:nth-child(1)")
	WebElement cancelBtn;

	@FindBy(xpath = "//a[@id='showBusinessHourButton']")
	WebElement afterSaveLoad;

	@FindBy(xpath = "//label[@class='col-sm-3 text_detlabel']")
	WebElement pageLoad;

	@FindBy(xpath = "//div[@ng-show='!aptmtCtrl.Account.IsCommercial']")
	WebElement saveAndLandOnApp;

	@FindBy(xpath = "//button[@ng-click='editAppointment();']")
	WebElement editAppointment;

	@FindBy(xpath = "//li[@class='k-state-default k-view-week k-state-selected']")
	WebElement viewWeeklyAppointment;

	@FindBy(xpath = "//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[2]/div[1]")
	WebElement weeklyAppointmentPageWait;

	@FindBy(xpath = "//li[@class='k-state-default k-header k-nav-next']")
	WebElement nextDay;

	@FindBy(xpath = "//*[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[2]/div[1]")
	WebElement pageWaitForEditApp;

	@FindBy(xpath = "//div[@class='k-floatwrap k-header k-scheduler-toolbar']")
	WebElement nextDayScroll;

	@FindBy(xpath = "//ul[@class='tabs1 calendar_licons']")
	WebElement beforeNextDayClick;

	@FindBy(xpath = "//li[@class='ng-scope' and @ng-if='Permission.AddSaveCalendar']")
	WebElement addCalendarIcon;

	@FindBy(xpath = "//input[@name='title' and @ng-model='viewtitle']")
	WebElement titleTxtBox;

	@FindBy(xpath = "//button[@class='btn btn-cancel' and text()='Save']")
	WebElement saveCalendarBtn;

	@FindBy(xpath = "//li[@class='ng-scope' and @ng-if='Permission.EditSaveCalendar']")
	WebElement manageCalendarIcon;

	@FindBy(xpath = "//button[@class='btn btn-cancel' and @ng-click='UpdateSavedCalendar(SavedCalendarList)']")
	WebElement mgeCalSaveBtn;

	@FindAll(@FindBy(xpath = "//input[@name='title' and @ng-model='cal.ViewTitle']"))
	List<WebElement> titleManageCal;

	@FindBy(xpath = "//button[@class='tooltip-bottom task_tpbut' and @data-tooltip='Add Task']")
	WebElement addTaskIcon;

	public AppointmentPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void appointmentTabClick() throws InterruptedException {
		clickAppoinment.click();
		load.pageWait(addAppIcon);
	}

	public void addAppointmentIconClick() throws InterruptedException {
		addAppIcon.click();
		load.pageWait(clickOnTimeCalendar);
		Thread.sleep(5000);
	}

	public void openApp() throws InterruptedException, AWTException, ParseException {
		String[] intialTime = { "00:00 AM", "01:00 AM", "02:00 AM", "03:00 AM", "04:00 AM", "05:00 AM", "06:00 AM",
				"07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM",
				"03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM", "09:00 PM", "10:00 PM",
				"11:00 PM" };
		// for(appRow+=4+appRow;appRow<=96;appRow++) {
		int divcount = 2;
		int rowInc = 1;
		int appRow1 = 0;
		Thread.sleep(3000);
		for (int appRow = 0; appRow < 24; appRow++) {
			WebElement div = driver.findElement(
					By.xpath("//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[" + divcount + "]/div[1]"));
			// WebElement
			// divStartTime=driver.findElement(By.xpath("//div[@class='tile-title-wrapper']/span[2]"));
			String txt = div.getText();
			// System.out.println(txt);
			String[] start = txt.split("Start Time:");
			// String finalStart = start[0].trim();
			String finalStart = start[1].trim();
			String startTime = finalStart.substring(0, 8).trim();
			// String con = start[5].substring(0, 5).trim();
			// String finalTime = startTime.trim() + ":" + con.trim();
			// System.out.println(finalTime);
			// DateTimeFormatter formatter = DateTimeFormatter
			SimpleDateFormat divtiming = new SimpleDateFormat("HH:mm a");
			SimpleDateFormat mintiming = new SimpleDateFormat("HH:mm");
			SimpleDateFormat hourtiming = new SimpleDateFormat("HH");
			Date divMin = mintiming.parse(startTime);
			String minOfDiv = mintiming.format(divMin);
			String[] splitMinOfDiv = minOfDiv.split(":");
			Date divHour = hourtiming.parse(startTime);
			int divMinInteger = Integer.valueOf(splitMinOfDiv[1]);
			String hourOfDiv = hourtiming.format(divHour);
			int divHourInteger = Integer.valueOf(hourOfDiv);

			Date intialMin = mintiming.parse(intialTime[appRow1]);
			Date intialHour = hourtiming.parse(intialTime[appRow1]);
			String hour = hourtiming.format(intialHour);
			String min = mintiming.format(intialMin);
			String[] intialMinSplit = min.split(":");
			int intialHourInteger = Integer.valueOf(hour);
			int intialMinInterger = Integer.valueOf(intialMinSplit[1]);
			Date divtimingFinal = divtiming.parse(startTime);
			Date intial = divtiming.parse(intialTime[appRow1]);
			long d = divtimingFinal.getTime() - intial.getTime();
			if (d < 0) {
				divcount++;
			} else if (!startTime.equals(intialTime[appRow1])) {
				if (divHourInteger > intialHourInteger == true) {
					// divcount++;
					rowInc = rowInc + 4;
					clickOnTimeCalendar = driver.findElement(By.xpath("//div[@id='scheduler']/table/tbody/tr[2]"
							+ "/td[2]/div/table/tbody/tr[" + rowInc + "]/td"));
					load.scrollView(clickOnTimeCalendar);
					appRow1++;
				}

				else if (divHourInteger == intialHourInteger && !(divMinInteger > intialMinInterger) == true) {
					divcount++;
					rowInc = rowInc + 4;
					clickOnTimeCalendar = driver.findElement(By.xpath("//div[@id='scheduler']/table/tbody/tr[2]"
							+ "/td[2]/div/table/tbody/tr[" + rowInc + "]/td"));
					load.scrollView(clickOnTimeCalendar);
					appRow1++;
				} else {
					Thread.sleep(600);
					clickOnTimeCalendar.click();
					Thread.sleep(4000);
					if (appType.isDisplayed() == true) {
						break;
					}
				}
			}

			else if (startTime.equals(intialTime[appRow1])) {
				divcount++;
				rowInc = rowInc + 4;
				clickOnTimeCalendar = driver.findElement(By.xpath(
						"//div[@id='scheduler']/table/tbody/tr[2]" + "/td[2]/div/table/tbody/tr[" + rowInc + "]/td"));
				// Actions actions = new Actions(driver);
				// actions.moveToElement(clickOnTimeCalendar).perform();
				load.scrollView(clickOnTimeCalendar);
				appRow1++;
				// intialTime="1:00 AM";
			}
		}
		load.pageWait(opp);
		Thread.sleep(2000);
	}

	public void fillAppData() throws InterruptedException, InvalidFormatException, IOException {
		appType.click();
		Thread.sleep(1000);
		int sizeOfOption = optionAll.size();
		// System.out.println(sizeOfOption);
		for (int i = 1; i <= sizeOfOption; i++) {
			String appTypeText = driver.findElement(By.xpath("//select[@name='AptTypeEnum']/option[" + i + "]"))
					.getText();
			utility.CommonMethods drpDownApp = new utility.CommonMethods();
			String drpTypeAppointment = drpDownApp.getExcelData(filePath, fileName).get("TypeOfAppointment");
			// System.out.println(appTypeText);
			if (appTypeText.equals(drpTypeAppointment)) {
				driver.findElement(By.xpath("//select[@name='AptTypeEnum']/option[" + i + "]")).click();
				Thread.sleep(2000);
				load.scrollDown(opp);
				Thread.sleep(2000);
				opp.click();
				Thread.sleep(2000);
				opp.sendKeys(Keys.DOWN);
				Thread.sleep(2000);
				load.scrollDown(appType);
				Thread.sleep(2000);
				String pattern = "MM/dd/yyyy hh:mm aa";
				// DateTime now=new DateTime("dd/MM/yyyy hh:mm:ss aa");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				Format minuteFormat = new SimpleDateFormat("mm");
				Format hourFormat = new SimpleDateFormat("hh");
				int minutes = Integer.parseInt(minuteFormat.format(new Date()));
				int hour = Integer.parseInt(hourFormat.format(new Date()));
				String start = simpleDateFormat.format(new Date());
				// System.out.println(start);
				int mod = minutes % 15;
				int res = 0;
				if ((mod) >= 8) {
					res = minutes + (15 - mod);
					if (res == 60 && hour == 12) {
						res = 00;
						hour = 01;
					} else if (res == 60) {
						res = 00;
						hour = hour + 1;
					}
				} else {
					res = minutes - mod;
				}
				String[] arrMinutes = start.split(":");
				String date = arrMinutes[0];
				String a = arrMinutes[1];
				date = date.substring(0, 11);
				// String omitSysMinutes=a.substring(0, 2);
				String afterMinutes = a.substring(2, 5);
				String frontZeroHour = String.format("%02d", hour);
				String finalStrtDateTime = date + frontZeroHour + ":" + res + afterMinutes;
				System.out.println(finalStrtDateTime);
				startDate.click();
				Thread.sleep(500);
				startDate.clear();
				Thread.sleep(500);
				startDate.click();
				Thread.sleep(500);
				startDate.sendKeys(finalStrtDateTime);
				Thread.sleep(500);
				startDate.sendKeys(Keys.TAB);
				Thread.sleep(500);
				/*
				 * int endDateHour = hour + 1; String finalEndDateTime = date + endDateHour +
				 * ":" + res + afterMinutes; if (endDateHour >= 12) { // String
				 * finalEndDateTime=date+endDateHour+":"+res+afterMinutes; endDate.click();
				 * Thread.sleep(500); endDate.clear(); Thread.sleep(500);
				 * endDate.sendKeys(finalEndDateTime); Thread.sleep(500); } else { endDateHour =
				 * 1; finalEndDateTime = date + endDateHour + ":" + res + afterMinutes;
				 * endDate.click(); Thread.sleep(500); endDate.clear(); Thread.sleep(500);
				 * endDate.sendKeys(finalEndDateTime); Thread.sleep(500); }
				 */
				break;
				/*
				 * //Date dNow = new Date(System.currentTimeMillis()+5*60*1000); //String
				 * patternEnd = "MM/dd/yyyy hh:mm aa"; //SimpleDateFormat simpleDateFormatEnd =
				 * new SimpleDateFormat(patternEnd); //String endDate =
				 * simpleDateFormatEnd.format(new Date()); //endDate=endDate.replace(,
				 * replacement) //String end=simpleDateFormatEnd.format(dNow);
				 * //System.out.println(end); //endDate.click(); //Thread.sleep(1000);
				 * //endDate.clear(); //Thread.sleep(1000); //endDate.sendKeys(end);
				 * //Thread.sleep(1000);
				 */
			}
		}
	}

	public void recurrenceEndAfterByDaily()
			throws InterruptedException, InvalidFormatException, IOException, ParseException {
		recurrenceChkBox.click();
		Thread.sleep(1000);

		utility.CommonMethods drpDownApp = new utility.CommonMethods();
		// DateFormat ss=new SimpleDateFormat("dd/mm/yyyy");
		// Date date = new Date();
		// String endBy =ss.format(date);
		String endBy = drpDownApp.getExcelData(filePath, fileName).get("EndBy");
		String endAfter = drpDownApp.getExcelData(filePath, fileName).get("EndAfter");
		String numWithoutDecimal = String.valueOf(endAfter).split("\\.")[0];
		// Date dateEnd=ss.parse(endDateFormat);
		Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher hasSpecial = special.matcher(endBy);
		boolean b = hasSpecial.find();
		if (b) {
			// input[@ng-model='calendarModelObject.EventRecurring.endBy' and @ng-value='3']
			endByRdoBtn.click();
			Thread.sleep(500);
			endByTxtBox.clear();
			Thread.sleep(500);
			endByTxtBox.sendKeys(endBy);
			Thread.sleep(800);
		} else {
			endAfterRdoBtn.click();
			Thread.sleep(500);
			endAfterTxtBoxClear.clear();
			Thread.sleep(500);
			endAfterTxtBox.sendKeys(numWithoutDecimal);
			Thread.sleep(500);
		}

	}

	public void saveAndReturnToAccount() throws InterruptedException {
		saveRtnAcc.click();
		Thread.sleep(3000);
		try {
			popupContinue.click();
		} catch (Exception e) {

		}
		load.pageWait(pageLoad);
	}

	public void saveAndReturnToAppointment() throws InterruptedException {
		saveRtnAcc.click();
		Thread.sleep(3000);
		try {
			popupContinue.click();
		} catch (Exception e) {

		}
		load.pageWait(saveAndLandOnApp);
	}

	public void saveAppMoveToCalenderPage() throws InterruptedException {
		appSave.click();
		Thread.sleep(3000);
		try {
			popupContinue.click();
		} catch (Exception e) {

		}
		load.pageWait(cancelBtn);
		cancelBtn.click();
		load.pageWait(afterSaveLoad);
	}

	public void saveAppMoveToCalenderPageWithoutCancelBut() throws InterruptedException {
		appSave.click();
		Thread.sleep(4000);
		try {
			popupContinue.click();
		} catch (Exception e) {

		}
		load.elementToBeClickable(weeklyAppointmentPageWait);
		Thread.sleep(5000);
		Coordinates coordinate = ((Locatable) weeklyAppointmentPageWait).getCoordinates();
		coordinate.inViewPort();

	}

	public void selectAllDayEvent() throws InterruptedException {
		// Thread.sleep(2000);
		load.scrollDown(allDayEventChkBox);
		allDayEventChkBox.click();
		Thread.sleep(500);
	}

	public void saveAllDayApp() throws InterruptedException {
		appSave.click();
		load.pageWait(afterSaveLoad);
	}

	public void editMactchedAppointment() throws InterruptedException {
		driver.navigate().refresh();
		load.elementToBeClickable(pageWaitForEditApp);
		Thread.sleep(8000);
		int sizeOfAppointmentDiv = divAppointment.size();
		System.out.println(sizeOfAppointmentDiv);
		for (int divAppCount = 2; divAppCount < sizeOfAppointmentDiv; divAppCount++) {
			WebElement div = driver.findElement(
					By.xpath("//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[" + divAppCount + "]/div[1]"));
			String txtOfDiv = div.getText();
			String[] lines = txtOfDiv.split("\\r?\\n");
			String firstLineTxt = lines[0].toString().trim();
			String actualAppTxt = ConvertLeadPage.accountName;
			Coordinates coordinate = ((Locatable) div).getCoordinates();
			coordinate.inViewPort();
			Thread.sleep(1000);
			load.scrollBy();
			Thread.sleep(1000);
			if (firstLineTxt.equals(actualAppTxt)) {
				div.click();
				load.elementToBeClickable(editAppointment);
				editAppointment.click();
				load.elementToBeClickable(appSave);
				break;
			}
		}

	}

	public void confirmNumOfRecurrenceApp() throws InterruptedException {
		driver.navigate().refresh();
		load.elementToBeClickable(pageWaitForEditApp);
		Thread.sleep(5000);
		int appoinmentCount = 0;
		int divApp = 2;
		WebElement div = null;
		for (int dayCount = 1; dayCount <= 6; dayCount++) {
			int sizeOfAppointmentDiv = divAppointment.size();
			for (int divAppCount = divApp; divAppCount < sizeOfAppointmentDiv; divAppCount++) {
				div = driver.findElement(
						By.xpath("//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[" + divAppCount + "]/div[1]"));
				String txtOfDiv = div.getText();
				String[] lines = txtOfDiv.split("\\r?\\n");
				String firstLineTxt = lines[0].toString().trim();
				String actualAppTxt = ConvertLeadPage.accountName;
				load.scrollView(div);
				Coordinates coordinate = ((Locatable) div).getCoordinates();
				coordinate.inViewPort();
				load.scrollBy();
				if (firstLineTxt.equals(actualAppTxt)) {
					appoinmentCount++;
					break;
				}
			}
			if (appoinmentCount >= 6) {
				break;
			}
			Coordinates coordinateNext = ((Locatable) nextDayScroll).getCoordinates();
			coordinateNext.inViewPort();
			Thread.sleep(1000);
			// System.out.println("Check the scroll");
			load.scrollTopOfPage();
			Thread.sleep(1000);
			nextDay.click();
			// Thread.sleep(1000);
			divApp = 1;
			div = driver.findElement(
					By.xpath("//div[@id='scheduler']/table/tbody/tr[2]/td[2]/div/div[" + divApp + "]/div[1]"));
			load.elementToBeClickable(div);
			Thread.holdsLock(5000);
			beforeNextDayClick.click();
			Thread.sleep(1000);
		}
	}

	public String openAndAddNewCalendar() throws InterruptedException, InvalidFormatException, IOException {
		addCalendarIcon.click();
		load.elementToBeClickable(saveCalendarBtn);
		inputTitle = load.getExcelData(filePath, fileName).get("TitleOfAddCalendar");
		titleTxtBox.sendKeys(inputTitle);
		Thread.sleep(1000);
		saveCalendarBtn.click();
		load.elementToBeClickable(manageCalendarIcon);
		return inputTitle;
	}

	public void openManageCalendar() throws InterruptedException {
		manageCalendarIcon.click();
		load.elementToBeClickable(mgeCalSaveBtn);
		int row = 1;
		for (WebElement singleTitle : titleManageCal) {
			String titleTxt = singleTitle.getAttribute("value");
			String inputTit = AppointmentPage.inputTitle;
			if (inputTit.equalsIgnoreCase(titleTxt)) {
				// singleTitle.click();
				WebElement deleteTitle = driver.findElement(By.xpath("//tr[" + row + "]/td[1]/button"));
				deleteTitle.click();
				load.elementToBeClickable(manageCalendarIcon);
				break;
			}
			row++;
		}
	}

	public void openTaskPage() throws InterruptedException {
		addTaskIcon.click();
		load.elementToBeClickable(opp);
	}

	public void fillTaskFields() throws InterruptedException {
		opp.click();
		Thread.sleep(2000);
		appSave.click();
		load.elementToBeClickable(addTaskIcon);
	}
}
