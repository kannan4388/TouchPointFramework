package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import pageObjects.CreateMpoOrderPage;

public class CreateMpoOrderStepDef extends ExtentReportListener{
	
	private WebDriver driver;
	CreateMpoOrderPage mpoOrder;

	@Given("^user save the MPO order page$")
    public void user_save_the_mpo_order_page() throws Throwable {
		
		ExtentTest logInfo = null;//Intializing variable for extent test report log
		try {
					logInfo = test.createNode(new GherkinKeyword("Given"), " user converting MPO order into Purchase Order");// creating node for extent report
		mpoOrder=new CreateMpoOrderPage();
		mpoOrder.openMpoOrder();
		logInfo.pass("Sucessfully MPO order converted into Purchase Order");//Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);//Passing fail log value to extent report
			e.printStackTrace();
		}
	}
}
