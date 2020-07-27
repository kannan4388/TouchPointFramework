package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import pageObjects.ReportsPage;
import pageObjects.SalesTaxSummaryReportPage;

public class SalesTaxSummaryStepDef extends ExtentReportListener {
	private WebDriver driver;
	ReportsPage openReportMenu = new ReportsPage();
	SalesTaxSummaryReportPage sTaxSummary = new SalesTaxSummaryReportPage();

	@And("^user navigates to Reports and loads data for Sales Tax Summary report$")
	public void user_navigates_to_reports_and_loads_data_for_sales_tax_summary_report() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"),
					"user navigates to Reports and loads data for Sales Tax Summary report");
			openReportMenu.openReport();
			sTaxSummary.openSalesTaxSummary();
			sTaxSummary.loadData();
			sTaxSummary.downloadFile();
			sTaxSummary.sortByZipCode();
			sTaxSummary.filterByZipCode();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
