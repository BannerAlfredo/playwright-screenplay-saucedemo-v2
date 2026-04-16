package edu.pe.cibertec.saucedemo.ui;

import net.serenitybdd.screenplay.playwright.Target;

public class ProductsPage {
    public static final Target SORT_DROPDOWN =
            Target.the("sort dropdown").locatedBy(".product_sort_container");

    public static final Target PRODUCT_PRICES =
            Target.the("product prices").locatedBy(".inventory_item_price");

    public static Target productByName(String name) {
        return Target.the("product")
                .locatedBy("//div[text()='"+name+"']");
    }

    public static final Target PRODUCT_NAME =
            Target.the("product name").locatedBy(".inventory_details_name");

    public static final Target PRODUCT_PRICE =
            Target.the("product price").locatedBy(".inventory_details_price");

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("add to cart button").locatedBy("button");
}
