package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CartTest {

    @Test
    public void testAddItemsToCartAndEmptyCart() {

        Configuration.browser = "chrome";
        open("http://localhost/litecart/en/");

        ElementsCollection products = $$(".products .image-wrapper");

        for (int i = 0; i < 3; i++) {

            SelenideElement quantityOfProductsInCart = $("#cart .quantity");

            quantityOfProductsInCart.shouldHave(text(Integer.toString(i)));

            products.get(i).click();

            SelenideElement sizeSelector = $(By.name("options[Size]"));

            if (sizeSelector.exists()) {
                sizeSelector.selectOption(1);
            }

            SelenideElement addToCartButton = $(By.name("add_cart_product"));

            addToCartButton.click();
            quantityOfProductsInCart.shouldHave(text(Integer.toString(i + 1)));
            back();
        }

        SelenideElement cart = $("#cart a.image");

        cart.click();

        ElementsCollection productsInCart = $$("#box-checkout-cart > div > ul > li");

        for (SelenideElement product : productsInCart) {

            SelenideElement summaryPrice = $(".dataTable.rounded-corners .footer  > td:nth-child(2) > strong");
            String orderPrice = summaryPrice.text();
            $(By.name("remove_cart_item")).click();

            if ($("#checkout-cart-wrapper > p:nth-child(2) > a").exists()) {
                $("#content p:nth-child(1)").shouldHave(text("There are no items in your cart."));
            } else {
                summaryPrice.shouldNotHave(text(orderPrice));
            }
        }
    }
}
