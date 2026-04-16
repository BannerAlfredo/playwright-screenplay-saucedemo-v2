package edu.pe.cibertec.saucedemo.stepdefinitions;

import com.microsoft.playwright.Page;
import edu.pe.cibertec.saucedemo.questions.TheProductDetail;
import edu.pe.cibertec.saucedemo.questions.TheProductPrice;
import edu.pe.cibertec.saucedemo.tasks.ClickOnProduct;
import edu.pe.cibertec.saucedemo.tasks.SortProducts;
import edu.pe.cibertec.saucedemo.tasks.VerifyProduct;
import edu.pe.cibertec.saucedemo.ui.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductStepDefinitions {

    @And("she sorts the products by {string}")
    public void sheSortsTheProductsBy(String sortOption) {
        theActorInTheSpotlight().attemptsTo(
                SortProducts.by(sortOption)
        );
    }

    @Then("the first product should have price {string}")
    public void theFirstProductShouldHavePrice(String price) {
        theActorInTheSpotlight().should(
                seeThat(TheProductPrice.ofFirstItem(), equalTo(price))
        );
    }

    @And("the last product should have price {string}")
    public void theLastProductShouldHavePrice(String price) {
        theActorInTheSpotlight().should(
                seeThat(TheProductPrice.ofLasItem(), equalTo(price))
        );
    }

    @And("she clicks on the product {string}")
    public void sheClicksOnTheProduct(String productName) {
        theActorInTheSpotlight().attemptsTo(
                ClickOnProduct.named(productName)
        );
    }

    @Then("she should see the product name {string}")
    public void sheShouldSeeTheProductName(String productName) {
        theActorInTheSpotlight().should(
                seeThat(TheProductDetail.name(), equalTo(productName))
        );
    }

    @And("she should see the product price {string}")
    public void sheShouldSeeTheProductPrice(String productPrice) {
        theActorInTheSpotlight().should(
                seeThat(TheProductDetail.price(), equalTo(productPrice))
        );
    }

    @And("she should see the Add to cart button")
    public void heShouldSeeTheAddToCartButton() {
        theActorInTheSpotlight().should(
                seeThat(TheProductDetail.addToCartButtonIsVisible(), is(true))
        );
    }

    Actor actor = theActorInTheSpotlight();

    @When("she sorts the products by {string}")
    public void sortProducts(String option) {
        actor.attemptsTo(SortProducts.by(option));
    }

    @Then("the first product should have price {string}")
    public void firstPrice(String price) {
        actor.attemptsTo(VerifyProduct.firstPrice(price));
    }

    @Then("the last product should have price {string}")
    public void lastPrice(String price) {
        actor.attemptsTo(VerifyProduct.lastPrice(price));
    }

    @When("she clicks on the product {string}")
    public void clickProduct(String product) {
        Page page = (Page) getDriver();
        page.locator(InventoryPage.itemNameLink(product)).click();
    }
    @Then("she should see the product name {string}")
    public void validateName(String name) {
        Page page = (Page) getDriver();
        String text = page.locator("[data-test='inventory-item-name']").textContent();
        assertTrue(text.contains(name));
    }

    @Then("she should see the product price {string}")
    public void validatePrice(String price) {
        Page page = (Page) getDriver();
        String text = page.locator("[data-test='inventory-item-price']").textContent();
        assertTrue(text.contains(price));
    }

    @Then("she should see the Add to cart button")
    public void validateButton() {
        Page page = (Page) getDriver();
        boolean visible = page.locator("[data-test^='add-to-cart']").isVisible();
        assertTrue(visible);
    }
}
