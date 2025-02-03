package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookie;

    @FindBy(xpath = "//span[text()='Elektronik']")
    private WebElement electronicsCategory;

    @FindBy(xpath = "//a[text()='Bilgisayar/Tablet']")
    private WebElement computerAndTabletCategory;

    @FindBy(xpath = "//a[text()='Tablet']")
    private WebElement tabletCategory;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToTabletCategory() {
        acceptCookie.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOf(electronicsCategory));
        actions.moveToElement(electronicsCategory).perform();
        wait.until(ExpectedConditions.visibilityOf(computerAndTabletCategory));
        actions.moveToElement(computerAndTabletCategory).perform();
        wait.until(ExpectedConditions.elementToBeClickable(tabletCategory)).click();
    }
}
