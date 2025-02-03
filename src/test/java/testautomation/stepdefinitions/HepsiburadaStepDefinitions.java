package testautomation.stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testautomation.pages.CartPage;
import testautomation.pages.HomePage;
import testautomation.pages.ProductDetailPage;
import testautomation.pages.TabletCategoryPage;

import java.time.Duration;

public class HepsiburadaStepDefinitions {
    private WebDriver driver;
    private HomePage homePage;
    private TabletCategoryPage tabletCategoryPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private Double selectedProductPrice;

    @Given("The user navigates to the Hepsiburada homepage")
    public void the_user_navigates_to_hepsiburada_homepage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.hepsiburada.com/");

        homePage = new HomePage(driver);
        tabletCategoryPage = new TabletCategoryPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
    }

    @When("The user goes to the Tablet category")
    public void the_user_navigates_to_the_tablet_category() {
        homePage.navigateToTabletCategory();
    }

    @And("The user filters by Apple brand and 13.2-inch screen size")
    public void the_user_filters_by_apple_brand_and_screen_size() {
        tabletCategoryPage.applyFilters();
    }

    @And("The user selects the highest-priced product")
    public void the_user_selects_the_highest_priced_product() {
        tabletCategoryPage.selectAndClickHighestPricedProduct();
    }

    @And("The user adds the product to the cart")
    public void the_user_adds_the_product_to_the_cart() {
        selectedProductPrice = productDetailPage.getProductPrice();
        productDetailPage.addToCart();
        productDetailPage.goToCart();
    }

    @Then("The user verifies that the product price matches the cart price")
    public void the_user_verifies_that_the_product_price_matches_the_cart_price() {
        cartPage.verifyPriceMatch(selectedProductPrice);
        driver.quit();
    }
}