package pages.lgcreateaccount;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import pages.ParentPortalPage;
import utils.MyLogger;


import java.util.List;

import static extended.MobileActions.*;
import static utils.Utils.*;

@Getter
public class CreateLGAccountPage extends ParentPortalPage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    //input text
    @AndroidFindBy(xpath = "//android.view.View[@text=\"First name\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement firstNameInput;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Last name\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement lastNameInput;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Email Address\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement emailInput;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Password\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement passwordInput;


    //security questions
    @AndroidFindBy(xpath = "//android.view.View[@text=\"Answer security question 1\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement answerSecurityQuestion1;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Answer security question 2\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement answerSecurityQuestion2;


    //Selectors dropdown
    @AndroidFindBy(xpath = "//android.view.View[@text=\"Please add a security question 1\"]/following::android.view.View/android.widget.Spinner[@text=\"Select a question for the security question\"]")
    private WebElement security1Dropdown;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Please add a security question 2\"]/following::android.view.View/android.widget.Spinner[@text=\"Select a question for the security question\"]")
    private WebElement security2Dropdown;


    //set options
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"What is the name of your favorite pet?\")")
    private WebElement option1Selector1;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"What is the name of your favorite movie?\")")
    private WebElement option3Selector2;

    @AndroidFindBy(xpath = "//android.widget.Spinner[@text=\"UNITED STATES OF AMERICA\"]")
    private WebElement countrySelector;

    @AndroidFindBy(xpath = "//android.widget.Spinner[@text=\"Select State\"]")
    private WebElement stateSelector;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"AZ\"]")
    private WebElement stateOption;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"Country\"]/child::android.view.View/android.widget.TextView")
    List<WebElement> countriesList;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"State\"]/child::android.view.View/android.widget.TextView")
    List<WebElement> statesList;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Phone number\"]/following::android.view.View/android.widget.EditText")
    private WebElement phoneInput;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text=\"Opt in to receive text messages\"]")
    private WebElement optionCheckBox;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Contact Preference\"]/following::android.view.View/android.widget.Spinner")
    private WebElement contactPreferenceDropdown;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign Up\")")
    private WebElement signUpButton;


    public CreateLGAccountPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public CreateLGAccountPage setFirstNameInput() {
        String firstName = generateRandomAlphaString(10);
        logger.info(STR."First Name = \{firstName}");
        typeOnElement(driver, firstNameInput,firstName );
        return this;
    }

    public CreateLGAccountPage setLastNameInput() {
        String lastName = generateRandomAlphaString(10);
        logger.info(STR."Last Name = \{lastName}");
        typeOnElement(driver, lastNameInput, lastName );
        return this;
    }

    public CreateLGAccountPage setEmailInput() {
        String email = generateRandomEmail(10);
        logger.info(STR."Email = \{email}");
        typeOnElement(driver, emailInput, email);
        return this;
    }

    public CreateLGAccountPage scrollToPassword() {
        scrollToElement(driver, "Password");
        return this;
    }

    public CreateLGAccountPage scrollToCountry() {
        scrollToElement(driver, "Country");
        return this;
    }

    public CreateLGAccountPage setPasswordInput() {
        String password =  generateRandomAlphaString(10);
        logger.info(STR."Password = \{password}");
        typeOnElement(driver, passwordInput, password);
        return this;
    }

    public CreateLGAccountPage setSecurityQuestion1() {
        clickElement(driver, security1Dropdown);
        clickElement(driver, option1Selector1);
        typeOnElement(driver, answerSecurityQuestion1, "pet1");
        return this;
    }

    public CreateLGAccountPage setSecurityQuestion2() {
        clickElement(driver, security2Dropdown);
        clickElement(driver, option3Selector2);
        typeOnElement(driver, answerSecurityQuestion2, "movie1");
        return this;
    }

    public CreateLGAccountPage setCountry()  {
        clickElement(driver, countrySelector);
        if (getCountriesList().isEmpty()){
            logger.error("Country list is empty");
        }else{
            clickElement(driver, getWebElementFromList(getCountriesList(), "UNITED STATES OF AMERICA"));
        }
         return this;
    }

    public Integer setState() {
        clickElement(driver, stateSelector);
        clickElement(driver, getWebElementFromList(getStatesList(), "AZ"));
        return getStatesList().size();
    }


}
