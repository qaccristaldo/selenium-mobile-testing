package org.stride.k12app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static utils.GetProperty.getProperties;
import static utils.Utils.forceWait;
import static utils.Utils.getUser;

import org.slf4j.Logger;
import pages.DashboardPage;
import pages.LoginPage;
import pages.base.BasePage;
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

public class LoginTest {
    private AndroidDriver driver;
    private BasePage basePage;
    static Logger logger = MyLogger.getLogger();
    User user;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;


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
        driver.quit();
    }

    @Test
    public void loginAsAnLC() {

        user = getUser("lcprod");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        dashboardPage = loginPage
                .clickOnLCSelector()
                .setInputUsername(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        dashboardPage.dismissDoItLater();
        assertTrue(dashboardPage.isOpen(user.getUserData().getName()));
        loginPage = dashboardPage.logout();
        assertTrue(loginPage.isOpen());

    }

    @Test
    public void unsuccessfulLCLoginInvalidData() {

        user = getUser("lcprod-fail");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .clickOnLCSelector()
                .setInputUsername(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        //for some reason, it is necessary to click twice in submit button
        loginPage.clickOnSubmitButton();
        assertTrue(loginPage.incorrectUserOrPassMsgAreDisplayed());

    }

    @Test
    public void unsuccessfulLCLoginEmptyUsername() {

        user = getUser("lcprod-empty-user");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .clickOnLCSelector()
                .setInputUsername(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        //for some reason, it is necessary to click twice in submit button
        loginPage.clickOnSubmitButton();
        assertTrue(loginPage.incorrectUserMsgIsDisplayed());

    }

    @Test
    public void unsuccessfulLCLoginEmptyPassword() {

        user = getUser("lcprod-empty-password");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .clickOnLCSelector()
                .setInputUsername(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        //for some reason, it is necessary to click twice in submit button
        loginPage.clickOnSubmitButton();
        assertTrue(loginPage.incorrectPassMsgIsDisplayed());

    }

    @Test
    public void loginAsAnLG() {

        user = getUser("lgprod");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        dashboardPage = loginPage
                .setInputEmail(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        assertTrue(dashboardPage.isOpen(user.getUserData().getName()));
        loginPage = dashboardPage.logout();
        assertTrue(loginPage.isOpen());

    }

    @Test
    public void unsuccessfulLGLoginInvalidData() {

        user = getUser("lcprod-fail");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .setInputEmail(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        assertTrue(loginPage.incorrectMailOrPassMsgAreDisplayed());

    }

    @Test
    public void unsuccessfulLGLoginEmptyUser() {

        user = getUser("lgprod-empty-user");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .setInputEmail(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        assertTrue(loginPage.incorrectMailIsDisplayed());

    }

    @Test
    public void unsuccessfulLGLoginEmptyPass() {

        user = getUser("lgprod-empty-password");
        assertTrue(basePage.isOpen());
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isOpen());
        loginPage
                .setInputEmail(user.getUserData().getUser())
                .setInputPassword(user.getUserData().getPassword())
                .clickOnSubmitButton();
        assertTrue(loginPage.incorrectPassMsgIsDisplayed());

    }




}
