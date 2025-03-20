package tests;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import util.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
