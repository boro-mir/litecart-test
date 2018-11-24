package com.litecart.pageobjects;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.litecart.pageobjects.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CartTest {

    private HomePage homePage;

    @BeforeClass
    public void configureSelenide() {
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void setUp() {
        open("http://localhost/litecart/en/");

        homePage = new HomePage();
    }

    @Test
    public void testAddItemsToCartAndEmptyCart() {

        for (int i = 0; i < 3; i++) {
            homePage = homePage
                    .checkNumberOfItemsInCart(i)
                    .openProductPage(i)
                    .selectSize(1)
                    .addToCart()
                    .checkNumberOfItemsInCart(i + 1);

            Selenide.back();
        }

        homePage
                .openCart()
                .removeCartItems();
    }

}
