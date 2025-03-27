package tests;

import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.LoginPage;
import testDataProvider.DataProviderClass;

public class LoginTest extends CommonConditions {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test(dataProvider = "testUserNameReq", dataProviderClass = DataProviderClass.class)
    public void testUsernameRequired(String username, String password) {
        logger.info("Starting test: testUsernameRequired for user: {}", username);

        String errorMessageUsernameReq = new LoginPage(driver)
                .openPage()
                .enterUsername(username)
                .enterPassword(password)
                .clearUsername()
                .clearPassword()
                .clickLoginButton()
                .getErrorMessage();

        Assertions.assertThat(errorMessageUsernameReq)
                .isEqualTo("Epic sadface: Username is required");

        logger.info("Test completed for user: {}", username);
    }

    @Test(dataProvider = "testPasswReq", dataProviderClass = DataProviderClass.class)
    public void shouldShowErrorPasswordRequired(String username, String password) {
        logger.info("Starting test: testPasswordRequired for user: {}", username);

        String errorMessagePasswordReq = new LoginPage(driver)
                .openPage()
                .enterUsername(username)
                .enterPassword(password)
                .clearPassword()
                .clickLoginButton()
                .getErrorMessage();

        Assertions.assertThat(errorMessagePasswordReq)
                .isEqualTo("Epic sadface: Password is required");

        logger.info("Test completed for user: {}", username);
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void LoginSuccess(String username, String password) {
        logger.info("Starting test: testLoginSuccess for user: {}", username);

        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickButtonToLogin()
                .getTitleLoggedInUser();


        Assertions.assertThat(dashboardTitle)
                .isEqualTo("Swag Labs");

        logger.info("Test completed for user: {}", username);
    }

    @Test(dataProvider = "lockedUser", dataProviderClass = DataProviderClass.class)
    public void shouldNotLoginLockedUser(String username, String password) {
        logger.info("Starting test: shouldNotLoginLockedUser for user: {}", username);

        String errorMessageLockedUser = new LoginPage(driver)
                .openPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton()
                .getErrorMessage();


        Assertions.assertThat(errorMessageLockedUser)
                .isEqualTo("Epic sadface: Sorry, this user has been locked out.");

        logger.info("Test completed for user: {}", username);
    }

}