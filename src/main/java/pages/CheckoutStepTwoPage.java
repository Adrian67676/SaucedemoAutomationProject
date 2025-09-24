package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepTwoPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By finishBtn = By.id("finish");
    private By cancelBtn = By.id("cancel");
    private By paymentInfo = By.cssSelector(".summary_info .summary_value_label");
    private By shippingInfo = By.cssSelector(".summary_info .summary_value_label");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn));
    }

    public boolean paymentInfoVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfo)).isDisplayed();
    }

    public boolean shippingInfoVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingInfo)).isDisplayed();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean cancelVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).isDisplayed();
    }
}
