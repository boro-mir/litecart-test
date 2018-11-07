package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductDetailPageTest {

    @Test
    public void testPDP() {
        Configuration.browser = "chrome";
        open("http://localhost/litecart/en/");
        SelenideElement plpItem = $$("#box-campaigns .link").first();

        String plpItemName = plpItem.find(By.className("name")).getText();
        String plpItemRegularPrice = plpItem.find(By.className("regular-price")).getText();
        String plpItemCampaignPrice = plpItem.find(By.className("campaign-price")).getText();

        plpItem.find(By.className("regular-price"))
                .shouldHave(cssValue("color", "rgba(119, 119, 119, 1)"))
                .shouldHave(cssValue("font-weight", "400"))
                .shouldHave(cssValue("font-size", "14.4px"));
        plpItem.find(By.className("campaign-price"))
                .shouldHave(cssValue("color", "rgba(204, 0, 0, 1)"))
                .shouldHave(cssValue("font-weight", "700"))
                .shouldHave(cssValue("font-size", "18px"));

        plpItem.click();
        $("[name=add_cart_product]").shouldBe(visible);

        $("#box-product .title").shouldHave(exactText(plpItemName));
        $("#box-product s.regular-price")
                .shouldHave(exactText(plpItemRegularPrice))
                .shouldHave(cssValue("color", "rgba(102, 102, 102, 1)"))
                .shouldHave(cssValue("font-weight", "400"))
                .shouldHave(cssValue("font-size", "16px"));
        $("#box-product strong.campaign-price")
                .shouldHave(exactText(plpItemCampaignPrice))
                .shouldHave(cssValue("color", "rgba(204, 0, 0, 1)"))
                .shouldHave(cssValue("font-weight", "700"))
                .shouldHave(cssValue("font-size", "22px"));
    }

}