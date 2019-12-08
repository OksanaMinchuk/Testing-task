package application.form;

import framework.element.Button;
import framework.form.BaseForm;

import org.openqa.selenium.By;

public class HeaderForm extends BaseForm {

    public Button installButton
            = new Button(By.xpath("//div[contains(@class,'header_installsteam_btn_green')]"), "install button");

    public Button logotypeButton
            = new Button(By.xpath("//div[contains(@class,'logo')]//span[contains(@id,'logo_holder')]"), "logotype button");

    public HeaderForm(String formName) {
        super(formName);
    }

    public boolean isLogotypePresent() {
        return logotypeButton.isElementPresent();
    }


}
