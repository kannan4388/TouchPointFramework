package stepdefinitions;

import cucumber.api.java.en.Then;
import pageObjects.ShipmentsPage;

public class ShipmentStepDef {

	ShipmentsPage shipment = new ShipmentsPage();

	@Then("^user lands on Shipments$")
	public void user_lands_on_shipments() throws Throwable {
		shipment.openShipment();
	}
}
