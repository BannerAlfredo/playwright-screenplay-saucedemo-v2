package edu.pe.cibertec.saucedemo.tasks;
import edu.pe.cibertec.saucedemo.ui.CarritoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EliminarDelCarrito implements Task {
    private final String producto;

    public EliminarDelCarrito(String producto) {
        this.producto = producto;
    }

    public static EliminarDelCarrito llamado(String producto) {
        return instrumented(EliminarDelCarrito.class, producto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CarritoPage.REMOVE_BUTTON(producto))
        );
    }
}
