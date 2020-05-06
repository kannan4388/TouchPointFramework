package stepdefinitions;

import cucumber.api.java.en.Given;
import pageObjects.SalesPage;

public class SalesStepDef {
	@Given("^user clicks the sale icon$")
    public void user_clicks_the_sale_icon() throws Throwable {
        //throw new PendingException();
		SalesPage objSales=new SalesPage();
		objSales.clickSales();
    }

}
