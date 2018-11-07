package com.litecart;

import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StickerVerificationTest {

    @Test
    public void testVerifyStickerIsPresent() {
        ElementsCollection allItems = $$(".products .link");
        ElementsCollection allStickers = $$(".products .image-wrapper > .sticker");
        open("http://localhost/litecart/en/");
        allItems.shouldHaveSize(allStickers.size());
    }

}