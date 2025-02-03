package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class ProductDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[@data-test-id='addToCart' and text()='Sepete ekle']")
    private WebElement addToCartButton;

    @FindBy(xpath = "(//div[@data-test-id='default-price']//span)[1]")
    private WebElement productPrice;

    @FindBy(xpath = "//button[text()='Sepete git']")
    private WebElement goToCartButton;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private String switchToNewTabAndGetPrice() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOf(productPrice));

        return productPrice.getText();
    }

    public Double getProductPrice() {
        String priceText = switchToNewTabAndGetPrice();
        String priceWithoutCurrency = priceText.replace(" TL", "").replace(".", "").replace(",", ".");

        return Double.parseDouble(priceWithoutCurrency);

    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton)).click();
    }
}
