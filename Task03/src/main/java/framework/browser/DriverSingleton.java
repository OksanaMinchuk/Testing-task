package framework.browser;

import framework.util.BrowserOption;
import framework.util.DataPropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static final Logger LOGGER = Logger.getRootLogger();

    private static WebDriver driver;
    private static DriverSingleton instanсe;

    private String driverType = DataPropertyReader.getValue("driver");

    private DriverSingleton() {
    }

    public static DriverSingleton getInstanсe() {
        if (instanсe == null) {
            instanсe = new DriverSingleton();
        }
        return instanсe;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            LOGGER.info("get driver: " + driverType);
            switch (driverType) {
                case ("FIREFOX_DRIVER"):
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(BrowserOption.getFirefoxOptions());
                    break;
                case ("CHROME_DRIVER"):
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(BrowserOption.getChromeOptions());
                    break;
                default: {
                    LOGGER.error("Driver " + driverType + " not found.");
                    throw new IllegalArgumentException("Driver " + driverType + " not found.");
                }
            }
        }
        return driver;
    }

    public void openBrowser() {
        LOGGER.info("open browser");
        driver.manage().window().maximize();
        driver.get(DataPropertyReader.getValue("website"));
    }

    public void closeBrowser() {
        LOGGER.info("close browser");
        driver.quit();
    }

}
