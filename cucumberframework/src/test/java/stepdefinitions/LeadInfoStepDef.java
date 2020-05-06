package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import pageObjects.LeadInfoPage;

public class LeadInfoStepDef extends ExtentReportListener {

	private WebDriver driver;
	LeadInfoPage objInfo;

	@Given("^user lands on convert lead page$")
	public void user_lands_on_convert_lead_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " user lands on convert Account page");// creating node for extent report
			objInfo = new LeadInfoPage();
			objInfo.convertLead();
			logInfo.pass("Web page moved to Convert Account page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

}
