package framework.element;

import framework.browser.DriverSingleton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MultipleElement {

    protected WebDriver driver;
    protected By locator;
    protected String elementName;

    public MultipleElement(By locator, String elementName) {
        driver = DriverSingleton.getInstanсe().getDriver();
        this.locator = locator;
        this.elementName = elementName;
    }

    public List<WebElement> getElements() {
        return driver.findElements(locator);
    }

    public WebElement getElementByIndex(int index) {
        return getElements().get(index);
    }

    public String getElementTextByIndex(int index) {
        return getElements().get(index).getText();
    }
}


