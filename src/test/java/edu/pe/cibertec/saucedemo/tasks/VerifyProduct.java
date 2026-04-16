package edu.pe.cibertec.saucedemo.tasks;

import com.microsoft.playwright.Page;
import edu.pe.cibertec.saucedemo.ui.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.Target;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyProduct implements Task {

    private String price;
    private boolean first;
    private boolean last;

    public static VerifyProduct firstPrice(String price) {
        VerifyProduct task = new VerifyProduct();
        task.price = price;
        task.first = true;
        return task;
    }

    public static VerifyProduct lastPrice(String price) {
        VerifyProduct task = new VerifyProduct();
        task.price = price;
        task.last = true;
        return task;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Page page = (Page) getDriver();

        if (first) {
            String text = page.locator(InventoryPage.FIRST_ITEM_PRICE).textContent();
            assertTrue(text.contains(price));
        }

        if (last) {
            String text = page.locator(InventoryPage.LAST_ITEM_PRICE).textContent();
            assertTrue(text.contains(price));
        }
    }
}