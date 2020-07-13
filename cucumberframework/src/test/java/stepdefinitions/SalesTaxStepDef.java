package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import pageObjects.ReportsPage;
import pageObjects.SalesTaxReportPage;

public class SalesTaxStepDef extends ExtentReportListener {
	private WebDriver driver;
	ReportsPage openReportMenu = new ReportsPage();
	SalesTaxReportPage salesTax = new SalesTaxReportPage();

	@And("^user navigates to Reports and loads data for Sales Tax report$")
	public void user_navigates_to_reports_and_loads_data_for_sales_tax_report() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"),
					"user navigates to Reports and loads data for Sales Tax report");// creating node
			// for extent
			// report
			openReportMenu.openReport();
			salesTax.openSaleTax();
			salesTax.loadData();
			salesTax.sortByZipCode();
			salesTax.filterByZipCode();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();

		}
	}
}
