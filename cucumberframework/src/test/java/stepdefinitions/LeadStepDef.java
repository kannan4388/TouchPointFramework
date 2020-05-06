package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.LeadPage;

public class LeadStepDef extends ExtentReportListener {
	private WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "LeadPage.xlsx";
	LeadPage objLead;

	@Given("^user lands on Lead page$")
	public void user_lands_on_Lead_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "user lands on Lead page");// creating node for extent report
			objLead = new LeadPage();
			objLead.openLead();
			logInfo.pass("Web Page moved to Lead Page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Given("^user enters all mandatory fields data$")
	public void user_enters_all_mandatory_fields_data() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), 
					"user enters all mandatory fields data");// creating node for extent report
			objLead = new LeadPage();
			objLead.enterMandatoryFields(filePath, fileName);
			logInfo.pass("Filled All Mandatory fields value");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user saves the lead page$")
	public void user_saves_the_lead_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "user saves the lead page");// creating node for extent report
			objLead.save();
			logInfo.pass("Lead Page Saved Successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
