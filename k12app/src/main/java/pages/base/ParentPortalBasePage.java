package pages.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.MyLogger;

import static extended.MobileActions.clickElement;
import static extended.MobileActions.isElementVisible;


@Getter
public class ParentPortalBasePage extends BasePage{

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"< Back\")")
    private WebElement goBackButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"K12 Logo\")")
    private WebElement k12HomeLogo;



    public ParentPortalBasePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;    }

    public void clickBackButton(AndroidDriver driver){
        clickElement(driver, goBackButton);
    }

    public Boolean isOpen(){

        boolean flag = false;
        try{
            flag = isElementVisible(driver, k12HomeLogo);
        }catch (NullPointerException e){
            logger.error(e.getMessage());
        }
        return flag ;
    }
}
