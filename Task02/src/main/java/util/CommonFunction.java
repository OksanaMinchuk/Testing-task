package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class CommonFunction {

    public static void switchWindow(WebDriver driver, int timeOutSec, int expectedNumberOfWindows) {

        String currentHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, timeOutSec);
        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));

        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(currentHandle)) driver.switchTo().window(handle);
        }
    }




}
