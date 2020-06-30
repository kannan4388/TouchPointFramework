package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pageObjects.ProcurementPage;

public class ProcurementStepDef extends ExtentReportListener {

	private WebDriver driver;
	ProcurementPage proc = new ProcurementPage();

	@Then("^user lands on Procurment dashboard$")
	public void user_lands_on_procurment_dashboard() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "lands on Procurement Dashboard page");// creating
																											// node for
																											// extent
																											// report
			proc.openProcurement();
			logInfo.pass("Web page landed on Procurement Dashboard page");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^Cancel the MPO order$")
	public void cancel_the_mpo_order() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "Whole Cancel MPO order");// creating node for extent
																							// report
			proc.cancelMPOOrder();
			logInfo.pass("Order updated as Cancelled and confirmed status changed to cancel");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^convert cancel mpo order into open order$")
	public void convert_cancel_mpo_order_into_open_order() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "convert cancel mpo order into open order");// creating
																												// node
																												// for
																												// extent
																												// report
			proc.convertIntoOpenOrder();
			logInfo.pass("Converted Cancel order into Open order again successfully");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@And("^Individual cancel Vendor PO$")
	public void individual_cancel_vendor_po() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"), "Individual cancel Vendor PO");// creating node for
			// extent report
			proc.cancelVendorPOOrder();
			logInfo.pass("Able to Cancel Vendor PO by individual line item successfully");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to
			// extent report
			// e.printStackTrace();
		}
	}
}
