package pageObjects;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QualifyLeadPage {

	private WebDriver driver;

	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "QualifyLead.xlsx";

	utility.CommonMethods objCM;

	/* Web Elements in Qualify Lead page */

	@FindBy(css = "i[class='far fa-clipboard-list fa_icon']")
	WebElement clickOnQualify;

	@FindBy(css = "label[class='qualify_label ng-binding']")
	WebElement pageLoad_Qualify;

	@FindBy(css = "i[class='far fa-pen fa-icon']")
	WebElement editQualify;

	@FindBy(css = "input[value='New Home?']")
	WebElement newHome;

	@FindBy(xpath = "//input[@name='rdoReceivedOther Quotes?' and@value='Yes']")
	WebElement receivedOtherQuotes;

	@FindBy(css = "input[class='form-control qualify_text ng-pristine ng-untouched ng-valid ng-empty']")
	WebElement budget;

	@FindBy(css = "i[class='far fa-check-circle fa_icon']")
	WebElement saveIcon;

	@FindBy(css = "i[class='far fa-plus-square fa-icon']")
	WebElement pageLoadWait;

	@FindBy(css = "i[class='fa fa-exchange']")
	WebElement pageLoadConvert;

	/* Intialize of Page Objects */

	public QualifyLeadPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	/* Methods in qualify page */

	// User opens the Qualify page
	public void openQualify() throws InterruptedException {

		clickOnQualify.click();
		objCM = new utility.CommonMethods();
		objCM.pageWait(pageLoad_Qualify);
		// Thread.sleep(3000);
	}

	public void editQualify() throws InterruptedException {
		editQualify.click();
		objCM = new utility.CommonMethods();
		objCM.pageWait(receivedOtherQuotes);
		// Thread.sleep(3000);
		// saveIcon.click();

	}

	public void fillDataInQualifyPage() throws InterruptedException, InvalidFormatException, IOException {
		newHome.click();
		Thread.sleep(500);
		receivedOtherQuotes.click();
		Thread.sleep(500);
		// objCM.getExcelData(filePath, fileName);
		budget.sendKeys(objCM.getExcelData(filePath, fileName).get("Budget"));
		Thread.sleep(500);

	}

	public void saveQualify() throws InterruptedException {
		try {
			saveIcon.click();
			objCM = new utility.CommonMethods();
			objCM.pageWait(pageLoadConvert);
			// Thread.sleep(8000);
		} catch (Exception e) {

		}
	}
}
