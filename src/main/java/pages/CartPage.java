package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By itemNames = By.className("inventory_item_name");
    private By checkoutBtn = By.id("checkout");
    private By continueShopping = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNames));
    }

    public List<String> getCartItemNames() {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickCheckout() {
        By checkoutBtn = By.id("checkout");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();

    }


    public boolean isCheckoutVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn)).isDisplayed();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping)).click();
    }
}
