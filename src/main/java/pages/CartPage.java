package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;


    private By firstProductAddButton = By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    private By cartIcon = By.className("shopping_cart_link");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        driver.findElement(firstProductAddButton).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }
}
