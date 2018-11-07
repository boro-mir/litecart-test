package com.litecart;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StickerVerificationTest {

    private ElementsCollection allItems = $$(".products .image-wrapper");

    @Test
    public void testVerifyStickerIsPresent() {
        open("http://localhost/litecart/en/");
        for (SelenideElement product : allItems) {
            product.findAll("div.sticker").shouldHaveSize(1);
        }
    }

}