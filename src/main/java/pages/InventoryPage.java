package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By products = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By addButtons = By.cssSelector(".btn_inventory");
    private By cartLink = By.className("shopping_cart_link");
    private By sortSelect = By.className("product_sort_container");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }

    public List<String> getProductNames() {
        List<WebElement> elems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        return elems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectSort(String visibleText) {
        WebElement selectEl = wait.until(ExpectedConditions.visibilityOfElementLocated(sortSelect));
        Select select = new Select(selectEl);
        select.selectByVisibleText(visibleText);
        // small wait for reorder
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
    }

    public void addProductToCart(String productName) {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> buttons = driver.findElements(addButtons);
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(productName)) {
                buttons.get(i).click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addButtons)).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
    }

    public boolean logoutVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
