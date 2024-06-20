package org.stride.k12app;

import extended.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import pages.DashboardPage;
import pages.enrollment.EnrollmentPage;
import pages.LoginPage;
import pages.base.BasePage;
import pages.enrollment.TellAboutYourStudentPage;
import pages.lgcreateaccount.ChooseRolePage;
import pages.lgcreateaccount.CreateLGAccountPage;
import pages.lgcreateaccount.GettingStartedLGPage;
import utils.Constants;
import utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.GetProperty.getProperties;
import static utils.Utils.forceWait;


public class STEnrollmentTest extends MobileActions {
    private AndroidDriver driver;
    private BasePage basePage;
    static Logger logger = MyLogger.getLogger();
    static LoginPage loginPage;
    static DashboardPage dashboardPage;
    static CreateLGAccountPage createLGAccountPage;
    static ChooseRolePage chooseRolePage;
    static GettingStartedLGPage gettingStartedLGPage;
    static EnrollmentPage enrollmentPage;
    static TellAboutYourStudentPage tellAboutYourStudentPage;


    @BeforeEach
    public void setUp() {

        String file = "local.properties";
        URL url;
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(getProperties(file, "automation_name"))
                .setUdid(getProperties(file, "udid"))
                .setAppPackage(getProperties(file, "app_package"))
                .setAppActivity(getProperties(file, "app_activity"))
                //.setNoReset(true)
                .setChromedriverExecutable(getProperties(file, "path_to_chromedriver"));
        try {
            url = new URI(getProperties(file, "appium_server_url")).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        driver = new AndroidDriver(
                url, options
        );
        driver.setSetting("enforceXPath1", true);
        logger.info(driver.toString());
        driver.startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        .withVideoSize("1280x720")
                        .withTimeLimit(Duration.ofSeconds(1800)));
        basePage = new BasePage(driver);

    }

    @AfterEach
    public void tearDown() {
        Timestamp stamp = Timestamp.valueOf(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestamp = formatter.format(stamp.toLocalDateTime())
                .replace(":", "-")
                .replace(".", "-");
        String path = String.format("evidence/video/%s.mp4", timestamp);
        forceWait(3000);
        String video = driver.stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        try {
            FileUtils.writeByteArrayToFile(new File(path), decode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        forceWait(2000);
        driver.quit();
    }

    @Test
    public void createLGAccountEnrollST() {

        loginPage = basePage.navigateToLoginPage();
        chooseRolePage = loginPage.clickOnDontHaveAnAccountYet();
        assertTrue(chooseRolePage.isOpen());
        scrollToElement(driver, Constants.SELECT_GUARDIAN_ACCOUNT);
        gettingStartedLGPage = chooseRolePage.clickOnSelectLGRoleButton();
        assertTrue(gettingStartedLGPage.isOpen());
        createLGAccountPage = gettingStartedLGPage.clickOnCreateAccountButton();
        assertTrue(createLGAccountPage.isOpen());
        switchForceNativeApp(driver);
        enrollmentPage = createLGAccountPage
                .setFirstNameInput()
                .setLastNameInput()
                .setEmailInput()
                .scrollToPassword()
                .setPasswordInput()
                .setSecurityQuestion1()
                .setSecurityQuestion2()
                .scrollToCountry()
                .setCountry()
                .setState()
                .setPhone()
                .setOptToReceiveMsg()
                .clickOnSignUpButton()
        ;

        assertNotNull(enrollmentPage);
        assertTrue(enrollmentPage.isOpen());


        tellAboutYourStudentPage = enrollmentPage
                .selectStudent()
                .clickOnNextButton();

        assertTrue(tellAboutYourStudentPage.isOpen());

        tellAboutYourStudentPage
                .setFirstName()
                .setLastName();

        forceWait(8000);


    }


}
