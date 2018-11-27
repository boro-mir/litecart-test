package com.litecart.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends AbstractPage<ProductPage> {

    private SelenideElement sizeSelector = $(By.name("options[Size]")),
            addToCartButton = $(By.name("add_cart_product"));

    public ProductPage() {
        this.url = "/";
    }

    public ProductPage(String url) {
        this.url = url;
    }

    public ProductPage selectSize(Integer index) {
        if (sizeSelector.exists()) {
            sizeSelector.selectOption(index);
        }

        return this;
    }

    public HomePage addToCart() {
        addToCartButton.click();

        return new HomePage();
    }

}
