package pages;

import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pages.base.ParentPortalBasePage;


@Getter
public class ParentPortalPage extends ParentPortalBasePage {



    public ParentPortalPage(AndroidDriver driver) {
        super(driver);
    }


}
