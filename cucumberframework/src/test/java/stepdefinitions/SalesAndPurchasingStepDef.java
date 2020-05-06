package stepdefinitions;

import cucumber.api.java.en.And;
import pageObjects.ReportsPage;
import pageObjects.SalesAndPurchasingDetailReportPage;

public class SalesAndPurchasingStepDef {

	ReportsPage openReportMenu = new ReportsPage();
	SalesAndPurchasingDetailReportPage salesDetailReport = new SalesAndPurchasingDetailReportPage();

	@And("^user navigates to Reports and loads data for Sales Detail report$")
	public void user_navigates_to_reports_and_loads_data_for_sales_detail_report() throws Throwable {
		openReportMenu.openReport();
		openReportMenu.individualReportOpen();
		salesDetailReport.loadDataForSalesDetailReport();
		salesDetailReport.getDownloadedDocumentName();
		salesDetailReport.sortByFirstName();
	}
}
