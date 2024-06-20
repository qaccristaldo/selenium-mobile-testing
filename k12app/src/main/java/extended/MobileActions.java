package extended;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import utils.MyLogger;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MobileActions {
    static Logger logger = MyLogger.getLogger();

    public static Boolean isElementVisible(AndroidDriver driver, WebElement element) {
        boolean isPresent = false;
        try {
            waitFor(driver).until(visibilityOf(element));
            isPresent = true;
        } catch (TimeoutException | NullPointerException e) {
            logger.error(e.getMessage());
        }
        return isPresent;
    }

    public static <T> T clickElement(AndroidDriver driver, WebElement element) {

        try {
            waitFor(driver).until(elementToBeClickable(element));
            element.click();

        } catch (TimeoutException e) {
            driver.findElement(By.xpath("//*[@class = 'android.webkit.WebView' and (@text = '' or . = '')]")).click();
            waitFor(driver).until(elementToBeClickable(element));
            element.click();
            logger.error(e.getMessage());

        }

        return null;
    }

    public static <T> T typeOnElement(AndroidDriver driver, WebElement element, String input) {
        try {
            waitFor(driver).until(visibilityOf(element));
            element.sendKeys(input);
        } catch (TimeoutException e) {
            driver.findElement(By.xpath("//*[@class = 'android.webkit.WebView' and (@text = '' or . = '')]")).click();
            waitFor(driver).until(visibilityOf(element));
            element.sendKeys(input);
            logger.error("There was an error. %s".formatted(e.getMessage()));

        }

        return null;
    }

    public static void scrollToElement(AndroidDriver driver, String element) {
        driver.getPageSource();
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    (STR."new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"\{element}\"))")));
        } catch (NoSuchElementException e) {
                logger.error("%s. Error in scrolling".formatted(e.getMessage()));
                System.exit(1);

        }

    }

    public static void tapByCoordinates(AndroidDriver driver, int x, int y) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickSequence = new Sequence(finger, 1);
        clickSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        clickSequence.addAction(finger.createPointerDown(0));
        clickSequence.addAction(finger.createPointerUp(0));
        driver.perform(Collections.singleton(clickSequence));
    }

    public static void scrollDown(AndroidDriver driver) {

        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;

        int endY = (int) (driver.manage().window().getSize().getHeight() * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(scroll));

    }

    public static <T> T waitForPresenceOfElement(AndroidDriver driver, WebElement element) {
        T output = null;
        try {
            output = (T) waitFor(driver).until(visibilityOf(element));
        } catch (TimeoutException e) {
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

    public static void switchForceNativeApp(AndroidDriver driver) {
        logger.info("switching context into NATIVE_APP ...");
        driver.context("NATIVE_APP");
    }
}
