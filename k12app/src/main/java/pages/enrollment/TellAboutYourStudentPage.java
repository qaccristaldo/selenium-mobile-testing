package pages.enrollment;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import pages.base.ParentPortalBasePage;
import pages.lgcreateaccount.CreateLGAccountPage;
import utils.MyLogger;

import static extended.MobileActions.*;
import static utils.Utils.generateRandomAlphaString;


@Getter
public class TellAboutYourStudentPage extends CreateLGAccountPage {


    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"K12 Home\")")
    private WebElement logoK12;

//    @AndroidFindBy(xpath = "//android.view.View[@text=\"First name\"]/following::android.view.View/android.widget.EditText")
//    private WebElement inputFirstName2;

    @AndroidFindBy(xpath = "//*[@hint='First name']")
    private WebElement inputFirstName;

    //@AndroidFindBy(xpath = "//android.view.View[@text=\"Last name\"]/following::android.view.View/android.widget.EditText")
    @AndroidFindBy(xpath = "//*[@hint='Last name']")
    private WebElement inputLastName;


    public TellAboutYourStudentPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @Override
    public Boolean isOpen() {
        boolean flag = false;
        try {
            flag = isElementVisible(driver, logoK12);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    public TellAboutYourStudentPage setFirstName(){
        System.out.println(inputFirstName.isDisplayed());

        typeOnElement(driver, inputFirstName, generateRandomAlphaString(8));
        return  this;
    }

    public TellAboutYourStudentPage setLastName(){
        scrollDown(driver);
        typeOnElement(driver, inputLastName, generateRandomAlphaString(8));
        return  this;
    }







}
