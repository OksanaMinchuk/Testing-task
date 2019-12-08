package application.page;

import application.form.TopSellingMenu;
import framework.element.TextBox;
import framework.form.BaseForm;
import org.openqa.selenium.By;

public class BrowsingActionPage extends BaseForm {

    public TopSellingMenu topSellingMenu = new TopSellingMenu("TopSellingMenu form on GamesOnSteamPage");

    public TextBox pageTitleBox = new TextBox(By.xpath("//div[contains(@class, 'content_hub')]//h2"), "pageTitleBox");

    public BrowsingActionPage(String formName) {
        super(formName);
    }
}
