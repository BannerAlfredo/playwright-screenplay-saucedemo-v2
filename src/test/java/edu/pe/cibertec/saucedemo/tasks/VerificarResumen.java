package edu.pe.cibertec.saucedemo.tasks;
import edu.pe.cibertec.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

public class VerificarResumen implements Task{
    private final String total;

    public VerificarResumen(String total) {
        this.total = total;
    }

    public static VerificarResumen conTotal(String total) {
        return instrumented(VerificarResumen.class, total);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.should(
                seeThat(
                        WebElementQuestion.the(CheckoutPage.ITEM_TOTAL),
                        containsText(total)
                )
        );
    }
}
