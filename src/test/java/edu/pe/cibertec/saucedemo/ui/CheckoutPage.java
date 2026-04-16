package edu.pe.cibertec.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;


public class CheckoutPage {
    public static final Target CART_ICON = Target.the("cart icon")
            .located(By.id("shopping_cart_container"));

    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .located(By.id("checkout"));

    public static final Target FIRST_NAME = Target.the("first name")
            .located(By.id("first-name"));

    public static final Target LAST_NAME = Target.the("last name")
            .located(By.id("last-name"));

    public static final Target POSTAL_CODE = Target.the("postal code")
            .located(By.id("postal-code"));

    public static final Target CONTINUE_BUTTON = Target.the("continue button")
            .located(By.id("continue"));

    public static final Target FINISH_BUTTON = Target.the("finish button")
            .located(By.id("finish"));

    public static final Target ITEM_TOTAL = Target.the("item total")
            .located(By.className("summary_subtotal_label"));

    public static final Target CONFIRMATION_MESSAGE = Target.the("confirmation message")
            .located(By.className("complete-header"));

    public static final Target ERROR_MESSAGE = Target.the("error message")
            .located(By.cssSelector("[data-test='error']"));

    public static final Target CHECKOUT_FORM = Target.the("checkout form")
            .located(By.id("checkout_info_container"));
}
