package testDataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "lockedUser")
    public static Object[][] lockedUser() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce"},
        };
    }

    @DataProvider(name = "allUsers")
    public static Object[][] allUsers() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "testUserNameReq")
    public static Object[][] testUserNameReq() {
        return new Object[][]{
                {"", "testpassword"},
        };
    }

    @DataProvider(name = "testPasswReq")
    public static Object[][] testPasswReq() {
        return new Object[][]{
                {"testuser", ""},
        };
    }
}