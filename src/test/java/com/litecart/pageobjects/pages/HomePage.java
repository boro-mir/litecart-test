package com.litecart.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends AbstractPage<HomePage> {

    private SelenideElement quantityOfProductsInCart = $("#cart .quantity"),
            cart = $("#cart a.image");

    private ElementsCollection products = $$(".products .image-wrapper");

    public HomePage() {
        this.url = "/";
    }

    public HomePage(String url) {
        this.url = url;
    }

    public HomePage checkNumberOfItemsInCart(int numberOfProducts) {
        quantityOfProductsInCart.shouldHave(text(Integer.toString(numberOfProducts)));

        return this;
    }

    public ProductPage openProductPage(Integer productIndex) {
        products.get(productIndex).click();

        return new ProductPage();
    }

    public CartPage openCart() {
        cart.click();

        return new CartPage();
    }

}
