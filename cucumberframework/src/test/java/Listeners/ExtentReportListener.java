package Listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageObjects.LoginPage;

public class ExtentReportListener {

	public static ExtentHtmlReporter report;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static WebDriver driver;

	public static ExtentReports setUp() {

		try {
			String reportLocation = System.getProperty("user.dir") + "\\Reports\\ExtentReport.html";
			report = new ExtentHtmlReporter(reportLocation);
			report.config().setDocumentTitle("Automation Test Report");
			report.config().setReportName("Smoke Test");
			report.config().setTheme(Theme.STANDARD);
			System.out.println("Extent Report location Intialized...");
			report.start();
			extent = new ExtentReports();
			extent.attachReporter(report);
			extent.setSystemInfo("Application", "HFC");
			extent.setSystemInfo("Operatimng System", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			System.out.println("System Info. set in Extent Report");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extent;
	}

	public static void testStepHandle(String testStatus, WebDriver driver, ExtentTest extentTest, Throwable throwable) {
		driver = LoginPage.getDriver();
		switch (testStatus) {
		case "FAIL":
			extentTest.fail(MarkupHelper.createLabel("Test Case is Failed: ", ExtentColor.RED));
			extentTest.error(throwable.fillInStackTrace());
			try {
				extentTest.addScreenCaptureFromPath(captureScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (driver != null) {
				driver.quit();
			}
			break;

		case "PASS":
			extentTest.pass(MarkupHelper.createLabel("Test Case is Passed: ", ExtentColor.GREEN));
			driver = LoginPage.getDriver();
			driver.quit();
			break;

		case "SKIP":
			extentTest.skip(MarkupHelper.createLabel("Test Case is Skipped: ", ExtentColor.BLUE));
			break;

		case "FATAL":
			extentTest.fatal(MarkupHelper.createLabel("Test Case is Fatal: ", ExtentColor.BROWN));
			break;

		case "WARNING":
			extentTest.fatal(MarkupHelper.createLabel("Test Case is Warning: ", ExtentColor.ORANGE));

		default:
			break;
		}
	}

	public static String captureScreenShot(WebDriver driver) throws IOException {
		// driver = LoginPage.getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// String screenShotName=scenario.getName().replaceAll(" ", "_");
		String dest = System.getProperty("user.dir") + "\\ScreenShot\\" + getCurrentDateAndTime() + ".png";
		FileUtils.copyFile(scrFile, new File(dest));
		return dest;
	}

	public static String getCurrentDateAndTime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd hh:mm a");
			Date date = new Date();
			str = dateFormat.format(date);
			// str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
			str = str.replaceAll("/", "").replaceAll(":", "").replace(" ", "_");
			// String date=DateTime.
		} catch (Exception e) {

		}
		return str;
	}
}
