package page;

import driver.DriverSinglton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import util.CommonFunction;
import util.DataWriterToCSV;
import util.PropertyReader;

import java.io.File;

import static org.testng.Assert.assertTrue;

/**
 * Class for testing all steps
 */

public class TotalTest {

    private DriverSinglton driverSinglton = DriverSinglton.getInstanse();
    private WebDriver driver = driverSinglton.getDriver();

    @Test
    public void totalTest() {

        driverSinglton.openBrowser();
        //step 1
        HomePage homePage = new HomePage();
        assertTrue(homePage.isGoHomePageButtonLocators(), "Expected go home page button locators.");

        //step 2
        homePage.goToLogin();
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName(PropertyReader.getValue("username"));
        loginPage.enterPassword(PropertyReader.getValue("password"));
            CommonFunction.switchWindow(driver, 10, driver.getWindowHandles().size());
        assertTrue(homePage.isGoHomePageButtonLocators(), "Expected go home page button locators.");

        //step 3, 4
        homePage.goToRandomElement();
        String actualTitle = driver.getTitle();
        CategoryPage categoryPage = new CategoryPage();
            String expectedTitle = categoryPage.getTitleCategoryPage();
            String delimeter = " ";
            String[] subStr = expectedTitle.split(delimeter);
        for(int i = 0; i < subStr.length; i++) {
            assertTrue(actualTitle.toLowerCase().contains(subStr[i].toLowerCase()), "Expected the same word in title.");
        }

        //step 5
        categoryPage.returnToHomePage();
        assertTrue(homePage.isGoHomePageButtonLocators(), "Expected go home page button locators.");

        //step 6
        DataWriterToCSV.writeFile(homePage.getPopularGoodsList());
        File file = new File(PropertyReader.getValue("resultFile"));
        assertTrue(file.exists(), "Expected file exists.");
        assertTrue(file.length() > 0, "Expected file is not empty.");

        //step 7
        homePage.logOut();
        assertTrue(homePage.isLogInButtonLocator(), "Expected LogIn button locator present on the home page.");
    }

    @AfterMethod
    public void tearDown() {
        driverSinglton.closeBrowser();
    }

}
