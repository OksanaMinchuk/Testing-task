package framework.element;

import org.openqa.selenium.By;

public class Button extends BaseElement {

    public Button(final By locator, String elementName) {
        super(locator, elementName);
    }

    public void submit() {
        LOGGER.info("submit button by elementName:  " + elementName);
        getElement().submit();
    }

}
