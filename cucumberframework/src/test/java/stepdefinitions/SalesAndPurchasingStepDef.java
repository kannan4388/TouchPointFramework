package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.And;
import pageObjects.ReportsPage;
import pageObjects.SalesAndPurchasingDetailReportPage;

public class SalesAndPurchasingStepDef extends ExtentReportListener {
	private WebDriver driver;
	ReportsPage openReportMenu = new ReportsPage();
	SalesAndPurchasingDetailReportPage salesDetailReport = new SalesAndPurchasingDetailReportPage();

	@And("^user navigates to Reports and loads data for Sales Detail report$")
	public void user_navigates_to_reports_and_loads_data_for_sales_detail_report() throws Throwable {
		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("And"),
					"user navigates to Reports and loads data for Sales Detail report");// creating node
			// for extent
			// report
			openReportMenu.openReport();
			openReportMenu.individualReportOpen();
			salesDetailReport.loadDataForSalesDetailReport();
			salesDetailReport.getDownloadedDocumentName();
			salesDetailReport.sortByFirstName();
			salesDetailReport.filterByFirstName();
			logInfo.pass("Load data,Download excel,Sorting,Filter and Pagination works fine");// Passing pass log value
																								// to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
