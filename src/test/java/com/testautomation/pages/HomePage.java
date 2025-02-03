package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriverWait wait;
    private final Actions actions;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookie;

    @FindBy(xpath = "//span[text()='Elektronik']")
    private WebElement electronicsCategory;

    @FindBy(xpath = "//a[text()='Bilgisayar/Tablet']")
    private WebElement computerAndTabletCategory;

    @FindBy(xpath = "//a[text()='Tablet']")
    private WebElement tabletCategory;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToTabletCategory() {
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookie));
        cookieButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieButton));

        WebElement electronics = wait.until(ExpectedConditions.visibilityOf(electronicsCategory));
        actions.moveToElement(electronics).perform();

        WebElement computerAndTablet = wait.until(ExpectedConditions.visibilityOf(computerAndTabletCategory));
        actions.moveToElement(computerAndTablet).perform();

        WebElement tablet = wait.until(ExpectedConditions.elementToBeClickable(tabletCategory));
        tablet.click();
    }

}
