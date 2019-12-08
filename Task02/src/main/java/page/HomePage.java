package page;

import driver.DriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import util.CommonFunction;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {

    private By logInButtonLocator = new By.ByLinkText("Войти");

    private By confirmCityInPopupWindowButtonLocator = By.xpath("//div[@class='n-region-notification__actions-cell']//span[contains(text(), 'Да, спасибо')]");

    private By categoryListLocator = By.xpath("//div[contains(@class,'n-w-tab_type_navigation-menu')]");

    private By userIconLocator = By.xpath("//div[contains(@class, 'n-passport-suggest-popup-opener')]//a[contains(@class,'header2-user user i-bem user_js_inited')]");

    private By userLogOutButtonLocator = By.xpath("//a[contains(text(),'Выйти')]");

    private By popularGoodsTitleLocator = By.xpath("//h3[text()='Популярные товары']");

    private By goHomePageButtonLocators = By.xpath("//a[contains(@class,'logo_part_market')]");

    private WebDriver driver;

    private final String STRING_DELIMETER = "Популярные товары</h3>";
    private final String GOODS_PATTERN = "<div class=\"name _3rufvyYddw _2oOs9ACcmk _3qQ4L4GpT7\" data-6d2c096b=\"true\">(.*?)</div>";

    public HomePage() {
        this.driver = DriverSinglton.getInstanse().getDriver();
    }

    /**
     * Go to authorization page
     */
    public void goToLogin() {

        System.out.println();

        WebDriverWait waitElement = new WebDriverWait(driver, 5);
        waitElement.until(ExpectedConditions.elementToBeClickable(logInButtonLocator));

        WebElement userButton = driver.findElement(logInButtonLocator);
        userButton.click();

        CommonFunction.switchWindow(driver, 10, driver.getWindowHandles().size());

    }

    /**
     * Returns displayed category goods list
     * @return presentList
     */
    public List<WebElement> getCategoriesList() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> goodsList = driver.findElements(categoryListLocator);
        List<WebElement> presentList = new LinkedList<>();

        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).isDisplayed()) {
                presentList.add(goodsList.get(i));
            }
        }
        return presentList;
    }

    /**
     * Go to random category element
     */
    public void goToRandomElement() {

        CommonFunction.switchWindow(driver, 5, 1);

        List<WebElement> presentList = getCategoriesList();
        Random random = new Random();
        int randomNumber = random.nextInt(presentList.size());
        WebElement randomElement = presentList.get(randomNumber);

        WebDriverWait waitElement = new WebDriverWait(driver, 5);
        waitElement.until(ExpectedConditions.elementToBeClickable(categoryListLocator));

        randomElement.click();
    }


    /**
     * Methods checks present element
     * @param element
     * @return
     */
    private boolean isElementPresent(By element) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(element);

        return list.size() > 0;
    }

    /**
     *
     * @return true if user icon present on page
     */
    public boolean  isUserIconPresent() {
        return isElementPresent(userIconLocator);
    }

    public boolean  isLogInButtonLocator() {
        return isElementPresent(logInButtonLocator);
    }

    public boolean  isGoHomePageButtonLocators() {
        return isElementPresent(goHomePageButtonLocators);
    }

    /**
     * close geolocation popup window
     */
    public void closePopupWindow() {

        WebDriverWait waitElement = new WebDriverWait(driver, 5);
        waitElement.until(ExpectedConditions.elementToBeClickable(confirmCityInPopupWindowButtonLocator));

        Actions action = new Actions(driver);
        WebElement confirmButton = driver.findElement(confirmCityInPopupWindowButtonLocator);
        action.click(confirmButton).perform();
    }

    /**
     *
     * @return popular goods list
     */
    public List<String> getPopularGoodsList() {

        List<String> wantedElements = new ArrayList<>();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)", "");

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1000, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(popularGoodsTitleLocator));

        String stringHTML = driver.getPageSource();

        String[] strings = stringHTML.split(STRING_DELIMETER);

        Pattern pattern = Pattern.compile(GOODS_PATTERN);
        Matcher match = pattern.matcher(strings[1]);

        while(match.find()) {
            wantedElements.add(match.group(1));
        }
        return wantedElements;
    }

    /**
     *
     * @return true if logIn button present on page
     */
    public void logOut() {

        CommonFunction.switchWindow(driver, 5, 1);

        WebDriverWait waitElement = new WebDriverWait(driver, 5);
        waitElement.until(ExpectedConditions.elementToBeClickable(userIconLocator));

        WebElement userIconButton = driver.findElement(userIconLocator);
            userIconButton.click();

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(userLogOutButtonLocator));

        WebElement logOutButton = driver.findElement(userLogOutButtonLocator);
            logOutButton.click();

    }

}
