package application.page;

import application.form.HeaderForm;
import framework.element.Button;
import framework.form.BaseForm;
import framework.util.LocalReader;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {

    public static final String GAME_TYPE = LocalReader.getValue("gameType");

    public HeaderForm headerForm = new HeaderForm("HeaderForm on HomePage");

    public Button gameButton = new Button(By.xpath("//div[contains(@id, 'genre_tab')]"), "select game menu button");
    public Button actionButton = new Button(By.xpath("//a[contains(text(), \'" + GAME_TYPE + "\')]"), "TypeGameButton");

    public HomePage(String formName) {
        super(formName);
    }



}
