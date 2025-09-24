package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By header = By.className("complete-header");
    private By backHome = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public boolean isOrderComplete() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).isDisplayed();
    }

    public boolean isBackHomeVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backHome)).isDisplayed();
    }

    public void clickBackHome() {
        wait.until(ExpectedConditions.elementToBeClickable(backHome)).click();
    }
}