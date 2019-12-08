package application.page;

import framework.element.Button;
import framework.form.BaseForm;
import org.openqa.selenium.By;

public class WelcomeSteamPage extends BaseForm {

    public Button installNowButton = new Button(By.xpath("//div[contains(@class,'about_install')]//a[contains(text(),'Steam')]"),
            "install now button");

    public WelcomeSteamPage(String formName) {
        super(formName);
    }


}
