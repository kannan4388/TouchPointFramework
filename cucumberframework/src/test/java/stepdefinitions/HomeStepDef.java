package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pageObjects.HomePage;

public class HomeStepDef extends ExtentReportListener {

	private WebDriver driver;
	HomePage home = new HomePage();

	@Then("^lands on Home page$")
	public void lands_on_home_page() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "lands on Home page");// creating node for extent
																						// report
			home.landOnHomePage();
			logInfo.pass("Web page landed on Home page");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}

	}

	@And("^verify created account in Home page$")
	public void verify_created_account_in_home_page() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "verify created account in Home page");// creating node
																										// for extent
																										// report
			home.verifyAndEditCreatedAccount();
			home.editAppSave();
			logInfo.pass("Edit and Updated the appointment successfully");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^Verify the created task present in Home Page$")
	public void verify_the_created_task_present_in_home_page() throws Throwable {
		// home.landOnHomePage();
		home.verifyAndEditCreatedTask();
	}
}
