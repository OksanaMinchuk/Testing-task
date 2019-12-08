package framework.element;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(final By locator, String elementName) {
        super(locator, elementName);
    }

    public void sendKeys(String key) {
        LOGGER.info("start sendKeys with key: " + key);
        getElement().sendKeys(key);
    }
}
