package page;

import driver.DriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import util.PropertyReader;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    private WebDriver driver;

    private By usernameFormLocator = By.xpath("//input[contains(@id,'passp-field-login')]");

    private By passwordFormLocator = By.cssSelector("input[name = \"passwd\"]");

    private By enterButtonLocator = By.xpath("//span[text() = 'Войти']");

    public LoginPage() {
        this.driver = DriverSinglton.getInstanse().getDriver();
    }

    public void enterUserName(String username) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1000, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(usernameFormLocator));

        WebElement userNameForm = driver.findElement(usernameFormLocator);

        userNameForm.sendKeys(username);

        if(userNameForm.getAttribute("value").equals(PropertyReader.getValue("username"))){
            WebElement enterButton = driver.findElement(enterButtonLocator);
            enterButton.submit();
        }
    }

    public void enterPassword (String password) {

        WebElement userPasswordForm = driver.findElement(passwordFormLocator);

        userPasswordForm.sendKeys(password);

        WebElement enterButton = driver.findElement(enterButtonLocator);

        enterButton.submit();

    }

}


