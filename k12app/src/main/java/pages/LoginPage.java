package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.base.ParentPortalBasePage;
import pages.lgcreateaccount.ChooseRolePage;
import utils.MyLogger;


import java.time.Duration;

import static extended.MobileActions.*;


@Getter
public class LoginPage extends ParentPortalBasePage {
    public AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

  @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(4)")
  private WebElement lCSelector;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Elija el Tipo de Cuenta\")")
  private WebElement chooseAccontType;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Soy el Mentor de mis estudiantes.\")")
  private WebElement lCLbl;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Nombre de usuario\"]/following::android.view.View/android.widget.EditText")
  private WebElement inputUsername;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Correo Electrónico\"]/following::android.view.View/android.widget.EditText")
  private WebElement inputEmail;

  @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
  private WebElement inputPassword;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Iniciar sesión\")")
  private WebElement submitButton;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"¿Aún no tienes una cuenta?\")")
  private WebElement dontHaveAnAccountYetLink;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"El nombre de usuario puede ser incorrecto, por favor intente de nuevo\")")
  private WebElement userIncorrectMsg;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"El correo electrónico puede ser incorrecto, por favor intente de nuevo\")")
  private WebElement mailIncorrectMsg;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"La contraseña puede ser incorrecta, por favor inténtelo de nuevo\")")
  private WebElement passIncorrectMsg;

    public LoginPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }


    public LoginPage clickOnLCSelector(){
        clickElement(driver, lCSelector);
        return this;
    }

    public Boolean isOpen(){
        return isElementVisible(driver, chooseAccontType);
    }

    public Boolean incorrectUserOrPassMsgAreDisplayed(){
        boolean flag = false;
        try{
            flag = incorrectUserMsgIsDisplayed() && incorrectPassMsgIsDisplayed();
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public Boolean incorrectMailOrPassMsgAreDisplayed(){
        boolean flag = false;
        try{
            flag = incorrectMailIsDisplayed() || incorrectPassMsgIsDisplayed();
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
          
    }

    public Boolean incorrectPassMsgIsDisplayed(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, passIncorrectMsg);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public Boolean incorrectUserMsgIsDisplayed(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, userIncorrectMsg);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public Boolean incorrectMailIsDisplayed(){
        return isElementVisible(driver, mailIncorrectMsg);
    }

    public Boolean lcIsSelected(AndroidDriver driver){

        return isElementVisible(driver, lCLbl);
    }

    public LoginPage setInputUsername(String user){
        typeOnElement(driver, inputUsername, user);
        return this;
    }

    public LoginPage setInputEmail(String user){
        typeOnElement(driver, inputEmail, user);
        return this;
    }

    public LoginPage setInputPassword( String pass){
        typeOnElement(driver, inputPassword, pass);
        return this;
    }

    public DashboardPage clickOnSubmitButton(){
        clickElement(driver, submitButton);
        return new DashboardPage(driver);
    }


    public ChooseRolePage clickOnDontHaveAnAccountYet(){
        clickElement(driver, dontHaveAnAccountYetLink);
        return new ChooseRolePage(driver);
    }


}
