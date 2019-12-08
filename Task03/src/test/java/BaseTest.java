import framework.browser.DriverSingleton;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private DriverSingleton driverSingleton = DriverSingleton.getInstanсe();

    protected static final Logger LOGGER = Logger.getRootLogger();

    @BeforeTest
    public void init() {
        driverSingleton.getDriver();
        driverSingleton.openBrowser();
    }

    @AfterTest
    public void tearDown() {
        driverSingleton.closeBrowser();
    }

}
