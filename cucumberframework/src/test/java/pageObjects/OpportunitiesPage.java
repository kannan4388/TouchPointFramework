package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {

	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "Opportunity.xlsx";
	public static String date = "";
	private WebDriver driver;
	utility.CommonMethods pageLoad = new utility.CommonMethods();

	@FindBy(css = "i[class='far fa-address-card fa_icon']")
	WebElement oppICon;

	@FindBy(css = "span[class='k-pager-info k-label']")
	WebElement oppPageLoad;

	@FindBy(css = "i[class='far fa-plus-square']")
	WebElement newOppIcon;

	@FindBy(css = "[name='OpportunityName']")
	WebElement oppNameTxtBox;

	@FindBy(xpath = "//select[@name='opportunity_Status'][1]")
	WebElement oppStatusDrpDown;

	@FindAll(@FindBy(xpath = "//select[@name='opportunity_Status'][1]/option"))
	List<WebElement> oppStatusOption;

	@FindBy(xpath = "//span[@aria-owns='opportunity_saleagent_listbox']")
	WebElement salesAgentDrpDown;

	@FindAll(@FindBy(xpath = "//ul[@id='opportunity_saleagent_listbox']/li"))
	List<WebElement> salesAgentOption;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap'][1]")
	WebElement sourceDrpDown;

	@FindAll(@FindBy(xpath = "//select[@id='RelatedSource']/option"))
	List<WebElement> sourceOption;

	@FindBy(xpath = "//button[@class='tooltip-bottom hfc_bgbut' and @data-tooltip='Save']")
	WebElement saveIcon;

	@FindBy(xpath = "//li[@rel='tab7' and @ng-show='BrandId!=3']")
	WebElement savePageLoad;

	public OpportunitiesPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openOpp() throws InterruptedException {
		oppICon.click();
		pageLoad.pageWait(oppPageLoad);
	}

	public void createNewOpp() throws InterruptedException {
		newOppIcon.click();
		pageLoad.pageWait(oppNameTxtBox);
	}

	public void fillMandatoryFields() throws InterruptedException, InvalidFormatException, IOException, AWTException {
		pageLoad = new utility.CommonMethods();
		String oppName = pageLoad.getExcelData(filePath, fileName).get("OpportunityName");
		String mergeName = pageLoad.dateFormat(date);
		String oppFinal = oppName + mergeName;
		oppNameTxtBox.sendKeys(oppFinal);
		Thread.sleep(500);
		// oppNameTxtBox.sendKeys(Keys.TAB);
		Actions mouse = new Actions(driver);
		mouse.moveToElement(oppNameTxtBox).build().perform();
		Thread.sleep(1000);
		oppStatusDrpDown.click();
		// JavascriptExecutor executor = (JavascriptExecutor)driver;
		// executor.executeScript("arguments[0].click();", oppStatusDrpDown);
		Thread.sleep(500);
		int statusSize = oppStatusOption.size();
		for (int i = 1; i <= statusSize; i++) {
			String statusText = driver
					.findElement(By.xpath("//select[@name='opportunity_Status'][1]/option[" + i + "]")).getText();
			if (statusText.equals("New")) {
				driver.findElement(By.xpath("//select[@name='opportunity_Status'][1]/option[" + i + "]")).click();
				Thread.sleep(500);
				salesAgentDrpDown.click();
				Thread.sleep(1000);
				String salesAgent = ConvertLeadPage.salesText;
				int salesDrpDown = salesAgentOption.size();
				for (int j = 1; j <= salesDrpDown; j++) {
					String salesText = driver
							.findElement(By.xpath("//ul[@id='opportunity_saleagent_listbox']/li[" + j + "]")).getText();
					System.out.println(salesText);
					if (salesAgent.equalsIgnoreCase(salesText)) {
						driver.findElement(By.xpath("//ul[@id='opportunity_saleagent_listbox']/li[" + j + "]")).click();
						Thread.sleep(500);
						sourceDrpDown.click();
						Thread.sleep(2000);
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyPress(KeyEvent.VK_ENTER);
						// int sourceDrp= sourceOption.size();
						/*
						 * for(int k=1;k<=sourceDrp;k++) { driver.findElement(By.
						 * xpath("//select[@id='RelatedSource']/option[text()='BBCS - Key Account']")).
						 * click(); String sourceDrpDown=driver.findElement(
						 * By.xpath("//select[@id='RelatedSource']/option["+k+"]")).getText(); String
						 * sourceText=""; utilities.CommonMethods drpDownSource=new
						 * utilities.CommonMethods(); sourceText=drpDownSource.getExcelData(filePath,
						 * fileName).get("SourceDropDown"); if(sourceText.equals(sourceDrpDown)) {
						 * driver.findElement(
						 * By.xpath("//select[@id='RelatedSource']/option["+k+"]")).click();
						 * Thread.sleep(2000); break; } }
						 */
					}
				}
			}
		}
	}

	public void saveOpp() throws InterruptedException {
		saveIcon.click();
		pageLoad.pageWait(savePageLoad);
	}
}
