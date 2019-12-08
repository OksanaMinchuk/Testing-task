package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import util.PropertyReader;

public class DriverSinglton {

    private static WebDriver driver;
    private static DriverSinglton instanse;

    private String driverType = PropertyReader.getValue("driver");

    private DriverSinglton() {
    }

    public static DriverSinglton getInstanse() {
        if (instanse == null) {
            instanse  = new DriverSinglton();
        }
        return instanse;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            switch (driverType) {
                case ("FIREFOX_DRIVER"):
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case ("CHROME_DRIVER"):
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case ("INTERNET_EXPLORER_DRIVER"):
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
        }
        return driver;
    }

    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(PropertyReader.getValue("website"));
    }

    public void closeBrowser() {
        driver.quit();
    }

}
