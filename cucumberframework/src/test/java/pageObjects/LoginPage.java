package pageObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	utility.CommonMethods waitForPageLoad = new utility.CommonMethods();
	// Intializing the page objects
	private static WebDriver driver;
	private static Properties prop;
	public static File folder;

	public LoginPage() throws FileNotFoundException, IOException {
		this.driver = getDriver();
		PageFactory.initElements(driver, this);
		prop = new Properties();
		FileReader fis = new FileReader(
				new File(".").getCanonicalPath() + "\\src\\test\\java\\config\\config.properties");

		prop.load(fis);
	}

	@FindBy(id = "UserName")
	WebElement username;

	@FindBy(id = "Password")
	WebElement password;

	@FindBy(id = "login_but")
	WebElement loginBtn;

	@FindBy(xpath = "//i[@class='fas fa-calendar-alt']")
	WebElement pageLoad;

	public void login() throws InterruptedException {
		// this.driver = Hook.getDriver();
		// System.out.println(un);
		String un = prop.getProperty("username");
		String pw = prop.getProperty("password");
		username.sendKeys(un);
		Thread.sleep(500);
		password.sendKeys(pw);
		Thread.sleep(1000);
	}

	public void clickLoginBtn() throws InterruptedException {
		loginBtn.click();
		// objMethods = new utility.CommonMethods();
		waitForPageLoad.pageWait(pageLoad);
	}

	public File launchBrowser() throws IOException {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//drivers//chromedriver.exe");

			// folder = new File(UUID.randomUUID().toString());
			// folder.mkdir();
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("--always-authorize-plugins");

			// Map<String, Object> prefs = new HashMap<String, Object>();
			// prefs.put("profile.default_content_settings.popups", 0);
			// prefs.put("download.default_directory", folder.getAbsolutePath());

			// options.setExperimentalOption("prefs", prefs);
			// System.out.println(options.getExperimentalOption("prefs"));
			// options.addArguments("--disable-notifications");
			// DesiredCapabilities cap = DesiredCapabilities.chrome();
			// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// cap.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//drivers//chromedriver.exe");
			// driver = new ChromeDriver(cap);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			// driver.manage().deleteAllCookies();
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		driver.get(prop.getProperty("url"));
		return folder;
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
