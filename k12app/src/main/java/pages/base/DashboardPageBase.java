package pages.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static extended.MobileActions.clickElement;


public class DashboardPageBase {



    @AndroidFindBy(uiAutomator = "new UiSelector().text(\" Comunidad\")")
    protected WebElement comunityButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\" Menú\")")
    protected WebElement menuButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Cerrar Sesión\")")
    protected WebElement closeSessionButton;

    public DashboardPageBase(WebDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    public void clickOnComunityButton(){
        comunityButton.click();
    }

    public void clickOnMenuButton(AndroidDriver driver){
      //  menuButton.click();
        clickElement(driver, menuButton);
    }

    public void clickOnCloseSesionButton(){
        closeSessionButton.click();
    }



}
