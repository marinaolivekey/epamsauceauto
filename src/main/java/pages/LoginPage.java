package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    @FindBy(css = ".app_logo")
    private WebElement dashboardTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage openPage() {
        logger.info("Login page is opening ...");
        driver.navigate().to(PAGE_URL);
        logger.info("Login page opened");
        return this;
    }

    public MainPage clickButtonToLogin() {
        logger.info("clicking Button to Login ...");
        loginButton.click();
        logger.info("Login performed");
        return new MainPage(driver);
    }

    public LoginPage enterUsername(String username) {
        logger.info("entering Username...");
        inputPassword.click();
        inputUsername.sendKeys(username);
        logger.info("Username is entered");
        return this;
    }

    public LoginPage enterPassword(String password) {
        logger.info("entering password...");
        inputPassword.click();
        inputPassword.sendKeys(password);
        logger.info("Username is entered");
        return this;
    }

    public LoginPage clearUsername() {
        logger.info("clearing Username...");
        inputPassword.click();
        inputUsername.clear();
        inputUsername.sendKeys("");
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputUsername, ""));
        logger.info("Username id cleared");
        return this;
    }

    public LoginPage clearPassword() {
        logger.info("clearing Password...");
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("");
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputUsername, ""));
        logger.info("Password id cleared");
        return this;
    }

    public LoginPage clickLoginButton() {
        logger.info("Clicking login button ...");
        loginButton.click();
        logger.info("Clicked login button");
        return this;
    }

    public String getErrorMessage() {
        logger.info("Loading error message ...");
        return errorMessage.getText();
    }
}
