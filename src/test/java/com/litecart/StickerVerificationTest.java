package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StickerVerificationTest {

    @Test
    public void testVerifyStickerIsPresent() {
        ElementsCollection allItems = $$(".products .image-wrapper");
        Configuration.browser = "chrome";
        open("http://localhost/litecart/en/");
        for (SelenideElement item : allItems) {
            item.shouldHave(attribute("title"));
        }
    }

}