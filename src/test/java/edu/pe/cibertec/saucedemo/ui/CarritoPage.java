package edu.pe.cibertec.saucedemo.ui;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarritoPage {
    public static final Target CART_BADGE = Target.the("cart badge")
            .located(By.className("shopping_cart_badge"));

    public static final Target CART_ICON = Target.the("cart icon")
            .located(By.id("shopping_cart_container"));

    public static Target ADD_TO_CART_BUTTON(String product) {
        String id = product.toLowerCase()
                .replace(" ", "-");
        return Target.the("add to cart button for " + product)
                .located(By.id("add-to-cart-" + id));
    }

    public static Target REMOVE_BUTTON(String product) {
        String id = product.toLowerCase()
                .replace(" ", "-");
        return Target.the("remove button for " + product)
                .located(By.id("remove-" + id));
    }

    public static Target PRODUCT_IN_CART(String product) {
        return Target.the("product in cart " + product)
                .located(By.xpath("//div[@class='inventory_item_name' and text()='" + product + "']"));
    }
}
