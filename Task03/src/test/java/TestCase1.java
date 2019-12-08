import application.page.HomePage;
import application.page.WelcomeSteamPage;
import framework.util.Constant;
import framework.util.FileHelper;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * TC-1 Steam app download
 */

public class TestCase1 extends BaseTest {

    @Test
    public void testCase1() {

        LOGGER.info("Start STEP_1. Open http://store.steampowered.com/.");
        HomePage homePage = new HomePage("HomePage");
        assertTrue(homePage.headerForm.isLogotypePresent(),
                "Expected logotype element presents on the HomePage.");

        LOGGER.info("Start STEP_2. Click “Install Steam” button.");
        homePage.headerForm.installButton.click();
        WelcomeSteamPage welcomeSteamPage = new WelcomeSteamPage("WelcomeSteamPage");
        assertTrue(welcomeSteamPage.installNowButton.isElementPresent(),
                "Expected installNow button presents on the WelcomeSteamPage.");

        LOGGER.info("Start STEP_3. Click “Install Steam Now” button -> download steam app.");
        FileHelper.cleanDirectory();
        welcomeSteamPage.installNowButton.click();
        File downloadFile = FileHelper.getFileFromDir();
        assertTrue(downloadFile.isFile(), "Expected download file is file.");
        assertEquals(downloadFile.length(), Constant.DOWNLOAD_FILE_LENGHT,
                "Expected download file has lenght: " + Constant.DOWNLOAD_FILE_LENGHT);
        assertTrue(downloadFile.getName().equals(Constant.DOWNLOAD_FILE_NAME),
                "Expected download file name is: " + Constant.DOWNLOAD_FILE_NAME);

    }

}
