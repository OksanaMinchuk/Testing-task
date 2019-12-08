package framework.element;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SubElement {

    protected static final Logger LOGGER = Logger.getRootLogger();

    private WebElement webElement;
    private String elementName;

    public SubElement(WebElement webElement, String elementName) {
        this.webElement = webElement;
        this.elementName = elementName;
    }

    public void click() {
        LOGGER.info("SubElement : click on button by elementName: " + elementName);
        webElement.click();
    }

    public String getSubElementText() {
        return webElement.getText();
    }

    public SubElement getSubElement(By locator, String elementName) {
        SubElement subElement = new SubElement(webElement.findElement(locator), elementName);
        return subElement;
    }

}
