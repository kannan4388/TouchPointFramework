package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AppointmentPage;

public class AppointmentStepDef extends ExtentReportListener {

	private WebDriver driver;
	AppointmentPage createAppointment = new AppointmentPage();

	@Given("^open Add Appointment page$")
	public void open_add_appointment_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " open Add Appointment page");// creating node for
																									// extent report
			createAppointment.appointmentTabClick();
			createAppointment.addAppointmentIconClick();
			createAppointment.openApp();
			logInfo.pass("Web page landed on Appointment page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@When("^user fill data in Appointment page$")
	public void user_fill_data_in_appointment_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), " user fill data in Appointment page");// creating
																											// node for
																											// extent
																											// report
			createAppointment.fillAppData();
			logInfo.pass("Data Filled in Appointment page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent rep e.printStackTrace();
		}
	}

	@Then("^user saves the Appointment$")
	public void user_saves_the_appointment() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user saves the Appointment");// creating node for
																									// extent report
			createAppointment.saveAndReturnToAppointment();
			logInfo.pass("Appointment created successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^Open Add appointment from Home page$")
	public void open_add_appointment_from_home_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " Open Add appointment from Home page");// creating
																											// node for
																											// extent
																											// report
			createAppointment.addAppointmentIconClick();
			createAppointment.openApp();
			logInfo.pass("Add Appointment page opened");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^Fill Recurrence data$")
	public void fill_recurrence_data() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " Fill Recurrence data");// creating
																							// node for
																							// extent
																							// report
			createAppointment.recurrenceEndAfterByDaily();
			logInfo.pass("Recurrence data filled");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^at last save the appointment and land on Calender page$")
	public void at_last_save_the_appointment_and_land_on_calender_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"),
					" save the appointment page and land on Calender page");// creating node for extent report
			createAppointment.saveAppMoveToCalenderPageWithoutCancelBut();
			logInfo.pass("Appointment created with recurrence successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user saves the Appointment and return to Account$")
	public void user_saves_the_appointment_and_return_to_account() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user saves the Appointment and return to Account");// creating
																														// node
																														// for
																														// extent
																														// report
			createAppointment.saveAndReturnToAccount();
			logInfo.pass("Appointment created with successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^Create All Day event$")
	public void create_all_day_event() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " Create All Day event");// creating node for extent
																							// report
			createAppointment.selectAllDayEvent();
			createAppointment.saveAllDayApp();
			logInfo.pass("All Day Event created successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^check the created appointment present in calendar page and edit it$")
	public void check_the_created_appointment_present_in_calendar_page_and_edit_it() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					" check the created appointment present in calendar page and edit it");// creating node for extent
																							// report
			createAppointment.saveAppMoveToCalenderPageWithoutCancelBut();
			createAppointment.editMactchedAppointment();
			logInfo.pass("Appointment exist in Calender page and Updated as Recurrence");// Passing pass log value to
																							// extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^View the Appointment by Next Day and Confirm all recurrence present$")
	public void view_the_appointment_by_Next_Day_and_confirm_all_recurrence_present() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					" View the Appointment by Next Day and Confirm all recurrence present");// creating node for extent
			// report
			createAppointment.saveAppMoveToCalenderPageWithoutCancelBut();
			createAppointment.confirmNumOfRecurrenceApp();
			logInfo.pass("Successfully created Recurrence Appointment for one week");// Passing pass log value to extent
																						// report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^Create new Add Calendar$")
	public void create_new_add_calendar() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Create new Add Calendar");// creating node for extent
			// report
			createAppointment.appointmentTabClick();
			createAppointment.addAppointmentIconClick();
			createAppointment.openAndAddNewCalendar();
			logInfo.pass("Add Calendar created Successfully");// Passing pass log value to extent
			// report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^Then verify the added new calendar$")
	public void Then_verify_the_added_new_calendar() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "then Verify the added new calendar");// creating node
																										// for extent
																										// report
			createAppointment.openManageCalendar();
			logInfo.pass("Verified presence of created Calendar and Removed the same in Manage Calendar");// Passing
																											// pass log
																											// value to
																											// extent
			// report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^Create new Task$")
	public void create_new_task() throws Throwable {
		createAppointment.appointmentTabClick();
		createAppointment.openTaskPage();
		createAppointment.fillTaskFields();

	}
}
