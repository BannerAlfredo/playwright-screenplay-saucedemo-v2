package edu.pe.cibertec.saucedemo.tasks;
import edu.pe.cibertec.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompletarCheckout implements Task{
    private final String nombre;
    private final String apellido;
    private final String codigo;

    public CompletarCheckout(String nombre, String apellido, String codigo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
    }

    public static CompletarCheckout conDatos(String nombre, String apellido, String codigo) {
        return instrumented(CompletarCheckout.class, nombre, apellido, codigo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CheckoutPage.CART_ICON),
                Click.on(CheckoutPage.CHECKOUT_BUTTON),

                Enter.theValue(nombre).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(apellido).into(CheckoutPage.LAST_NAME),
                Enter.theValue(codigo).into(CheckoutPage.POSTAL_CODE),

                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );
    }
}
