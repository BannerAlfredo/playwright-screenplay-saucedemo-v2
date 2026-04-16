package edu.pe.cibertec.saucedemo.stepdefinitions;
import edu.pe.cibertec.saucedemo.tasks.CompletarCheckout;
import edu.pe.cibertec.saucedemo.tasks.VerificarResumen;
import edu.pe.cibertec.saucedemo.ui.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import net.serenitybdd.screenplay.actions.Click;

public class CheckoutStepDefinitions {
    @And("she proceeds to checkout with first name {string}, last name {string} and postal code {string}")
    public void completarCheckout(String nombre, String apellido, String codigo) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CompletarCheckout.conDatos(nombre, apellido, codigo)
        );
    }

    @And("she verifies the order summary shows item total {string}")
    public void verificarResumen(String total) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VerificarResumen.conTotal(total)
        );
    }

    @And("she completes the order")
    public void completarOrden() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }

    @Then("she should see the confirmation message {string}")
    public void validarConfirmacion(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(
                        WebElementQuestion.the(CheckoutPage.CONFIRMATION_MESSAGE),
                        containsText(mensaje)
                )
        );
    }

    @Then("she should see the checkout error message {string}")
    public void validarError(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(
                        WebElementQuestion.the(CheckoutPage.ERROR_MESSAGE),
                        containsText(mensaje)
                )
        );
    }

    @Then("the checkout form should remain visible")
    public void validarFormulario() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(
                        WebElementQuestion.the(CheckoutPage.CHECKOUT_FORM),
                        isVisible()
                )
        );
    }
}
