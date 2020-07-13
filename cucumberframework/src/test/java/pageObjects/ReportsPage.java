package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsPage {

	private WebDriver driver;
	utility.CommonMethods waitForElementToLoad = new utility.CommonMethods();

	@FindBy(xpath = "//li[@ng-class='NavbarService.ReportsSelected']")
	WebElement reportMenu;

	@FindBy(xpath = "//ul[@class='reporst_lists']/li[2]/a")
	WebElement reportNames;

	@FindBy(xpath = "//ul[@class='reporst_lists']/li[5]")
	WebElement reportLoadElement;

	@FindBy(xpath = "//button[@class='hfc_bgbut tooltip-bottom']")
	WebElement downloadBtn;

	public ReportsPage() {
		this.driver = LoginPage.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void openReport() throws InterruptedException {
		reportMenu.click();
		waitForElementToLoad.elementToBeClickable(reportLoadElement);
	}

	public void individualReportOpen() {
		reportNames.click();
	}
}
