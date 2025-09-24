package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOnePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By cancelBtn = By.id("cancel");
    private By title = By.className("title");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(title, "Checkout: Your Information"));
    }

    public void fillForm(String fn, String ln, String pc) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(postalCode).sendKeys(pc);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isCancelVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).isDisplayed();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }
}