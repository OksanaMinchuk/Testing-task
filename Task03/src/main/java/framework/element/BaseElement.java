package framework.element;

import framework.browser.DriverSingleton;
import framework.util.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Class for abstract element
 */

public abstract class BaseElement {

    protected static final Logger LOGGER = Logger.getRootLogger();

    protected WebDriver driver;
    protected By locator;
    protected String elementName;

    public BaseElement(final By locator, String elementName) {
        driver = DriverSingleton.getInstanсe().getDriver();
        this.locator = locator;
        this.elementName = elementName;
    }

    public By getLocator() {
        return locator;
    }

    public String getElementName() {
        return elementName;
    }

    public WebElement getElement() {
        waitImplicity();
        return driver.findElement(locator);
    }

    public List<WebElement> getElements() {
        waitImplicity();
        return driver.findElements(locator);
    }

    public String getText() {
        waitImplicity();
        return getElement().getText();
    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    public boolean isElementPresent() {
        waitForIsElementPresence();
        LOGGER.info("check element present : " + elementName + ", on page: " + driver.getTitle());
        List<WebElement> list = driver.findElements(locator);
        return list.size() > 0;
    }

    public void click() {
        waitForIsElementToBeClickable();
        LOGGER.info("click on button: " + elementName);
        getElement().click();
    }

    public void moveToElement() {
        Actions actions = new Actions(driver);
        LOGGER.info("move to element: " + getElementName());
        actions.moveToElement(getElement()).build().perform();
    }

    public void waitImplicity() {
        driver.manage().timeouts().implicitlyWait(Constant.TIME_OUT_SEC, TimeUnit.SECONDS);
    }

    public void waitForIsElementPresence() {
        WebDriverWait waitElement = new WebDriverWait(driver, Constant.TIME_OUT_SEC);
        waitElement.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForIsElementToBeClickable() {
        WebDriverWait waitElement = new WebDriverWait(driver, Constant.TIME_OUT_SEC);
        waitElement.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void waitFluentForIsElementToBeVisibility(int withTimeoutSec, int pollingEveryMls) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(withTimeoutSec, TimeUnit.SECONDS)
                .pollingEvery(pollingEveryMls, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitFluentForIsElementToBeClickable(int withTimeoutSec, int pollingEveryMls) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(withTimeoutSec, TimeUnit.SECONDS)
                .pollingEvery(pollingEveryMls, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
