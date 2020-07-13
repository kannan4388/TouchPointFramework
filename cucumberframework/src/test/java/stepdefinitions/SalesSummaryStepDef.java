package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import pageObjects.ReportsPage;
import pageObjects.SalesSummaryReportPage;

public class SalesSummaryStepDef extends ExtentReportListener {
	private WebDriver driver;
	ReportsPage openReportMenu = new ReportsPage();
	SalesSummaryReportPage salesSum = new SalesSummaryReportPage();

	@And("^user navigates to Reports and loads data for Sales Summary report$")
	public void user_navigates_to_reports_and_loads_data_for_sales_summary_report() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"),
					"user navigates to Reports and loads data for Sales Summary report");// creating node
			// for extent
			// report
			openReportMenu.openReport();
			salesSum.openSalesSummary();
			salesSum.loadData();
			salesSum.getDownloadedDocumentName();
			salesSum.sortByFirstName();
			salesSum.filterByFirstName();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
