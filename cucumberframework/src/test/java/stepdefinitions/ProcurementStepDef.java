package stepdefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import pageObjects.ProcurementPage;

public class ProcurementStepDef {

	private WebDriver driver;
	ProcurementPage proc = new ProcurementPage();

	@Then("^user lands on Procurment dashboard$")
	public void user_lands_on_procurment_dashboard() throws Throwable {
		proc.openProcurement();
	}

}
