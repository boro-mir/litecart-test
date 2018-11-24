package com.litecart.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private SelenideElement summaryPrice = $(".dataTable.rounded-corners .footer  > td:nth-child(2) > strong"),
            removeItemButton = $(By.name("remove_cart_item")),
            backButton = $("#checkout-cart-wrapper > p:nth-child(2) > a"),
            messageForEmptyCart = $("#content p:nth-child(1)");

    private ElementsCollection productsInCart = $$("#box-checkout-cart > div > ul > li");

    public void removeCartItems() {
        for (SelenideElement product : productsInCart) {
            String orderPrice = summaryPrice.text();

            removeItemButton.click();

            if (backButton.exists()) {
                messageForEmptyCart.shouldHave(text("There are no items in your cart."));
            } else {
                summaryPrice.shouldNotHave(text(orderPrice));
            }
        }
    }
}
