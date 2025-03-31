package stepDefinitions;

import driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        logger.info("Opening SauceDemo login page...");
        loginPage.openPage();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        logger.info("Entering username: {} and password: {}", username, password);
        loginPage.enterUsername(username).enterPassword(password);
    }

    @And("I clear the username field")
    public void iClearTheUsernameField() {
        logger.info("Clearing username field...");
        loginPage.clearUsername();
    }

    @And("I clear the password field")
    public void iClearThePasswordField() {
        logger.info("Clearing password field...");
        loginPage.clearPassword();
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        logger.info("Clicking login button...");
        mainPage = loginPage.clickButtonToLogin(); // For successful login
    }

    @Then("I should see the dashboard with title {string}")
    public void iShouldSeeTheDashboardWithTitle(String expectedTitle) {
        logger.info("Verifying dashboard title...");
        String actualTitle = mainPage.getTitleLoggedInUser();
        Assertions.assertThat(actualTitle).isEqualTo(expectedTitle);
        logger.info("Dashboard title verified: {}", actualTitle);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedError) {
        logger.info("Verifying error message...");
        String actualError = loginPage.getErrorMessage();
        Assertions.assertThat(actualError).isEqualTo(expectedError);
        logger.info("Error message verified: {}", actualError);
    }
}
