package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage {
	utility.CommonMethods waitForPageLoad = new utility.CommonMethods();
	private WebDriver driver;
	public static LinkedHashMap<String, String> excelData;

	/* Intializing the objects */

	/* Mandatory fields webelements */
	@FindBy(xpath = "//i[@class='far fa-plus-square']")
	WebElement leadClick;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;

	@FindBy(xpath = "//input[@name='zipCode']")
	WebElement zipCode;

	@FindBy(xpath = "//input[@class='frm_controllead1 form-control ng-empty ng-valid-maxlength'][1]")
	WebElement address;

	@FindBy(xpath = "//input[@name='user_email']")
	WebElement email;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	WebElement drpDownSource;

	@FindBy(css = "option[value='63']")
	WebElement drpSelect;

	@FindBy(css = "button[class='btn btn-primary cancel_but']")
	WebElement popUp;

	@FindBy(css = "i[class='far fa-check-circle fa_icon']")
	WebElement saveLead;

	@FindBy(xpath = "//label[@class='col-sm-3 text_detlabel' and text()='Name']")
	WebElement savePageLoad;

	/* Non mandatory web elements */

	public LeadPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	/* Methods in LeadPage */

	/* To open Lead page method */
	public void openLead() throws InterruptedException {
		leadClick.click();
		// objMethods = new utility.CommonMethods();
		waitForPageLoad.pageWait(firstName);
		// Thread.sleep(3000);
	}

	/* Methods to fill data for mandartory fields in Lead page */
	public void enterMandatoryFields(String filePath, String fileName)
			throws InterruptedException, AWTException, IOException, InvalidFormatException {

		// objMethods = new utility.CommonMethods();
		String Address = waitForPageLoad.getExcelData(filePath, fileName).get("Address");
		firstName.sendKeys(waitForPageLoad.getExcelData(filePath, fileName).get("FirstName"));
		Thread.sleep(500);
		String Name = waitForPageLoad.getExcelData(filePath, fileName).get("LastName");
		String pattern = " yyyyMMdd hh:mm aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		// System.out.println(date);
		// DateTime time=DateTime.now();
		// String finalName=Name+String.valueOf(date);
		lastName.sendKeys(Name + date);
		Thread.sleep(500);
		zipCode.sendKeys(waitForPageLoad.getExcelData(filePath, fileName).get("ZipCode"));
		Thread.sleep(500);
		// zipCode.sendKeys(Keys.TAB);
		// Thread.sleep(1000);
		address.click();
		String emailAddress = waitForPageLoad.getExcelData(filePath, fileName).get("Email");
		address.sendKeys(Address);
		Thread.sleep(1000);
		email.click();
		Thread.sleep(8000);
		if (popUp.isDisplayed() == true) {
			popUp.click();
			Thread.sleep(2000);
		}
		email.sendKeys(emailAddress);
		Thread.sleep(500);
		zipCode.click();
		Thread.sleep(6000);
		// driver.switchTo().defaultContent();
		if (popUp.isDisplayed() == true) {
			popUp.click();
			Thread.sleep(2000);
			drpDownSource.click();
			Thread.sleep(2500);
			// String sourceTxt="BBCS - Website";
			// driver.findElement(By.xpath("//*[@id='RelatedSource']/option[@value='62']")).click();
			// Thread.sleep(2000);
			// driver.findElement(By.xpath(xpathExpression))
			// drpDownSource.click();
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
		} else {
			drpDownSource.click();
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

		}

		/*
		 * JavascriptExecutor js=(JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();",ele);
		 */
		Thread.sleep(5000);
	}

	/* Method for saving Lead page */
	public void save() throws InterruptedException {
		saveLead.click();
		Thread.sleep(4500);
		try {
			// driver.switchTo().defaultContent();
			// popUp.click();

			Alert alert = driver.switchTo().alert();
			waitForPageLoad.waitForAlert(driver);
			// alert.accept();
			Thread.sleep(1000);
			alert.accept();
		} catch (Exception e) {

		}
		Thread.sleep(1000);
		// objMethods = new utility.CommonMethods();
		waitForPageLoad.pageWait(savePageLoad);
	}
}
