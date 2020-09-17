package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.QuotesPage;

public class QuotesStepDef extends ExtentReportListener {

	private WebDriver driver;
	QuotesPage savingQuote = new QuotesPage();

	@Given("^user lands on Quotes page$")
	public void user_lands_on_Quotes_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " user lands on Quotes page");// creating node for
																									// extent report
			savingQuote.openQuotes();
			logInfo.pass("Browser navigated to Quote page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user save Quote page$")
	public void user_save_Quote_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user save Quote page");// creating node for extent
																							// report
			savingQuote.configureCoreProduct();
			savingQuote.returnToQuote();
			logInfo.pass("Quote page saved successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Given("^user edit the quotes$")
	public void user_edit_the_quotes() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "user edit the quotes");// creating node for extent
																							// report
			savingQuote.editQuote();
			logInfo.pass("Edit Quote page displayed");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user change the quote status as Accepted$")
	public void user_change_the_quote_status_as_accepted() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "user change the quote status as Accepted");// creating
																												// node
																												// for
																												// extent
																												// report
			savingQuote.updateQuoteStatus();
			logInfo.pass("Updated Quote Status as Accepted");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^finally user save the quote page$")
	public void finally_user_save_the_quote_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "user update the quote page");// creating node for
																								// extent report
			savingQuote.saveQuote();
			logInfo.pass("Quote has been updated successfully...");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user verifies the discount table$")
	public void user_verifies_the_discount_table() throws Throwable {
		savingQuote.discountSetUp();
	}
}
