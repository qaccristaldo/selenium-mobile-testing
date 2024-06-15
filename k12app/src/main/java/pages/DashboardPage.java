package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;

import pages.base.DashboardPageBase;
import utils.Constants;
import utils.MyLogger;

import java.time.Duration;

import static extended.MobileActions.clickElement;
import static extended.MobileActions.waitFor;
import static java.lang.StringTemplate.STR;


@Getter
public class DashboardPage extends DashboardPageBase {

    private AndroidDriver driver;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Lo haré mas tarde\")")
    private WebElement doItLater;


    static Logger logger = MyLogger.getLogger();

    public DashboardPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }


    public Boolean isOpen(String name){
        boolean flag = false;
        WebElement welcome;
        String n = "";
        try {
            welcome = (WebElement) waitFor(driver).until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(Constants.WELCOME_LOCATOR, name))));
            flag = welcome.isDisplayed();

            n = welcome.getText().substring(5, welcome.getText().length()-1);
            logger.info(STR."User name found = \{ n }");
        }catch (NoSuchElementException e){
            logger.error(e.getMessage());
        }

        return flag && n.equals(name);
    }



    private void dismissPasswordManager(WebDriver driver) {
        /*
        try {
            waitFor(driver, 6).until(ExpectedConditions.visibilityOf(getNoButton()));
            getNoButton().click();
        }catch (TimeoutException | NoSuchElementException e){
            logger.error(e.getMessage());
        }

         */
    }

    public void dismissDoItLater() {
        try {
            clickElement(driver, doItLater);
        }catch (TimeoutException | NoSuchElementException e){
            logger.error(e.getMessage());
        }
    }

    public LoginPage logout(){
        try {
            clickElement(driver, menuButton);
            clickElement(driver, closeSessionButton);
        }catch (NoSuchElementException e){
            logger.error(e.getMessage());
        }
        return new LoginPage(driver);
    }

    public void clickReRegButton(WebDriver driver){
        /*
        switchContextWebView((AndroidDriver) driver);
        try {
            waitFor(driver).until(ExpectedConditions.visibilityOf(reRegButton));
            reRegButton.click();
        }catch (NoSuchElementException e){
            logger.error(e.getMessage());
        }
    }

    public void clickEnrollmentButton(WebDriver driver){
        switchContextWebView((AndroidDriver) driver);
        try {
            waitFor(driver).until(ExpectedConditions.visibilityOf(enrollmentButton));
            enrollmentButton.click();
        }catch (NoSuchElementException e){
            logger.error(e.getMessage());
        }*/
    }



}
