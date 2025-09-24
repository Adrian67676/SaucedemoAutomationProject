package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CartPage;

import java.util.List;

public class InventoryTests extends BaseTest {

    @Test
    public void addProductAndGoToCart() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstProductToCart();
        inv.goToCart();
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isCheckoutVisible(), "Checkout button should be visible in cart");
    }

    @Test
    public void sortAtoZ() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        InventoryPage inv = new InventoryPage(driver);
        inv.selectSort("Name (A to Z)"); 
        List<String> names = inv.getProductNames();
        Assert.assertTrue(names.size() > 0, "Products should be listed"); 
    }

    @Test
    public void logoutFromMenu() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        InventoryPage inv = new InventoryPage(driver);
        inv.openMenu();
        Assert.assertTrue(inv.logoutVisible(), "Logout link should be visible"); 
        inv.clickLogout();
        // back to login page: url contains saucedemo.com (login)
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
