package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;


public class CheckoutTests extends BaseTest {

    @Test
    public void checkoutFullFlow() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstProductToCart();
        inv.goToCart();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        Assert.assertTrue(stepOne.isCancelVisible(), "Cancel must be visible on step one");
        stepOne.fillForm("Johnny", "Silverhand", "35677");
        stepOne.clickContinue();

        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        Assert.assertTrue(stepTwo.paymentInfoVisible(), "Payment info must be visible");
        Assert.assertTrue(stepTwo.shippingInfoVisible(), "Shipping info must be visible");
        Assert.assertTrue(stepTwo.cancelVisible(), "Cancel must be visible on step two");
        stepTwo.clickFinish();

        CheckoutCompletePage complete = new CheckoutCompletePage(driver);
        Assert.assertTrue(complete.isOrderComplete(), "Order should be completed");
        Assert.assertTrue(complete.isBackHomeVisible(), "Back Home button should be visible");
        complete.clickBackHome();

        // back to inventory
        InventoryPage inv2 = new InventoryPage(driver);
        Assert.assertTrue(inv2.getProductNames().size() > 0);
    }
}