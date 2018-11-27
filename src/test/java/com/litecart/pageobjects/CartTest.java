package com.litecart.pageobjects;

import com.codeborne.selenide.Configuration;
import com.litecart.pageobjects.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {

    private HomePage homePage;

    @BeforeClass
    public void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://localhost/litecart/en";
    }

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage().open();
    }

    @Test
    public void testAddItemsToCartAndEmptyCart() {

        for (int i = 0; i < 3; i++) {
            homePage = homePage
                    .checkNumberOfItemsInCart(i)
                    .openProductPage(i)
                    .selectSize(1)
                    .addToCart()
                    .checkNumberOfItemsInCart(i + 1)
                    .back();
        }

        homePage
                .openCart()
                .removeCartItems();
    }
}
