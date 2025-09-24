package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void successfulLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Should navigate to inventory page"); 
    }

    @Test
    public void failedLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("bad_user", "bad_pass");
        Assert.assertTrue(login.isErrorVisible(), "Error message expected on bad credentials");
    }
}
