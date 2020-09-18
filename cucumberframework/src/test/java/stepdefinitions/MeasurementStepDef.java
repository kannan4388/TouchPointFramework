package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.MeasurementPage;

public class MeasurementStepDef extends ExtentReportListener {
	
	
	private WebDriver driver;
	MeasurementPage measure;

	/* Coded by UmaVasan on 07/08/20 */
	
	@Then("^user removes filled rows in the measurement page$")
	public void user_removes_filled_rows_in_the_measurement_page() throws Throwable {
		
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user removes filled rows in the measurement page ");
			measure.removeAllFilledRows();
			logInfo.pass("All rows removed successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
			}
	
	@Then("^user adds new row in the measurement page$")
	public void user_adds_new_row_in_the_measurement_page()
			throws Throwable {
		ExtentTest logInfo = null;
		
//		try {
//			logInfo = test.createNode(new GherkinKeyword("Then"), " user adds new row in the measurement page ");												
			
			measure.addNewRow();
//			logInfo.pass("New row added successfully");// Passing pass log value to extent report
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
//			e.printStackTrace();
//		}
			}
			
	
	//@DataProvider
//	@Then("^user adds new row in the measurement page$-> {String}{String}{String} {String}{String} {String}{String}{String} "
//			+ "{String}{String} {String}{String} {String}")
//	
//	public void user_adds_new_row_in_the_measurement_page(String Room,String  WindowLocation,String MountType ,
//			String Width1,String Height1,String ImageName,String System,String SystemDescription,String Type,String Description, 
//			String Height2,String Width2,String Depth) throws Throwable {
//		ExtentTest logInfo = null;
//		
//		try {
//			logInfo = test.createNode(new GherkinKeyword("Then"), " user adds new row in the measurement page ");												
//			
//			measure.addNewRow( Room,  WindowLocation, MountType , Width1, Height1, ImageName, System,SystemDescription,Type,Description,Height2,Width2,Depth);
//			logInfo.pass("New row added successfully");// Passing pass log value to extent report
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
//			e.printStackTrace();
//		}
//			}
	
	@And("^user validates row count in the measurement page$")
	public void user_validates_row_count_in_the_measurement_page() throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), " user validates row count in the measurement page ");												
			measure.validateRowCount();
			logInfo.pass("Row count of measurement page validated successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
			}
	@Then("^user copies rows in the measurement page$")
	public void user_copies_rows_in_the_measurement_page() throws Throwable {
		ExtentTest logInfo = null;
//		try {
//			logInfo = test.createNode(new GherkinKeyword("Then"), " user copies rows in the measurement page ");
			measure.copyLine();
//			logInfo.pass("All rows removed successfully");// Passing pass log value to extent report
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
//			e.printStackTrace();
//		}
	}
	@Then("^user fills row data in the measurement page$")
	public void user_fills_row_data_in_the_measurement_page() throws Throwable {
		ExtentTest logInfo = null;
//		try {
//			logInfo = test.createNode(new GherkinKeyword("Then"), " user fills row data in the measurement page ");
			measure.fillRowDataWithImageUpload();
			//logInfo.pass("All rows filled successfully");// Passing pass log value to extent report
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
//			e.printStackTrace();
//		}
	}

	
	/* Coded by UmaVasan on 07/08/20 */
	
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
																																																	// report
			measure.saveMeasurement();
			logInfo.pass("Measurement page saved successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
