package framework.form;

import framework.browser.DriverSingleton;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public abstract class BaseForm {

    protected static final Logger LOGGER = Logger.getRootLogger();

    protected WebDriver driver;
    protected String formName;

    public BaseForm(String formName) {
        LOGGER.info("create form by name: " + formName);
        driver = DriverSingleton.getInstanсe().getDriver();
        this.formName = formName;
    }

    public void dropFormDown(By element) {
        LOGGER.info("drop down form: " + formName);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

}
