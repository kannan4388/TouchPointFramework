package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private static WebDriver driver;
	private static String existingAccount;
	private static int pageNum = 3;

	@FindBy(xpath = "//li[@ng-class='NavbarService.HomeSelected']")
	WebElement homeIcon;

	@FindBy(xpath = "//div[@class='hfc-text-block']")
	WebElement homePageLoad;

	@FindAll(@FindBy(xpath = "//*[@id='MyAppointmentsGrid']/div[2]/table/tbody/tr/td[1]"))
	List<WebElement> todaysAppointmentSubjectCol;

	@FindBy(xpath = "//button[@ng-click='saveCalendarData();']")
	WebElement appSave;

	@FindBy(css = "#emailContinue")
	WebElement continueEmailYes;

	@FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[1]")
	WebElement waitAfterEditAppointmentSave;

	@FindBy(xpath = "//button[text()='Delete Appt']")
	WebElement waitForPageLoadAppointment;

	@FindBy(xpath = "//ul[@class='k-pager-numbers k-reset'][1]/li[3]")
	WebElement paginationApp;

	@FindAll(@FindBy(xpath = "//div[@id='MyTasksGrid']/div/table/tbody/tr"))
	List<WebElement> todayTaskRows;

	@FindBy(xpath = "//select[@ng-model='calendarModelObject.ReminderMinute']")
	WebElement reminderDrpDown;

	@FindAll(@FindBy(xpath = "//*[@id='MyAppointmentsGrid']/div[2]/table/tbody/tr"))
	List<WebElement> trRowsInHomeGrid;

	public HomePage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	utility.CommonMethods waitForPageLoad = new utility.CommonMethods();

	public void landOnHomePage() throws InterruptedException {
		homeIcon.click();
		waitForPageLoad.pageWait(waitAfterEditAppointmentSave);
	}

	public void verifyAndEditCreatedAccount() throws InterruptedException {
		existingAccount = ConvertLeadPage.accountName;
		// int sizeOfSubject=todaysAppointmentSubjectCol.size();
		for (int rowCount = 1; rowCount <= 26; rowCount++) {
			if (rowCount >= 26) {
				driver.findElement(By.xpath("//ul[@class='k-pager-numbers k-reset'][1]/li[" + pageNum + "]")).click();
				// verifyAndEditCreatedAccount();
				Thread.sleep(3000);
				pageNum++;
				rowCount = 1;
			}
			int sizeOfRows = trRowsInHomeGrid.size();
			if (rowCount <= sizeOfRows) {
				WebElement buttonXpath = driver.findElement(By.xpath("//*[@id='MyAppointmentsGrid']/div[2]/"
						+ "table/tbody/tr[" + rowCount + "]/td[7]/ul/li/div/button"));
				WebElement editAppointmentClick = driver.findElement(By.xpath("//*[@id='MyAppointmentsGrid']/div[2]/"
						+ "table/tbody/tr[" + rowCount + "]/td[7]/ul/li/div/ul/li[1]"));

				WebElement actualRowTxt = driver.findElement(
						By.xpath("//div[@id='MyAppointmentsGrid']/div[2]/table/tbody/tr[" + rowCount + "]/td[1]"));
				String rowSubjectTxt = actualRowTxt.getText();
				// rowCount++;
				// waitForPageLoad.scrollBy();;
				if (rowSubjectTxt.equals(existingAccount)) {
					System.out.println("Created Account displayed in Home Page");
					buttonXpath.click();
					Thread.sleep(800);
					editAppointmentClick.click();
					waitForPageLoad.pageWait(waitForPageLoadAppointment);
					Thread.sleep(2000);
					break;
				}
			}
			if (rowCount > sizeOfRows) {
				break;
			}
		}
	}

	public void editAppSave() throws InterruptedException {
		AppointmentPage saveApp = new AppointmentPage();
		saveApp.selectAllDayEvent();
		appSave.click();
		waitForPageLoad.pageWait(continueEmailYes);
		Thread.holdsLock(1500);
		continueEmailYes.click();
		waitForPageLoad.pageWait(waitAfterEditAppointmentSave);
	}

	public void verifyAndEditCreatedTask() throws InterruptedException {
		int taskRowIncrement = 1;
		int pageNavigation = 3;
		for (WebElement taskRow : todayTaskRows) {
			if (taskRowIncrement >= 26) {
				WebElement taskTablePagination = driver
						.findElement(By.xpath("//*[@id='MyTasksGrid']/div[3]/ul/li[" + pageNavigation + "]"));
				taskTablePagination.click();
				waitForPageLoad.elementToBeClickable(homePageLoad);
				pageNavigation++;
			}
			String taskSubjectCol = driver
					.findElement(
							By.xpath("//div[@id='MyTasksGrid']/div/table/tbody/tr[" + taskRowIncrement + "]/td[1]"))
					.getText().trim();
			// String taskRowTxt=taskRow.getText().trim();
			String actualTaskTxt = ConvertLeadPage.accountName;
			if (taskSubjectCol.equalsIgnoreCase(actualTaskTxt)) {
				WebElement taskMatchBtn = driver.findElement(By.xpath("//div[@id='MyTasksGrid']/div/table/tbody/tr["
						+ taskRowIncrement + "]/td[6]/ul/li/div/button"));
				Coordinates coordinate = ((Locatable) taskMatchBtn).getCoordinates();
				coordinate.inViewPort();
				waitForPageLoad.scrollBy120();
				taskMatchBtn.click();
				WebElement editTask = driver.findElement(By.xpath("//div[@id='MyTasksGrid']/div/table/tbody/tr["
						+ taskRowIncrement + "]/td[6]/ul/li/div/ul/li[1]"));
				waitForPageLoad.elementToBeClickable(editTask);
				Thread.sleep(1000);
				editTask.click();
				waitForPageLoad.elementToBeClickable(appSave);
				waitForPageLoad.dropDownSelectByIndex(reminderDrpDown);
				appSave.click();
				waitForPageLoad.elementToBeClickable(homePageLoad);
				break;
			}
			taskRowIncrement++;
		}
	}
}
