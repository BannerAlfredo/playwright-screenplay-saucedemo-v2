package edu.pe.cibertec.saucedemo.stepdefinitions;
import edu.pe.cibertec.saucedemo.tasks.AgregarAlCarrito;
import edu.pe.cibertec.saucedemo.tasks.EliminarDelCarrito;
import edu.pe.cibertec.saucedemo.ui.CarritoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class CarritoStepDefinitions {
    private Actor actor = Actor.named("Alice");

    @And("she adds the product {string} to the cart")
    public void agregarProducto(String producto) {
        actor.attemptsTo(
                AgregarAlCarrito.llamado(producto)
        );
    }

    @And("she removes the product {string} from the cart")
    public void eliminarProducto(String producto) {
        actor.attemptsTo(
                EliminarDelCarrito.llamado(producto)
        );
    }

    @Then("the cart icon should display {string}")
    public void validarCantidad(String cantidad) {
        actor.should(
                seeThat(WebElementQuestion.the(CarritoPage.CART_BADGE),
                        containsText(cantidad))
        );
    }

    @Then("the cart should contain {string} and {string}")
    public void validarDosProductos(String p1, String p2) {
        actor.should(
                seeThat(WebElementQuestion.the(CarritoPage.PRODUCT_IN_CART(p1)), isVisible()),
                seeThat(WebElementQuestion.the(CarritoPage.PRODUCT_IN_CART(p2)), isVisible())
        );
    }

    @Then("the cart should only contain {string}")
    public void validarUnProducto(String producto) {
        actor.should(
                seeThat(
                        WebElementQuestion.the(CarritoPage.PRODUCT_IN_CART(producto)),
                        isVisible()
                )
        );
    }
}
