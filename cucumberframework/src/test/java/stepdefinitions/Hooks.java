package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Hooks {
	public static String featureName = "";
	public static String scenarioName = "";

	@Before
	public static void beforeScenario(Scenario scenario) {
		featureName = scenario.getId().split(";")[0];
		scenarioName = scenario.getName();
		System.out.println("Feature Name --> " + featureName);
		System.out.println("Scenario Name --> " + scenario.getName());
	}
}
