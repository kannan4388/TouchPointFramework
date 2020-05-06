package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.CreateSalesOrderPage;

public class CreateSalesOrderStepDef extends ExtentReportListener {

	private WebDriver driver;
	CreateSalesOrderPage salesOrder;

	@Given("^lands on create Sales Order page$")
	public void lands_on_create_sales_order_page() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " lands on create Sales Order page");// creating node for extent report
			salesOrder = new CreateSalesOrderPage();
			salesOrder.openCreateSalesOrder();
			logInfo.pass("Browser landed on  Create Sales Order page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^user convert Sales Order into Mpo Order$")
	public void user_convert_sales_order_into_mpo_order() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), " user convert Sales Order into Mpo Order");// creating node for extent report
			salesOrder = new CreateSalesOrderPage();
			salesOrder.saveSalesOrder();
			logInfo.pass("Sales Order converted into Mpo Order successfully");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
