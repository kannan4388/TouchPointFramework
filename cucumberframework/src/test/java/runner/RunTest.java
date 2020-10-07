package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

/**
 * @author Kannan
 *
 */

// Uncomment @RunWith if you are using Junit to run Test 
// @RunWith(Cucumber.class)

@CucumberOptions(features = { "src//test//java//features" }, glue = { "stepdefinitions", "utility" }, plugin = {
		"pretty", "html:target/cucumber" }, monochrome = true, tags = { "@web" })
//@Test
public class RunTest {
	private TestNGCucumberRunner testNGCucumberRunner;
	public Scenario scenario;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		// String featureName = scenario.getId().split(":")[0];
		// String scenarioName = scenario.getName();
	}

	@Test(dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		// cucumberFeature.getCucumberFeature();
		// String featureName = scenario.getId().split(":")[0];
		// String scenarioName = scenario.getName();
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

}
