package page;

import driver.DriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CommonFunction;

import java.util.Set;

public class CategoryPage {

    private By categoryTitleLocators = By.xpath("//h1[@class = '_39qdPorEKz']");
    private By goHomePageButtonLocators = By.xpath("//a[contains(@class,'logo_part_market')]");

    private WebDriver driver;

    public CategoryPage() {
        this.driver = DriverSinglton.getInstanse().getDriver();
    }

    public String getTitleCategoryPage() {
        WebElement titleElement = driver.findElement(categoryTitleLocators);
        return titleElement.getText();
    }

    public void returnToHomePage() {

        WebDriverWait waitElement = new WebDriverWait(driver, 5);

        waitElement.until(ExpectedConditions.elementToBeClickable(goHomePageButtonLocators));
        WebElement goHomeButton = driver.findElement(goHomePageButtonLocators);
        goHomeButton.click();

        CommonFunction.switchWindow(driver, 10, 1);
    }

}
