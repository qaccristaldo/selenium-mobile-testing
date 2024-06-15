package pages.lgcreateaccount;

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
public class ChooseRolePage extends ParentPortalBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"SELECCIONA TU ROL\")")
    private WebElement selectRoleLbl;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"ELEGIR CUENTA DE TUTOR\")")
    private WebElement selectLGRoleButton;

    public ChooseRolePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public Boolean isOpen(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, selectRoleLbl);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public GettingStartedLGPage clickOnSelectLGRoleButton(){
        clickElement(driver, selectLGRoleButton);
        return new GettingStartedLGPage(driver);
    }


}
