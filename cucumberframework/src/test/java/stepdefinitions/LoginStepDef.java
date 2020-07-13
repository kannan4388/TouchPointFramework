package stepdefinitions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;

import Listeners.ExtentReportListener;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.LoginPage;

public class LoginStepDef extends ExtentReportListener {
	LoginPage objLogin;
	utility.CommonMethods file;
	static Properties prop;
	private static WebDriver driver;

	// public static String username=null;
	/// public static String password=null;
	public LoginStepDef() throws FileNotFoundException, IOException {
		prop = new Properties();
		FileReader fis = new FileReader(
				new File(".").getCanonicalPath() + "\\src\\test\\java\\config\\config.properties");// getting Properties
																									// file location
		prop.load(fis);// Reading properties file values
	}

	@Given("^Open Chrome browser and url$")
	public void open_chrome_browser_and_url() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			// featureName = Hooks.featureName;
			// scenarioName = Hooks.scenarioName;
			// String scenario=scen.getScenario();
			test = extent.createTest(Feature.class, Hooks.scenarioName);// creating test for
			// extent report
			test = test.createNode(Hooks.featureName);// creating node for extent report
			logInfo = test.createNode(new GherkinKeyword("Given"), "Open Chrome browser and url");// Storing value into
			// Extent report
			// variable
			objLogin = new LoginPage();
			objLogin.launchBrowser();// method for Opening browser with url
			logInfo.pass("Browser opened with URL");// Passing pass log value to extent
			// report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to
			// extent report
			e.printStackTrace();
		}
	}

	@Given("^user enters username and password$")
	public void user_enters_username_and_password() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "user enters username and password");// creating node
																										// for extent
																										// report
			objLogin = new LoginPage();
			objLogin.login();// method for entering username and password

			logInfo.pass("Entered URL with username and password");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}

	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() throws Throwable {

		ExtentTest logInfo = null;// Intializing variable for extent test report log
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "user clicks on login button");// creating node for
																									// extent report
			objLogin = new LoginPage();
			objLogin.clickLoginBtn();// method for the login button
			logInfo.pass("Web page lands on Home Page");// Passing pass log value to extent report
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);// Passing fail log value to extent report
			e.printStackTrace();
		}
	}

	@Then("^close the browser at the end$")
	public void close_the_browser_at_the_end() throws Throwable {
		objLogin = new LoginPage();
		objLogin.closeBrowser();
	}

}
