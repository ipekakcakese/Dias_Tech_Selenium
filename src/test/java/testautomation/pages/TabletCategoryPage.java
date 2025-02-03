package testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TabletCategoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By appleBrandLocator = By.xpath("//a[contains(@href, '/apple-tablet-xc-3008012-b8849')]//span[text()='Apple']");
    private final By screenSizeOptionLocator = By.xpath("//a[contains(@href, 'filtreler=ekranboyutu:13€2C2€20in€C3€A7')]//span[text()='13,2 inç']");
    private final By productPricesLocator = By.cssSelector("div[data-test-id='price-current-price']");
    private final By filteredAppleLocator = By.xpath("//div[@data-test-id='filterbox-item-display-name' and text()='Apple']");
    private final By filteredScreenSizeLocator = By.xpath("//div[@data-test-id='filterbox-item-display-name' and text()='13,2 inç']");

    public TabletCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void applyFilters() {
        clickElementAndRefreshPage(appleBrandLocator,filteredAppleLocator);
        clickElementAndRefreshPage(screenSizeOptionLocator,filteredScreenSizeLocator);
    }

    public void selectAndClickHighestPricedProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPricesLocator));

        List<WebElement> priceElements = driver.findElements(productPricesLocator);
        double maxPrice = 0;
        WebElement highestPriceProduct = null;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace(".", "").replace(",", ".").replace(" TL","" );
            double price = Double.parseDouble(priceText);

            if (price > maxPrice) {
                maxPrice = price;
                highestPriceProduct = priceElement;
            }
        }

        if (highestPriceProduct != null) {
            highestPriceProduct.findElement(By.xpath("./..")).click();
        } else {
            System.out.println("No product found!");
        }
    }

    private void clickElementAndRefreshPage(By locator,By filteredLocator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

        driver.navigate().refresh();

        wait.until(ExpectedConditions.elementToBeClickable(filteredLocator));
    }
}