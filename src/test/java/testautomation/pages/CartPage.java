package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "basket_payedPrice")
    private WebElement cartPrice;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public Double getCartPrice() {
        wait.until(ExpectedConditions.visibilityOf(cartPrice));
        String cartPriceText = cartPrice.getText();
        String cartPrice= cartPriceText.replace(".", "").replace(",", ".");

        return Double.parseDouble(cartPrice);
    }

    public void verifyPriceMatch(Double expectedPrice) {
        Double actualPrice = getCartPrice();
        if (!actualPrice.equals(expectedPrice)) {
            throw new AssertionError("Price mismatch: Expected " + expectedPrice + " but found " + actualPrice);
        }
    }
}
