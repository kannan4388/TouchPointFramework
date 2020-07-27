package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.MeasurementPage;

public class MeasurementStepDef extends ExtentReportListener {

	private WebDriver driver;
	MeasurementPage measure;

	@Given("^user lands on Measurement page$")
	public void user_lands_on_measurement_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " user opens Measurement page");// creating node for
																									// extent report
			measure = new MeasurementPage();
			measure.openMeasure();
			logInfo.pass("Measurement page displayed");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}

	}

	@Then("^user fills the data to measurement$")
	public void user_fills_the_data_to_measurement() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user fills the data to measurement page ");// creating
																												// node
																												// for
																												// extent
																												// report
			measure.fillData();
			logInfo.pass("Measurement page data filled");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to
			// extent report
			e.printStackTrace();
		}
	}

	@Then("^user fill data and upload image in measurement$")
	public void user_fill_data_and_upload_image_in_measurement() throws Throwable {
		measure.fillWithImageUpload();
	}

	@Then("^user saves the measurement page$")
	public void user_saves_the_measurement_page() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user saves the measurement page ");// creating node
																										// for extent
																										// report
			measure.saveMeasurement();
			logInfo.pass("Measurement page saved successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
