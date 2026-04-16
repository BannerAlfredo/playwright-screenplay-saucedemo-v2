package edu.pe.cibertec.saucedemo.stepdefinitions;

import edu.pe.cibertec.saucedemo.questions.TheErrorMessage;
import edu.pe.cibertec.saucedemo.questions.ThePageTitle;
import edu.pe.cibertec.saucedemo.tasks.LoginAs;
import edu.pe.cibertec.saucedemo.tasks.OpenTheLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import com.microsoft.playwright.Page;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginStepDefinitions {

    private long startTime;
    private long endTime;

    @Given("{word} is on the SauceDemo login page")
    public void openLoginPage(String actorName) {
        Actor actor = OnStage.theActorCalled(actorName);
        actor.whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration());
        actor.attemptsTo(OpenTheLoginPage.page());
    }

    @When("she logs in with username {string} and password {string}")
    public void loginWith(String username, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                LoginAs.user(username).withPassword(password)
        );
    }

    @Then("she should be redirect to the inventory page")
    public void shouldBeRedirectedToInventoryPage() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ThePageTitle.displayed(), equalTo("Products"))
        );
    }

    @Then("she should see the page title {string}")
    public void shouldSeeThePageTitle(String pageTitle) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ThePageTitle.displayed(), equalTo(pageTitle))
        );
    }

    @Then("she should see the error message {string}")
    public void shouldSeeTheErrorMessage(String errorMessage) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(TheErrorMessage.displayed(), equalTo(errorMessage))
        );
    }

    @Then("she should remain on the login page")
    public void shouldRemainOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ThePageTitle.displayed(), not(equalTo("Products")))
        );
    }

    @When("she logs in (performance) with username {string} and password {string}")
    public void loginPerformance(String username, String password) {

        startTime = System.currentTimeMillis();

        OnStage.theActorInTheSpotlight().attemptsTo(
                LoginAs.user(username).withPassword(password)
        );

        endTime = System.currentTimeMillis();
    }

    @And("the page load time should be greater than {int} milliseconds")
    public void validarTiempo(int tiempo) {
        long total = endTime - startTime;

        System.out.println("Tiempo de carga: " + total);

        if (total <= tiempo) {
            throw new AssertionError("El tiempo fue menor al esperado");
        }
    }

    @And("she navigates to the cart page")
    public void irCarrito() {
        Page page = OnStage.theActorInTheSpotlight()
                .abilityTo(BrowseTheWebWithPlaywright.class)
                .getCurrentPage();

        page.locator(".shopping_cart_link").click();
    }

    @And("she navigates back to the inventory page")
    public void volverInventario() {
        Page page = OnStage.theActorInTheSpotlight()
                .abilityTo(BrowseTheWebWithPlaywright.class)
                .getCurrentPage();

        page.goBack();
    }

    @Then("she should still be logged in")
    public void validarSesion() {
        Page page = OnStage.theActorInTheSpotlight()
                .abilityTo(BrowseTheWebWithPlaywright.class)
                .getCurrentPage();

        assertThat(page.locator(".title")).isVisible();
        assertThat(page.locator(".title")).containsText("Products");
    }
}