package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.QualifyLeadPage;

public class QualifyLeadStepDef extends ExtentReportListener {

	private WebDriver driver;
	QualifyLeadPage objLP;

	@Given("^user lands on qualify page$")
	public void user_lands_on_qualify_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "user lands on qualify page");// creating node for extent report
			objLP = new QualifyLeadPage();
			objLP.openQualify();
			logInfo.pass("Web page landed on Qualify page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user edits the qualify page$")
	public void user_edits_the_qualify_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "user edits the qualify page");// creating node for extent report
			objLP.editQualify();
			logInfo.pass("Web page lands on Edit Qualify page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user enters data to some fields$")
	public void user_enters_data_to_some_fields() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "user enters data to some fields");// creating node for extent report
			objLP.fillDataInQualifyPage();
			logInfo.pass("Filled data upto Budget field");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^user saves the qualify page$")
	public void user_saves_the_qualify_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "user saves the qualify page");// creating node for extent report
			objLP.saveQualify();
			logInfo.pass("Qualify page saved Successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
