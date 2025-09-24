package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private CheckoutPage checkoutPage;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "H:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCheckoutFlowChromePath() {

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("title"), "Products"));


        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        addToCart.click();


        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        cartLink.click();


        wait.until(ExpectedConditions.urlContains("/cart"));


        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();


        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("Ion");
        checkoutPage.enterLastName("Popescu");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout_summary_container")));


        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishButton.click();


        String message = checkoutPage.getSuccessMessage();
        assertEquals("Thank you for your order!", message);
    }

    @AfterEach
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}
