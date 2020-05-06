package utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.LoadUtil;

public class BrowserUtil {
	public static WebDriver driver;
	public static Properties prop;

	public BrowserUtil() {
		try {
			// this.driver=Hook.getDriver();
			prop = new Properties();
			/*
			 * File fil= new File("."); System.out.println(fil.getCanonicalPath());
			 */
			FileReader fis = new FileReader(
					new File(".").getCanonicalPath() + "\\src\\test\\java\\config\\config.properties");
			// System.out.println(fis);
			prop.load(fis);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/*
	 * @After() public void killBrowser(Scenario scenario){ if (scenario.isFailed())
	 * { scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),
	 * "image/png"); } driver.close(); driver.quit(); }
	 */

	public static void initialization() {

		/*
		 * String browserName=prop.getProperty("browser"); //String
		 * browserName="chrome"; if(browserName.equals("chrome")) {
		 * //System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		 * 
		 * ChromeOptions options = new ChromeOptions();
		 * //options.setExperimentalOption("excludeSwitches", new
		 * String[]{"enable-automation"}); options.addArguments("disable-infobars");
		 * options.addArguments("--disable-extensions");
		 * 
		 * 
		 * DesiredCapabilities cap = DesiredCapabilities.chrome();
		 * cap.setCapability(ChromeOptions.CAPABILITY, options);
		 * cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
		 * UnexpectedAlertBehaviour.ACCEPT);
		 * 
		 * 
		 * //driver=new ChromeDriver(); System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir") + "//drivers//chromedriver.exe"); driver = new
		 * ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); } else
		 * if(browserName.equals("FF")) { System.setProperty("webdriver.gecko.driver",
		 * "Driver/geckodriver.exe"); driver=new FirefoxDriver(); } else
		 * if(browserName.equals("IE")) { System.setProperty("webdriver..driver",
		 * "Driver/chromedriver.exe"); driver=new FirefoxDriver(); } //
		 * driver.manage().window().maximize(); // driver.manage().deleteAllCookies();
		 * //driver.manage().timeouts().pageLoadTimeout(LoadUtil.PAGE_LOAD_TIMEOUT,
		 * TimeUnit.SECONDS);
		 * //driver.manage().timeouts().implicitlyWait(LoadUtil.IMPLICIT_WAIT,
		 * TimeUnit.SECONDS); driver.get(prop.getProperty("url"));
		 */
		// return driver;
		// String un=prop.getProperty("username");
	}

}
