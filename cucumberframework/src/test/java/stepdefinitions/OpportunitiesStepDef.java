package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.OpportunitiesPage;

public class OpportunitiesStepDef extends ExtentReportListener {

	private WebDriver driver;
	OpportunitiesPage opp;

	@Given("^user opens Opportunity page$")
	public void user_opens_opportunity_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " user opens Opportunity page");// creating node for extent report
			opp = new OpportunitiesPage();
			opp.openOpp();
			logInfo.pass("Opportunity page opened");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@When("^user fills the data$")
	public void user_fills_the_data() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), " user fills Opportunity page data");// creating node for extent report
			opp = new OpportunitiesPage();
			opp.createNewOpp();
			opp.fillMandatoryFields();
			logInfo.pass("Mandatory fields data filled in Opportunity page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user saves the screen$")
	public void user_saves_the_screen() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user saves the Opportunity screen");// creating node for extent report
			opp = new OpportunitiesPage();
			opp.saveOpp();
			logInfo.pass("Opportunity page saved successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
