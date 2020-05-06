package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class ITestListenerImpl extends ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static WebDriver driver;

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("FAIL");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIP");
	}

	public void onTestFailedButWithinPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Execution started in TouchPoint environment");
		extent = setUp();

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Execution completed in TouchPoint environment");
		extent.flush();
		System.out.println("Generated Report...");
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// extent = setUp();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("PASS");
	}

	public void onTestWarning(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("WARNING");
	}

	public void onTestFatal(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("FATAL");
	}
}
