import application.form.TopSellingMenu;
import application.model.Game;
import application.page.BrowsingActionPage;
import application.page.HomePage;
import application.page.SelectedGamePage;
import framework.element.SubElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * TC-2 Special calculation check
 */

public class TestCase2 extends BaseTest {

    @Test
    public void testCase2() {

        LOGGER.info("Start STEP_1. Open http://store.steampowered.com/.");
        HomePage homePage = new HomePage("HomePage");
        assertTrue(homePage.headerForm.isLogotypePresent(),
                "Expected logotype element presents on the HomePage.");

        LOGGER.info("Start STEP_2. Select Games -> Action in the top menu.");
        homePage.gameButton.moveToElement();
        homePage.actionButton.click();
        BrowsingActionPage browsingActionPage = new BrowsingActionPage("browsingActionPage");
        assertTrue(browsingActionPage.pageTitleBox.getText().contains(homePage.GAME_TYPE),
                "Expected page title box contains selected game type.");

        LOGGER.info("Start STEP_3. Navigate to “Top Selling” tab.");
        browsingActionPage.topSellingMenu.topSellingButton.click();
        assertTrue(browsingActionPage.pageTitleBox.isElementPresent(),
                "Expected topSellingMenu is displayed(open) on the GamesOnSteamPage.");

        LOGGER.info("Start STEP_4. Click the game with the highest discount on the 1 page of the games list.");
        TopSellingMenu topSellingMenu = new TopSellingMenu("TopSellingMenu");
        SubElement wantedSubElement = topSellingMenu.getWantedDiscountSubLable();

        Game game = topSellingMenu.getWantedGame(wantedSubElement);
        assertTrue(game.getDiscount() != null, "Expected discount rate is saved.");
        assertTrue(game.getOriginalPrice() != null, "Expected initial price is saved.");
        assertTrue(game.getFinalPrice() != null, "Expected discounted price is saved.");
        wantedSubElement.click();

        SelectedGamePage selectedGamePage = new SelectedGamePage("SelectedGamePage");
        String expectedNamePage = game.getName();
        String actualNamePage = selectedGamePage.getGameTitle().getText();
        assertTrue(actualNamePage.equalsIgnoreCase(expectedNamePage),
                "Expected page title equals selected game name.");

        LOGGER.info("Start STEP_5. Check that game discount rate, initial and discounted prices are the same as on the step 4.");
        String expectedGameDiscount = game.getDiscount();
        String actualGameDiscount = selectedGamePage.gameDiscount.getText();
        assertTrue(actualGameDiscount.equalsIgnoreCase(expectedGameDiscount),
                "Expected game discount equals selected game discount.");

        String expectedOriginalPrice = game.getOriginalPrice();
        String actualOriginalPrice = selectedGamePage.gameOriginalPrice.getText();
        assertTrue(actualOriginalPrice.contains(expectedOriginalPrice),
                "Expected game OriginalPrice equals selected game OriginalPrice.");

        String expectedFinalPrice = game.getFinalPrice();
        String actualFinalPrice = selectedGamePage.gameFinalPrice.getText();
        assertTrue(actualFinalPrice.contains(expectedFinalPrice),
                "Expected game FinalPrice equals selected game FinalPrice.");

    }
}
