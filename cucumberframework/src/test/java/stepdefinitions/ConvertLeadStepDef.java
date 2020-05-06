package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.ConvertLeadPage;

public class ConvertLeadStepDef extends ExtentReportListener{
	
	private WebDriver driver;
	ConvertLeadPage convertLead;

	@Given("^user fills data$")
	public void user_fills_data() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " user fills Convert Lead page data");// creating node for extent report
			convertLead = new ConvertLeadPage();
			convertLead.fillData();
			logInfo.pass("Data filled in Convert Lead page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user convert it as Lead$")
	public void user_convert_it_as_lead() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user convert it as Account");// creating node for extent report
			convertLead.saveConvertLead();
			convertLead.getAccountName();
			logInfo.pass("Lead successfully converted into Account");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

}