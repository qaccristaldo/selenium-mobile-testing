package pages.enrollment;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import pages.base.ParentPortalBasePage;
import utils.MyLogger;

import static extended.MobileActions.clickElement;
import static extended.MobileActions.isElementVisible;


@Getter
public class EnrollmentPage extends ParentPortalBasePage {


    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"K12 Home\")")
    private WebElement logoK12;

    @AndroidFindBy(xpath = "//*[@class = 'android.widget.RadioButton' and (@text = 'Student' or . = 'Student')]")
    private WebElement studentSelector;

    @AndroidFindBy(xpath = "//*[@class = 'android.widget.Button' and (@text = 'Next' or . = 'Next')]")
    private WebElement nextButton;

    public EnrollmentPage(AndroidDriver driver) {
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

    public EnrollmentPage selectStudent(){
        clickElement(driver, studentSelector);
        return this;
    }

    public TellAboutYourStudentPage clickOnNextButton(){
        clickElement(driver, nextButton);
        return new TellAboutYourStudentPage(driver);
    }


}
