package extended;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import utils.MyLogger;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MobileActions {
    static Logger logger = MyLogger.getLogger();

    public static Boolean isElementVisible( AndroidDriver driver, WebElement element){
        Boolean isPresent = null;
        try {
            waitFor(driver).until(visibilityOf(element));
            isPresent = true;
        }catch (TimeoutException | NullPointerException e){
            logger.error(e.getMessage());
        }
        return isPresent;
    }

    public static <T> T clickElement(AndroidDriver driver,  WebElement element) {
        try{
            waitFor(driver).until(elementToBeClickable(element));
            element.click();
        }catch (TimeoutException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    public static <T> T typeOnElement(AndroidDriver driver, WebElement element, String input) {
        try {
            waitFor(driver).until(visibilityOf(element));
            element.sendKeys(input);
        }catch (TimeoutException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    public static void scrollToElement(AndroidDriver driver, String element) {


        driver.findElement(AppiumBy.androidUIAutomator(
                (STR."new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"\{element}\"))")));


    }

    public static <T> T waitForPresenceOfElement(AndroidDriver driver, WebElement element) {
        T output = null;
        try {
            output = (T) waitFor(driver).until(visibilityOf(element));
        }catch (TimeoutException e){
            logger.error(e.getMessage());
        }
        return output;
    }

    public static Wait waitFor(AndroidDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(ofSeconds(15))
                .pollingEvery(ofSeconds(3))
                .ignoring(NoSuchElementException.class);
    }
}
