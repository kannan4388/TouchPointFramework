package pageObjects;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConvertLeadPage {
	public static String filePath = System.getProperty("user.dir") + "\\InputExcelData\\";
	public static String fileName = "ConvertLead.xlsx";
	public static String salesText = null;
	public static String accountName;
	private WebDriver driver;
	utility.CommonMethods pageRefresh = new utility.CommonMethods();

	/* Web Elements in Convert Lead page */

	@FindBy(xpath = "//input[@id='optNewAccount']")
	WebElement newAccountRadio;

	@FindBy(xpath = "//span[@aria-owns='salesAgent_listbox']")
	WebElement drpSalesAgent;

	@FindAll(@FindBy(xpath = "//ul[@id='salesAgent_listbox']/li[@class='k-item ng-scope']"))
	List<WebElement> selectSalesAgent;

	@FindBy(xpath = "//button[text()='Convert']")
	WebElement saveConvert;

	@FindBy(xpath = "//label[text()='Name']")
	WebElement pageLoad;

	@FindBy(xpath = "//label[@class='ldet_label ng-binding'][1]")
	WebElement firstNameLabel;

	@FindBy(xpath = "//label[@class='ldet_label ng-binding'][2]")
	WebElement lastNameLabel;

	public ConvertLeadPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	/* Methods in Convert Lead page */

	public String fillData() throws InterruptedException, InvalidFormatException, IOException {
		utility.CommonMethods scrollPage = new utility.CommonMethods();
		Thread.sleep(5000);
		scrollPage.scrollUptoBottom();
		Coordinates coordinate = ((Locatable) saveConvert).getCoordinates();
		coordinate.inViewPort();
		scrollPage.scrollBy();
		newAccountRadio.click();
		Thread.sleep(500);
		scrollPage.scrollDown(drpSalesAgent);
		Thread.sleep(500);
		drpSalesAgent.click();
		int salesCount = selectSalesAgent.size();
		// System.out.println(salesCount);
		for (int i = 1; i <= salesCount; i++) {
			String textSales = driver
					.findElement(By.xpath("//ul[@id='salesAgent_listbox']/li[@class='k-item ng-scope'][" + i + "]"))
					.getText();
			System.out.println(textSales);
			utility.CommonMethods drpDownSales = new utility.CommonMethods();
			salesText = drpDownSales.getExcelData(filePath, fileName).get("Sales");
			if (textSales.equalsIgnoreCase(salesText)) {
				driver.findElement(By.xpath("//ul[@id='salesAgent_listbox']/li[@class='k-item ng-scope'][" + i + "]"))
						.click();
				Thread.sleep(1000);
				break;
			}
		}
		return salesText;
	}

	public void saveConvertLead() throws InterruptedException {
		saveConvert.click();
		utility.CommonMethods waitCM = new utility.CommonMethods();
		waitCM.pageWait(pageLoad);
		Thread.sleep(3000);
		// Utilities.CommonMethods pageRefresh=new Utilities.CommonMethods();
		//// pageRefresh.refreshPage();
		//// waitCM.pageWait(pageLoad);
		// Thread.sleep(6000);
	}

	public String getAccountName() {
		String firstName = firstNameLabel.getText();
		String lastName = lastNameLabel.getText();
		accountName = firstName + " " + lastName;
		return accountName;
	}
}
